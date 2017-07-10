/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author John
 */
public class JavaChains {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bugfixingmethod();
        //   functionvaluetest();
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

    public static void jfreechartTest() {

    }

}
