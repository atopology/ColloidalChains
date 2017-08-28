/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stats;

import javachains.Particle;
import javachains.State;
import metrics.Metric;

/**
 *
 * @author Serafim
 */
public class StateStats {

    Statistics sa;
    double cap;
    Metric m;

    public StateStats(double r, Metric m) {
        this.sa = new Statistics();
        this.cap = r;
        this.m = m;
    }

    public void Run(State s) {
        for (Object o1 : s.getItems()) {
            int counter = 0;
            for (Object o2 : s.getItems()) {
                if (!o1.equals(o2)) {
                    Particle p1 = (Particle) o1;
                    Particle p2 = (Particle) o2;
                    double distance = this.m.distance(p1, p2);
                    if (distance < this.cap) {
                        counter++;
                    }

                }
            }
            this.sa.add(counter);

        }
    }
    
    public Statistics returnStatistics()
    {
    return this.sa;
    }

}
