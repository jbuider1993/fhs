package com.fhs.flow.vo;


import org.activiti.engine.history.HistoricTaskInstance;

/**
 * 历史任务
 *
 * @author wanglei
 */
public class HistoryTaskVO {

    /**
     * 历史任务对象
     */
    private HistoricTaskInstance historicTaskInstance;

    /**
     * 历史任务变量
     */
    private StringBuilder variable = new StringBuilder();

    public HistoricTaskInstance getHistoricTaskInstance() {
        return historicTaskInstance;
    }

    public void setHistoricTaskInstance(HistoricTaskInstance historicTaskInstance) {
        this.historicTaskInstance = historicTaskInstance;
    }

    public StringBuilder getVariable() {
        return variable;
    }

    public void setVariable(StringBuilder variable) {
        this.variable = variable;
    }


}
