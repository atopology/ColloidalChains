/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

/**
 *
 * @author John
 */
public class Particle {

    private double radius;
    private double x;
    private double y;

    public Particle(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public double retrieveX()
    {
    return this.x;
    }
    public double retrieveY()
    {
    return this.y;
    }
    
    public double retrieveR()
    {
    return this.radius;
    }

    
}
