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

    public void WriteWholeHistroy(History h, String path) throws FileNotFoundException, UnsupportedEncodingException {
        String p = generateTimeStamp();
        PrintWriter writer = new PrintWriter(path + "history" + p + ".txt", "UTF-8");
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

    public int LoadState(String nameoffile, CoreRun run, int buffer) throws IOException, NoSuchFieldException {
        try {
            State newstate = new State("wasd", run.returnMetric());
            FileReader fr = new FileReader(nameoffile);
            BufferedReader br = new BufferedReader(fr);
            String k = br.readLine();
            int i = 0;
            int count = 0;
            double potential = 0;
            while (k != null) {
                
                if (i == buffer) {
                    String par[] = k.split(" ");
                    potential = Double.parseDouble(par[1]);
                }
                if (i > buffer) {
                    String par[] = k.split(" ");
                    double x = Double.parseDouble(par[2]);
                    double y = Double.parseDouble(par[4]);
                    double r = run.returnR();
                    Particle p = new Particle(x, y, r);
                    newstate.add(p);
                    count++;
                }

                i++;

                k = br.readLine();
            }
            newstate.setPotential(potential);
            run.setSaveadState(newstate);
            return count;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public boolean LoadParamters(Loader l, String nameoffile) throws IOException {
        try {
            FileReader fr = new FileReader(nameoffile);
            BufferedReader br = new BufferedReader(fr);
            double[] parameters = new double[16];
            for (int i = 0; i <= 15; i++) {
                String line = br.readLine();
                String word[] = line.split(" ");
                double value = Double.parseDouble(word[1]);
                parameters[i] = value;

            }
            l.InitilizeRun(parameters[0], parameters[1], parameters[2], parameters[3], (int) Math.round(parameters[4]), parameters[5], parameters[6], parameters[7], parameters[8], parameters[9], parameters[10], parameters[11], parameters[12], parameters[13], parameters[14], (long) parameters[15]);

        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

}
