/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import Stats.StateStats;
import java.util.Random;
import javachains.BoxGenerator.BoxGenerator;
import metrics.Metric;
import org.jfree.data.xy.XYSeriesCollection;
import pixelapproximation.FillerLogic;

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
    private FillerLogic approximator;
    private StateStats StateStats;
    private BoxGenerator gen;
    private boolean debugmessages;
    private long timestepsmax;
    private long curtime;

    public CoreRun(double x, double y, double energyR, double energyA, double deltaR, double deltaA, Random random, Metric m, int N, double r, double dx, double dy, int approxdepth) {
        this.simulator = new SimpleSimulation(energyR, energyA, deltaR, deltaA, m);
        this.approximator = new FillerLogic(approxdepth, random, m);
        this.xlength = x;
        this.ylength = y;
        this.history = new History();
        this.N = N;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.scalingfactor = 1.0;
        this.debugmessages = false;

    }

    public void setTimeSteps(long p) {
        this.timestepsmax = p;
    }

    public void setdebugmessages(boolean k) {
        this.debugmessages = k;
    }

    public CoreRun() {
        this.simulator = new SimpleSimulation();
        this.history = new History();
        this.scalingfactor = 1.0;
        this.debugmessages = false;

    }

    public void setGenerator(BoxGenerator g) {
        this.gen = g;
    }

    public void setStateStats(StateStats s) {
        this.StateStats = s;
    }

    public StateStats returnStatistic() {
        return this.StateStats;

    }

    public void setRandom(Random r) {
        this.random = r;
    }

    public void setApproximator(FillerLogic approximator) {
        this.approximator = approximator;
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
        this.ourBox = dataGenerator(n, r, x, y, m, this.random);

    }

    public State dataGenerator(int n, double r, double x, double y, Metric m, Random ra) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        State data = new State("the data", m);
        for (int i = 0; i < n; i++) {
            double tx = ra.nextDouble() * x;
            double ty = ra.nextDouble() * y;
            Particle p = new Particle(tx, ty, r);
            while (!data.addParticle(p)) {
                tx = ra.nextDouble() * x;
                ty = ra.nextDouble() * y;
                p.forceChangeX(tx);
                p.setY(ty);
            }
        }
        return data;

    }
    // Attempted moves are done here in "square". 
    // Here we need to be careful, because we want each movement to stay in the box

    public State genNewBoxUsingApproxMethods(double dx, double dy) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, CloneNotSupportedException {
        State newbox = this.ourBox.clone();
        for (Object o : newbox.getItems()) {
            Particle p = (Particle) o;
            this.approximator.moveGivenParticle(p, this.dx, this.dy, newbox);
        }
        return newbox;
    }

    public State genNewBox(double dx, double dy) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //   System.out.println("Box generating started");
        State newbox = new State("key", this.m);
        int debugcompute = 0;
        for (Object o : this.ourBox.getItems()) {
            //       System.out.println("--Round + " + debugcompute);
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
                //             System.out.println("Change X: " + pdx + " Change Y: " + pdy);
            }
            debugcompute++;
        }
        //       System.out.println("Box generating ended");
        return newbox;
    }

    public State genNewBoxAlternated(double dx, double dy) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, CloneNotSupportedException {
        State newbox = this.ourBox.clone();

        for (Object o : newbox.getItems()) {
            Particle p = (Particle) o;
            //    double xt = p.getXValue();
            //    double yt = p.getYValue();
            double pdx = -dx + this.random.nextDouble() * 2 * dx;
            double pdy = -dy + this.random.nextDouble() * 2 * dy;
            this.m.move(p, pdx, pdy);

            while (canWeFindProblems(p, newbox)) {

                //        xt = p.getXValue();
                //        yt = p.getYValue();
                pdx = -dx + this.random.nextDouble() * 2 * dx;
                pdy = -dy + this.random.nextDouble() * 2 * dy;
                this.m.move(p, pdx, pdy);
            }
        }
        //    System.out.println("Total number of retries: " + retries);
        return newbox;
    }

    public boolean canWeFindProblems(Particle p, State box) {
        for (Object o : box.getItems()) {
            Particle iterating = (Particle) o;
            if (!iterating.equals(p)) {
                if (p.retrieveR() + iterating.retrieveR() >= this.m.distance(p, iterating)) {
                    //   System.out.println("This particle: " + p);
                    //  System.out.println("Problematic particle: " + iterating);
                    return true;
                }

            } else {
            }

        }
        return false;
    }

    public State genNewBoxAlternative(double dx, double dy) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        State newbox = new State("key", this.m);
        for (Object o : this.ourBox.getItems()) {
            Particle p = (Particle) o;
            double xt = p.getXValue();
            double yt = p.getYValue();
            double pdx = -dx + this.random.nextDouble() * 2 * dx;
            double pdy = -dy + this.random.nextDouble() * 2 * dy;
            Particle np = new Particle(coordinatefix(xt + pdx, this.xlength), coordinatefix(yt + pdy, this.ylength), p.retrieveR());
            newbox.add(np);
        }
        return newbox;
    }

    public boolean tryGenNewBox(State q, double dx, double dy) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        State newbox = q;
        int debugcounter = 0;
        for (Object o : this.ourBox.getItems()) {
            Particle p = (Particle) o;
            double xt = p.getXValue();
            double yt = p.getYValue();
            double pdx = -dx + this.random.nextDouble() * 2 * dx;
            double pdy = -dy + this.random.nextDouble() * 2 * dy;
            Particle np = new Particle(coordinatefix(xt + pdx, this.xlength), coordinatefix(yt + pdy, this.ylength), p.retrieveR());

            if (!newbox.addParticle(np)) {
                System.out.println("Failed at: " + debugcounter);
                return false;
            }
            debugcounter++;
        }
        return true;
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
        if (newpotential == Double.NEGATIVE_INFINITY) {
            return false;
        }
        double probability = this.simulator.computeProbability(newpotential, oldpotential);
