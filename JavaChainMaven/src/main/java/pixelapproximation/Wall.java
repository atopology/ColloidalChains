/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelapproximation;

import java.util.LinkedList;
import javachains.Particle;

/**
 *
 * @author Serafim
 */
public class Wall {

    private LinkedList<Particle> problemParticles;

    public Wall() {
        this.problemParticles = new LinkedList<Particle>();

    }

    public LinkedList<Particle> returnList() {
        return this.problemParticles;
    }

    public void addToList(Particle p) {

        this.problemParticles.add(p);

    }

}
