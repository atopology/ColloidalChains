/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

import javachains.Particle;


public class TorusMetric extends EuclideanMetric implements Metric {

    private double x0;
    private double y0;
    private double x1;
    private double y1;

    public TorusMetric(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public double distance(Particle A, Particle B) {
        double epsilon = Math.pow(10, -6);
        double xA = A.getXValue();
        double xB = B.getXValue();
        double yA = A.getYValue();
        double yB = B.getYValue();
        double smallest = Double.MAX_VALUE;
        double dx = Math.abs(x1 - x0);
        double dy = Math.abs(y1 - y0);
        for (double p = -dx; p <= dx + epsilon; p = p + dx) {
            for (double q = -dy; q <= dy + epsilon; q = q + dy) {
                double d = super.euclideanDistance(xA, yA, xB + p, yB + q);
                smallest = Math.min(d, smallest);
            }
        }

        return smallest;
    }

}
