/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Override
    public void move(Particle A, double dx, double dy) {
        try {
            A.forceChangeX(normalizeX(A, dx));
            A.setY(normalizeY(A, dy));
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TorusMetric.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TorusMetric.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TorusMetric.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public double normalizeX(Particle A, double dx) {
        double tx = A.getXValue() + dx;
        double newx = 0;
        if (tx >= this.x1) {
            newx = tx - (this.x1 - this.x0);
        } else if (this.x0 > tx) {
            newx = tx + (this.x1 - this.x0);
        } else {
            newx = tx;
        }
        
        return newx;
    }
    
    public double normalizeY(Particle A, double dy) {
        double ty = A.getYValue() + dy;
        double newy = 0;
        if (ty >= this.y1) {
            newy = ty - (this.y1 - this.y0);
        } else if (this.y0 > ty) {
            newy = ty + (this.y1 - this.y0);
        } else {
            newy = ty;
        }
        
        return newy;
    }
    
}
