package fall2018.csc2017.GameCentre;
import java.io.Serializable;

//
//public interface GameSettings {
//    private String maxScoreSetBy;
//
//    public String getGameId();
//
//
//}

public abstract class GameSettings implements Serializable {
    private String maxScoreSetBy;
    private double maxScore;
    private int boardSize;
    private int numUndoes;

    /**
     * this is the default
     * notice if your score can be below 1, alter this default in your game settings
     */
    public GameSettings() {
        this.maxScore = 1;
    }

    public GameSettings(double startingScore) {
        this.maxScore = startingScore;
    }

    public GameSettings(int boardSize) {
        this.boardSize = boardSize;
    }

    public GameSettings(int boardSize, int numUndoes) {
        this.boardSize = boardSize;
        this.numUndoes = numUndoes;
    }


    public GameSettings(double startingScore, int boardSize) {
        this.boardSize = boardSize;
        this.maxScore = startingScore;
    }

    /**
     * should return all relevant info about this game
     * will also be used to compare games
     * ex: SlidingTiles \n num_undos={} \n board_size={}
     * @return
     */
    abstract public String getGameId();

    abstract public GameSettings copy();

    public void setMaxScore(double maxScore, String userName) {
        this.maxScore = maxScore;
        this.maxScoreSetBy = userName;
    }

    public String getMaxScoreSetBy() {
        return maxScoreSetBy;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getNumUndoes() {
        return numUndoes;
    }
}