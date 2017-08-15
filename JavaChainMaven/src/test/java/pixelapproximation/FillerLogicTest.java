/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelapproximation;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import javachains.Particle;
import metrics.TorusMetric;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Serafim
 */
public class FillerLogicTest {

    private FillerLogic logic;

    public FillerLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.logic = new FillerLogic(5, new Random(), new TorusMetric(-100, -100, 100, 100));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of minimalDistanceSegmentCircleX method, of class FillerLogic.
     */
    @Test
    public void testMinimalDistanceSegmentCircleX() throws Exception {
        double x0 = -3.0;
        double x1 = 0.0;
        double c = 0.0;
        Particle p = new Particle(3, 4, 1);
        double r = 1.0;
        double expResult = 5.0;
        double result = logic.minimalDistanceSegmentCircleX(x0, x1, c, p, r);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of minimalDistanceSegmentCircleY method, of class FillerLogic.
     */
    @Test
    public void testMinimalDistanceSegmentCircleY() throws Exception {
        System.out.println("minimalDistanceSegmentCircleY");
        double y0 = 3.0;
        double y1 = 0.0;
        double w = 0.0;
        Particle p = new Particle(4, 3, 1);
        double r = 1.0;
        double expResult = 4.0;
        double result = this.logic.minimalDistanceSegmentCircleY(y0, y1, w, p, r);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of SquareIteration method, of class FillerLogic.
     */
    @Test
    public void testSquareIteration() throws Exception {
        System.out.println("SquareIteration");
        Square s = null;
        double r = 0.0;
        FillerLogic instance = null;
        //      instance.SquareIteration(s, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SquareWallIteration method, of class FillerLogic.
     */
    @Test
    public void testSquareWallIteration() throws Exception {
        System.out.println("SquareWallIteration");
        Square s = null;
        double r = 0.0;
        String direction = "";
        Collection<Particle> lists = null;
        FillerLogic instance = null;
        instance.SquareWallIteration(s, r, direction, lists);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doIntersect method, of class FillerLogic.
     */
    @Test
    public void testDoIntersect() {
        System.out.println("doIntersect");
        double r1 = 0.0;
        double r2 = 0.0;
        double db = 0.0;
        FillerLogic instance = null;
        boolean expResult = false;
        boolean result = instance.doIntersect(r1, r2, db);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of xLegal method, of class FillerLogic.
     */
    @Test
    public void testXLegal() throws Exception {
        System.out.println("xLegal");
        double x0 = 0.0;
        double x1 = 0.0;
        double r = 0.0;
        double c = 0.0;
        LinkedList<Particle> list = null;
        FillerLogic instance = null;
        boolean expResult = false;
        boolean result = instance.xLegal(x0, x1, r, c, list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of yLegal method, of class FillerLogic.
     */
    @Test
    public void testYLegal() throws Exception {
        System.out.println("yLegal");
        double y0 = 0.0;
        double y1 = 0.0;
        double r = 0.0;
        double c = 0.0;
        LinkedList<Particle> list = null;
        FillerLogic instance = null;
        boolean expResult = false;
        boolean result = instance.yLegal(y0, y1, r, c, list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveGivenParticle method, of class FillerLogic.
     */
    @Test
    public void testMoveGivenParticle() throws Exception {
        System.out.println("moveGivenParticle");
        Particle moving = null;
        double dx = 0.0;
        double dy = 0.0;
        FillerLogic instance = null;
        Particle expResult = null;
        //      Particle result = instance.moveGivenParticle(moving, dx, dy);
        //       assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of average method, of class FillerLogic.
     */
    @Test
    public void testAverage() {
        System.out.println("average");
        double a = 3.0;
        double b = 2.0;
        double expResult = 2.5;
        double result = this.logic.average(a, b);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of generateRandomMovement method, of class FillerLogic.
     */
    @Test
    public void testGenerateRandomMovement() throws Exception {
        System.out.println("generateRandomMovement");
        Particle moving = null;
        ArrayDeque<Square> AcceptedList = null;
        FillerLogic instance = null;
        Particle expResult = null;
        Particle result = instance.generateRandomMovement(moving, AcceptedList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRandomParticleInSquare method, of class FillerLogic.
     */
    @Test
    public void testGenerateRandomParticleInSquare() throws Exception {

        Square s = new Square(1.0, 1.0, 3.0, 3.0, 0);
        double r = 1.0;
        boolean k = true;
        for (int i = 0; i < 100; i++) {
            Particle result = this.logic.generateRandomParticleInSquare(s, r);
            double x = result.getXValue();
            double y = result.getYValue();
            k = k && (x >= 1) && (y >= 1) && (y <= 3) && (x <= 3);
        }

        assertTrue(k);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testGenerateRandomParticleInSquareSetAntiNull() throws Exception {

        ArrayDeque<Square> deque = new ArrayDeque<Square>();
        Square s = new Square(1.0, 1.0, 3.0, 3.0, 0);
        Square s2 = new Square(3.0, 1.0, 5.0, 3.0, 0);
        Square s3 = new Square(1.0, 3.0, 3.0, 5.0, 0);
        Square s4 = new Square(3.0, 3.0, 5.0, 5.0, 0);
        double r = 1.0;
        deque.add(s);
        deque.add(s2);
        deque.add(s3);
        deque.add(s4);
        Particle q = new Particle(3.0, 3.0, 1.0);
        boolean k = true;
        for (int i = 0; i < 100; i++) {
            Particle result = this.logic.generateRandomMovement(q, deque);
            k = k && (result != null);
        }

        assertTrue(k);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of computeAreaSum method, of class FillerLogic.
     */
    @Test
    public void testComputeAreaSum() {
        System.out.println("computeAreaSum");
        ArrayDeque<Square> Accepted = null;
        FillerLogic instance = null;
        double expResult = 0.0;
        double result = instance.computeAreaSum(Accepted);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of lineSegmentAndCircleIntersection method, of class FillerLogic.
     *
     * We are not currently using this method and thus will omit all the tests.
     */
}
