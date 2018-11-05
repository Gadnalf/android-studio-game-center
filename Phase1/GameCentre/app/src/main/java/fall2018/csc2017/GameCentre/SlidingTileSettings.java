package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class SlidingTileSettings implements Serializable {
    private int numTiles;
    private long maxScore;
    private int numUndoes;
    private String maxScoreSetBy;

    public SlidingTileSettings(int numTiles, int numUndoes) {
        this.numTiles = numTiles;
        this.maxScore = 1;
        this.numUndoes = numUndoes;
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
        String gameId = "num_tiles_" + Integer.toString(numTiles) + "num_undoes_" + Integer.toString(numUndoes);
        return gameId;
    }

    public int getNumUndoes() {
        return numUndoes;
    }

    public void setNumUndoes(int numUndoes) {

        this.numUndoes = numUndoes;
    }

    public String getMaxScoreSetBy() {
        return this.maxScoreSetBy;
    }
}
