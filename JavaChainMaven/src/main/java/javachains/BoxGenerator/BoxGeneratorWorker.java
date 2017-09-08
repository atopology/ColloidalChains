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
import metrics.Metric;

/**
 *
 * @author Serafim
 */
public class BoxGeneratorWorker extends Thread {

    private CoreRun run;
    private int workernumber;

    public BoxGeneratorWorker(CoreRun run) {
        this.run = run;
    }

    @Override
    public void run() {

        javachains.State curbox = this.run.returnCurBox();
        javachains.State q = null;
        while ((!this.run.iterate(curbox, q)) && (this.run.returnCurTime() <= this.run.returnTimeStepsMax()) && (this.run.returnBreakPoint() == false)) {

            q = generateNewState(curbox);
            this.run.addTime(1);
            //      q.setPotential(this.simulator.computeSumOfPotentials(q));

            //           System.out.println("Generating new one...");
        }
        if ((q != null) && (this.run.returnBreakPoint() == false)) {
            this.run.setCoffeeBreak(true);
            this.run.setCurBox(q);
        }
    }

    public javachains.State generateNewState(javachains.State s) {
        Random r = this.run.returnRandom();
        Metric m = this.run.returnMetric();
        double dx = this.run.returnDx();
        double dy = this.run.returnDy();

        javachains.State newbox = new javachains.State("key", m);
        for (Object o : s.getItems()) {
            try {
                Particle p = (Particle) o;
                Particle np = p.clond();
                double pdx = -dx + r.nextDouble() * 2 * dx;
                double pdy = -dy + r.nextDouble() * 2 * dy;
                m.move(np, pdx, pdy);
                if (!computeThings(np, newbox, run)) {
                    return null;
                }
                newbox.add(np);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(StandartBoxGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newbox;
    }

    public boolean computeThings(Particle p, javachains.State newbox, CoreRun run) {
        double pot = newbox.returnPotential();
        for (Object o : newbox.getItems()) {
            Particle q = (Particle) o;
            double extra = run.returnSimulator().computeEnergy(p, q);
            if (extra == Double.NEGATIVE_INFINITY) {
                return false;
            }
            pot = pot + extra;

        }
        newbox.setPotential(pot);
        return true;
    }

}
