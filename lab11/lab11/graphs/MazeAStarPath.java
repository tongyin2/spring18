package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {

        return Math.abs(maze.toX(s) - maze.toX(t)) + Math.abs(maze.toY(s) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        MinPQ<Integer> pq = new MinPQ<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return h(o1) - h(o2);
            }
        });

        pq.insert(s);
        while(!pq.isEmpty()) {
            int v = pq.delMin();
            marked[v] = true;
            announce();

            if(v == t) {
                break;
            }

            for(int w : maze.adj(v)) {
                if(!marked[w]) {
                    if(distTo[w] == 0 || distTo[v] + 1 < distTo[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v]+1;
                    }
                    pq.insert(w);
                }
            }
        }

    }

    @Override
    public void solve() {
        astar(s);
    }

}

