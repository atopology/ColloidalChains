/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains.onebyonecomputer;

import javachains.Particle;

/**
 *
 * @author Serafim
 */
public interface ParticleAccelerator {

    public void computeState(Particle newone, Particle oldone);

}
