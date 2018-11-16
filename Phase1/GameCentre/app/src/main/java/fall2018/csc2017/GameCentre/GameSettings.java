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

    public GameSettings() {
        this.maxScore = 1;
    }

    public GameSettings(double startingScore) {
        this.maxScore = startingScore;
    }

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