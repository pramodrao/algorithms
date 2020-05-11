package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class FloodFill {

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int rowLen = image.length - 1;
        int colLen = image[0].length - 1;
        int origColor = image[sr][sc];
        image[sr][sc] = newColor;
        colorNeighbours(image, sr, sc, rowLen, colLen, origColor, newColor);
        return image;
    }

    private static void colorNeighbours(int[][] image, int row, int col,
                                        int rowLen, int colLen, int origColor, int newColor) {
        int topColor, leftColor, rightColor, bottomColor;
        topColor = (row == 0) ? -1 : image[row-1][col];
        leftColor = (col == 0) ? -1 : image[row][col-1];
        bottomColor = (row == rowLen) ? -1 : image[row+1][col];
        rightColor = (col == colLen) ? -1 : image[row][col+1];

        if (topColor != -1 && topColor != newColor && topColor == origColor) {
            image[row-1][col] = newColor;
            colorNeighbours(image, row-1, col, rowLen, colLen, origColor, newColor);
        }
        if (leftColor != -1 && leftColor != newColor && leftColor == origColor) {
            image[row][col-1] = newColor;
            colorNeighbours(image, row, col-1, rowLen, colLen, origColor, newColor);
        }
        if (bottomColor != -1 && bottomColor != newColor && bottomColor == origColor) {
            image[row+1][col] = newColor;
            colorNeighbours(image, row+1, col, rowLen, colLen, origColor, newColor);
        }
        if (rightColor != -1 && rightColor != newColor && rightColor == origColor) {
            image[row][col+1] = newColor;
            colorNeighbours(image, row, col+1, rowLen, colLen, origColor, newColor);
        }
    }

    public static void main(String[] args) {
        int[][] image = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        int sr = 1, sc = 1, newColor = 2;

        int[][] newImage = floodFill(image, sr, sc, newColor);

        for (int i = 0; i < newImage.length; i++) {
            int[] row = newImage[i];
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] +",");
            }
            System.out.println();
        }
    }
}
