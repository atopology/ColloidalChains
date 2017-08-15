/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import UserInterface.BasicUi;
import UserInterface.CompicatedUI;
import graphicss.DotPlot;
import graphicss.FrameXY;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import metrics.EuclideanMetric;
import metrics.Metric;
import metrics.TorusMetric;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import testpackage.XYLineChart_AWT;

public class JavaChains {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, CloneNotSupportedException {
        // bugfixingmethod();
        //  functionvaluetest();
        //  functionPlotTest();
        //   valuePowTest();

        //    distanceTest();
        //    classHierArchyTest();
//        someExperement();
        //   tryingToAccessPrivateField();
        //   TestingAnotherThing();
        //     testingTest();
        //   launchDotPlot();
        //     ActualParametersTest();
        //   interfaceTesting();
        simpleInterfaceTesting();
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

    public static void distanceTest() throws NoSuchFieldException {
        TorusMetric metric = new TorusMetric(0.0, 0.0, 1.0, 1.0);
        Particle A = new Particle(0.05, 0.1, 0.1);
        Particle B = new Particle(0.05, 0.9, 0.9);
        System.out.println(metric.distance(A, B));
    }

    public static void classHierArchyTest() throws NoSuchFieldException {
        XYSeries s = new XYSeries("lol");
        Particle p = new Particle(1, 2, 3);
        s.add(p);
        Particle q = (Particle) s.getDataItem(0);
        System.out.println(q.retrieveR());
    }

    public static void someExperement() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Particle p = new Particle(3, 3, 3);
        p.forceChangeX(4);
    }

    public static void tryingToAccessPrivateField() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        XYDataItem b = new XYDataItem(3, 3);
        Field f = b.getClass().getDeclaredField("x");
        f.setAccessible(true);
        f.set(b, 2.0);
        System.out.println(b.getX());
    }

    public static void TestingAnotherThing() throws NoSuchFieldException {
        Particle p = new Particle(1, 2, 3);
        p.TestInsideClass();
    }

    public static void testingTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Particle p = new Particle(3, 3, 3);
        p.forceChangeX(2);
        System.out.println(p.getX());
    }

    public static void launchDotPlot() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // we define length:
        double x = 100.0;
        double y = 100.0;
        double r = 1.0;
        int n = 1500;
        Metric m = new TorusMetric(0, 0, x, y);
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 6.0, 5.0, m);
        CoreMachine dataGenerator = new CoreMachine(new Random());
        XYSeriesCollection data = dataGenerator.GenerateParticlesInBox(n, r, x, y, m);
        State box = (State) data.getSeries().get(0);
        double energysum = simulation.computeSumOfPotentials(box);
        DotPlot plotplot = new DotPlot("this is title", "Sum of energies: " + energysum, r, data);
        plotplot.pack();
        RefineryUtilities.centerFrameOnScreen(plotplot);
        plotplot.setVisible(true);
    }

    public static void ActualParametersTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        double x = 1.0;
        double y = 1.0;
        double fraction = 0.5;
        double energyR = 3.75;
        double deltaR = 0.5;
        double energyA = -7;
        int N = 1000;
        double r = computeRusingFraction(fraction, x * y, N);
        double deltaA = 0.05 * r;
        Metric m = new TorusMetric(0, 0, x, y);
        SimpleSimulation simulation = new SimpleSimulation(energyR, energyA, deltaR, deltaA, m);
        CoreMachine dataGenerator = new CoreMachine(new Random());
        XYSeriesCollection data = dataGenerator.GenerateParticlesInBox(N, r, x, y, m);
        State box = (State) data.getSeries().get(0);
        double energysum = simulation.computeSumOfPotentials(box);
        DotPlot plotplot = new DotPlot("This is title", "Total potential: " + energysum, r, data);
        plotplot.pack();
        RefineryUtilities.centerFrameOnScreen(plotplot);
        plotplot.setVisible(true);
        System.out.println("program succefully closed");
    }

    public static double computeRusingFraction(double fraction, double area, int N) {
        double result = Math.sqrt((fraction * area) / (Math.PI * N));
        return result;
    }

    public static void interfaceTesting() throws IOException {
        CompicatedUI ui = new CompicatedUI(null);
        ui.run();
    }

    public static void simpleInterfaceTesting() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, CloneNotSupportedException {
        BasicUi ui = new BasicUi();
        ui.run();
    }
    
    public static void TestCircleApproximation()
    {
    
    
    
    }
    
 

    //Dataset creator will be done here:
}
