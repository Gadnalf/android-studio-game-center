package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class SlidingTileSettings implements Serializable {
    private int numTiles;
    private long maxScore;
    private int numUndos;
    private String maxScoreSetBy;

    public SlidingTileSettings(int numTiles, int numUndos) {
        this.numTiles = numTiles;
        this.maxScore = 1;
        this.numUndos = numUndos;
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

    public void setMaxScore(long maxScore, String userName) {

        this.maxScore = maxScore;
        this.maxScoreSetBy = userName;
    }

    public String getGameId() {
        String gameId = "num_tiles_" + Integer.toString(numTiles) + "num_undos_" + Integer.toString(numUndos);
        return gameId;
    }

    public int getNumUndos() {
        return numUndos;
    }

    public void setNumUndos(int numUndos) {

        this.numUndos = numUndos;
    }

    public String getMaxScoreSetBy() {
        return this.maxScoreSetBy;
    }
}
