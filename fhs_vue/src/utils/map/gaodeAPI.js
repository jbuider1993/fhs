import "@/assets/e6Font/iconfont.css";
import { ll84To02, ll02To84 } from "@/utils/map/mapToolsFun.js";
class GaodeAPI {
    _that = this;
    constructor(map) {
        this.map = map;
        this.geocoder = null;
        this.mouseTool = null; // 鼠标绘制点线面工具
        this.rangingTool = null; //测距
        this.autocomplete = null; // 关键字搜索
        this.traffic = null; // 路况图层
        this.satellite = null; // 卫星图层
        this.roadNet = null; // 路网
        this.driving = null; // 路径规划
    }
    createLngLat(rOpts) {
        let lnglat = null;
        let opts = {
            lng84: 0,
            lat84: 0,
            lng02: 0,
            lat02: 0
        };
        Object.assign(opts, rOpts);
        opts.lng84 = Number(opts.lng84);
        opts.lat84 = Number(opts.lat84);
        opts.lng02 = Number(opts.lng02);
        opts.lat02 = Number(opts.lat02);
        if (opts.lng02 === 0 || opts.lat02 === 0) {
            let llObj02 = { lon: 0, lat: 0 };
            if (opts.lng84 !== 0 && opts.lat84 !== 0) {
                llObj02 = ll84To02(opts.lng84, opts.lat84);
            }
            opts.lng02 = llObj02.lng;
            opts.lat02 = llObj02.lat;
        }
        if (opts.lng02 !== 0 && opts.lat02 !== 0) {
            lnglat = new AMap.LngLat(opts.lng02, opts.lat02);
            // lnglat = [opts.lng02, opts.lat02];
        }
        return lnglat;
    }
    createMarker(rOpts) {
        let opts = {
            position: null,
            width: 28,
            height: 28,
            icon: "/static/e6Images/m_1.png",
            color: "#000",
            label: "",
            winDetails: "",
            offsetType: 0, //0-图标整体中心；1-图标底部中心
            draggable: false,
            angle: 0, //点标记的旋转角度
            extData: {
                clickback: null,
                label: null
            }
        };
        Object.assign(opts, rOpts);
        let marker = null;
        if (opts.position != null) {
            let iOffset = { x: 0, y: 0 };
            if (opts.offsetType === 1) {
                iOffset = { x: (opts.width / 2) * -1, y: opts.height * -1 };
            } else {
                iOffset = { x: (opts.width / 2) * -1, y: (opts.height / 2) * -1 };
            }
            //图标后显示的文字
            let label = "";
            let labelStr =
                opts.label != ""
                    ? opts.label
                    : opts.extData.label != null
                    ? opts.extData.label
                    : "";
            if (opts.label != "" || opts.extData.label != null) {
                label = {
                    offset: new AMap.Pixel(
                        opts.width,
                        Math.round((opts.height - 22) / 2)
                    ),
                    content:
                        opts.label != "" || opts.extData.label != null
                            ? "<span class='e6-marker-label' style='color:" +
                            opts.color +
                            ";'>" +
                            labelStr +
                            "</span>"
                            : "",
                    labelStr: labelStr
                };
            }
            marker = new AMap.Marker({
                position: opts.position,
                offset: new AMap.Pixel(iOffset.x, iOffset.y),
                icon: opts.icon,
                topWhenClick: true,
                draggable: opts.draggable,
                title: opts.label, //鼠标hover的title
                clickable:
                    opts.winDetails != "" || opts.extData.clickback != null
                        ? true
                        : false,
                label: opts.label != "" ? label : "", //真正的图标后的文字
                angle: opts.angle, //点标记的旋转角度
                extData: opts.extData
            });
            marker.myLabel = label;
            marker.width = opts.width;
            marker.height = opts.height;
            marker.color = opts.color;
            let infoWindow = null;
            if (opts.winDetails != "") {
                infoWindow = new AMap.InfoWindow({
                    content: opts.winDetails,
                    offset: new AMap.Pixel(0, (opts.height / 2) * -1)
                });
                // 鼠标点击marker弹出自定义的信息窗体
                AMap.event.addListener(marker, "click", () => {
                    infoWindow.open(this.map, marker.getPosition());
                });
            }
            marker.infoWin = infoWindow;
        }
        return marker;
    }
    createText(rOpts) {
        let opts = {
            label: "",
            position: null,
            anchor: "top-center",
            cursor: "pointer",
            winDetails: "",
            extData: {
                clickback: null
            }
        };
        Object.assign(opts, rOpts);
        let text = null;
        if (opts.position != null) {
            text = new AMap.Text({
                text: opts.label,
                position: opts.position,
                anchor: opts.anchor,
                cursor: opts.cursor,
                topWhenClick: true,
                clickable:
                    opts.winDetails != "" || opts.extData.clickback != null
                        ? true
                        : false,
                extData: opts.extData
            });
            let infoWindow = null;
            if (opts.winDetails != "") {
                infoWindow = new AMap.InfoWindow({
                    content: opts.winDetails
                });
                // 鼠标点击marker弹出自定义的信息窗体
                AMap.event.addListener(text, "click", () => {
                    infoWindow.open(this.map, text.getPosition());
                });
            }
            text.infoWin = infoWindow;
        }
        return text;
    }
    createCircle(rOpts) {
        let oCircle = null;
        let opts = {
            center: null,
            radius: 200,
            strokeColor: "#409eff",
            strokeOpacity: 1,
            strokeWeight: 4,
            strokeStyle: "solid",
            fillColor: "#409eff",
            fillOpacity: 0.1,
            enableClicking: false
        };
        Object.assign(opts, rOpts);
        if (rOpts.center != null) {
            oCircle = new AMap.Circle(opts);
        }
        return oCircle;
    }
    createLine(rOpts) {
        let oPolyline = null;
        let opts = {
            path: [],
            strokeColor: "#409eff",
            strokeOpacity: 1,
            strokeWeight: 4,
            strokeStyle: "solid",
            showDir: false,
            zIndex: 50
        };
        Object.assign(opts, rOpts);
        if (opts.path.length > 0) {
            oPolyline = new AMap.Polyline(opts);
        }
        return oPolyline;
    }
    createPolygon(rOpts) {
        let oPolygon = null;
        let opts = {
            path: [],
            strokeColor: "#409eff",
            strokeOpacity: 1,
            strokeWeight: 4,
            strokeStyle: "solid",
            fillColor: "#409eff",
            fillOpacity: 0.1
        };
        Object.assign(opts, rOpts);
        if (opts.path.length > 0) {
            oPolygon = new AMap.Polygon(opts);
        }
        return oPolygon;
    }
    closeMouseTool() {
        if (this.mouseTool != null) {
            this.mouseTool.close();
            this.mouseTool.off("draw");
            this.mouseTool = null;
        }
    }
    drawOverlay(rOpts) {
        var opts = {};
        this.closeMouseTool();
        this.mouseTool = new AMap.MouseTool(this.map);
        this.mouseTool.on("draw", e => {
            this.mouseTool.close();
            this.map.setDefaultCursor("default");
            if (typeof rOpts.callback != "undefined" && rOpts.callback != null) {
                let extData = rOpts.extData || {};
                rOpts.callback(e.obj, extData);
            }
        });
        if (typeof rOpts.type != "undefined" && rOpts.type != "") {
            this.map.setDefaultCursor("crosshair");
        }
        switch (rOpts.type) {
            case "marker": {
                let mOpts = rOpts.opts || {};
                opts = {
                    offset: new AMap.Pixel((28 / 2) * -1, (28 / 2) * -1),
                    icon: "/e6Images/m_1.png",
                    draggable: false,
                    width: 28,
                    height: 28,
                    offsetType: 0
                };
                Object.assign(opts, mOpts);
                let iOffset = { x: 0, y: 0 };
                if (opts.offsetType === 1) {
                    iOffset = { x: (opts.width / 2) * -1, y: opts.height * -1 };
                } else {
                    iOffset = {
                        x: (opts.width / 2) * -1,
                        y: (opts.height / 2) * -1
                    };
                }
                opts.offset = new AMap.Pixel(iOffset.x, iOffset.y);
                this.mouseTool.marker(opts);
                break;
            }
            case "polyline": {
                let lOpts = rOpts.opts || {};
                opts = {
                    strokeColor: "#409eff",
                    strokeOpacity: 1,
                    strokeWeight: 4,
                    strokeStyle: "solid",
                    showDir: false
                };
                Object.assign(opts, lOpts);
                this.mouseTool.polyline(opts);
                break;
            }
            case "polygon": {
                let gOpts = rOpts.opts || {};
                opts = {
                    strokeColor: "#409eff",
                    strokeOpacity: 1,
                    strokeWeight: 4,
                    strokeStyle: "solid",
                    fillColor: "#409eff",
                    fillOpacity: 0.1
                };
                Object.assign(opts, gOpts);
                this.mouseTool.polygon(opts);
                break;
            }
            case "rectangle": {
                let eOpts = rOpts.opts || {};
                opts = {
                    strokeColor: "#409eff",
                    strokeOpacity: 1,
                    strokeWeight: 4,
                    strokeStyle: "solid",
                    fillColor: "#409eff",
                    fillOpacity: 0.1
                };
                Object.assign(opts, eOpts);
                this.mouseTool.rectangle(opts);
                break;
            }
            case "circle": {
                let cOpts = rOpts.opts || {};
                opts = {
                    radius: 200,
                    strokeColor: "#409eff",
                    strokeOpacity: 1,
                    strokeWeight: 4,
                    strokeStyle: "solid",
                    fillColor: "#409eff",
                    fillOpacity: 0.1,
                    enableClicking: false
                };
                Object.assign(opts, cOpts);
                this.mouseTool.circle(opts);
                break;
            }
            default:
                break;
        }
    }
    createClusterer(markers, rOpts) {
        let cluster = null;
        var opts = {
            gridSize: 60,
            minClusterSize: 2,
            maxZoom: 18,
            averageCenter: false,
            zoomOnClick: true,
            styles: [
                {
                    url: "e6Images/bg_cluster.png",
                    size: new AMap.Size(30, 38),
                    offset: new AMap.Pixel(-15, -38),
                    textColor: "#fff",
                    textSize: 12
                }
            ]
        };
        Object.assign(opts, rOpts);
        cluster = new AMap.MarkerClusterer(this.map, markers, opts);
        return cluster;
    }
    addOverlay(overlays) {
        var addedOverlays = [];
        if (Array.isArray(overlays)) {
            addedOverlays = overlays.filter(function(overlay) {
                return typeof overlay != "undefined" && overlay != null;
            });
        } else {
            if (typeof overlays != "undefined" && overlays != null) {
                addedOverlays.push(overlays);
            }
        }
        this.map.add(addedOverlays);
    }
    removeOverlay(overlays) {
        var delOverlays = [];
        if (Array.isArray(overlays)) {
            delOverlays = overlays.filter(function(overlay) {
                return typeof overlay != "undefined" && overlay != null;
            });
        } else {
            if (typeof overlays != "undefined" && overlays != null) {
                delOverlays.push(overlays);
            }
        }
        if (delOverlays.length > 0) {
            this.map.remove(delOverlays);
        }
    }
    setCenter(lnglat) {
        this.map.setCenter(lnglat);
    }
    setZoom(level) {
        this.map.setZoom(level);
    }
    setZoomAndCenter(level, lnglat) {
        this.map.setZoomAndCenter(level, lnglat);
    }
    getZoom() {
        return this.map.getZoom();
    }
    setFitView(rOpts) {
        let opts = {
            overlayList: null,
            immediately: true,
            avoid: [10, 10, 10, 10],
            maxZoom: 18
        };
        Object.assign(opts, rOpts);
        if (opts.overlayList.length > 1) {
            this.map.setFitView(
                opts.overlayList,
                opts.immediately,
                opts.avoid,
                opts.maxZoom
            );
        } else if (opts.overlayList.length === 1) {
            //以防后期方法不一样
            switch (opts.overlayList[0].CLASS_NAME) {
                case "AMap.Marker":
                    this.setCenter(opts.overlayList[0].getPosition());
                    this.setZoom(13);
                    break;
                case "AMap.Circle":
                case "AMap.Polygon":
                case "AMap.Rectangle":
                case "AMap.Polyline":
                    this.map.setFitView(
                        opts.overlayList,
                        opts.immediately,
                        opts.avoid,
                        opts.maxZoom
                    );
                    break;
                default:
                    break;
            }

            // try {
            //   this.setCenter(opts.overlayList[0].getPosition());
            //   this.setZoom(13);
            // } catch (ex) {
            //   try {
            //     this.setCenter(opts.overlayList[0].getCenter());
            //     this.setZoom(13);
            //   } catch (ex) {
            //     this.map.setFitView(
            //       opts.overlayList,
            //       opts.immediately,
            //       opts.avoid,
            //       opts.maxZoom
            //     );
            //   }
            // }
        }
    }
    getPosition(overlay) {
        var oLngLat = { lnglat: null, lng: 0, lat: 0 };
        oLngLat.lnglat = overlay.getPosition();
        oLngLat.lng = oLngLat.lnglat.getLng();
        oLngLat.lat = oLngLat.lnglat.getLat();
        return oLngLat;
    }
    getLng(lnglat) {
        return lnglat.getLng();
    }
    getLat(lnglat) {
        return lnglat.getLat();
    }
    setPosition(overlay, lnglat) {
        switch (overlay.CLASS_NAME) {
            case "AMap.Marker":
                overlay.setPosition(lnglat);
                break;
            case "AMap.Circle":
                overlay.setCenter(lnglat);
                break;
            default:
                break;
        }
    }
    getAddress(lnglat = null, callback = null) {
        this.initGeocoder();
        if (lnglat != null) {
            this.geocoder.getAddress(lnglat, (status, result) => {
                let rObj = null;
                if (result.info == "OK") {
                    rObj = result.regeocode;
                }
                if (callback != null) {
                    callback(rObj);
                }
            });
        }
    }
    initGeocoder() {
        if (this.geocoder == null) {
            this.geocoder = new AMap.Geocoder();
        }
    }
    setIcon(overlay, opts) {
        let label = overlay.getLabel();
        if (label) {
            overlay.width = opts.width || 28;
            overlay.height = opts.height || 28;
            this.setLabelStr(overlay, opts.labelStr ? opts.labelStr : label.labelStr);
        }
        overlay.setIcon(opts.icon);
    }
    setLabel(overlay, label) {
        overlay.setLabel(label);
    }
    setLabelStr(overlay, labelStr) {
        let label = {
            offset: new AMap.Pixel(
                overlay.width,
                Math.round((overlay.height - 22) / 2)
            ),
            content:
            "<span class='e6-marker-label' style='color:" +
            overlay.color +
            ";'>" +
            labelStr +
            "</span>",
            labelStr: labelStr
        };
        overlay.setLabel(label);
    }
    setOptions(overlay, opts) {
        //以防后期方法不一样
        switch (overlay.CLASS_NAME) {
            case "AMap.Circle":
                overlay.setOptions(opts);
                break;
            case "AMap.Polygon":
                overlay.setOptions(opts);
                break;
            case "AMap.Rectangle":
                overlay.setOptions(opts);
                break;
            case "AMap.Polyline":
                overlay.setOptions(opts);
                break;
            default:
                break;
        }
    }
    getZoomByLevelTxt(txt) {
        let zoom = 0;
        switch (txt) {
            case "全国":
                zoom = 5;
                break;
            case "省":
                zoom = 7;
                break;
            case "市":
                zoom = 11;
                break;
            case "区":
                zoom = 14;
                break;
            default:
                break;
        }
        return zoom;
    }
    getPath(overlay) {
        let path = [];
        switch (overlay.CLASS_NAME) {
            case "AMap.Polygon":
                path = overlay.getPath();
                break;
            case "AMap.Rectangle":
                path = overlay.getPath();
                break;
            default:
                break;
        }
        return path;
    }
    getMaxMin(overlay) {
        let bounds = overlay.getBounds();
        let sw02 = bounds.getSouthWest();
        let ne02 = bounds.getNorthEast();

        let sw84 = ll02To84(sw02.getLng(), sw02.getLat());
        let ne84 = ll02To84(ne02.getLng(), ne02.getLat());
        return { sw: sw84, ne: ne84 };
    }
    addListener_drag(overlay, callback) {
        AMap.event.addListener(overlay, "dragend", () => {
            let position = overlay.getPosition();
            callback({ lng: position.getLng(), lat: position.getLat() });
        });
    }
    addListener_zoom(callback) {
        AMap.event.addListener(this.map, "zoomend", () => {
            callback();
        });
    }
    addListener_click(overlay, callback) {
        ////以下两种方式都可
        // AMap.event.addListener(overlay, "click", () => {
        //   callback();
        // });
        // overlay.on("click", eResult => {
        //   callback(eResult);
        // });
        AMap.event.addListener(overlay, "click", eResult => {
            callback(eResult);
        });
    }
    addListener_move(callback) {
        AMap.event.addListener(this.map, "moveend", () => {
            callback();
        });
    }
    getExtData(overlay) {
        let extData = null;
        switch (overlay.CLASS_NAME) {
            case "AMap.Marker":
                extData = overlay.getExtData();
                break;
            default:
                break;
        }
        return extData;
    }
    setExtData(overlay, extData) {
        switch (overlay.CLASS_NAME) {
            case "AMap.Marker":
                overlay.setExtData(extData);
                break;
            default:
                break;
        }
    }
    getBounds() {
        let bounds = this.map.getBounds();
        let southWest = bounds.getSouthWest();
        let northEast = bounds.getNorthEast();
        return {
            lng_min: southWest.getLng(),
            lat_min: southWest.getLat(),
            lng_max: northEast.getLng(),
            lat_max: northEast.getLat()
        };
    }
    isPointInRing(lnglat, aLnglat) {
        return AMap.GeometryUtil.isPointInRing(lnglat, aLnglat);
    }
    isPointInBounds(lnglat) {
        let bounds = this.getBounds();
        let aLnglat = [
            [bounds.lng_min, bounds.lat_max],
            [bounds.lng_max, bounds.lat_max],
            [bounds.lng_max, bounds.lat_min],
            [bounds.lng_min, bounds.lat_min]
        ];
        let isIn = this.isPointInRing(lnglat, aLnglat);
        return isIn;
    }
    closeInfoWindow(overlay) {
        if (overlay && overlay.infoWin != null) {
            overlay.infoWin.close();
        } else {
            this.map.clearInfoWindow();
        }
    }
    openInfoWindow(overlay) {
        if (overlay.infoWin != null) {
            overlay.infoWin.open(this.map, overlay.getPosition());
        }
    }
    setzIndex(opts) {
        if (typeof opts.marker != "undefined" && opts.marker != null) {
            opts.marker.setzIndex(opts.zIndex);
        }
    }
    setMapStyle(type) {
        let style = "";
        switch (type) {
            case 0:
                style = "amap://styles/normal";
                break;
            case 1:
                style = "amap://styles/a9f1e915ab3714ffd6d2c8b01118e1b3";
                break;
        }
        if (style != "") {
            this.map.setMapStyle(style);
        }
    }
    openRangingTool() {
        if (this.rangingTool == null) {
            this.rangingTool = new AMap.RangingTool(this.map);
            AMap.event.addListener(this.rangingTool, "end", opts => {
                this.map.setDefaultCursor("default");
                this.rangingTool.turnOff();
            });
        }
        this.map.setDefaultCursor("crosshair");
        this.rangingTool.turnOn();
    }
    keywordsSearch(sTxt) {
        return new Promise((resolve, reject) => {
            if (this.autocomplete == null) {
                this.autocomplete = new AMap.Autocomplete({
                    city: "全国",
                    datatype: "all"
                });
            }
            this.autocomplete.search(sTxt, (status, result) => {
                if (status == "complete" && result.tips) {
                    let comResult = [];
                    result.tips.forEach(item => {
                        let lnglat = item.location;
                        let winInfo = `<div class='win-table'>
                            <table>
                              <tr><td><h2>名称：</h2></td><td>${
                            item.name
                            }</td></tr>
                              <tr><td><h2>地址：</h2></td><td>${item.district}${
                            item.address
                            }</td></tr>
                            </table>
                           </div>`;
                        comResult.push({
                            id: item.id,
                            name: item.name,
                            lng84: lnglat.getLng(),
                            lat84: lnglat.getLat(),
                            address: item.district + item.address,
                            winInfo
                        });
                    });
                    resolve(comResult);
                } else {
                    result();
                }
            });
        });
    }
    showTraffic() {
        if (!this.traffic) {
            this.traffic = new AMap.TileLayer.Traffic({
                map: this.map,
                opacity: 1,
                autoRefresh: true
                // ,interval: 180 // 刷新间隔，默认180s刷新一次
            });
        }
        this.traffic.show();
    }
    refreshTraffic() {
        if (this.traffic) {
            this.traffic.reload();
        }
    }
    hideTraffic() {
        if (this.traffic) {
            this.traffic.hide();
        }
    }
    changeMapType(type) {
        switch (type) {
            case "tileLayer":
                if (this.satellite) {
                    this.satellite.hide();
                }
                if (this.roadNet) {
                    this.roadNet.hide();
                }
                break;
            case "satellite":
                if (!this.satellite) {
                    this.satellite = new AMap.TileLayer.Satellite({
                        map: this.map
                    });
                }
                if (!this.roadNet) {
                    this.roadNet = new AMap.TileLayer.RoadNet({
                        map: this.map
                    });
                }
                this.satellite.show();
                this.roadNet.show();
                break;
            default:
                break;
        }
    }
    drivingSearch(params) {
        if (!this.driving) {
            let dOpts = {
                extensions: "all",
                map: this.map,
                hideMarkers: true,
                showTraffic: true,
                isOutline: false,
                autoFitView: true
            };
            Object.assign(dOpts, params.opts);
            this.driving = new AMap.Driving(dOpts);
        }
        this.driving.search(
            params.start,
            params.end,
            { waypoints: params.waypoints },
            (status, result)=>{
                // 写成匿名函数给callback传参，为了后期多种地图统一扩展
                params.callback(status, result);
            }
        );
    }
    clearDriving(){
        if(this.driving){
            this.driving.clear();
        }
    }
}
export { GaodeAPI };

// export {
//   createMarker,
//   createLngLat,
//   addWinBottom,
//   createCircle,
//   createLine,
//   createPolygon,
//   drawOverlay,
//   createClusterer,
//   addOverlay,
//   setCenter,
//   setZoom,
//   setViewport
// };
