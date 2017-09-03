/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Serafim
 */
public class FileManager {

    public FileManager() {

    }
    
    public void WriteWholeHistroy(History h) throws FileNotFoundException, UnsupportedEncodingException
    {
    PrintWriter writer = new PrintWriter("history " + Math.random() + ".txt", "UTF-8");
    int i = 0;
    for (State s: h.boxStates)
    {
    WriteState(s,i,writer);
    i++;
    }
    }
    
    public void WriteSingleState(State s, int round) throws FileNotFoundException, UnsupportedEncodingException
    {
    PrintWriter writer = new PrintWriter("state" + round + ".txt", "UTF-8");
    WriteState(s, round, writer);
    writer.close();
    
    
    }

    public void WriteState(State s, int round, PrintWriter writer) throws FileNotFoundException, UnsupportedEncodingException {
        writer.println("State " + round + ":");
        int i = 0;
        for (Object o : s.getItems()) {
            Particle p = (Particle) o;
            writer.println(i + ": " + "X: " + p.getXValue() + " Y: " + p.getYValue());
            i++;
        }
    }

}
