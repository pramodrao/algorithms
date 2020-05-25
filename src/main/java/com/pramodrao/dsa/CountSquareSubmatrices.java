package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class CountSquareSubmatrices {

    public static int countSquares(int[][] matrix) {
        if(null == matrix || matrix.length == 0) return 0;
        int rowLen = matrix.length + 1;
        int colLen = matrix[0].length + 1;

        int[][] tempMat = new int[rowLen][colLen];
        int row = 0, col = 0;
        for (row = 1; row < rowLen; row++) {
            for (col = 1; col < colLen; col++) {
                tempMat[row][col] = matrix[row-1][col-1];
            }
        }

        int sum = 0;
        for (row = 1; row < rowLen; row++) {
            for (col = 1; col < colLen; col++) {
                if (tempMat[row][col] == 1) {
                    int left = tempMat[row][col-1];
                    int diag = tempMat[row-1][col-1];
                    int top = tempMat[row-1][col];
                    int min = Math.min(diag, Math.min(left, top));
                    tempMat[row][col] = tempMat[row][col] + min;
                    sum += tempMat[row][col];
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
//        int[][] arr = new int[][]{{1, 1, 1},{1, 1, 1},{1, 1, 1}};
//        int[][] arr = new int[][]{{0, 1, 1, 1},{1, 1, 1, 1},{0, 1, 1, 1}};
        int[][] arr = new int[][]{{1, 0, 1},{1, 1, 0},{1, 1, 0}};
        System.out.println(countSquares(arr));
    }
}
