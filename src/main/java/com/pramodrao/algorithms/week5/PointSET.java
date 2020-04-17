package com.pramodrao.algorithms.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author pramod.rao
 */
public class PointSET {

    final private SET<Point2D> pointL;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        pointL = new SET<Point2D>();
    }

    /**
     * is the set empty?
     * @return
     */
    public boolean isEmpty() {
        return pointL.isEmpty();
    }

    /**
     * number of points in the set
     * @return
     */
    public int size() {
        return pointL.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     * @param p
     */
    public void insert(Point2D p) {
        if (null == p) throw new IllegalArgumentException("Null Point.");
        pointL.add(p);
    }

    /**
     * does the set contain point p?
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (null == p) throw new IllegalArgumentException("Null Point.");
        return pointL.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.02);
        for (Point2D p : pointL) {
            StdDraw.point(p.x(), p.y());
        }
        StdDraw.show();
    }

    /**
     * all points that are inside the rectangle (or on the boundary)
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) throw new IllegalArgumentException("Null Rectange.");
        SET<Point2D> intersectingPoints = new SET<Point2D>();
        for (Point2D p : pointL) {
            if (rect.contains(p)) intersectingPoints.add(p);
        }
        return intersectingPoints;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (null == p) throw new IllegalArgumentException("Null Point.");
        if (isEmpty()) return null;

        Point2D nearestPoint = null;
        double nearestDist = Double.MAX_VALUE;

        for (Point2D p1 : pointL) {
            double d = p.distanceSquaredTo(p1);
            if (d < nearestDist) {
                nearestPoint = p1;
                nearestDist = d;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
    }
}