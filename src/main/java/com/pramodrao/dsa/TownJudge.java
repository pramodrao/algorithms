package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class TownJudge {
    public static int findJudge(int N, int[][] trust) {

        if (N == 1) return 1;

        int[] trusts = new int[N+1];
        int[] trustees = new int[N+1];

        int i = 0, len = trust.length-1;
        while (i <= len) {
            trusts[trust[i][0]] += 1;
            trustees[trust[i][1]] += 1;
            i++;
        }

        for (i = 1; i <= N; i++) {
            if (trusts[i] == 0 && trustees[i] == N-1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[][] trust = new int[][]{{1,2}};
//        int N = 2;

//        int[][] trust = new int[][]{{1,3}, {2,3}};
//        int N = 3;

//        int[][] trust = new int[][]{{1,3}, {2,3}, {3,1}};
//        int N = 3;

//        int[][] trust = new int[][]{{1,2}, {2,3}};
//        int N = 3;

        int[][] trust = new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}};
        int N = 4;

        System.out.println(findJudge(N, trust));
    }
}
