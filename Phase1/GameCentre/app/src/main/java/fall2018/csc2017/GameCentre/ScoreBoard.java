package fall2018.csc2017.GameCentre;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * used to keep track of the scores on gameSettings
 *
 * automatically calculate the score after the user finishes the gameSettings and store their score on the scoreboard.
 * You should be able to implement and show per-gameSettings and per-user scoreboards.
 * This scoreboard needs to get updated if the users finishes with higher scores. => only care about top scores
 */
public class ScoreBoard implements Serializable {
    public HashMap<String, GameSettings> perGameScoreBoard;
    public HashMap<String, GameSettings> perUserScoreBoard;

    private String user;
    private GameSettings gameSettings;

    public ScoreBoard(String user, GameSettings gameSettings) {
        this.user = user;
        this.gameSettings = gameSettings;
        perGameScoreBoard = new HashMap<>();
        perUserScoreBoard = new HashMap<>();
    }


    public double updateScoreBoard(double newScore) {
        updateGameScoreBoard(newScore);
        updateUserScoreBoard(newScore);
        return newScore;
    }

    public boolean isNewGame() {

        return this.perGameScoreBoard.containsKey(this.gameSettings.getGameId()) == false;
    }

    public boolean isNewGameForUser() {

        return this.perUserScoreBoard.containsKey(this.gameSettings.getGameId()) == false;
    }

    public void updateUserScoreBoard(double newScore) {
        //TODO: make sure the user will be updated after user changes
        //TODO: make sure these wont be overwritten with different users
        if (isNewGameForUser()) {
            GameSettings gameSettingsCopy = this.gameSettings.copy();
            gameSettingsCopy.setMaxScore(newScore, getUser());
            this.perUserScoreBoard.put(this.gameSettings.getGameId(),
                    gameSettingsCopy);
        } else {
            if (newScore > getPerUserScoreBoard()
                    .get(this.gameSettings.getGameId())
                    .getMaxScore()) {
                GameSettings updatedSettings = this.perUserScoreBoard.get(
                        this.gameSettings.getGameId());
                updatedSettings.setMaxScore(newScore, getUser());
                this.perUserScoreBoard.put(this.gameSettings.getGameId(), updatedSettings);
            }
        }
    }

    public void updateGameScoreBoard(double newScore) {
        if (isNewGame()) {
            this.gameSettings.setMaxScore(newScore, getUser());
            this.perGameScoreBoard.put(this.gameSettings.getGameId(), this.gameSettings);
        } else {
            if (newScore > getPerGameScoreBoard()
                    .get(this.gameSettings.getGameId())
                    .getMaxScore()) {
                GameSettings updatedSettings = this.perGameScoreBoard.get(
                        this.gameSettings.getGameId());
                updatedSettings.setMaxScore(newScore, getUser());
                this.perGameScoreBoard.put(this.gameSettings.getGameId(), updatedSettings);
            }
        }
    }

    public static void addScoreToSavedScoreboard(String user,
                                                 double score,
                                                 GameSettings gameSettings,
                                                 AppCompatActivity appCompatActivity) {
        ScoreBoard scoreBoard = new ScoreBoard(user, gameSettings);
        scoreBoard.setPerGameScoreBoard(
                SaveAndLoad.loadPermGameScoreboard(appCompatActivity)
        );
        scoreBoard.setPerUserScoreBoard(
                SaveAndLoad.loadPermUserScoreboard(appCompatActivity, user)
        );
        scoreBoard.updateScoreBoard(score);
        SaveAndLoad.savePermScoreboard(
                appCompatActivity,
                user,
                scoreBoard);
    }


    //---------------------setters and getters

    public HashMap<String, GameSettings> getPerGameScoreBoard() {
        return perGameScoreBoard;
    }

    public void setPerGameScoreBoard(HashMap<String, GameSettings> perGameScoreBoard) {
        this.perGameScoreBoard = perGameScoreBoard;
    }

    public HashMap<String, GameSettings> getPerUserScoreBoard() {
        return perUserScoreBoard;
    }

    public void setPerUserScoreBoard(HashMap<String, GameSettings> perUserScoreBoard) {
        this.perUserScoreBoard = perUserScoreBoard;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {

        this.user = user;
        this.perUserScoreBoard = new HashMap<>();
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings slidingTileSettings) {
        this.gameSettings = slidingTileSettings;
    }

}
