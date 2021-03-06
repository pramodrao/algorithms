package com.pramodrao.algorithms.week5;


import edu.princeton.cs.algs4.*;

/**
 * @author pramod.rao
 */
public class KdTree {

    private Node root;
    private int size;
    private Node closest;

    /**
     * construct an empty set of points
     */
    public KdTree() {
        root = null;
        size = 0;
    }

    /**
     * is the set empty?
     * @return
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * number of points in the set
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add the point to the set (if it is not already in the set)
     * @param p
     */
    public void insert(Point2D p) {
        if (null == p) throw new IllegalArgumentException("Null Point.");
        if ( contains(p)) return;
        root = insert(p, root, true);
    }

    private Node insert(Point2D p, Node parent, boolean isVertical) {

        if (null == parent) {
            Node n = new Node(p);
            n.isVertical = isVertical;
            size++;
            return n;
        }

        if (isVertical) {
            if (p.x() > parent.point.x()) {
                parent.right = insert(p, parent.right, false);
            } else {
                parent.left = insert(p, parent.left, false);
            }
        } else {
            if (p.y() > parent.point.y()) {
                parent.right = insert(p, parent.right, true);
            } else {
                parent.left = insert(p, parent.left, true);
            }
        }

        return parent;
    }

    /**
     * does the set contain point p?
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        if (null == p) throw new IllegalArgumentException("Null Point.");
        if (null == root) return false;
        return contains(p, root, root.isVertical);
    }

    private boolean contains(Point2D p, Node parent, boolean isVertical) {
        if (null == parent) return false;
        if ( p.equals(parent.point)) return true;

        if (isVertical) {
            if (p.x() > parent.point.x())
                return contains(p, parent.right, false);
            else return contains(p, parent.left, false);
        } else {
            if (p.y() > parent.point.y())
                return contains(p, parent.right, true);
            else return contains(p, parent.left, true);
        }
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        if (null == root) return;
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.02);
        draw(root);
        StdDraw.show();
    }

    private void draw(Node n) {
        if (null == n) return;
        StdDraw.point(n.point.x(), n.point.y());
        draw(n.left);
        draw(n.right);
    }


    /**
     * all points that are inside the rectangle (or on the boundary)
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> pointsInRange = new SET<Point2D>();
        range(root, rect, pointsInRange);
        return pointsInRange;
    }

    private void range(Node n, RectHV rect, SET<Point2D> pointsInRange) {
        if (null == n) return;

        if (n.isVertical) {
            if (n.point.x() < rect.xmin())
                range(n.right, rect, pointsInRange);
            else if (n.point.x() > rect.xmax())
                range(n.left, rect, pointsInRange);
            else {
                range(n.left, rect, pointsInRange);
                range(n.right, rect, pointsInRange);
                if(rect.contains(n.point))
                    pointsInRange.add(n.point);
            }
        } else {
            if (n.point.y() < rect.ymin())
                range(n.right, rect, pointsInRange);
            else if (n.point.y() > rect.ymax())
                range(n.left, rect, pointsInRange);
            else {
                range(n.left, rect, pointsInRange);
                range(n.right, rect, pointsInRange);
                if (rect.contains(n.point))
                    pointsInRange.add(n.point);
            }
        }
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (null == root) return null;
        if (null == p) throw new IllegalArgumentException("Null Point.");
        nearest(p, root);
        return closest.point;
    }

    private void nearest(Point2D p, Node current) {
        if (null == current) return;
        if (null == closest) closest = current;

        else {
            int c = p.distanceToOrder().compare(current.point, closest.point);
            if (c == -1) {
                closest = current;
            }
        }
        // Go towards the query point
        if (current.isVertical) {
            if (p.x() > current.point.x()) {
                nearest(p, current.right);
            } else {
                nearest(p, current.left);
            }
        } else {
            if (p.y() > current.point.y()) {
                nearest(p, current.right);
            } else {
                nearest(p, current.left);
            }
        }
    }

    private class Node {
        Point2D point;
        Node left;
        Node right;
        boolean isVertical;

        Node(Point2D p){
            point = p;
            left = null;
            right = null;
            isVertical = true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(isVertical).append(", ").append(point.toString());
            if (null != left) sb.append("\nLEFT: ").append(left.toString());
            if (null != right) sb.append("\nRIGHT: ").append(right.toString());

            return sb.toString();
        }
    }

    public static void main(String[] args) {
    }
}
