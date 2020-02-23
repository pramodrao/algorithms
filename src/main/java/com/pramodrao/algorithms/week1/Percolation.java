package com.pramodrao.algorithms.week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author pramod.rao
 */
public class Percolation {

    private final int length;
    private final int topPlaceholder;
    private final int bottomPlaceholder;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufIsFull;
    private final boolean[] openStatus;
    private final boolean[] fullStatus;
    private int openSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Must be greater than 0.");
        length = n;
        int components = length * length;
        topPlaceholder = components;
        bottomPlaceholder = components + 1;
        uf = new WeightedQuickUnionUF(components + 2);
        ufIsFull = new WeightedQuickUnionUF(components + 1);
        openStatus = new boolean[components];
        fullStatus = new boolean[components];
        for (int i = 0; i < components; i++) {
            openStatus[i] = false;
            fullStatus[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        int componentIndex = getComponentIndex(row, col);

        if (openStatus[componentIndex]) return;

        openStatus[componentIndex] = true;
        openSites += 1;

        // If adjacent are open connect them
        // If row == 1 and col == 1 connect them to the top
        // If row == length and col == length connect them to the bottom
        if (row == 1 || row == length) {

            if (row == 1) {
                uf.union(topPlaceholder, componentIndex);
                ufIsFull.union(topPlaceholder, componentIndex);
            }

            if (row == length) {
                uf.union(bottomPlaceholder, componentIndex);
            }
        }

        int[][] adjacentSites = findAdjacentSites(row, col);
        int adjacentSitesLength = adjacentSites.length;
        for (int i = 0; i < adjacentSitesLength; i++) {
            int[] site = adjacentSites[i];
            int adjacentRow = site[0];
            int adjacentCol = site[1];
            if (adjacentRow < 1 || adjacentRow > length || adjacentCol < 1 || adjacentCol > length) continue;
            int siteComponentIndex = getComponentIndex(adjacentRow, adjacentCol);
            if (isOpen(site[0], site[1])) {
                if (!(uf.find(componentIndex) == uf.find(siteComponentIndex))) {
                    uf.union(componentIndex, siteComponentIndex);
                }
                if (!(ufIsFull.find(componentIndex) == ufIsFull.find(siteComponentIndex))) {
                    ufIsFull.union(componentIndex, siteComponentIndex);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int componentIndex = getComponentIndex(row, col);
        return openStatus[componentIndex];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int componentIndex = getComponentIndex(row, col);
        if ( fullStatus[componentIndex] ) return true;
        else {
            boolean status = (ufIsFull.find(componentIndex) == ufIsFull.find(topPlaceholder));
            fullStatus[componentIndex] = status;
            return status;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (uf.find(bottomPlaceholder) == uf.find(topPlaceholder));
    }

    private int[][] findAdjacentSites(int row, int col) {

        return new int[][]{
                {row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}
        };
    }

    private int getComponentIndex(int row, int col) {
        if (row < 1 || row > length || col < 1 || col > length)
            throw new IllegalArgumentException("Row/Col value should be between 1 and " + length + ". Given: " + row + ", " + col);

        return ((row - 1) * length + col) - 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        StdOut.println("Enter number of elements");
        int n = Integer.parseInt(StdIn.readLine());
        Percolation percolation = new Percolation(n);
        StdOut.println(percolation.numberOfOpenSites());
        StdOut.println("Next Command");

        while (true) {
            String[] e = StdIn.readLine().split(",");
            if (e[0].equalsIgnoreCase("open")) {
                percolation.open(Integer.parseInt(e[1]), Integer.parseInt(e[2]));
                StdOut.println("Next Command");
            } else if (e[0].equalsIgnoreCase("isopen")) {
                StdOut.println(percolation.isOpen(Integer.parseInt(e[1]), Integer.parseInt(e[2])));
            } else if (e[0].equalsIgnoreCase("isfull")) {
                StdOut.println(percolation.isFull(Integer.parseInt(e[1]), Integer.parseInt(e[2])));
            } else if (e[0].equalsIgnoreCase("percolates")) {
                StdOut.println(percolation.percolates());
            } else {
                StdOut.println("Bye Bye!!");
                break;
            }
        }
    }
}
