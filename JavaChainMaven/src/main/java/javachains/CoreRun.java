/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.Random;
import metrics.Metric;
import org.jfree.data.xy.XYSeriesCollection;

public class CoreRun {

    private double xlength;
    private double ylength;
    private double deltaA;
    private double energyR;
    private double deltaR;
    private double energyA;
    private int N;
    private Random random;
    private Metric m;
    private double r;
    private double dx;
    private double dy;

    private SimpleSimulation simulator;
    private State ourBox;
    private History history;

    public CoreRun(double x, double y, double energyR, double energyA, double deltaR, double deltaA, Random random, Metric m, int N, double r, double dx, double dy) {
        this.simulator = new SimpleSimulation(energyR, energyA, deltaR, deltaA, m);
        this.xlength = x;
        this.ylength = y;
        this.history = new History();
        this.N = N;
        this.r = r;
        this.dx = dx;
        this.dy = dy;

    }

    public void GenerateParticlesInBox(int n, double r, double x, double y, Metric m) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        State data = new State("the data", m);

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
        this.ourBox = data;

    }
    // Attempted moves are done here in "square". 
    // Here we need to be careful, because we want each movement to stay in the box

    public State genNewBox(double dx, double dy) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        State newbox = new State("key", this.m);
        for (Object o : newbox.getItems()) {
            Particle p = (Particle) o;
            double xt = p.getXValue();
            double yt = p.getYValue();
            double pdx = -dx + this.random.nextDouble() * 2 * dx;
            double pdy = -dy + this.random.nextDouble() * 2 * dy;
            Particle np = new Particle(coordinatefix(xt + pdx, this.xlength), coordinatefix(yt + pdy, this.ylength));
            while (!newbox.addParticle(p)) {
                xt = p.getXValue();
                yt = p.getYValue();
                pdx = -dx + this.random.nextDouble() * 2 * dx;
                pdy = -dy + this.random.nextDouble() * 2 * dy;
                np.forceChangeX(coordinatefix(xt + pdx, this.xlength));
                np.setY(coordinatefix(yt + pdy, this.ylength));
            }
        }
        return newbox;
    }

    //
    // Here we assume that -p < x < 2*p
    public double coordinatefix(double x, double p) {
        if (x > p) {
            return x - p;
        } else if (x < 0) {
            return x + p;
        } else {
            return x;
        }

    }

    public boolean iterate(double oldpotential, double newpotential) {
        double probability = this.simulator.computeProbability(newpotential, oldpotential);
        double g = this.random.nextDouble();
        return g <= probability;
    }

    // Does full run n times
    public void run(int n) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        GenerateParticlesInBox(this.N, this.r, this.xlength, this.ylength, this.m);
        this.history.add(this.ourBox);
        this.ourBox.setPotential(this.simulator.computeSumOfPotentials(this.ourBox));
        for (int i = 1; i <= n; i++) {
            State q = genNewBox(this.dx, this.dy);
            q.setPotential(this.simulator.computeSumOfPotentials(q));
            while (!iterate(this.ourBox.returnPotential(), q.returnPotential())) {
                q = genNewBox(this.dx, this.dy);
                q.setPotential(this.simulator.computeSumOfPotentials(q));
            }
            this.history.add(q);
            this.ourBox = q;
        }

    }

    public History returnHistory() {
        return this.history;
    }

}
