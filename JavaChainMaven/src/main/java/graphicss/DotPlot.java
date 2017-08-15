/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicss;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.ShapeUtilities;

/**
 *
 * @author Serafim
 */
public class DotPlot extends JFrame {

    private double fixedR;
    private double scalingfactor;

    public DotPlot(String title, String subtitle, double radius, XYDataset dataset) {
        super(title);
        this.fixedR = radius;
        JPanel panel = creatPanel(dataset, subtitle);
        panel.setPreferredSize(new Dimension(560, 367));
        setContentPane(panel);
        this.scalingfactor = 1.0;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public JPanel creatPanel(XYDataset dataset, String subtitle) {

        JFreeChart jfreechart = ChartFactory.createScatterPlot(subtitle,
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        //      Shape cross = ShapeUtilities.createDiagonalCross(3, 1);

        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesShape(0, new Ellipse2D.Double(0.0, 0.0, 1.0, 1.0));
    // Remember to uncomment this lateR:  
        //   renderer.setSeriesShape(0, new Ellipse2D.Double(0.0, 0.0, this.fixedR * this.scalingfactor, this.fixedR * scalingfactor));

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

    public void setScaling(double s) {
        this.scalingfactor = s;
    }

}
