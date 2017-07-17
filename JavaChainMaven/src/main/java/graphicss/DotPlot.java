/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicss;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
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
public class DotPlot extends ApplicationFrame {

    private double fixedR;

    public DotPlot(String title, double radius, XYDataset dataset) {
        super(title);
        this.fixedR = radius;
        JPanel panel = creatPanel(dataset);
        panel.setPreferredSize(new Dimension(560, 367));
        setContentPane(panel);
    }

    public JPanel creatPanel(XYDataset dataset) {

        JFreeChart jfreechart = ChartFactory.createScatterPlot("Particles in box",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        //      Shape cross = ShapeUtilities.createDiagonalCross(3, 1);

        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesShape(0, new Ellipse2D.Double(0.0, 0.0, this.fixedR, this.fixedR));
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

}
