package hw4.puzzle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Board implements WorldState{
    int[][] tiles;

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > size() - 1 || j < 0 || j > size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new ArrayDeque<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.offer(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int count = 0;
        for(int i = 0; i < size(); i++){
            for(int j = 0; j < size(); j++) {
                if(i == size() - 1 && j == size() - 1) {
                    break;
                }
                if(tileAt(i, j) != i*size() + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for(int i = 0; i < size(); i++) {
            for(int j = 0; j < size(); j++){
                int v = tileAt(i, j);
                if(v != 0) {
                    int x = (v-1) / size();
                    int y = (v-1) % size();
                    count += x - i > 0 ? x - i : i - x;
                    count += y - j > 0 ? y - j : j - y;
                }

            }
        }
        return count;
    }
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if(this == y){
            return true;
        }
        if(y == null || y.getClass()!=getClass()){
            return false;
        }
        Board other = (Board) y;
        if(other.size() != this.size()) {
            return false;
        }

        for(int i = 0; i < size(); i++){
            for (int j = 0; j < size(); j++){
                int w = tiles[i][j];
                int m = other.tiles[i][j];
                if(w!=m){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
