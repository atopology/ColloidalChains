/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import graphicss.DotPlot;
import java.util.Random;
import java.util.Scanner;
import javachains.Calculator;
import javachains.CoreRun;
import javachains.SimpleSimulation;
import javachains.State;
import metrics.Metric;
import metrics.TorusMetric;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import pixelapproximation.FillerLogic;

/**
 *
 * @author Serafim
 */
public class BasicUi {

    private CoreRun runningThing;
    private Scanner scan;
    private int ApproxDepth;

    public BasicUi() {
        this.ApproxDepth = 6;
        this.runningThing = new CoreRun();
        this.scan = new Scanner(System.in);
    }

    public void run() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, CloneNotSupportedException {
        System.out.println("Welcome to BasicUI of this program!");

        boolean stillRun = true;
        while (stillRun) {
            System.out.print("Your command: ");
            String k = scan.next();

            if (k.equals("setup")) {

                System.out.print("N:");
                String q = scan.next();
                int N = Integer.parseInt(q);
                System.out.print("Fraction:");
                q = scan.next();
                double fraction = Double.parseDouble(q);
                System.out.print("X-length:");
                q = scan.next();
                double xlength = Double.parseDouble(q);
                System.out.print("Y-length:");
                q = scan.next();
                double ylength = Double.parseDouble(q);
                double radius = Calculator.computeRusingFraction(fraction, xlength * ylength, N);
                System.out.print("Step dx (Please enter in form a b , where y = a*r+b):");
                q = scan.next();
                double dxa = Double.parseDouble(q);
                q = scan.next();
                double dxb = Double.parseDouble(q);
                double dx = Calculator.linearfunction(dxa, dxb, radius);
                System.out.print("Step dy (Please enter in form a b , where y = a*r+b):");
                q = scan.next();
                double dya = Double.parseDouble(q);
                q = scan.next();
                double dyb = Double.parseDouble(q);
                double dy = Calculator.linearfunction(dya, dyb, radius);
                System.out.print("EnergyR:");
                q = scan.next();
                double EnergyR = Double.parseDouble(q);
                System.out.print("DeltaR (Please enter in form a b , where y = a*r+b):");
                q = scan.next();
                double DeltaRa = Double.parseDouble(q);
                q = scan.next();
                double DeltaRb = Double.parseDouble(q);
                double DeltaR = Calculator.linearfunction(DeltaRa, DeltaRb, radius);
                System.out.print("EnergyA (Please enter in form a b , where y = a*r+b):");
                q = scan.next();
                double a1 = Double.parseDouble(q);
                q = scan.next();
                double b1 = Double.parseDouble(q);
                double EnergyA = Calculator.linearfunction(a1, b1, radius);
                System.out.print("DeltaA (Please enter in form a b , where y = a*r+b):");
                q = scan.next();
                double a2 = Double.parseDouble(q);
                q = scan.next();
                double b2 = Double.parseDouble(q);
                double DeltaA = Calculator.linearfunction(a2, b2, radius);

                setparameters(dx, dy, N, radius, xlength, ylength, DeltaA, EnergyA, DeltaR, EnergyR);
            } else if (k.equals("calculate")) {
                if (!this.runningThing.ableToRun()) {
                    System.out.println("Not able to run! Please enter all the parameters correctly");
                } else {
                    this.runningThing.reset();
                    int n = scan.nextInt();
                    this.runningThing.run(n);
                }
            } else if (k.equals("plot")) {
                int m = scan.nextInt();
                if (m >= this.runningThing.returnHistory().returnHistory().size()) {
                    System.out.println("out of bounds error");
                } else {
                    String title = "Particle simulation";
                    State s = this.runningThing.returnHistory().returnHistory().get(m);
                    double potential = s.returnPotential();
                    String subtitle = "Potential of the system: " + potential;
                    double r = this.runningThing.returnR();
                    XYSeriesCollection data = new XYSeriesCollection();
                    data.addSeries(s);
                    DotPlot plotplot = new DotPlot(title, subtitle, r, data);
                    plotplot.setScaling(this.runningThing.returnScale());
                    plotplot.pack();
                    RefineryUtilities.centerFrameOnScreen(plotplot);
                    plotplot.setVisible(true);
                }
            } else if (k.equals("exit")) {
                System.exit(0);
            } else if (k.equals("setscaling")) {
                System.out.print("scale:");
                String qav = this.scan.next();
                double dabv = Double.parseDouble(qav);
                this.runningThing.setScale(dabv);
            } else if (k.equals("loaddefault")) {
                parametrize(0.05, 0, 0.05, 0, 1000, 0.35, 1.0, 1.0, 0.05, 0, 0, -7, 0.5, 0, 3.75);
            }
        }

    }

    private void setparameters(double dx, double dy, int N, double radius, double xlength, double ylength, double DeltaA, double EnergyA, double DeltaR, double EnergyR) {
        // adding parameters
        this.runningThing.setDx(dx);
        this.runningThing.setDy(dy);
        this.runningThing.setN(N);
        this.runningThing.setR(radius);
        this.runningThing.setX(xlength);
        this.runningThing.setY(ylength);
        Metric m = new TorusMetric(0.0, 0.0, xlength, ylength);
        this.runningThing.setMetric(m);
        SimpleSimulation s = this.runningThing.returnSimulator();
        s.setDeltaA(DeltaA);
        s.setEnergyA(EnergyA);
        s.setDeltaR(DeltaR);
        s.setEnergyR(EnergyR);
    }

    private void parametrize(double dxa, double dxb, double dya, double dyb, int N, double fraction, double xlength, double ylength, double DeltaAa, double DeltaAb, double EnergyAa, double EnergyAb, double DeltaRa, double DeltaRb, double EnergyR) {
        // adding parameters
        double radius = Calculator.computeRusingFraction(fraction, xlength * ylength, N);
        this.runningThing.setDx(Calculator.linearfunction(dxa, dxb, radius));
        this.runningThing.setDy(Calculator.linearfunction(dya, dyb, radius));
        this.runningThing.setN(N);
        this.runningThing.setR(radius);
        this.runningThing.setX(xlength);
        this.runningThing.setY(ylength);
        Metric m = new TorusMetric(0.0, 0.0, xlength, ylength);
        this.runningThing.setMetric(m);
        Random r = new Random();
        this.runningThing.setRandom(r);
        this.runningThing.setApproximator(new FillerLogic(this.ApproxDepth, r, m));
        SimpleSimulation s = this.runningThing.returnSimulator();
        s.setDeltaA(Calculator.linearfunction(DeltaAa, DeltaAb, radius));
        s.setEnergyA(Calculator.linearfunction(EnergyAa, EnergyAb, radius));
        s.setDeltaR(Calculator.linearfunction(DeltaRa, DeltaRb, radius));
        s.setEnergyR(EnergyR);
    }

}
