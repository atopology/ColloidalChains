/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixelapproximation;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import javachains.Particle;
import javachains.State;
import metrics.Metric;

/**
 *
 * @author Serafim
 */
public class FillerLogic {

    private State currentState;
    private Metric m;
    private Random random;
    private int ApproXDepth;

    // Circle and line intersection methodology:
    public FillerLogic(int approdepth) {
        this.ApproXDepth = approdepth;
    }

    public double minimalDistanceSegmentCircleX(double x0, double x1, double c, Particle p, double r) throws NoSuchFieldException {
        double xmin = Math.min(x0, x1);
        double xmax = Math.max(x0, x1);

        if ((xmin <= p.getXValue()) && (p.getXValue() <= xmax)) {
            Particle temp = new Particle(p.getXValue(), c, r);
            double distance = this.m.distance(temp, p);
            return distance;
        } else {
            Particle t1 = new Particle(xmin, c, r);
            Particle t2 = new Particle(xmax, c, r);
            double distance = Math.min(m.distance(t1, p), m.distance(t2, p));
            return distance;
        }
    }

    public double minimalDistanceSegmentCircleY(double y0, double y1, double w, Particle p, double r) throws NoSuchFieldException {
        double ymin = Math.min(y0, y1);
        double ymax = Math.max(y0, y1);

        if ((ymin <= p.getYValue()) && (p.getYValue() <= ymax)) {
            Particle temp = new Particle(w, p.getYValue(), r);
            double distance = this.m.distance(temp, p);
            return distance;
        } else {
            Particle t1 = new Particle(w, ymin, r);
            Particle t2 = new Particle(w, ymax, r);
            double distance = Math.min(m.distance(t1, p), m.distance(t2, p));
            return distance;
        }
    }

    public Square initilizeFirstSquare(double x0, double x1, double y0, double y1, double r) throws NoSuchFieldException {

        Square FirstSquare = new Square(x0, x1, y0, y1, 0);
        FirstSquare.InitWalls();
        SquareIteration(FirstSquare, r);
        return FirstSquare;
    }

    public void SquareIteration(Square s, double r) throws NoSuchFieldException {

        double x0 = s.returnx0();
        double x1 = s.returnx1();
        double y0 = s.returny0();
        double y1 = s.returny1();

        for (Object o : this.currentState.getItems()) {
            Particle w = (Particle) o;
            double radw = w.retrieveR();
            double p1 = minimalDistanceSegmentCircleX(x0, x1, y0, w, r);
            double p2 = minimalDistanceSegmentCircleX(x0, x1, y1, w, r);
            double p3 = minimalDistanceSegmentCircleY(y0, y1, x0, w, r);
            double p4 = minimalDistanceSegmentCircleY(y0, y1, x1, w, r);
            if (doIntersect(r, radw, p1)) {
                s.returnSouth().addToList(w);
            }
            if (doIntersect(r, radw, p2)) {
                s.returnNorth().addToList(w);
            }
            if (doIntersect(r, radw, p3)) {
                s.returnWest().addToList(w);
            }
            if (doIntersect(r, radw, p4)) {
                s.returnEast().addToList(w);
            }

        }

    }

    public boolean doIntersect(double r1, double r2, double db) {
        return db <= r1 + r2;

    }

    public boolean xLegal(double x0, double x1, double r, double c, LinkedList<Particle> list) throws NoSuchFieldException {
        for (Particle p : list) {
            if (doIntersect(p.retrieveR(), r, minimalDistanceSegmentCircleX(x0, x1, c, p, r))) {
                return false;
            }
        }
        return true;
    }

    public boolean yLegal(double y0, double y1, double r, double c, LinkedList<Particle> list) throws NoSuchFieldException {
        for (Particle p : list) {
            if (doIntersect(p.retrieveR(), r, minimalDistanceSegmentCircleY(y0, y1, c, p, r))) {
                return false;
            }
        }
        return true;
    }

