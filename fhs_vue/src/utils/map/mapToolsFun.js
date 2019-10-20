var geojsonArea = require("@mapbox/geojson-area");
const DIFF = 0.01;
const PRECISION = 0.000001;
// 84 ——> 02
const ll84To02 = function(lng84 = 0, lat84 = 0) {
    let rObj = { lng: 0, lat: 0 };
    lng84 = parseFloat(lng84);
    lat84 = parseFloat(lat84);
    let mgLon = 0;
    let mgLat = 0;
    let a = 6378245.0;
    let ee = 0.00669342162296594323;
    let pi = Math.PI;
    if (outOfChina(lng84, lat84)) {
        mgLat = lat84;
        mgLon = lng84;
    } else {
        let dLon = transformLon(lng84 - 105.0, lat84 - 35.0);
        let dLat = transformLat(lng84 - 105.0, lat84 - 35.0);
        let radLat = (lat84 / 180.0) * pi;
        let magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        let sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / (((a * (1 - ee)) / (magic * sqrtMagic)) * pi);
        dLon = (dLon * 180.0) / ((a / sqrtMagic) * Math.cos(radLat) * pi);
        mgLat = lat84 + dLat;
        mgLon = lng84 + dLon;
    }
    rObj.lng = mgLon;
    rObj.lat = mgLat;
    return rObj;
};
const outOfChina = function(lng, lat) {
    if (lng < 72.004 || lng > 137.8347) {
        return true;
    }
    if (lat < 0.8293 || lat > 55.8271) {
        return true;
    }
    return false;
};
const transformLat = function(x, y) {
    let pi = 3.14159265358979324;
    let ret =
        -100.0 +
        2.0 * x +
        3.0 * y +
        0.2 * y * y +
        0.1 * x * y +
        0.2 * Math.sqrt(Math.abs(x));
    ret +=
        ((20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0) /
        3.0;
    ret +=
        ((20.0 * Math.sin(y * pi) + 40.0 * Math.sin((y / 3.0) * pi)) * 2.0) / 3.0;
    ret +=
        ((160.0 * Math.sin((y / 12.0) * pi) + 320 * Math.sin((y * pi) / 30.0)) *
            2.0) /
        3.0;
    return ret;
};
const transformLon = function(x, y) {
    let pi = 3.14159265358979324;
    let ret =
        300.0 +
        x +
        2.0 * y +
        0.1 * x * x +
        0.1 * x * y +
        0.1 * Math.sqrt(Math.abs(x));
    ret +=
        ((20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0) /
        3.0;
    ret +=
        ((20.0 * Math.sin(x * pi) + 40.0 * Math.sin((x / 3.0) * pi)) * 2.0) / 3.0;
    ret +=
        ((150.0 * Math.sin((x / 12.0) * pi) + 300.0 * Math.sin((x / 30.0) * pi)) *
            2.0) /
        3.0;
    return ret;
};
const getCenterInfo = function(llObjArr) {
    let aLng = [];
    let aLat = [];
    llObjArr.forEach(element => {
        aLng.push(element.lng);
        aLat.push(element.lat);
    });
    aLng.sort(function(a, b) {
        return a - b;
    }); //从小到大排序
    aLat.sort(function(a, b) {
        return a - b;
    });
    let lat_center = aLat[0] / 2 + aLat[aLat.length - 1] / 2;
    let lng_center = aLng[0] / 2 + aLng[aLng.length - 1] / 2;
    let lat_min = aLat[0];
    let lng_min = aLng[0];
    let lat_max = aLat[aLat.length - 1];
    let lng_max = aLng[aLng.length - 1];
    return { lat_center, lng_center, lat_min, lng_min, lat_max, lng_max };
};
const getNewLLByOld = function(opts) {
    let lng_diff = opts.lng_new - opts.lng_old;
    let lat_diff = opts.lat_new - opts.lat_old;
    let llObjArr_new = [];
    opts.llObjArr_old.forEach(llObj => {
        llObjArr_new.push({
            lng: llObj.lng * 1 + lng_diff,
            lat: llObj.lat * 1 + lat_diff
        });
    });
    return llObjArr_new;
};
// 02 ——> 84
const ll02To84 = function(lng02 = 0, lat02 = 0) {
    let lat84 = 0;
    let lng84 = 0;
    let rObj = { lng: 0, lat: 0 };
    if (outOfChina(lng02, lat02)) {
        rObj.lng = lng02;
        rObj.lat = lat02;
    } else {
        lat84 = lat02;
        lng84 = lng02;
        rObj = compare(lng02, lat02, lng84, lat84, DIFF);
    }
    return rObj;
};
const compare = function(lng02, lat02, lng84, lat84, diff) {
    let rObj = { lng: lng84, lat: lat84 };
    //构造矩形
    let lat84_min = lat84 - diff;
    let lng84_min = lng84 - diff;
    let lat84_max = lat84 + diff;
    let lng84_max = lng84 + diff;

    let lat02_min, lng02_min, lat02_max, lng02_max, lat02_mid, lng02_mid;

    //计算矩形中心的02坐标
    let llObj02_mid = ll84To02(lng84, lat84);
    lat02_mid = llObj02_mid.lat;
    lng02_mid = llObj02_mid.lng;

    //中心的02坐标与传入02坐标在规定范围内，直接返回
    if (
        Math.abs(lat02_mid - lat02) < PRECISION &&
        Math.abs(lng02_mid - lng02) < PRECISION
    ) {
        return rObj;
    }

    //计算最小最大点的02坐标
    let llObj02_min = ll84To02(lng84_min, lat84_min);
    lat02_min = llObj02_min.lat;
    lng02_min = llObj02_min.lng;

    let llObj02_max = ll84To02(lng84_max, lat84_max);
    lat02_max = llObj02_max.lat;
    lng02_max = llObj02_max.lng;

    //判断最大最小点的02坐标是否覆盖传入02坐标
    //超出范围
    if (
        lat02_min > lat02 ||
        lng02_min > lng02 ||
        lat02_max < lat02 ||
        lng02_max < lng02
    ) {
        if (lat02_min > lat02) {
            lat84 = lat84 - diff - lat02_min + lat02;
        } else if (lng02_min > lng02) {
            lng84 = lng84 - diff - lng02_min + lng02;
        } else if (lat02_max < lat02) {
            lat84 = lat84 + diff;
        } else if (lng02_max < lng02) {
            lng84 = lng84 + diff;
        }
        //扩大范围后重新计算，按道理应该不需要扩大范围
        rObj = compare(lng02, lat02, lng84, lat84, diff);
    } else {
        // //计算矩形中心的02坐标
        // llObj02_mid = ll84To02(lng84, lat84);
        // lat02_mid = llObj02_mid.lat;
        // lng02_mid = llObj02_mid.lng;

        //中心的02坐标与传入02坐标在规定范围内，直接返回
        if (
            Math.abs(lat02_mid - lat02) < PRECISION &&
            Math.abs(lng02_mid - lng02) < PRECISION
        ) {
            return rObj;
        } else {
            //将矩形划分为4个小矩形，判断传入02坐标在那个矩形内，并以覆盖矩形继续计算
            if (lat02 >= lat02_mid && lng02 <= lng02_mid) {
                //以小矩形的中心点为传入点，范围变为原来的1/2继续比较
                lat84 = lat84 + diff / 2;
                lng84 = lng84 - diff / 2;
                rObj = compare(lng02, lat02, lng84, lat84, diff / 2);
            } else if (lat02 <= lat02_mid && lng02 <= lng02_mid) {
                lat84 = lat84 - diff / 2;
                lng84 = lng84 - diff / 2;
                rObj = compare(lng02, lat02, lng84, lat84, diff / 2);
            } else if (lat02 <= lat02_mid && lng02 >= lng02_mid) {
                lat84 = lat84 - diff / 2;
                lng84 = lng84 + diff / 2;
                rObj = compare(lng02, lat02, lng84, lat84, diff / 2);
            } else if (lat02 >= lat02_mid && lng02 >= lng02_mid) {
                lat84 = lat84 + diff / 2;
                lng84 = lng84 + diff / 2;
                rObj = compare(lng02, lat02, lng84, lat84, diff / 2);
            }
        }
    }
    return rObj;
};
const getAcreage = function(llArr) {
    let acreage = 0;
    let obj = {
        type: "Polygon",
        coordinates: [llArr]
    };
    acreage = geojsonArea.geometry(obj);
    acreage = (acreage / Math.pow(1000, 2)).toFixed(3);
    return acreage;
};
const addInfoWinOper = function(tableStr, rOpts) {
    let infoStr = "";
    let operStr = "";
    if (rOpts.addMaker) {
        operStr +=
            "<a href='javascript:void(0);'><i class='e6-icon-location_line' title='添加点'></i></a>";
    }
    if (rOpts.gotoTrack) {
        operStr +=
            "<a href='javascript:void(0);'><i class='e6-icon-track_line' title='跳转轨迹'></i></a>";
    }
    if (operStr != "") {
        operStr = "<div class='win-oper'>" + operStr + "</div>";
    }
    infoStr = "<div class='win-info'>" + tableStr + operStr + "</div>";
    return infoStr;
};

export {
    ll84To02,
    ll02To84,
    getCenterInfo,
    getNewLLByOld,
    getAcreage,
    addInfoWinOper
};
