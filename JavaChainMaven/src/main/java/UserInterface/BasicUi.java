/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import graphicss.DotPlot;
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

/**
 *
 * @author Serafim
 */
public class BasicUi {

    private CoreRun runningThing;
    private Scanner scan;

    public BasicUi() {

        this.runningThing = new CoreRun();
        this.scan = new Scanner(System.in);
    }

    public void run() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
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
                System.out.print("Step dx:");
                q = scan.next();
                double dx = Double.parseDouble(q);
                System.out.print("Step dy:");
                q = scan.next();
                double dy = Double.parseDouble(q);
                System.out.print("EnergyR:");
                q = scan.next();
                double EnergyR = Double.parseDouble(q);
                System.out.print("DeltaR:");
                q = scan.next();
                double DeltaR = Double.parseDouble(q);
                System.out.print("EnergyA: (Please enter in form a b , where y = ax+b");
                q = scan.next();
                double a1 = Double.parseDouble(q);
                q = scan.next();
                double b1 = Double.parseDouble(q);
                double EnergyA = Calculator.linearfunction(a1, b1, radius);
                System.out.print("DeltaA: (Please enter in form a b , where y = ax+b");
                q = scan.next();
                double a2 = Double.parseDouble(q);
                q = scan.next();
                double b2 = Double.parseDouble(q);
                double DeltaA = Calculator.linearfunction(a1, b1, radius);

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

            if (k.equals("calculate")) {
                if (!this.runningThing.ableToRun()) {
                    System.out.println("Not able to run! Please enter all the parameters correctly");
                } else {
                    this.runningThing.reset();
                    int n = scan.nextInt();
                    this.runningThing.run(n);
                }
            }
            if (k.equals("plot")) {
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
                    plotplot.pack();
                    RefineryUtilities.centerFrameOnScreen(plotplot);
                    plotplot.setVisible(true);
                }
            }

            if (k.equals("exit")) {
                return;
            }
        }

    }

}
