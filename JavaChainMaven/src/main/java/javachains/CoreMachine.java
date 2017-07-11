/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.Random;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Serafim
 */
public class CoreMachine {

    private Random random;

    public CoreMachine(Random r) {
        this.random = r;
    }
    public XYSeriesCollection GenerateData(int n, SimpleSimulation simulation, double s) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries data = new XYSeries("the function");

        for (int i = 0; i < n; i++) {
            double ra = s + random.nextDouble() * simulation.returnDeltaR();
            double result = simulation.computeEnergyR(s, ra);
            data.add(ra, result);
        }
        dataset.addSeries(data);
        return dataset;
    }

}
