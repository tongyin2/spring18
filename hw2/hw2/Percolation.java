package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF ds;
    WeightedQuickUnionUF ds2;
    private int size;
    boolean[][] isopen;
    private int numOfOpenSites;

    public Percolation(int N) {
        ds = new WeightedQuickUnionUF(N * N + 2);
        ds2 = new WeightedQuickUnionUF(N*N + 1);
        size = N;
        isopen = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            ds.union(N*N, i);
            ds.union(N*N+1, N*(N-1)+i);
            ds2.union(N*N, i);
        }
        numOfOpenSites = 0;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) return;

        isopen[row][col] = true;
        int d = XYtoD(row, col);
        if(row > 0) {
            if(isOpen(row - 1, col)) {
                ds.union(d, d - size);
                ds2.union(d, d - size);
            }
        }

        if(row < size - 1) {
            if(isOpen(row + 1, col)) {
                ds.union(d, d + size);
                ds2.union(d, d + size);
            }
        }

        if(col > 0) {
            if(isOpen(row, col - 1)) {
                ds.union(d, d - 1);
                ds2.union(d, d-1);
            }
        }

        if(col < size - 1) {
            if (isOpen(row, col + 1)) {
                ds.union(d, d + 1);
                ds2.union(d, d+1);
            }
        }
        numOfOpenSites++;
    }

    public boolean isOpen(int row, int col) {
        return isopen[row][col];
    }

    public boolean isFull(int row, int col) {
        int d = XYtoD(row, col);
        return ds2.connected(d, size * size) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {

        return ds.connected(size*size, size*size + 1);
    }

    public static void main(String[] args) {

    }

    private int XYtoD(int row, int col) {
        return row * size + col;
    }

}
