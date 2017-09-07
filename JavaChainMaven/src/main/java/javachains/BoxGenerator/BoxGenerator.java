/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javachains.BoxGenerator;

import java.util.Random;
import javachains.CoreRun;
import javachains.State;
import metrics.Metric;

/**
 *
 * @author Serafim
 */
public interface BoxGenerator {
    
    public State generateNewState(State s,Random r, Metric m,double dx, double dy, CoreRun run );
    
}
