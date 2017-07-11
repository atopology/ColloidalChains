/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicss;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Serafim
 */
public class FrameXY extends ApplicationFrame {

    public FrameXY(String title, String chartTitle, XYSeriesCollection dataset) {
        super(title);
        JFreeChart xylineChart
                = ChartFactory.createXYLineChart(
                        chartTitle,
                        "Distance",
                        "Energy",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, true, false);

        ChartPanel chartPanel = new ChartPanel(
                xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        XYPlot plot
                = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);

    }

}
