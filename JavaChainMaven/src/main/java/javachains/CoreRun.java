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

    private Random random;
    private double xlength;
    private double ylength;
    private int N;
    private Metric m;
    private double r;
    private double dx;
    private double dy;
    private SimpleSimulation simulator;
    private State ourBox;
    private History history;
    private double scalingfactor;

    public CoreRun(double x, double y, double energyR, double energyA, double deltaR, double deltaA, Random random, Metric m, int N, double r, double dx, double dy) {
        this.simulator = new SimpleSimulation(energyR, energyA, deltaR, deltaA, m);
        this.xlength = x;
        this.ylength = y;
        this.history = new History();
        this.N = N;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.scalingfactor = 1.0;

    }

    public CoreRun() {
        this.simulator = new SimpleSimulation();
        this.history = new History();
        this.random = new Random();
        this.scalingfactor = 1.0;

    }

    public boolean ableToRun() {
        return (this.xlength != 0) && (this.ylength != 0) && (this.r != 0) && (this.dx != 0) && (this.dy != 0) && (simulator.readyToCompute());
    }

    public void setScale(double s) {
        this.scalingfactor = s;
    }

    public double returnScale() {
        return this.scalingfactor;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double returnR() {
        return this.r;
    }

    public void setN(int N) {
        this.N = N;
    }

    public double returnN() {
        return this.N;
    }

    public void setX(double x) {
        this.xlength = x;
    }

    public double returnX() {
        return this.xlength;
    }

    public double returnY() {
        return this.ylength;
    }

    public void setY(double y) {
        this.ylength = y;
    }

    public void setMetric(Metric m) {
        this.m = m;
        if (this.simulator != null) {
            this.simulator.setMetric(m);
        }
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double returnDx() {
        return this.dx;
    }

    public double returnDy() {
        return this.dy;
    }

    public void setDy(double dy) {
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
        System.out.println("Box generating started");
        State newbox = new State("key", this.m);
        int debugcompute = 0;
        for (Object o : this.ourBox.getItems()) {
            System.out.println("--Round + " + debugcompute);
            Particle p = (Particle) o;
            double xt = p.getXValue();
            double yt = p.getYValue();
            double pdx = -dx + this.random.nextDouble() * 2 * dx;
            double pdy = -dy + this.random.nextDouble() * 2 * dy;
            Particle np = new Particle(coordinatefix(xt + pdx, this.xlength), coordinatefix(yt + pdy, this.ylength), p.retrieveR());

            while (!newbox.addParticle(np)) {
                xt = p.getXValue();
                yt = p.getYValue();
                pdx = -dx + this.random.nextDouble() * 2 * dx;
                pdy = -dy + this.random.nextDouble() * 2 * dy;
                np.forceChangeX(coordinatefix(xt + pdx, this.xlength));
                np.setY(coordinatefix(yt + pdy, this.ylength));
                System.out.println("Change X: " + pdx + " Change Y: " + pdy);
            }
            debugcompute++;
        }
        System.out.println("Box generating ended");
        return newbox;
    }
    
    public State genNewBoxAlternative(double dx, double dy)
    {
    return null;
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
        System.out.print("probability: " + probability);
        double g = this.random.nextDouble();
        if (g <= probability) {
            System.out.println(" succeful!");
            return true;
        }
        System.out.println(" failed!");
        return false;
    }

    // Does full run n times
    public void run(int n) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        GenerateParticlesInBox(this.N, this.r, this.xlength, this.ylength, this.m);
        this.history.add(this.ourBox);
        this.ourBox.setPotential(this.simulator.computeSumOfPotentials(this.ourBox));
        for (int i = 1; i <= n; i++) {
            State q = genNewBox(this.dx, this.dy);
            double potpot = this.simulator.computeSumOfPotentials(q);
            q.setPotential(potpot);
            System.out.print("Round " + i + ": ");
            while (!iterate(this.ourBox.returnPotential(), q.returnPotential())) {
                q = genNewBox(this.dx, this.dy);
                q.setPotential(this.simulator.computeSumOfPotentials(q));
                System.out.println("Generating new one...");
            }
            System.out.println("Reached end");
            this.history.add(q);
            this.ourBox = q;
        }

    }

    public History returnHistory() {
        return this.history;
    }

    public SimpleSimulation returnSimulator() {
        return this.simulator;
    }

    public void reset() {
        this.history.reset();
    }

}
