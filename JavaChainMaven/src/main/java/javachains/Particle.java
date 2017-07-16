/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachains;

import java.lang.reflect.Field;
import org.jfree.data.xy.XYDataItem;

/**
 *
 * This class will most likely not be used
 */
public class Particle extends XYDataItem {

    private double radius;
    private Field f;

    public Particle(Number x, Number y) {
        super(x, y);

    }

    public Particle(double x, double y, double r) throws NoSuchFieldException {
        super(x, y);
        this.radius = r;
        this.f = this.getClass().getSuperclass().getDeclaredField("x");
        this.f.setAccessible(true);
    }
    

    public double retrieveR() {
        return this.radius;
    }

    public void TestInsideClass() {
        System.out.println(this.getClass());
        System.out.println(super.getClass());
        XYDataItem item = new XYDataItem(7, 7);
        System.out.println(item.getClass());
        System.out.println(this.getClass().getSuperclass());
    }

    // Because of this special feature in the libary:
    
    // * Sets the y-value for this data item.  Note that there is no
   //* corresponding method to change the x-value.

    // I had to create getaround like this:
    public void forceChangeX(double xa) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        this.f.set(this, xa);

    }

}
