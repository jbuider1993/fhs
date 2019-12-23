package com.fhs.workflow.service.impl;

import com.fhs.workflow.img.BpmProcessDiagramGenerator;
import com.fhs.workflow.service.BpmImageService;
import com.fhs.workflow.util.ThreadMapUtil;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@Service
public class DefaultInstHistImgService  implements BpmImageService {
    
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    HistoryService historyService;

    @Autowired
    protected RuntimeService runtimeService;

    public DefaultInstHistImgService() {
    }

    @Override
    public InputStream draw(String actDefId, String processInstanceId) throws Exception {

            Map<String, Paint> nodeMap = new HashMap();
            Map<String, Paint> flowMap = new HashMap();
            Map<String, Paint> gateMap = new HashMap();

            // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
            List<HistoricActivityInstance> historicActivityInstances = historyService
                    .createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                    .orderByHistoricActivityInstanceStartTime().asc().list();

            for(HistoricActivityInstance historicActivityInstance : historicActivityInstances){
                nodeMap.put(historicActivityInstance.getActivityId(),new Color(26,179,148));
            }
            if(!this.isFinished(processInstanceId)){
                runtimeService.getActiveActivityIds(processInstanceId).forEach(activitiId->{
                    nodeMap.put(activitiId,Color.RED);
                });
            }


            // 计算活动线
           List<String> highLightedFlows = getHighLightedFlows(
                (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                        .getDeployedProcessDefinition(actDefId),
                historicActivityInstances);
            highLightedFlows.forEach(lineId->{
                flowMap.put(lineId,Color.GRAY);
            });
            ThreadMapUtil.put("DefaultInstHistImgService_nodeMap", nodeMap);
            ThreadMapUtil.put("DefaultInstHistImgService_flowMap", flowMap);
            ThreadMapUtil.put("DefaultInstHistImgService_gateMap", gateMap);
            InputStream imageStream = null;

            try {
                BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
                BpmProcessDiagramGenerator diagramGenerator = new BpmProcessDiagramGenerator();
                imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", nodeMap, flowMap);
            } finally {
                IOUtils.closeQuietly(imageStream);
            }

            return imageStream;
    }

    /**
     * 判断一个流程是否已经完成了
     * @param processInstanceId 实例id
     * @return true 已经就结束  false 未结束
     */
    public boolean isFinished(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processInstanceId).count() > 0;
    }


    /**
     * @author H.J
     * @date 2018/4/9 10:29
     * @title getHighLightedFlows
     * @description: 获取流程应该高亮的线
     * @param processDefinitionEntity 流程定义实例
     * @param historicActivityInstances 流程活动节点实例
     * @return: java.util.List<java.lang.String>
     */
    public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<>();// 用以保存高亮的线flowId
        List<String> highActivitiImpl = new ArrayList<>();

        for(HistoricActivityInstance historicActivityInstance : historicActivityInstances){
            highActivitiImpl.add(historicActivityInstance.getActivityId());
        }

        for(HistoricActivityInstance historicActivityInstance : historicActivityInstances){
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstance.getActivityId());
            List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
            // 对所有的线进行遍历
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                if (highActivitiImpl.contains(pvmActivityImpl.getId())) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }

        return highFlows;
    }

}
