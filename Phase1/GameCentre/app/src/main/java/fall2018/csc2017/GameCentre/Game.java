package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class Game implements Serializable {
    private int numTiles;
    private long maxScore;
    private static int numGames = -1;
    private int gameId;
    private int numUndos;

    public Game(int numTiles) {
        this.numTiles = numTiles;
        this.maxScore = 1;
        this.gameId = this.numGames + 1;
    }

    public int getNumTiles() {
        return numTiles;
    }

    public void setNumTiles(int numTiles) {
        this.numTiles = numTiles;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }

    public int getGameId() {
        return gameId;
    }

    public int getNumUndos() {
        return numUndos;
    }
}
