/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import Stats.StateStats;
import graphicss.DotPlot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javachains.Calculator;
import javachains.CoreRun;
import javachains.FileManager;
import javachains.Loader;
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
    private Random r;
    private Metric m;
    private Loader loader;
    private FileManager filemanager;
    private double scale;

    public BasicUi() {
        this.ApproxDepth = 6;
        this.runningThing = new CoreRun();
        this.scan = new Scanner(System.in);
        this.m = new TorusMetric(0, 0, 1, 1);
        this.r = new Random();
        this.loader = new Loader(this.runningThing, m, r);
        this.filemanager = new FileManager();
        this.scale = 1.0;

    }

    public void run() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, CloneNotSupportedException, FileNotFoundException, UnsupportedEncodingException, IOException, InterruptedException {
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
                System.out.print("Timesteps: ");
                q = scan.next();
                long timestep = Long.parseLong(q);
                this.loader.InitilizeRun(dxa, dxb, dya, dyb, N, fraction, xlength, ylength, a2, b2, a1, b1, DeltaRa, DeltaRb, EnergyR, timestep);
            } else if (k.equals("calculate")) {
                if (!this.runningThing.ableToRun()) {
                    System.out.println("Not able to run! Please enter all the parameters correctly");
                } else {
                    String pam = scan.next();
                    if (pam.equals("classic")) {
                        String q = scan.next();
                        int i = Integer.parseInt(q);
                        this.runningThing.reset();
                        long startTime = System.currentTimeMillis();
                        this.runningThing.runClassic(i);
                        long endTime = System.currentTimeMillis();
                        long totalTime = endTime - startTime;
                        System.out.println("Number of succeful states:" + this.runningThing.returnHistory().returnHistory().size());
                        System.out.println("Total computation time:" + totalTime);
                    } else if (pam.equals("onebyone")) {
                        this.runningThing.reset();
                        long startTime = System.currentTimeMillis();
                        this.runningThing.runAnother();
                        long endTime = System.currentTimeMillis();
                        long totalTime = endTime - startTime;
                        System.out.println("Number of saved states" + this.runningThing.returnHistory().returnHistory().size());
                        System.out.println("Total computation time:" + totalTime);
                    }
                }
            } else if (k.equals("plot")) {
                int m = scan.nextInt();
                if (m >= this.runningThing.returnHistory().returnHistory().size()) {
                    System.out.println("out of bounds error");
                } else {

                    State s = this.runningThing.returnHistory().returnHistory().get(m);
                    plotBox(s);
                }
            } else if (k.equals("exit")) {
                System.exit(0);
            } else if (k.equals("setscale")) {
                String qav = this.scan.next();
                double dabv = Double.parseDouble(qav);
                this.scale = dabv;
            } else if (k.equals("loaddefault")) {
                this.loader.InitilizeRun(0.05, 0, 0.05, 0, 1000, 0.35, 1.0, 1.0, 0.05, 0, 0, -7, 0.5, 0, 3.75, 1000000);
            } else if (k.equals("switch")) {
                k = this.scan.next();
                if (k.equals("effective")) {

                    this.loader.switchToEffective();
                    System.out.println("Switched to effective mode ");
                }
                if (k.equals("standart")) {
                    this.loader.switchToStandart();
                    System.out.println("Switched to standard mode");
                }
            } else if (k.equals("debugmessages")) {
                k = this.scan.next();
                if (k.equals("on")) {
                    System.out.println("debugmessages activated");
                    this.runningThing.setdebugmessages(true);
                }
                if (k.equals("off")) {
                    System.out.println("debugmessages turned off");
                    this.runningThing.setdebugmessages(false);

                }
            } else if (k.equals("printtofile")) {
                if (this.runningThing.returnHistory() != null) {

                    k = this.scan.next();
                    if (k.equals("all")) {

                        System.out.print("path: ");
                        k = this.scan.next();
                        this.filemanager.WriteWholeHistroy(this.runningThing.returnHistory(), k);

                    } else {
                        int i = Integer.parseInt(k);
                        State s = this.runningThing.returnHistory().returnHistory().get(i);
                        if (s == null) {
                            System.out.println("Out of bounds error");
                        } else {
                            this.filemanager.WriteSingleState(s, i);
                        }
                    }

                } else {
                    System.out.println("error no history avaible");
                }
            } else if (k.equals("readparameters")) {
                k = this.scan.next();
                if (this.filemanager.LoadParamters(loader, k)) {
                    System.out.println("Succeful!");

                } else {
                    System.out.println("Failed");
                }

            } else if (k.equals("CurrentRadius")) {

                System.out.println("Current radius is: " + this.runningThing.returnRadius());
            } else if (k.equals("save")) {
                int index = Integer.parseInt(this.scan.next());
                ArrayList<State> states = this.runningThing.returnHistory().returnHistory();
                this.runningThing.setSaveadState(states.get(index));
                System.out.println("Operation completed succefully!");

                //  this.runningThing.setSaveadState();
            } else if (k.equals("loadstate")) {
                String bambam = this.scan.next();
                int number = Integer.parseInt((this.scan.next()));
                int j = this.filemanager.LoadState(bambam, runningThing, number);
                if (j == -1) {
                    System.out.println("Error loading file");
                } else {
                    System.out.println("Number of particles loaded: " + j);
                }

            } else if (k.equals("resetStats")) {
                this.runningThing.initstat();
            } else if (k.equals("printstat")) {
                System.out.println(this.runningThing.returnStat().information());
            } else if (k.equals("runsaved")) {
                this.runningThing.reset();
                long startTime = System.currentTimeMillis();
                this.runningThing.runAnotherFromFixedState(this.runningThing.returnSaved());
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Number of saved states" + this.runningThing.returnHistory().returnHistory().size());
                System.out.println("Total computation time:" + totalTime);
            } else if (k.equals("plotsaved")) {
                plotBox(this.runningThing.returnSaved());
            }

        }

    }