//        if (this.debugmessages) {
//            System.out.print("Trying with probability: " + probability + " | oldpotential: " + oldpotential + " | newpotential: " + newpotential);
//        }
        double g = this.random.nextDouble();
        if (g <= probability) {
//            if (this.debugmessages) {
//                System.out.println(" result: succeful!");
//            }
            return true;
        }
//        if (this.debugmessages) {
//            System.out.println(" result: failed!");
//        }
        return false;
    }

    // Does full run n times
    public void run() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, CloneNotSupportedException {
        if (this.debugmessages) {
            System.out.println("Run starting ");
       }
        this.curtime = 0;
        GenerateParticlesInBox(this.N, this.r, this.xlength, this.ylength, this.m);
        this.history.add(this.ourBox);
        this.ourBox.setPotential(this.simulator.computeSumOfPotentials(this.ourBox));
    //    this.StateStats.Run(ourBox);

        if (this.debugmessages) {
            System.out.println("Initilization succefully completed");
       //     System.out.println(this.StateStats.returnStatistics().information());
        }

        while (this.curtime <= this.timestepsmax) {
//            if (this.debugmessages) {
//                System.out.println("Round " + i + ": ");
//            }

            State q = this.gen.generateNewState(this.ourBox, this.random, this.m, this.dx, this.dy,this);
            this.curtime++;
            //        System.out.println("Validity check: " + "First: " + q.getItems().get(0) + "Second: " + this.ourBox.getItems().get(0));
            double potpot = this.simulator.computeSumOfPotentials(q);
            q.setPotential(potpot);
            while ((!iterate(this.ourBox.returnPotential(), q.returnPotential())) && (this.curtime <= this.timestepsmax)) {

                q = this.gen.generateNewState(this.ourBox, this.random, this.m, this.dx, this.dy,this);
                this.curtime++;
                q.setPotential(this.simulator.computeSumOfPotentials(q));

                //           System.out.println("Generating new one...");
            }
            //       System.out.println("Reached end");
            this.history.add(q);
            this.ourBox = q;
           // this.StateStats.Run(ourBox);
            if (this.debugmessages) {
                System.out.println("Success at time: " + this.curtime);
            }
            
//            if (this.debugmessages) {
//                System.out.println("Statistic at time: " + this.curtime);
//                System.out.println(this.StateStats.returnStatistics().information());
//            }

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
    
    public void addTime(long d)
    {
    this.curtime = this.curtime + d;
    
    }

}
