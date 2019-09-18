package com.fhs.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.SystemColor;
import java.text.NumberFormat;
import java.util.Vector;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * 生成JFreeChart图表的工厂类<br/>
 * 目的：根据MVC的设计思想，数据与展现分离。调用者只需传入数据，即可生成图表。
 *
 * @author liuyimin
 *
 */
public class ChartFactory {
	/**
	 * 生成柱状图
	 *
	 * @param title
	 *            柱状图的标题
	 * @param categoryAxisLabel
	 *            x轴标题
	 * @param valueAxisLabel
	 *            y轴标题
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createBarChart(String title, String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel,
				dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}

	/**
	 * 生成柱状图和折线组合图
	 *
	 * @param title
	 *            柱状图的标题
	 * @param categoryAxisLabel
	 *            x轴标题
	 * @param valueAxisLabel
	 *            y轴标题
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createBarLineChart(String title, String categoryAxisLabel, String barValueAxisLabel,String lineValueAxisLabel,
			Vector<Serie> barSeries, Vector<Serie> lineSeries, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset barDataSet = ChartUtils.createDefaultCategoryDataset(barSeries, categories);
		// 1：创建数据集合
		DefaultCategoryDataset lineDataSet = ChartUtils.createDefaultCategoryDataset(lineSeries, categories);
		LineAndShapeRenderer lineRender = new LineAndShapeRenderer();
		lineRender.setStroke(new BasicStroke(1.5F));
		BarRenderer barRender = new BarRenderer();
		barRender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRender.setMaximumBarWidth(0.075);// 设置柱子最大宽度
		barRender.setShadowVisible(false);
		// 最短的BAR长度，避免数值太小而显示不出
		barRender.setMinimumBarLength(0.5);
		barRender.setBarPainter(new StandardBarPainter());
		// 设置柱形图上的文字偏离值
		barRender.setItemLabelAnchorOffset(10D);

		CategoryPlot plot = new CategoryPlot();
		plot.setDataset(0, lineDataSet);
		plot.setDataset(1, barDataSet);
		plot.setRenderer(0,lineRender);
		plot.setRenderer(1,  barRender);
		plot.setDomainAxis(new CategoryAxis());
		NumberAxis barNumberAxis = new NumberAxis();
		barNumberAxis.setLabel(lineValueAxisLabel);
		plot.setRangeAxis(barNumberAxis);
		NumberAxis lineNumberAxis = new NumberAxis();
		lineNumberAxis.setLabel(barValueAxisLabel);
	    //设置Y轴刻度
	    plot.setRangeAxis(1, lineNumberAxis);
	    plot.mapDatasetToRangeAxis(0, 1);
	    plot.mapDatasetToRangeAxis(1, 0);
	    JFreeChart chart = new JFreeChart(plot);
	    chart.setTitle(title);
	    chart.setBackgroundPaint(SystemColor.WHITE);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot(),1);// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}

	/**
	 * 生成饼图
	 *
	 * @param title
	 *            饼图的标题
	 * @param categories
	 *            类别
	 * @param datas
	 *            数据
	 * @return
	 */
	public static JFreeChart createPieChart(String title, String[] categories, Object[] datas) {
		// 1：创建数据集合
		DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
		JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart(title, dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setPieRender(chart.getPlot());
		/**
		 * 可以注释测试
		 */
		// plot.setSimpleLabels(true);//简单标签,不绘制线条
		// plot.setLabelGenerator(null);//不显示数字
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		return chart;
	}

	/**
	 * 生成折线图
	 *
	 * @param title
	 *            折线图的标题
	 * @param categoryAxisLabel
	 *            x轴标题
	 * @param valueAxisLabel
	 *            y轴标题
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createLineChart(String title, String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		JFreeChart chart = org.jfree.chart.ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel,
				dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}

	/**
	 * 生成StackedBarChart
	 *
	 * @param title
	 *            StackedBarChart的标题
	 * @param domainAxisLabel
	 * @param rangeAxisLabel
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createStackedBarChart(String title, String domainAxisLabel, String rangeAxisLabel,
			Vector<Serie> series, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		JFreeChart chart = org.jfree.chart.ChartFactory.createStackedBarChart(title, domainAxisLabel, rangeAxisLabel,
				dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setStackBarRender(chart.getCategoryPlot());
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}

}
