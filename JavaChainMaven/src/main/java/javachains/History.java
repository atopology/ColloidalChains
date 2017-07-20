/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Serafim
 */
public class History {

    ArrayList<State> boxStates;

    public History() {
        this.boxStates = new ArrayList<State>();

    }

    public void add(State s) {
        this.boxStates.add(s);

    }

    public ArrayList<State> returnHistory() {
        return this.boxStates;
    }

    public void reset() {
        this.boxStates.clear();
    }

}
