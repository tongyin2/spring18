package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] numOfSitsToPercolate;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        numOfSitsToPercolate = new double[T];
        for(int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            int x, y;
            double count = 0;
            do {
                do {
                    int d = StdRandom.uniform(N * N);
                    x = d / N;
                    y = d % N;
                }while(per.isOpen(x, y));
                per.open(x, y);
                count++;
            }while(!per.percolates());
            numOfSitsToPercolate[i] = count / (N*N);
        }

    }
    public double mean() {
        return StdStats.mean(numOfSitsToPercolate);
    }
    public double stddev() {
        return StdStats.stddev(numOfSitsToPercolate);
    }
    public double confidenceLow() {
        return mean() - 1.96*stddev() / Math.sqrt(numOfSitsToPercolate.length);
    }
    public double confidenceHigh() {
        return mean() + 1.96*stddev() / Math.sqrt(numOfSitsToPercolate.length);
    }

    public static void main(String[] args){
        PercolationStats ps = new PercolationStats(40, 50, new PercolationFactory());
        System.out.println(ps.confidenceLow() + "  :  "+ ps.confidenceHigh());
    }
}
