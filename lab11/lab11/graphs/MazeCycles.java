package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    public  boolean isCycled;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        isCycled = false;
        edgeTo[0] = 0;
        distTo[0] = 0;
        detectCycle(0);
    }

    // Helper methods go here
    public void detectCycle(int v) {
        marked[v] = true;
        announce();

        if (isCycled) return;

        for(int w : maze.adj(v)) {
            if(!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                detectCycle(w);
            }else if(marked[w] && w != edgeTo[v]) {
                isCycled = true;
            }
        }
    }
}

