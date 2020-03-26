package com.pramodrao.algorithms.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        Board board;
        int moves;
        SearchNode previous;

        SearchNode(Board b, int m, SearchNode p) {
            this.board = b;
            this.moves = m;
            this.previous = p;
        }

        public int compareTo(SearchNode that) {
            int thisPriority = this.board.manhattan() + this.moves;
            int thatPriority = that.board.manhattan() + that.moves;
            if (thisPriority > thatPriority) return 1;
            else if (thisPriority < thatPriority) return -1;
            else return 0;
        }
    }

    private int numMoves = 0;
    private Board lastBoard;
    private boolean isSolvable = false;
    private Stack<Board> solutionSet;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (null == initial) throw new NullPointerException("Board cannot be null.");
        solutionSet = new Stack<Board>();
        MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
        queue.insert(new SearchNode(initial, 0, null));

        Board shuffled = initial.twin();
        MinPQ<SearchNode> shuffledQueue = new MinPQ<SearchNode>();
        shuffledQueue.insert(new SearchNode(shuffled, 0, null));

        while (!queue.min().board.isGoal() && !shuffledQueue.min().board.isGoal()) {
            SearchNode sn = queue.delMin();
            for (Board b : sn.board.neighbors()) {
//                StdOut.println("Neighbour: " +b.toString());
                if (null == sn.previous || !sn.previous.board.equals(b)) {
                    queue.insert(new SearchNode(b, sn.moves+1, sn));
                }
            }
//            StdOut.println("\n\n");

            SearchNode snShuffled = shuffledQueue.delMin();
            for (Board b : snShuffled.board.neighbors()) {
                if (null == snShuffled.previous || !snShuffled.previous.board.equals(b)) {
                    shuffledQueue.insert(new SearchNode(b, snShuffled.moves+1, snShuffled));
                }
            }
        }

        SearchNode minNode = queue.min();
        if (minNode.board.isGoal()) isSolvable = true;

        if (isSolvable) {
            while (null != minNode.previous) {
                solutionSet.push(minNode.board);
                minNode = minNode.previous;
            }
            solutionSet.push(minNode.board);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.isSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (isSolvable()) {
            return solutionSet.size()-1;
        }
        return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (isSolvable()) return solutionSet;
        return null;
    }


    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
//        In in = new In(args[0]);
        In in = new In("/Users/pramod.rao/Desktop/Personal/git/algorithms/src/main/resources/8puzzle/puzzle3x3-unsolvable1.txt");
//        In in = new In("/Users/pramod.rao/Desktop/Personal/git/algorithms/src/main/resources/8puzzle/puzzle01.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board.toString());
        }
    }
}