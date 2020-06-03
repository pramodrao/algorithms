package com.pramodrao.dsajune20;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author pramod.rao
 */
public class TwoCityScheduling {
    public static int twoCitySchedCost(int[][] costs) {

        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] A, int[] B) {
                return (A[0] - A[1]) - (B[0] - B[1]);
            }
        });


        int res = 0; int len = costs.length/2;
        for (int i = 0; i < len; i++) {
            res += costs[i][0];
        }
        for (int i = len; i < costs.length; i++) {
            res += costs[i][1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{{10,20},{30,200},{400,50},{30,20}};
        System.out.println(twoCitySchedCost(costs));
    }
}
