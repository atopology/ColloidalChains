/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains.BoxGenerator;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javachains.Particle;
import javachains.State;
import metrics.Metric;

/**
 *
 * @author Serafim
 */
public class EffectiveBoxGenerator implements BoxGenerator {

    @Override
    public State generateNewState(State s, Random random, Metric m, double dx, double dy) {
        try {
            State newbox = s.clone();
            for (Object o : newbox.getItems()) {
                Particle p = (Particle) o;
                //    double xt = p.getXValue();
                //    double yt = p.getYValue();
                double pdx = -dx + random.nextDouble() * 2 * dx;
                double pdy = -dy + random.nextDouble() * 2 * dy;
                m.move(p, pdx, pdy);
                while (canWeFindProblems(p, newbox, m)) {
                    //        xt = p.getXValue();
                    //        yt = p.getYValue();
                    pdx = -dx + random.nextDouble() * 2 * dx;
                    pdy = -dy + random.nextDouble() * 2 * dy;
                    m.move(p, pdx, pdy);
                }
            }    //    System.out.println("Total number of retries: " + retries);
            return newbox;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(EffectiveBoxGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean canWeFindProblems(Particle p, State box, Metric m) {
        for (Object o : box.getItems()) {
            Particle iterating = (Particle) o;
            if (!iterating.equals(p)) {
                if (p.retrieveR() + iterating.retrieveR() >= m.distance(p, iterating)) {
                    //   System.out.println("This particle: " + p);
                    //  System.out.println("Problematic particle: " + iterating);
                    return true;
                }

            } else {
            }

        }
        return false;
    }

}
