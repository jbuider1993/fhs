import alarmHandler from "@/components/alarmHandler/src/alarmHandler";
alarmHandler.install = function(Vue) {
  Vue.component(alarmHandler.name, alarmHandler);
};
export default alarmHandler;
