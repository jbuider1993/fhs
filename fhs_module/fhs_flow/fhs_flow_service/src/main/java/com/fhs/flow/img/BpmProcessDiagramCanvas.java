package com.fhs.flow.img;

import com.fhs.flow.util.ThreadMapUtil;
import org.activiti.bpmn.model.AssociationDirection;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Map;

public class BpmProcessDiagramCanvas extends DefaultProcessDiagramCanvas {
    public BpmProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
        LABEL_COLOR = Color.BLACK;
        LABEL_FONT = null;
    }

    public void drawHighLight(int x, int y, int width, int height, Paint paint) {
        Paint originalPaint = this.g.getPaint();
        Stroke originalStroke = this.g.getStroke();
        this.g.setPaint(paint);
        this.g.setStroke(THICK_TASK_BORDER_STROKE);
        RoundRectangle2D rect = new RoundRectangle2D.Double((double) x, (double) y, (double) width, (double) height, 20.0D, 20.0D);
        this.g.draw(rect);
        this.g.setPaint(originalPaint);
        this.g.setStroke(originalStroke);
    }

    public void drawSequenceflow(int[] xPoints, int[] yPoints, boolean drawConditionalIndicator, boolean isDefault, boolean highLighted, Paint paint, double scaleFactor) {
        String connectionType = "sequenceFlow";
        AssociationDirection associationDirection = AssociationDirection.ONE;
        Paint originalPaint = this.g.getPaint();
        Stroke originalStroke = this.g.getStroke();
        this.g.setPaint(CONNECTION_COLOR);
        if (connectionType.equals("association")) {
            this.g.setStroke(ASSOCIATION_STROKE);
        } else if (highLighted) {
            this.g.setPaint(paint);
            this.g.setStroke(HIGHLIGHT_FLOW_STROKE);
        }

        for (int i = 1; i < xPoints.length; ++i) {
            Integer sourceX = xPoints[i - 1];
            Integer sourceY = yPoints[i - 1];
            Integer targetX = xPoints[i];
            Integer targetY = yPoints[i];
            java.awt.geom.Line2D.Double line = new java.awt.geom.Line2D.Double((double) sourceX, (double) sourceY, (double) targetX, (double) targetY);
            this.g.draw(line);
        }

        java.awt.geom.Line2D.Double line;
        if (isDefault) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[0], (double) yPoints[0], (double) xPoints[1], (double) yPoints[1]);
            this.drawDefaultSequenceFlowIndicator(line, scaleFactor);
        }

        if (drawConditionalIndicator) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[0], (double) yPoints[0], (double) xPoints[1], (double) yPoints[1]);
            this.drawConditionalSequenceFlowIndicator(line, scaleFactor);
        }

        if (associationDirection.equals(AssociationDirection.ONE) || associationDirection.equals(AssociationDirection.BOTH)) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[xPoints.length - 2], (double) yPoints[xPoints.length - 2], (double) xPoints[xPoints.length - 1], (double) yPoints[xPoints.length - 1]);
            this.drawArrowHead(line, scaleFactor);
        }

        if (associationDirection.equals(AssociationDirection.BOTH)) {
            line = new java.awt.geom.Line2D.Double((double) xPoints[1], (double) yPoints[1], (double) xPoints[0], (double) yPoints[0]);
            this.drawArrowHead(line, scaleFactor);
        }

        this.g.setPaint(originalPaint);
        this.g.setStroke(originalStroke);
    }

    @Override
    public void drawExclusiveGateway(GraphicInfo graphicInfo, double scaleFactor) {
        Paint paint = this.g.getPaint();
        Map<String, Paint> gateMap = (Map) ThreadMapUtil.get("DefaultInstHistImgService_gateMap");
        FlowNode flowNode = (FlowNode) ThreadMapUtil.get("BpmProcessDiagramGenerator_flowNode");
        this.g.setPaint((Paint) gateMap.getOrDefault(flowNode.getId(), paint));
        super.drawExclusiveGateway(graphicInfo, scaleFactor);
        this.g.setPaint(paint);
    }
}
