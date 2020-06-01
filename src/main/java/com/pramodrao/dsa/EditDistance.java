package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class EditDistance {

    public static int minDistance(String word1, String word2) {

        if (null == word1 && null == word2) return 0;

        int l1 = word1.length();
        int l2 = word2.length();

        if (l1 == 0) return l2;
        if (l2 == 0) return l1;

        int[][] dp = new int[l1+1][l2+1];

        int i;
        for (i = 1; i <= l2; i++) {
            dp[0][i] = i;
        }

        for (i = 1; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    int replaceD = dp[i - 1][j - 1];
                    int insertD = dp[i][j - 1];
                    int deleteD = dp[i - 1][j];
                    dp[i][j] = 1 + Math.min(replaceD, Math.min(insertD, deleteD));
                    System.out.println(replaceD +", " +insertD +", " +deleteD +", " +dp[i][j]);
                }
            }
        }
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
//        String  word1 = "intention", word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }
}
