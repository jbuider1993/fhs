import Vue from "vue";
import VueAMap from "vue-amap";
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
    key: "672a0b0ad3864a331c9e2f2e62e45693",
    plugin: [
        "Autocomplete",
        "PlaceSearch",
        "Scale",
        "OverView",
        "ToolBar",
        "MapType",
        "AMap.MouseTool",
        "AMap.MarkerClusterer",
        "AMap.Geocoder",
        "AMap.RangingTool",
        "AMap.Driving"
    ],
    // 默认高德 sdk 版本为 1.4.4
    v: "1.4.4",
    uiVersion: "1.0.11" // UI版本号
});
