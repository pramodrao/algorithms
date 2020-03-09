package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author pramod.rao
 */
public class FastCollinearPoints {

    private LineSegment[] collinearSegments;


    /**
     * finds all line segments containing 4 or more points
     * @param data array of points in which to find collinear ones
     */
    public FastCollinearPoints(Point[] data) {
        if (null == data) throw new IllegalArgumentException("No arguments passed.");

        int numPoints = data.length;
        for (int i = 0; i < numPoints; i++) {
            if (null == data[i]) throw new IllegalArgumentException("Null point.");
        }

        Point[] points = new Point[numPoints];
        System.arraycopy(data, 0, points, 0, numPoints);
        Arrays.sort(points);

        for (int i = 0; i < numPoints; i++) {
            if (i > 0 && points[i - 1].compareTo(points[i]) == 0)
                throw new IllegalArgumentException("Duplicate points.");
        }


        LineSegment[] sampleSet = new LineSegment[numPoints*numPoints];
        int index = 0;
        for (int i = 0; i < numPoints-1; i++) {

            Arrays.sort(points, i+1, numPoints-1, points[i].slopeOrder());
            double firstSlope = points[i].slopeTo(points[i+1]);
            int pointsInLine = 0;
            for (int j = i+1; j < numPoints; j++) {
                double slope = points[i].slopeTo(points[j]);
                if (slope == firstSlope) pointsInLine++;
                else {
                    if (pointsInLine >= 4) {
                        Point[] line = new Point[pointsInLine];
                        line[0] = points[i];
                        for ( int k = 1; k < pointsInLine; k++ ) {
                            line[k] = points[k+j-pointsInLine];
                        }
                        Arrays.sort(line);
                        sampleSet[index++] = new LineSegment(line[0], line[pointsInLine-1]);
                    }
                    firstSlope = slope;
                    pointsInLine = 2;
                }
            }
            if (pointsInLine >= 4) {
                Point[] line = new Point[pointsInLine];
                line[0] = points[i];
                for ( int k = 1; k < pointsInLine; k++ ) {
                    line[k] = points[k+numPoints-pointsInLine];
                }
                Arrays.sort(line);
                sampleSet[index++] = new LineSegment(line[0], line[pointsInLine-1]);
            }
        }

        collinearSegments = new LineSegment[index];
        System.arraycopy(sampleSet, 0, collinearSegments, 0, index);
    }

    /**
     * the number of line segments
     * @return the number of collinear line segments
     */
    public int numberOfSegments() {
        return collinearSegments.length;
    }

    /**
     * the line segments
     * @return array of collinear line segments
     */
    public LineSegment[] segments() {
        LineSegment[] returnData = new LineSegment[collinearSegments.length];
        System.arraycopy(collinearSegments, 0, returnData, 0, collinearSegments.length);
        return returnData;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("/Users/pramod.rao/Desktop/Personal/git/algorithms/src/main/resources/input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.02);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdDraw.setPenRadius(0.002);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}