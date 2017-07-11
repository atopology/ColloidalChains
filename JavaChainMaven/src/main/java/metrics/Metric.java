/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics;

import javachains.Particle;

/**
 *
 * @author Serafim
 */
public interface Metric {

    public double distance(Particle A, Particle B);

}
