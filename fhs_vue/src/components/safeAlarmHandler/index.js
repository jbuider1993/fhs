import safeAlarmHandler from "@/components/safeAlarmHandler/src/safeAlarmHandler";
safeAlarmHandler.install = function(Vue) {
  Vue.component(safeAlarmHandler.name, safeAlarmHandler);
};
export default safeAlarmHandler;
