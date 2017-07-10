/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import Graphics.FrameXY;
import java.util.ArrayList;
import java.util.Random;
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
        functionPlotTest();
        
    }

    public static void functionvaluetest() {
        System.out.println("Prepare for the tests");
        double s = 2.0;
        Random random = new Random();
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 10.0, 2.0);

        for (int i = 0; i < 10; i++) {
            double ra = 2 + random.nextDouble() * 10;
            double result = simulation.computeEnergyR(s, ra);
            System.out.println(ra + " : " + result);
        }

    }

    public static void bugfixingmethod() {
        double s = 2.0;
        Random random = new Random();
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 10.0, 2.0);
        double result1 = simulation.computeEnergyR(s, 2.0);
        System.out.println(result1);
    }

    public static void functionPlotTest() {
        SimpleSimulation simulation = new SimpleSimulation(10.0, -4.0, 9.0, 3.0);
        CoreMachine machine = new CoreMachine();
        XYSeriesCollection dataset = machine.GenerateData(40, simulation, 1.0);
        FrameXY chart = new FrameXY("Name of this window", "the function", dataset);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(     chart);
        chart.setVisible(true);

    }

    //Dataset creator will be done here:
}
