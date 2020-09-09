package com.company;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class PercolationStats {
    private double numOfTimings;
    private double[] timings;



    public PercolationStats(int N, int T) { // create N-by-N grid, with all sites initially blocked
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("N and T must be >= 0");
        }
        this.numOfTimings = T;
    }

    public double mean() {
        return StdStats.mean(this.timings);
    }

    public double stddev() {
        return StdStats.stddev(this.timings);
    }

    public double confidenceLow() {
        return mean() - (1.96 * stddev())/Math.sqrt(this.numOfTimings); // 1.96 is the z value used for a 95% confidence interval
    }

    public double confidenceHigh() {
        // Determine the confidence level and find the appropriate z*-value
        // Find the sample mean
        // Multiply z* times
        // Take lus or minus the margin of error to obtain the CI.
        //The lower end of the CI is
        //high limit = mean - marginOfError
        return mean() + (1.96 * stddev())/Math.sqrt(this.numOfTimings); // 1.96 is the z value used for a 95% confidence interval
    }



    public static void main(String[] args) { // unit testing
        // write your code here
        Percolation my_object = new Percolation(100);
        int distinct_counter = 0;
        // we're going to utilize a list of booleans and flip each index if they have appeared
    }

}