    public Particle moveGivenParticle(Particle moving, double dx, double dy) throws NoSuchFieldException {
        double x0 = moving.getXValue() - dx;
        double y0 = moving.getYValue() - dy;
        double x1 = moving.getXValue() + dx;
        double y1 = moving.getYValue() + dy;
        double r = moving.retrieveR();
        Square FirstSquare = initilizeFirstSquare(x0, y0, x1, y1, r);
        ArrayDeque<Square> AcceptedList = new ArrayDeque<Square>();
        ArrayDeque<Square> YellowList = new ArrayDeque<Square>();
        if (FirstSquare.doesIntersectSomeone()) {
            YellowList.add(FirstSquare);
        } else {
            AcceptedList.add(FirstSquare);
        }
        while (!YellowList.isEmpty()) {

            Square iterating = YellowList.pollFirst();
            int depth = iterating.returnDepth();
            if (!iterating.doesIntersectSomeone()) {
                AcceptedList.add(iterating);
            } else if (depth < this.ApproXDepth) {
                double midx = average(iterating.returnx0(), iterating.returnx1());
                double midy = average(iterating.returny0(), iterating.returny1());
                Square first = new Square(x0, y0, midx, midy, depth + 1);
                Square second = new Square(midx, y0, x1, midy, depth + 1);
                Square third = new Square(x0, midy, midx, y1, depth + 1);
                Square fourth = new Square(midx, midy, x1, y1, depth + 1);
                Wall NorthOne = new Wall();
                Wall NorthTwo = new Wall();
                Wall EastOne = new Wall();
                Wall EastTwo = new Wall();
                Wall WestOne = new Wall();
                Wall WestTwo = new Wall();
                Wall SouthOne = new Wall();
                Wall SouthTwo = new Wall();
                Wall CenterNorth = new Wall();
                Wall CenterSouth = new Wall();
                Wall CenterEast = new Wall();
                Wall CenterWest = new Wall();

                first.setNorth(NorthOne);
                first.setSouth(CenterWest);
                first.setEast(CenterNorth);
                first.setWest(WestOne);

                second.setNorth(NorthTwo);
                second.setSouth(CenterEast);
                second.setEast(EastOne);
                second.setWest(CenterNorth);

                third.setNorth(CenterWest);
                third.setSouth(SouthOne);
                third.setEast(CenterSouth);
                third.setWest(WestTwo);

                fourth.setNorth(CenterEast);
                fourth.setSouth(SouthTwo);
                fourth.setEast(EastTwo);
                fourth.setWest(CenterSouth);

                SquareIteration(first, r);
                SquareIteration(second, r);
                SquareIteration(third, r);
                SquareIteration(fourth, r);

                if (first.doesIntersectSomeone()) {

                } else {

                }
                if (second.doesIntersectSomeone()) {

                } else {

                }
                if (third.doesIntersectSomeone()) {

                } else {

                }
                if (fourth.doesIntersectSomeone()) {

                } else {

                }
            }
        }

        return generateRandomMovement(moving, AcceptedList);
    }

    public double average(double a, double b) {
        return (a + b) / 2;

    }

    public Particle generateRandomMovement(Particle moving, ArrayDeque<Square> AcceptedList) throws NoSuchFieldException {
        double sum = computeAreaSum(AcceptedList);
        double wanted = this.random.nextDouble() * sum;
        double w = 0;
        double w2 = 0;
        Iterator<Square> iterator = AcceptedList.descendingIterator();
        while (iterator.hasNext()) {
            Square s = iterator.next();
            double area = s.Area();
            w2 = w2 + area;
            if ((w <= wanted) && (wanted <= w2)) {
                return generateRandomParticleInSquare(s, moving.retrieveR());
            }
            w = w + area;

        }
        return null;
    }

    public Particle generateRandomParticleInSquare(Square s, double r) throws NoSuchFieldException {
        double x0 = s.returnx0();
        double y0 = s.returny0();
        double x1 = s.returnx1();
        double y1 = s.returny1();
        double newX = x0 + this.random.nextDouble() * (x1 - x0);
        double newY = y0 + this.random.nextDouble() * (y1 - y0);
        Particle newParticle = new Particle(newX, newY, r);
        return newParticle;
    }

    public double computeAreaSum(ArrayDeque<Square> Accepted) {
        double sum = 0;
        Iterator<Square> iterator = Accepted.descendingIterator();
        while (iterator.hasNext()) {
            Square s = iterator.next();
            sum = sum + s.Area();
        }
        return sum;

    }

    // We assume that y0 <= y1 and x0 <= x1
    //! This method is currently unused
    public boolean lineSegmentAndCircleIntersection(double x0a, double x1a, double y0a, double y1a, Particle p) {
        double y0 = Math.min(y0a, y1a);
        double y1 = Math.max(y0a, y1a);
        double x0 = Math.min(x0a, x1a);
        double x1 = Math.max(x0a, x1a);
        double Cx = p.getXValue();
        double Cy = p.getYValue();
        double r = p.retrieveR();
        if (x0 == x1) {
            double disc = r * r - (x0 - Cx) * (x0 - Cx);
            if (disc < 0) {
                return false;
            } else {
                double ysol1 = Math.sqrt(disc) + Cy;
                double ysol2 = -Math.sqrt(disc) + Cy;
                if ((ysol1 <= y1) && (ysol1 >= y0)) {
                    return true;
                }
                return (ysol2 <= y1) && (ysol2 >= y0);
            }
        } else if (y0 == y1) {
            double disc = r * r - (y0 - Cy) * (y0 - Cy);
            if (disc < 0) {
                return false;
            } else {
                double xsol1 = Math.sqrt(disc) + Cx;
                double xsol2 = -Math.sqrt(disc) + Cx;
                if ((xsol1 <= x1) && (xsol1 >= x0)) {
                    return true;
                }
                return (xsol2 <= x1) && (xsol2 >= x0);
            }
        }

        return false;
    }

}
