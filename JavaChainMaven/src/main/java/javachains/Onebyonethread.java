/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

/**
 *
 * @author Serafim
 */
public class Onebyonethread extends Thread {

    private double result;
    private CoreRun run;
    private Particle newone;
    private Particle iterating;

    public Onebyonethread(CoreRun run, Particle newone, Particle iterating) {
        this.result = 0;
        this.run = run;
        this.newone = newone;
        this.iterating = iterating;

    }

    @Override
    public void run() {
        if (!this.run.returnLock()) {
            double potpot = this.run.returnSimulator().computeEnergy(newone, iterating);
            if (potpot == Double.NEGATIVE_INFINITY) {
                this.run.addRejections(1);
                this.run.setGloballock(true);
                return;
            }
        }

    }

}
