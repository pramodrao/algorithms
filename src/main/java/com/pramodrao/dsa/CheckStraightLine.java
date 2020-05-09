package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class CheckStraightLine {
    public static boolean checkStraightLine(int[][] coordinates) {

        if (null == coordinates) return true;
        int len = coordinates.length;
        if (len < 3) return true;

        double lineSlope = getSlope(coordinates[0], coordinates[1]);
        for (int i = 2; i < len; i++) {
            double slope = getSlope(coordinates[i-1], coordinates[i]);
            if (slope != lineSlope) return false;
        }
        return true;
    }

    private static double getSlope(int[] p1, int[] p2) {
        int yDiff = p1[1] - p2[1];
        int xDiff = p1[0] - p2[0];

        if ( yDiff == 0 && xDiff == 0 ) return Double.NEGATIVE_INFINITY;
        if ( xDiff == 0 ) return Double.POSITIVE_INFINITY;
        if ( yDiff == 0 ) return 0.0;

        return ((double)yDiff)/xDiff;

    }

    public static void main(String[] args) {
        int[][] coordinates = new int[][]{{1,2},{2,3},{3,4},{4,5},{5,7},{6,7}};
        System.out.println(checkStraightLine(coordinates));
    }
}
