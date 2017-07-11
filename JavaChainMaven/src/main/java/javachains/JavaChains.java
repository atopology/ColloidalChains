/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import graphicss.FrameXY;
import java.util.ArrayList;
import java.util.Random;
import metrics.EuclideanMetric;
import metrics.Metric;
import metrics.TorusMetric;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import testpackage.XYLineChart_AWT;

/**
 *
 * @author John
 */
public class JavaChains {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // bugfixingmethod();
        //  functionvaluetest();
        //  functionPlotTest();
        //   valuePowTest();
        Metric m = new EuclideanMetric();
        distanceTest();
    }

    public static void functionvaluetest(Metric m) {
        System.out.println("Prepare for the tests");
        double s = 2.0;
        Random random = new Random();
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 10.0, 2.0, m);

        for (int i = 0; i < 10; i++) {
            double ra = 2 + random.nextDouble() * 10;
            double result = simulation.computeEnergyR(s, ra);
            System.out.println(ra + " : " + result);
        }

    }

    public static void bugfixingmethod(Metric m) {
        double s = 2.0;
        Random random = new Random();
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 10.0, 2.0, m);
        double result1 = simulation.computeEnergyR(s, 2.0);
        System.out.println(result1);
    }

    public static void functionPlotTest(Metric m) {
        Random r = new Random();
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 9.0, 3.0, m);
        CoreMachine machine = new CoreMachine(r);
        XYSeriesCollection dataset = machine.GenerateData(100000, simulation, 1.0);
        FrameXY chart = new FrameXY("Name of this window", "the function", dataset);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

    }

    public static void valuePowTest() {
        System.out.println(Math.pow(10, -6));
    }

    public static void distanceTest() {
        TorusMetric metric = new TorusMetric(0.0, 0.0, 1.0, 1.0);
        Particle A = new Particle(0.05, 0.1, 0.1);
        Particle B = new Particle(0.05, 0.9, 0.9);
        System.out.println(metric.distance(A, B));
    }

    //Dataset creator will be done here:
}
