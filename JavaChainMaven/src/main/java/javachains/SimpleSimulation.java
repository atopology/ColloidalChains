/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

/**
 *
 * @author John
 */
public class SimpleSimulation {

    // Assumpsions on varriables:
    // EnergyA < 0 < EnergyR
    // DeltaA < DeltaR
    private double EnergyR;
    private double EnergyA;
    private double DeltaR;
    private double DeltaA;

    public SimpleSimulation(double EnergyR, double EnergyA, double DeltaR, double DeltaA) {
        this.EnergyR = EnergyR;
        this.EnergyA = EnergyA;
        this.DeltaR = DeltaR;
        this.DeltaA = DeltaA;

    }

    // List of parameters will be written here:
    public double computeEnergy(Particle A, Particle B) {
        double s = A.retrieveR() + B.retrieveR();
        double d = distance(A, B);
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

    public double computeProbability() {
        // To be implemented later. 

        return 0;
    }

    public double distance(Particle A, Particle B) {
        double dx = Math.abs(A.retrieveX() - B.retrieveX());
        double dy = Math.abs(A.retrieveY() - B.retrieveY());
        double answer = Math.sqrt(dx * dx + dy * dy);
        return answer;
    }

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

}
