package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class SlidingTileSettings implements Serializable {
    private int boardSize;
    private double maxScore;
    private int numUndoes;
    private String maxScoreSetBy;

    public SlidingTileSettings(int boardSize, int numUndoes) {
        this.boardSize = boardSize;
        this.maxScore = 1;
        this.numUndoes = numUndoes;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore, String userName) {

        this.maxScore = maxScore;
        this.maxScoreSetBy = userName;
    }

    public String getGameId() {
        String gameId = "num_tiles_" + Integer.toString(boardSize) + "num_undoes_" + Integer.toString(numUndoes);
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
