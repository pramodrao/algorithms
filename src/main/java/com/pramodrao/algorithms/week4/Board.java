package com.pramodrao.algorithms.week4;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pramod.rao
 */
public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int dim;
    private int[][] tiles;
    private int zeroRow = -1;
    private int zeroCol = -1;

    public Board(int[][] boardTiles) {
        dim = boardTiles.length;
        tiles = new int[dim][];
        for (int row = 0; row < dim; row++) {
            tiles[row] = new int[boardTiles[row].length];
            System.arraycopy(boardTiles[row], 0, tiles[row], 0, tiles[row].length);
        }

        for ( int row = 0; row < dim; row++ ) {
            for ( int col = 0; col < dim; col++ ) {
                if ( tiles[row][col] == 0) {
                    zeroCol = col;
                    zeroRow = row;
                    break;
                }
            }
        }
    }

    private void swap(int row1, int col1, int row2, int col2, int[][] tiles) {
        int temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dim).append('\n');
        for ( int row = 0; row < dim; row++ ) {
            for ( int col = 0; col < dim; col++ ) {
                sb.append(tiles[row][col]);
                if ( col < dim -1) sb.append(' ');
            }
            if ( row < dim -1) sb.append('\n');
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return dim;
    }

    // number of tiles out of place
    public int hamming() {
        int distance = 0;
        for ( int row = 0; row < dim; row++ ) {
            for ( int col = 0; col < dim; col++ ) {
                int element = tiles[row][col];
                if (element == 0) continue;
                if (element != (dim*row) + (col + 1)) distance++;
            }
        }
        return distance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int distance = 0;
        for ( int row = 0; row < dim; row++ ) {
            for ( int col = 0; col < dim; col++ ) {
                int element = tiles[row][col];
                if (element == 0) continue;
                int elementCol = element/dim;
                int elementRow = element % dim;
                distance += Math.abs((elementRow - row) + (elementCol - col));
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {

        boolean isGoalBoard = true;
        for (int row = 0; row < dim; row++)
            for (int col = 0; col < dim; col++) {
                int num = tiles[row][col];
//                StdOut.print(num +", " +((dim*row) + (col + 1)) +"\t");
                if ( num != 0 && num != (dim*row) + (col + 1)) {
                    isGoalBoard = false;
                    break;
                }
            }

//        StdOut.println("Checking Board: " +this.toString() +"\t" +isGoalBoard);
        return isGoalBoard;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if ( this.toString().equals(y.toString())) return true;
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                return new BoardIterator();
            }
        };
    }

    private class BoardIterator implements Iterator<Board> {

        Board[] boards;
        int numBoards = 0;

        public BoardIterator() {
            boards = new Board[4];
            if (zeroCol + 1 < dim) {
                int[][] bt = getCopy(tiles);
                bt[zeroRow][zeroCol] = bt[zeroRow][zeroCol+1];
                bt[zeroRow][zeroCol+1] = 0;
                boards[numBoards] = new Board(bt);
                numBoards++;
            }
            if (zeroCol - 1 >= 0) {
                int[][] bt = getCopy(tiles);
                bt[zeroRow][zeroCol] = bt[zeroRow][zeroCol-1];
                bt[zeroRow][zeroCol-1] = 0;
                boards[numBoards] = new Board(bt);
                numBoards++;
            }
            if (zeroRow + 1 < dim) {
                int[][] bt = getCopy(tiles);
                bt[zeroRow][zeroCol] = bt[zeroRow+1][zeroCol];
                bt[zeroRow+1][zeroCol] = 0;
                boards[numBoards] = new Board(bt);
                numBoards++;
            }
            if (zeroRow - 1 >= 0) {
                int[][] bt = getCopy(tiles);
                bt[zeroRow][zeroCol] = bt[zeroRow-1][zeroCol];
                bt[zeroRow-1][zeroCol] = 0;
                boards[numBoards] = new Board(bt);
                numBoards++;
            }
        }

        public boolean hasNext() {
            return (numBoards > 0);
        }

        public Board next() {
            if (hasNext()) {
                return boards[--numBoards];
            } else
                throw new NoSuchElementException("No more boards left.");
        }

        public void remove() {
            throw new UnsupportedOperationException("This operation is not supported.");
        }

        private int[][] getCopy(int[][] orig) {
            int[][] bt = new int[orig.length][];
            for (int row = 0; row < orig.length; row++) {
                bt[row] = new int[orig[row].length];
                System.arraycopy(orig[row], 0, bt[row], 0, bt[row].length);
            }
            return bt;
        }
    }
//    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tileCopy = new int[tiles.length][];
        for (int row = 0; row < dim; row++) {
            tileCopy[row] = new int[tiles[row].length];
            System.arraycopy(tiles[row], 0, tileCopy[row], 0, tileCopy[row].length);
        }

        if (tileCopy[0][0] != 0 && tileCopy[0][1] != 0)
            swap(0, 0, 0, 1, tileCopy);
        else
            swap(1, 0, 1, 1, tileCopy);

        return new Board(tileCopy);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles1 = new int[3][3];
        String tileNumbers = "8,1,3,4,0,2,7,6,5";
        int begin = 0;
        int end = tileNumbers.indexOf(',');
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                if ( end < 0 )
                    tiles1[i][j] = Integer.parseInt(tileNumbers.substring(begin));
                else
                    tiles1[i][j] = Integer.parseInt(tileNumbers.substring(begin, end));
                begin = end+1;
                end = tileNumbers.indexOf(',', end+1);
            }
        }

//        int[][] tiles2 = new int[3][3];
        Board board = new Board(tiles1);
        StdOut.println("Manhattan Distance: " +board.manhattan());
        StdOut.println("Hamming Distance: " +board.hamming());

        tileNumbers = "0,8,1,3,4,2,7,6,5";
        begin = 0;
        end = tileNumbers.indexOf(',');
//        Arrays.fill(tiles1, 0);

        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                if ( end < 0 )
                    tiles1[i][j] = Integer.parseInt(tileNumbers.substring(begin));
                else
                    tiles1[i][j] = Integer.parseInt(tileNumbers.substring(begin, end));
                begin = end+1;
                end = tileNumbers.indexOf(',', end+1);
            }
        }

        Board board1 = new Board(tiles1);
        StdOut.println("Manhattan Distance: " +board1.manhattan());
        StdOut.println("Hamming Distance: " +board1.hamming());

        StdOut.println(board.equals(board1));

        StdOut.println(board.toString());
        StdOut.println("Neighbours: ");
        Iterator<Board> iter = board.neighbors().iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next().toString());
        }

//        StdOut.println(board1.toString());
//        StdOut.println("Neighbours: ");
//        Iterator<Board> iter1 = board1.neighbors().iterator();
//        while (iter1.hasNext()) {
//            StdOut.println(iter1.next().toString());
//        }
    }

}