/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Renderer;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

public class SPlotfinal extends ApplicationFrame {
    
    public SPlotfinal(String s) {
        super(s);
        JPanel jpanel = createDemoPanel();
    //    jpanel.setLayout(null);
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }
    
    public static JPanel createDemoPanel() {
        
        JFreeChart jfreechart = ChartFactory.createScatterPlot("Scatter Plot Demo",
                "X", "Y", samplexydataset3(), PlotOrientation.VERTICAL, true, true, false);
        //      Shape cross = ShapeUtilities.createDiagonalCross(3, 1);

        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesShape(0, new Ellipse2D.Double(0.0, 0.0, 10.0, 10.0));

//        xyPlot.setRenderer(new XYLineAndShapeRenderer(false, true){
//
//        @Override
//        public Shape getItemShape(int row, int col) {
//            if (row == 0 & col == 1) {
//                return ShapeUtilities.createDiagonalCross(5, 2);
//            } else {
//                return super.getItemShape(row, col);
//            }
//        }
//    });
        //     XYItemRenderer renderer = xyPlot.getRenderer();
        //  renderer.setBaseShape(cross);
        //     renderer.setBasePaint(Color.red);
        //changing the Renderer to XYDotRenderer
        //xyPlot.setRenderer(new XYDotRenderer());
        //      XYDotRenderer xydotrenderer = new XYDotRenderer();
        //       xyPlot.setRenderer(xydotrenderer);
        //      xydotrenderer.setSeriesShape(0, cross);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        return new ChartPanel(jfreechart);
    }
    
    private static XYDataset samplexydataset2() {
        int cols = 20;
        int rows = 20;
        double[][] values = new double[cols][rows];
        
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries(1);
        Random rand = new Random();
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                double x = Math.round(rand.nextDouble() * 500);
                double y = Math.round(rand.nextDouble() * 500);
                
                series.add(x, y);
            }
        }
        xySeriesCollection.addSeries(series);
        
        return xySeriesCollection;
    }
    
    private static XYDataset samplexydataset3() {
        int cols = 20;
        int rows = 20;
        double[][] values = new double[cols][rows];
        
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries("Random");
        series.add(0.1, 0.1);
        xySeriesCollection.addSeries(series);
        
        return xySeriesCollection;
    }
    
    public static void main(String args[]) {
        SPlotfinal scatterplotdemo4 = new SPlotfinal("Scatter Plot Demo 4");
        scatterplotdemo4.pack();
        RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
        scatterplotdemo4.setVisible(true);
    }
}