//    private void setparameters(double dx, double dy, int N, double radius, double xlength, double ylength, double DeltaA, double EnergyA, double DeltaR, double EnergyR) {
//        // adding parameters
//        this.runningThing.setDx(dx);
//        this.runningThing.setDy(dy);
//        this.runningThing.setN(N);
//        this.runningThing.setR(radius);
//        this.runningThing.setX(xlength);
//        this.runningThing.setY(ylength);
//        Metric m = new TorusMetric(0.0, 0.0, xlength, ylength);
//        this.runningThing.setMetric(m);
//        SimpleSimulation s = this.runningThing.returnSimulator();
//        s.setDeltaA(DeltaA);
//        s.setEnergyA(EnergyA);
//        s.setDeltaR(DeltaR);
//        s.setEnergyR(EnergyR);
//    }
//    
//    private void parametrize(double dxa, double dxb, double dya, double dyb, int N, double fraction, double xlength, double ylength, double DeltaAa, double DeltaAb, double EnergyAa, double EnergyAb, double DeltaRa, double DeltaRb, double EnergyR) {
//        // adding parameters
//        double radius = Calculator.computeRusingFraction(fraction, xlength * ylength, N);
//        this.runningThing.setDx(Calculator.linearfunction(dxa, dxb, radius));
//        this.runningThing.setDy(Calculator.linearfunction(dya, dyb, radius));
//        this.runningThing.setN(N);
//        this.runningThing.setR(radius);
//        this.runningThing.setX(xlength);
//        this.runningThing.setY(ylength);
//        Metric m = new TorusMetric(0.0, 0.0, xlength, ylength);
//        this.runningThing.setMetric(m);
//        Random r = new Random();
//        this.runningThing.setRandom(r);
// //       this.runningThing.setApproximator(new FillerLogic(this.ApproxDepth, r, m));
//        this.runningThing.setStateStats(new StateStats(2 * radius + Calculator.linearfunction(dxa, dxb, radius), m));
//        SimpleSimulation s = this.runningThing.returnSimulator();
//        s.setDeltaA(Calculator.linearfunction(DeltaAa, DeltaAb, radius));
//        s.setEnergyA(Calculator.linearfunction(EnergyAa, EnergyAb, radius));
//        s.setDeltaR(Calculator.linearfunction(DeltaRa, DeltaRb, radius));
//        s.setEnergyR(EnergyR);
//    }
    private void plotBox(State s) {
        String title = "Particle simulation";
        double potential = s.returnPotential();
        String subtitle = "Potential of the system: " + potential;
        double r = this.runningThing.returnR();
        XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(s);
        DotPlot plotplot = new DotPlot(title, subtitle, r, data, this.scale);
        plotplot.pack();
        RefineryUtilities.centerFrameOnScreen(plotplot);
        plotplot.setVisible(true);
    }
}
