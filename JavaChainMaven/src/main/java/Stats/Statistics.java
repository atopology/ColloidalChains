/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stats;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Serafim
 */
public class Statistics {

    private LinkedList<Integer> entities;
    private String startString;

    public Statistics() {
        this.entities = new LinkedList<Integer>();
        this.startString = "";
    }

    public void setStartString(String k) {
        this.startString = k;
    }

    public void add(int i) {
        entities.add(i);
    }

    public int min() {
        int min = Integer.MAX_VALUE;
        for (Integer i : this.entities) {
            min = Math.min(i, min);
        }
        return min;
    }

    public int max() {
        int min = Integer.MIN_VALUE;
        for (Integer i : this.entities) {
            min = Math.max(i, min);
        }
        return min;
    }

    public double Average() {
        double sum = 0;
        for (Integer i : this.entities) {
            sum = sum + i;
        }
        return sum / this.entities.size();
    }

    public String information() {
        String newline = System.getProperty("line.separator");
        String k = this.startString;
        k = k + "Maximum: " + max() + newline;
        k = k + "Minimum: " + min() + newline;
        k = k + "Average: " + Average() + newline;

        return k;
    }

}
