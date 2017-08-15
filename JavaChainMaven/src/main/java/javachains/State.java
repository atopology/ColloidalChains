/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metrics.Metric;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

public class State extends XYSeries implements Cloneable {

    private Metric metric;
    private double potential;

    public State(Comparable key) {
        super(key);
        this.potential = 0;
    }
    public State(Comparable key, Metric metric) {
        super(key);
        this.metric = metric;
    }
    @Override
    public State clone() throws CloneNotSupportedException {
        State newone = new State("key", metric);
        for (Object o : super.getItems()) {
            try {
                Particle p = (Particle) o;
                Particle q = p.clond();
                newone.add(q);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newone;

    }
    public boolean isPossibleToAddParticle(Particle q) {
        for (Object qt : super.getItems()) {
            Particle p = (Particle) qt;
            if (p.retrieveR() + q.retrieveR() >= metric.distance(p, q)) {
                return false;
            }
        }
        return true;
    }
    // Collision detector will be implemented here: 
    // O(N) algorithm 
    //  public boolean isPossibleToAddParticle(XYDataItem q) {
    //   if (super.getItems().isEmpty()) {
    //        return true;
    //     } else {
    //          for (Object q : super.getItems()) {
    //               XYDataItem qa = (XYDataItem) q;
    //              if (qa.getX() + p.retrieveR() >= metric.distance(p, q)) {
    //                  return false;
    //              }
    //          }
    //         return true;
    //       }
    //    }

    public boolean addParticle(Particle p) {
        if (isPossibleToAddParticle(p)) {
            super.add(p);
            return true;
        }
        return false;
    }

    public void setPotential(double p) {
        this.potential = p;
    }

    public double returnPotential() {
        return this.potential;
    }
}
