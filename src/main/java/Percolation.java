package com.company;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;
    private int openSites;
    int n;
    private WeightedQuickUnionUF WeightedQuickUnion;



    public Percolation(int N) { // create N-by-N grid, with all sites initially blocked
        if (N <= 0){
            throw new java.lang.IllegalArgumentException("N must be >= 0");
        }
        grid = new boolean[N*N];  // create a grid array of n * n size
        n = N;
        openSites = 0;
        WeightedQuickUnion = new WeightedQuickUnionUF((N * N));
    }

    private void indexCheck(int row, int col) {
        if (row < 0 || row > this.n || col < 0 || col > this.n){
            throw new java.lang.IndexOutOfBoundsException("Add error message here");
        }
    }

    public void open(int row, int col) { // open the site (row, col) if it is not open already
        indexCheck(row, col);
        grid[(row * 10) + col] = true;
        // connects to other adjacent open sites
        if (row != 0) { // if not top row
            if (isOpen(row - 1, col)) { // Checking above
                WeightedQuickUnion.union(gridIndex(row, col), gridIndex(row - 1, col));
            }
        }
        if (row != n) { // if not bottom row
            if (isOpen(row + 1, col)) { // Checking below
                WeightedQuickUnion.union(gridIndex(row, col), gridIndex(row + 1, col));
            }
        }
        if (col != 0) { // if not leftmost col
            if (isOpen(row, col - 1)) { // Checking left
                WeightedQuickUnion.union(gridIndex(row, col), gridIndex(row, col - 1));
            }
        }
        if (col != n) { // if not most rightmost col
            if (isOpen(row, col + 1)) { // Checking right
                WeightedQuickUnion.union(gridIndex(row, col), gridIndex(row, col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        indexCheck(row, col);
        return grid[gridIndex(row, col)];
    }

    private int gridIndex(int row, int col){
        return (row * n) + col;
    }

    public boolean isFull(int row, int col){ // is the site (row, col) full?
        indexCheck(row, col);
        if (isOpen(row, col)){ // first check if site is open, which is required to be full
            for (int top = 0; top<n; top++){
                if (WeightedQuickUnion.connected(gridIndex(row, col), top)){ // checks if site is connected to any of the top sites
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites(){ // number of open sites
        return openSites;
    }

    public boolean percolates(){ // does the system percolate?
        if (n <= openSites) { // checks that open sites are at least n, as percolation is impossible with less
            for (int top = 0; top < this.n; top++){ // for each top site
                for (int bottom = 0; bottom < this.n; top++){
                    if (WeightedQuickUnion.connected(gridIndex(0, top), gridIndex(n - 1, bottom))){
                        // checks if one of the top sites is connected to one of them bottom sites, indicating percolation
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) { // unit testing
        // write your code here
        Percolation percolation = new Percolation(100);
        int distinct_counter = 0;
        // we're going to utilize a list of booleans and flip each index if they have appeared
        while (! percolation.percolates()){
            StdOut.println(distinct_counter);
            int row = StdRandom.uniform(percolation.n - 1);
            int col = StdRandom.uniform(percolation.n - 1); // get random number from 0 to N
            if (!percolation.isOpen(row, col)){  // if the index is false, meaning we haven't visited this index before
                percolation.open(row, col); // flip the boolean so we don't count it twice
                distinct_counter++;  // increment the unique counter
            }

        }
        //StdOut.println(distinct_counter);
    }
}
