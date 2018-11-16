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

    /**
     * should return all relevant info about this game
     * will also be used to compare games
     * ex: SlidingTiles \n num_undos={} \n board_size={}
     * @return
     */
    abstract public String getGameId();

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

}