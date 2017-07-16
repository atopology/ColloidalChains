/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.LinkedList;
import metrics.Metric;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

public class Box extends XYSeries {
    
    private Metric metric;
    
    public Box(Comparable key) {
        super(key);
    }
    
    public Box(Comparable key, Metric metric) {
        super(key);
        this.metric = metric;
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
}
