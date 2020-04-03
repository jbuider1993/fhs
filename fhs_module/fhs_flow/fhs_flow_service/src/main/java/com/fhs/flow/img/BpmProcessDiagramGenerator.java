package com.fhs.flow.img;

import com.fhs.flow.util.AppUtil;
import com.fhs.flow.util.StringUtil;
import com.fhs.flow.util.ThreadMapUtil;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import java.util.Map;
import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.Artifact;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.CallActivity;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowElementsContainer;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.bpmn.model.Lane;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.Pool;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.SubProcess;

import java.awt.*;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class BpmProcessDiagramGenerator extends DefaultProcessDiagramGenerator {
    private static ProcessEngineConfiguration processEngineConfiguration;

    public BpmProcessDiagramGenerator() {
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, Map<String, Paint> nodeMap, Map<String, Paint> flowMap) {
        this.prepareBpmnModel(bpmnModel);
        BpmProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnModel, imageType, processEngineConfiguration().getActivityFontName(), processEngineConfiguration().getActivityFontName(), processEngineConfiguration().getAnnotationFontName(), processEngineConfiguration().getClassLoader());
        Iterator var6 = bpmnModel.getPools().iterator();

        while(var6.hasNext()) {
            Pool pool = (Pool)var6.next();
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
            processDiagramCanvas.drawPoolOrLane(pool.getName(), graphicInfo);
        }

        var6 = bpmnModel.getProcesses().iterator();

        Process process;
        Iterator var14;
        while(var6.hasNext()) {
            process = (Process)var6.next();
            var14 = process.getLanes().iterator();

            while(var14.hasNext()) {
                Lane lane = (Lane)var14.next();
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(lane.getId());
                processDiagramCanvas.drawPoolOrLane(lane.getName(), graphicInfo);
            }
        }

        var6 = bpmnModel.getProcesses().iterator();

        while(var6.hasNext()) {
            process = (Process)var6.next();
            var14 = process.findFlowElementsOfType(FlowNode.class).iterator();

            while(var14.hasNext()) {
                FlowNode flowNode = (FlowNode)var14.next();
                this.drawActivity(processDiagramCanvas, bpmnModel, flowNode, nodeMap, flowMap, 1.0D);
            }
        }

        var6 = bpmnModel.getProcesses().iterator();

        while(true) {
            List subProcesses;
            do {
                if (!var6.hasNext()) {
                    return processDiagramCanvas.generateImage(imageType);
                }

                process = (Process)var6.next();
                var14 = process.getArtifacts().iterator();

                while(var14.hasNext()) {
                    Artifact artifact = (Artifact)var14.next();
                    this.drawArtifact(processDiagramCanvas, bpmnModel, artifact);
                }

                subProcesses = process.findFlowElementsOfType(SubProcess.class, true);
            } while(subProcesses == null);

            Iterator var18 = subProcesses.iterator();

            while(var18.hasNext()) {
                SubProcess subProcess = (SubProcess)var18.next();
                Iterator var11 = subProcess.getArtifacts().iterator();

                while(var11.hasNext()) {
                    Artifact subProcessArtifact = (Artifact)var11.next();
                    this.drawArtifact(processDiagramCanvas, bpmnModel, subProcessArtifact);
                }
            }
        }
    }

    private void drawActivity(BpmProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode, Map<String, Paint> nodes, Map<String, Paint> flows, double scaleFactor) {
        ThreadMapUtil.put("BpmProcessDiagramGenerator_flowNode", flowNode);
        DefaultProcessDiagramGenerator.ActivityDrawInstruction drawInstruction = (ActivityDrawInstruction)this.activityDrawInstructions.get(flowNode.getClass());
        boolean highLighted;
        if (drawInstruction != null) {
            drawInstruction.draw(processDiagramCanvas, bpmnModel, flowNode);
            boolean multiInstanceSequential = false;
            boolean multiInstanceParallel = false;
            highLighted = false;
            if (flowNode instanceof Activity) {
                Activity activity = (Activity)flowNode;
                MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = activity.getLoopCharacteristics();
                if (multiInstanceLoopCharacteristics != null) {
                    multiInstanceSequential = multiInstanceLoopCharacteristics.isSequential();
                    multiInstanceParallel = !multiInstanceSequential;
                }
            }

            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
            if (!(flowNode instanceof SubProcess)) {
                if (flowNode instanceof CallActivity) {
                    highLighted = true;
                }
            } else {
                highLighted = graphicInfo.getExpanded() != null && !graphicInfo.getExpanded();
            }

            if (scaleFactor == 1.0D) {
                processDiagramCanvas.drawActivityMarkers((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), multiInstanceSequential, multiInstanceParallel, highLighted);
            }

            if (nodes.keySet().contains(flowNode.getId())) {
                this.drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()), (Paint)nodes.get(flowNode.getId()));
            }
        }

        Iterator var25 = flowNode.getOutgoingFlows().iterator();

        while(var25.hasNext()) {
            SequenceFlow sequenceFlow = (SequenceFlow)var25.next();
            highLighted = flows.keySet().contains(sequenceFlow.getId());
            String defaultFlow = null;
            if (flowNode instanceof Activity) {
                defaultFlow = ((Activity)flowNode).getDefaultFlow();
            } else if (flowNode instanceof Gateway) {
                defaultFlow = ((Gateway)flowNode).getDefaultFlow();
            }

            boolean isDefault = false;
            if (defaultFlow != null && defaultFlow.equalsIgnoreCase(sequenceFlow.getId())) {
                isDefault = true;
            }

            boolean drawConditionalIndicator = sequenceFlow.getConditionExpression() != null && !(flowNode instanceof Gateway);
            String sourceRef = sequenceFlow.getSourceRef();
            String targetRef = sequenceFlow.getTargetRef();
            FlowElement sourceElement = bpmnModel.getFlowElement(sourceRef);
            FlowElement targetElement = bpmnModel.getFlowElement(targetRef);
            List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
            if (graphicInfoList != null && graphicInfoList.size() > 0) {
                graphicInfoList = connectionPerfectionizer(processDiagramCanvas, bpmnModel, sourceElement, targetElement, graphicInfoList);
                int[] xPoints = new int[graphicInfoList.size()];
                int[] yPoints = new int[graphicInfoList.size()];

                for(int i = 1; i < graphicInfoList.size(); ++i) {
                    GraphicInfo graphicInfo = (GraphicInfo)graphicInfoList.get(i);
                    GraphicInfo previousGraphicInfo = (GraphicInfo)graphicInfoList.get(i - 1);
                    if (i == 1) {
                        xPoints[0] = (int)previousGraphicInfo.getX();
                        yPoints[0] = (int)previousGraphicInfo.getY();
                    }

                    xPoints[i] = (int)graphicInfo.getX();
                    yPoints[i] = (int)graphicInfo.getY();
                }

                processDiagramCanvas.drawSequenceflow(xPoints, yPoints, drawConditionalIndicator, isDefault, highLighted, (Paint)flows.get(sequenceFlow.getId()), scaleFactor);
                GraphicInfo labelGraphicInfo;
                if (StringUtil.isNotEmpty(sequenceFlow.getName()) && !sequenceFlow.getName().startsWith("连线")) {
                    labelGraphicInfo = new GraphicInfo();
                    labelGraphicInfo.setX((double)((xPoints[0] + xPoints[1]) / 2));
                    labelGraphicInfo.setY((double)((yPoints[0] + yPoints[1]) / 2 - 15));
                    processDiagramCanvas.drawLabel(sequenceFlow.getName(), labelGraphicInfo, false);
                }

                labelGraphicInfo = bpmnModel.getLabelGraphicInfo(sequenceFlow.getId());
                if (labelGraphicInfo != null) {
                    processDiagramCanvas.drawLabel(sequenceFlow.getName(), labelGraphicInfo, false);
                }
            }
        }

        if (flowNode instanceof FlowElementsContainer) {
            var25 = ((FlowElementsContainer)flowNode).getFlowElements().iterator();

            while(var25.hasNext()) {
                FlowElement nestedFlowElement = (FlowElement)var25.next();
                if (nestedFlowElement instanceof FlowNode) {
                    this.drawActivity(processDiagramCanvas, bpmnModel, (FlowNode)nestedFlowElement, nodes, flows, scaleFactor);
                }
            }
        }

        if (flowNode instanceof ExclusiveGateway) {
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
            GraphicInfo info = new GraphicInfo();
            info.setX(graphicInfo.getX() + 40.0D);
            info.setY(graphicInfo.getY() + 40.0D);
            processDiagramCanvas.drawLabel(flowNode.getName(), info, true);
        }

    }

    private void drawHighLight(BpmProcessDiagramCanvas processDiagramCanvas, GraphicInfo graphicInfo, Paint paint) {
        processDiagramCanvas.drawHighLight((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), paint);
    }

    protected static BpmProcessDiagramCanvas initProcessDiagramCanvas(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        double minX = 1.7976931348623157E308D;
        double maxX = 0.0D;
        double minY = 1.7976931348623157E308D;
        double maxY = 0.0D;

        GraphicInfo graphicInfo;
        for(Iterator var14 = bpmnModel.getPools().iterator(); var14.hasNext(); maxY = graphicInfo.getY() + graphicInfo.getHeight()) {
            Pool pool = (Pool)var14.next();
            graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
            minX = graphicInfo.getX();
            maxX = graphicInfo.getX() + graphicInfo.getWidth();
            minY = graphicInfo.getY();
        }

        List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
        Iterator var24 = flowNodes.iterator();

        label155:
        while(var24.hasNext()) {
            FlowNode flowNode = (FlowNode)var24.next();
            GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
            if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > maxX) {
                maxX = flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth();
            }

            if (flowNodeGraphicInfo.getX() < minX) {
                minX = flowNodeGraphicInfo.getX();
            }

            if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > maxY) {
                maxY = flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight();
            }

            if (flowNodeGraphicInfo.getY() < minY) {
                minY = flowNodeGraphicInfo.getY();
            }

            Iterator var18 = flowNode.getOutgoingFlows().iterator();

            while(true) {
                List graphicInfoList;
                do {
                    if (!var18.hasNext()) {
                        continue label155;
                    }

                    SequenceFlow sequenceFlow = (SequenceFlow)var18.next();
                    graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
                } while(graphicInfoList == null);

                Iterator var21 = graphicInfoList.iterator();

                while(var21.hasNext()) {
                    graphicInfo = (GraphicInfo)var21.next();
                    if (graphicInfo.getX() > maxX) {
                        maxX = graphicInfo.getX();
                    }

                    if (graphicInfo.getX() < minX) {
                        minX = graphicInfo.getX();
                    }

                    if (graphicInfo.getY() > maxY) {
                        maxY = graphicInfo.getY();
                    }

                    if (graphicInfo.getY() < minY) {
                        minY = graphicInfo.getY();
                    }
                }
            }
        }

        List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);
        Iterator var27 = artifacts.iterator();

        while(var27.hasNext()) {
            Artifact artifact = (Artifact)var27.next();
            GraphicInfo artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact.getId());
            if (artifactGraphicInfo != null) {
                if (artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth() > maxX) {
                    maxX = artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth();
                }

                if (artifactGraphicInfo.getX() < minX) {
                    minX = artifactGraphicInfo.getX();
                }

                if (artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight() > maxY) {
                    maxY = artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight();
                }

                if (artifactGraphicInfo.getY() < minY) {
                    minY = artifactGraphicInfo.getY();
                }
            }

            List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(artifact.getId());
            if (graphicInfoList != null) {
                Iterator var35 = graphicInfoList.iterator();

                while(var35.hasNext()) {
                    graphicInfo = (GraphicInfo)var35.next();
                    if (graphicInfo.getX() > maxX) {
                        maxX = graphicInfo.getX();
                    }

                    if (graphicInfo.getX() < minX) {
                        minX = graphicInfo.getX();
                    }

                    if (graphicInfo.getY() > maxY) {
                        maxY = graphicInfo.getY();
                    }

                    if (graphicInfo.getY() < minY) {
                        minY = graphicInfo.getY();
                    }
                }
            }
        }

        int nrOfLanes = 0;
        Iterator var30 = bpmnModel.getProcesses().iterator();

        while(var30.hasNext()) {
            Process process = (Process) var30.next();
            Iterator var34 = process.getLanes().iterator();

            while(var34.hasNext()) {
                Lane l = (Lane)var34.next();
                ++nrOfLanes;
                graphicInfo = bpmnModel.getGraphicInfo(l.getId());
                if (graphicInfo.getX() + graphicInfo.getWidth() > maxX) {
                    maxX = graphicInfo.getX() + graphicInfo.getWidth();
                }

                if (graphicInfo.getX() < minX) {
                    minX = graphicInfo.getX();
                }

                if (graphicInfo.getY() + graphicInfo.getHeight() > maxY) {
                    maxY = graphicInfo.getY() + graphicInfo.getHeight();
                }

                if (graphicInfo.getY() < minY) {
                    minY = graphicInfo.getY();
                }
            }
        }

        if (flowNodes.isEmpty() && bpmnModel.getPools().isEmpty() && nrOfLanes == 0) {
            minX = 0.0D;
            minY = 0.0D;
        }

        return new BpmProcessDiagramCanvas((int)maxX + 10, (int)maxY + 10, (int)minX, (int)minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
    }

    private static ProcessEngineConfiguration processEngineConfiguration() {
        if (processEngineConfiguration == null) {
            processEngineConfiguration = (ProcessEngineConfiguration) AppUtil.getBean(ProcessEngineConfiguration.class);
        }

        return processEngineConfiguration;
    }
}
