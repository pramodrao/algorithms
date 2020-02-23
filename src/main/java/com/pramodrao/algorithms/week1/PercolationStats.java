package com.pramodrao.algorithms.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author pramod.rao
 */
public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96d;
    private final double[] openSiteFractions;
    private double mean = Double.NaN;
    private double stddev = Double.NaN;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n should be greater than 0. Number of trials should be greater than 0.");

        openSiteFractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            openSiteFractions[i] = (double)percolation.numberOfOpenSites() / (double)(n * n);

        }
    }

    // sample mean of percolation threshold
    public double mean() {
        if (Double.isNaN(mean))
            mean =  StdStats.mean(openSiteFractions);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (Double.isNaN(stddev))
            stddev = StdStats.stddev(openSiteFractions);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

        int numTrials = openSiteFractions.length;
        return (mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(numTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        int numTrials = openSiteFractions.length;
        return (mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(numTrials)));
    }

    // test client (see below)
    public static void main(String[] args) {

        if (args.length < 2)
            throw new IllegalArgumentException("Improper Arguments. Please enter values of n and T.");

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}