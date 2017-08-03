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
public class Square {

    private double x0;
    private double x1;
    private double y0;
    private double y1;
    private int depth;
    private Wall wallnorth;
    private Wall wallsouth;
    private Wall walleast;
    private Wall wallwest;

    public Square(double x0, double y0, double x1, double y1, int depth) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.depth = depth;

    }

    public void InitWalls() {
        this.wallnorth = new Wall();
        this.wallsouth = new Wall();
        this.walleast = new Wall();
        this.wallwest = new Wall();

    }

    public double Area() {
        return Math.abs(this.x1 - this.x0) * Math.abs(this.y1 - this.y0);

    }

    public void setNorth(Wall k) {
        this.wallnorth = k;
    }

    public void setEast(Wall k) {
        this.wallsouth = k;
    }

    public void setWest(Wall k) {
        this.wallwest = k;
    }

    public void setSouth(Wall k) {
        this.wallsouth = k;
    }

    public Wall returnNorth() {
        return wallnorth;
    }

    public Wall returnSouth() {
        return wallsouth;
    }

    public Wall returnEast() {
        return walleast;
    }

    public Wall returnWest() {
        return wallwest;
    }

    public int returnDepth() {
        return this.depth;
    }

    public boolean doesIntersectSomeone() {
        return (this.wallnorth.returnList().isEmpty()) && (this.wallsouth.returnList().isEmpty()) && (this.walleast.returnList().isEmpty()) && (this.wallwest.returnList().isEmpty());
    }

    public double returnx0() {
        return this.x0;
    }

    public double returnx1() {
        return this.x1;
    }

    public double returny0() {
        return this.y0;
    }

    public double returny1() {
        return this.y1;
    }

   
}
