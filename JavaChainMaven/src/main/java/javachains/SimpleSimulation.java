/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import metrics.Metric;

public class SimpleSimulation {

    // Assumpsions on varriables:
    // EnergyA < 0 < EnergyR
    // DeltaA < DeltaR
    private double EnergyR;
    private double EnergyA;
    private double DeltaR;
    private double DeltaA;
    private Metric metric;

    public SimpleSimulation(double EnergyR, double EnergyA, double DeltaR, double DeltaA, Metric metric) {
        this.EnergyR = EnergyR;
        this.EnergyA = EnergyA;
        this.DeltaR = DeltaR;
        this.DeltaA = DeltaA;
        this.metric = metric;

    }

    public SimpleSimulation() {

    }

    // List of parameters will be written here:
    public double computeEnergy(Particle A, Particle B) {
        double s = A.retrieveR() + B.retrieveR();
        double d = metric.distance(A, B);
        return computeEnergyR(s, d);
    }

    public double computeEnergyR(double s, double d) {

        // This is going to be piecewise linear function depending on some parameters 
        if (d < s) {
            return Double.NEGATIVE_INFINITY;
        } else if (d < s + this.DeltaA) {
            double a2 = (EnergyR / (DeltaR * DeltaA)) * (DeltaR - DeltaA) - EnergyA / DeltaA;
            double b2 = EnergyA - a2 * s;
            return a2 * d + b2;
        } else if (d < s + this.DeltaR) {
            double a = -EnergyR / DeltaR;
            double b = (EnergyR / DeltaR) * (s + DeltaR);
            return a * d + b;
        } else {
            return 0;
        }
        // test comment
    }

    public double computeSumOfPotentials(State box) {
        double sum = 0;
        for (Object o1 : box.getItems()) {
            for (Object o2 : box.getItems()) {
                if (!o1.equals(o2)) {
                    Particle p1 = (Particle) o1;
                    Particle p2 = (Particle) o2;
                    double pr = computeEnergy(p1, p2);
                    if (pr == Double.NEGATIVE_INFINITY) {
                        return Double.NEGATIVE_INFINITY;
                    } else {
                        sum = sum + pr;
                    }
                }
            }
        }
        return sum;
    }

    public double computeProbability(double EAttempt, double ECurrent) {
        double delta = EAttempt - ECurrent;
        double result = Math.pow(Math.E, -delta);
        return result;
    }

    // In this method we assume that all the particles have same radius
    public double returnEnergyR() {
        return this.EnergyR;

    }

    public double returnEnergyA() {
        return this.EnergyA;

    }

    public double returnDeltaR() {
        return this.DeltaR;

    }

    public double returnDeltaA() {
        return this.DeltaA;

    }

    public void setEnergyR(double s) {
        this.EnergyR = s;
    }

    public void setEnergyA(double s) {
        this.EnergyA = s;
    }

    public void setDeltaR(double s) {
        this.DeltaR = s;
    }

    public void setDeltaA(double s) {
        this.DeltaA = s;
    }

    public void setMetric(Metric m) {
        this.metric = m;
    }

    public boolean readyToCompute() {
        return (this.DeltaA != 0) && (this.DeltaR != 0) && (this.EnergyR != 0) && (this.EnergyA != 0);
    }

}
