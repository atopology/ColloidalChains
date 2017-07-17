/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.LinkedList;
import java.util.Random;
import metrics.Metric;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
    
    public XYSeriesCollection GenerateParticlesInBox(int n, double r, double x, double y, Metric m) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        Box data = new Box("the data",m);
        
        for (int i = 0; i < n; i++) {
            double tx = this.random.nextDouble() * x;
            double ty = this.random.nextDouble() * y;
            Particle p = new Particle(tx, ty, r);
            while (!data.addParticle(p)) {
                tx = this.random.nextDouble() * x;
                ty = this.random.nextDouble() * y;
                p.forceChangeX(tx);
                p.setY(ty);
            }
        }
        
        dataset.addSeries(data);
        return dataset;
    }
    
}
