/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Experemental;

import javachains.Particle;

/**
 *
 * @author Serafim
 */
public class AnalyticsExperement {

    public AnalyticsExperement() {

    }

    public void IntegralTestOneRepeat() throws NoSuchFieldException, Exception {
        Particle p = new Particle(2, 2, 0.25);
        double x1 = 3.0;
        double x0 = 1.0;
        double y1 = 3.0;
        double y0 = 1.0;
  //      double i1 = IntegralFunctionv2(p, y1, x1, x0);
   //     double i2 = IntegralFunctionv2(p, y0, x1, x0);
   //     double sub = i1 - i2;
        double sub = 0;
        System.out.println("Area of square after substraction of the circle " + sub);

    }

    public void SimpleExperement() throws NoSuchFieldException {
        Particle theone = new Particle(1.0, 1.0, 0.25);
        double dy = 0.01;
        for (double y = 0; y < 2.01; y = y + dy) {
            System.out.println("y: " + y + " density: " + Density(theone, y, 2.0, 0));
        }
    }

    public void integralTestOne() throws NoSuchFieldException, Exception {
        Particle p = new Particle(2, 0, 0.5);
        double x1 = 2.0;
        double x0 = 0;
        double y1 = 2.0;
        double y0 = 0;
        double i1 = integralFunction(p, y1, x1, x0);
        double i2 = integralFunction(p, y0, x1, x0);
        double sub = i1 - i2;
        System.out.println("Area of square after substraction of the circle " + sub);

    }

    public double Density(Particle p, double y, double x1, double x0) {

        double a = p.getXValue();
        //   double b = p.getYValue();
        double inin = Math.min(x1, a + Magic(p.retrieveR(), y, p.getYValue()));
        double outout = Math.max(x0, a - Magic(p.retrieveR(), y, p.getYValue()));
        double midlde = inin - outout;
        return Math.abs(x1 - x0) - Math.max(0, midlde);
    }

    public double Magic(double r, double y, double b) {
        return Math.sqrt(Math.max((4 * r * r) - (y - b) * (y - b), 0));
    }

    // Lets write the integral function down right here and check how it would look like:
    public double integralFunction(Particle p, double y, double x1, double x0) throws Exception {
        double a = p.getXValue();
        double b = p.getYValue();
        double r = p.retrieveR();
        double magic = Magic(r, y, b);

        boolean k11 = (-a + magic + x0 >= 0);
        boolean k21 = (a + magic - x1 > 0);
        boolean k31 = (a + magic - x0 >= 0);

        boolean d1 = k11 && k21 && k31;

        boolean k12 = (-a + magic + x0 >= 0);
        boolean k22 = (a + magic - x1 <= 0);
        boolean k32 = (x0 - x1 <= 0);

        boolean d2 = k12 && k22 && k32;

        boolean k13 = (-a + magic + x0 < 0);
        boolean k23 = (magic >= 0);
        boolean k33 = a + magic - x1 > 0;

        boolean d3 = k13 && k23 && k33;

        boolean k14 = -a + magic + x0 < 0;
        boolean k24 = a + magic - x1 <= 0;
        boolean k34 = -a + magic + x1 >= 0;

        boolean d4 = k14 && k24 && k34;

        boolean u1 = d1 || d2 || d3 || d4;

        if (u1) {
            return (x1 - x0) * y;
        }

        boolean w1 = magic < 0;
        boolean w2 = -a + magic + x0 < 0;
        boolean w3 = a + magic - x1 > 0;

        boolean u2 = w1 && w2 && w3;

        if (u2) {
            double part1 = (b - y) * magic + 4 * r * r * Math.atan((b - y) / magic) - x0 * y + x1 * y;
            return part1;
        }
        boolean o1 = -a + magic + x0 < 0;
        boolean o2 = -a + magic + x1 < 0;
        boolean o3 = a + magic - x1 <= 0;
        boolean u3 = o1 && o2 && o3;

        if (u3) {
            double part2 = a * y - (y / 2 - b / 2) * magic + 2 * r * r * Math.atan(((y - b) * magic) / (-1 * magic * magic)) - x0 * y;
            return part2;
        }

        boolean v1 = a + magic - x0 < 0;
        boolean v2 = -a + magic + x0 >= 0;
        boolean v3 = a + magic - x1 > 0;

        boolean u4 = v1 && v2 && v3;

        if (u4) {
            double part3 = -a * y - (y / 2 - b / 2) * magic + 2 * r * r * Math.atan(((y - b) * magic) / (-1 * magic * magic)) + x1 * y;
            return part3;
        }
        System.out.println(y);
        throw new Exception("Something went badly wrong with integral");

    }
    
    public double specialCase()
    {
    
    return 0;
    }
    
    

   

}
