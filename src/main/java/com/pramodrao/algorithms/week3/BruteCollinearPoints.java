package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author pramod.rao
 */
public class BruteCollinearPoints {

    private LineSegment[] collinearSegments;

    /**
     * finds all line segments containing 4 points
     * @param data array of points in which to find collinear ones
     */
    public BruteCollinearPoints(Point[] data) {

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
        for (int p = 0; p < numPoints; p++) {
            for (int q = p+1; q < numPoints; q++) {
                double slope_pq = points[p].slopeTo(points[q]);
                for (int r = q+1; r < numPoints; r++) {
                    double slope_pr = points[p].slopeTo(points[r]);
                    if ( slope_pq == slope_pr ) {
                        for (int s = r+1; s < numPoints; s++) {
                            double slope_ps = points[p].slopeTo(points[s]);
                            if ( slope_pq == slope_ps ) {
                                LineSegment ps = new LineSegment(points[p], points[s]);
                                sampleSet[index++] = ps;
                            }
                        }
                    }
                }
            }
        }

        collinearSegments = new LineSegment[index];
        System.arraycopy(sampleSet, 0, collinearSegments, 0, index);

    }

    /**
     * the number of line segments
     * @return
     */
    public int numberOfSegments() {
        return collinearSegments.length;
    }

    /**
     * the line segments
     * @return
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
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
