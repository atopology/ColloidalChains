/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains.BoxGenerator;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javachains.CoreRun;
import javachains.Particle;
import javachains.State;
import metrics.Metric;

/**
 *
 * @author Serafim
 */
public class StandartBoxGenerator implements BoxGenerator {

    @Override
    public State generateNewState(State s, Random r, Metric m, double dx, double dy, CoreRun run) {
        State newbox = new State("key", m);
        for (Object o : s.getItems()) {
            try {
                Particle p = (Particle) o;
                Particle np = p.clond();
                double pdx = -dx + r.nextDouble() * 2 * dx;
                double pdy = -dy + r.nextDouble() * 2 * dy;
                m.move(np, pdx, pdy);

                newbox.add(np);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(StandartBoxGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newbox;
    }

    public boolean computeThings(Particle p, State newbox) {
        double pot = newbox.returnPotential();
        for (Object o : newbox.getItems()) {
             
           
        }

        return true;
    }

}
