/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import Stats.StateStats;
import java.util.Random;
import javachains.BoxGenerator.EffectiveBoxGenerator;
import javachains.BoxGenerator.StandartBoxGenerator;
import metrics.Metric;
import metrics.TorusMetric;

/**
 *
 * @author Serafim
 */
public class Loader {

    private CoreRun runningThing;
    private Metric m;
    private Random r;
    private EffectiveBoxGenerator effectiveGenerator;
    private StandartBoxGenerator standartgen;

    public Loader(CoreRun run, Metric m, Random r) {
        this.runningThing = run;
        this.m = m;
        this.r = r;
        this.effectiveGenerator = new EffectiveBoxGenerator();
        this.standartgen = new StandartBoxGenerator();
        this.runningThing.setGenerator(standartgen);
    }

    public void switchToEffective() {
        this.runningThing.setGenerator(effectiveGenerator);
    }

    public void switchToStandart() {
        this.runningThing.setGenerator(standartgen);
    }

    public void InitilizeRun(double dxa, double dxb, double dya, double dyb, int N, double fraction, double xlength, double ylength, double DeltaAa, double DeltaAb, double EnergyAa, double EnergyAb, double DeltaRa, double DeltaRb, double EnergyR, long timesteps) {
        double radius = Calculator.computeRusingFraction(fraction, xlength * ylength, N);
        this.runningThing.setDx(Calculator.linearfunction(dxa, dxb, radius));
        this.runningThing.setDy(Calculator.linearfunction(dya, dyb, radius));
        this.runningThing.setN(N);
        this.runningThing.setR(radius);
        this.runningThing.setX(xlength);
        this.runningThing.setY(ylength);
        this.runningThing.setMetric(m);
        this.runningThing.setRandom(r);
        this.runningThing.setTimeSteps(timesteps);
        this.runningThing.initstat();
        TorusMetric asd = (TorusMetric) this.m;
        asd.setx1(xlength);
        asd.sety1(ylength);
        //       this.runningThing.setApproximator(new FillerLogic(this.ApproxDepth, r, m));
        double distancee = 2 * radius + Math.sqrt(2)*Calculator.linearfunction(dxa, dxb, radius);
        StateStats stats = new StateStats(distancee, m);
        stats.returnStatistics().setStartString("Number of particles which can potentially collide at distance: " + distancee + System.getProperty("line.separator"));
        
        this.runningThing.setStateStats(stats);
        SimpleSimulation s = this.runningThing.returnSimulator();
        s.setDeltaA(Calculator.linearfunction(DeltaAa, DeltaAb, radius));
        s.setEnergyA(Calculator.linearfunction(EnergyAa, EnergyAb, radius));
        s.setDeltaR(Calculator.linearfunction(DeltaRa, DeltaRb, radius));
        s.setEnergyR(EnergyR);

    }

}
