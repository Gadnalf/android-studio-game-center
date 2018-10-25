package fall2018.csc2017.GameCentre;

import java.io.Serializable;

/**
 * used to keep track of the scores on game
 */
public class ScoreBoard implements Serializable {
    public int numMoves;
    public long startTime;

    public ScoreBoard() {
        this.numMoves = 0;
        this.startTime = System.nanoTime();
    }

    public void move() {
        this.numMoves ++;
    }

    public long getTimePlayed() {
        long endTime = System.nanoTime();
        return endTime - this.startTime;
    }

    public long getScore() {
        return (long) this.numMoves / getTimePlayed();
    }



}
