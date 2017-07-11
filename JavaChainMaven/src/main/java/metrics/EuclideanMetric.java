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
public class EuclideanMetric implements Metric {

    public double euclideanDistance(double xA, double yA, double xB, double yB) {
        double dx = Math.abs(xA - xB);
        double dy = Math.abs(yA - yB);
        double answer = Math.sqrt(dx * dx + dy * dy);
        return answer;
    }

    @Override
    public double distance(Particle A, Particle B) {
        return euclideanDistance(A.retrieveX(), A.retrieveY(), B.retrieveX(), B.retrieveY());
    }

}
