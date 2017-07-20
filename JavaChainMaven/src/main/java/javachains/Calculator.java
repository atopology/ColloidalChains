/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

/**
 *
 * @author Serafim
 */
public class Calculator {

    // For now we use predefined 

    public static double computeRusingFraction(double fraction, double area, int N) {
        double result = Math.sqrt((fraction * area) / (Math.PI * N));
        return result;
    }
    public static double linearfunction(double a, double b, double x) {
        return a * x + b;
    }
}
