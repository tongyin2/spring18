package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    public int numOfmoves;
    public List<WorldState> list;

    public Solver(WorldState initial) {
        SearchNode first = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> pq = new MinPQ<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return o1.priority - o2.priority;
            }
        });

        numOfmoves = 0;
        list = new ArrayList<>();
        pq.insert(first);
        process(pq);

    }

    private void process(MinPQ<SearchNode> pq) {
        if(pq.isEmpty()) {
            return;
        }

        SearchNode x = pq.delMin();
        if(x.currentState.isGoal()) {
            numOfmoves = x.numOfMovesToHere;
            rebuildPath(list, x);
            return;
        }else {
            for (WorldState neighbor : x.currentState.neighbors()) {
                if(x.preSearchNode == null || !x.preSearchNode.currentState.equals(neighbor)) {
                    pq.insert(new SearchNode(neighbor, x.numOfMovesToHere+1, x));
                }
            }
        }
        process(pq);
    }

    private void rebuildPath(List<WorldState> list, SearchNode theNode) {
        while(theNode != null) {
            list.add(0, theNode.currentState);
            theNode = theNode.preSearchNode;
        }
    }

    public int moves() {
        return numOfmoves;
    }

    public Iterable<WorldState> solution() {
        return list;
    }

    class SearchNode {
        public WorldState currentState;
        public int numOfMovesToHere;
        public SearchNode preSearchNode;
        public int priority;

        public SearchNode(WorldState currentState, int numOfMovesToHere, SearchNode preSearchNode) {
            this.currentState = currentState;
            this.numOfMovesToHere = numOfMovesToHere;
            this.preSearchNode = preSearchNode;
            this.priority = currentState.estimatedDistanceToGoal() + numOfMovesToHere;
        }
    }
}
