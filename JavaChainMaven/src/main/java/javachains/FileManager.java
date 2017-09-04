/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Serafim
 */
public class FileManager {

    public FileManager() {

    }

    private String generateTimeStamp() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        return timeStamp;
    }

    public void WriteWholeHistroy(History h) throws FileNotFoundException, UnsupportedEncodingException {
        String p = generateTimeStamp();
        PrintWriter writer = new PrintWriter("history" + p + ".txt", "UTF-8");
        int i = 0;
        for (State s : h.boxStates) {
            WriteState(s, i, writer);
            i++;
        }
        writer.close();
    }

    public void WriteSingleState(State s, int round) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("state" + round + ".txt", "UTF-8");
        WriteState(s, round, writer);
        writer.close();

    }

    public void WriteState(State s, int round, PrintWriter writer) throws FileNotFoundException, UnsupportedEncodingException {
        writer.println("State " + round + ":");
        writer.println("Potential: " + s.returnPotential());
        int i = 0;
        for (Object o : s.getItems()) {
            Particle p = (Particle) o;
            writer.println(i + ": " + "X: " + p.getXValue() + " Y: " + p.getYValue());
            i++;
        }
    }

    public boolean LoadParamters(Loader l, String nameoffile) throws IOException {
        try {
            FileReader fr = new FileReader(nameoffile);
            BufferedReader br = new BufferedReader(fr);
            double[] parameters = new double[15];
            for (int i = 0; i <= 14; i++) {
                String line = br.readLine();
                String word[] = line.split(" ");
                double value = Double.parseDouble(word[1]);
                parameters[i] = value;
                
            }
            l.InitilizeRun(parameters[0], parameters[1], parameters[2], parameters[3], (int) Math.round(parameters[4]), parameters[5], parameters[6], parameters[7], parameters[8], parameters[9],parameters[10], parameters[11], parameters[12], parameters[13],parameters[14]);
            
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

}
