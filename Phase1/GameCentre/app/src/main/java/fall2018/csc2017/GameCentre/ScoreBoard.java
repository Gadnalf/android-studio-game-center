package fall2018.csc2017.GameCentre;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * used to keep track of the scores on game
 *
 * automatically calculate the score after the user finishes the game and store their score on the scoreboard.
 * You should be able to implement and show per-game and per-user scoreboards.
 * This scoreboard needs to get updated if the users finishes with higher scores. => only care about top scores
 */
public class ScoreBoard implements Serializable {
    public static ArrayList<Game> perGameScoreBoard;
    public static ArrayList<User> perUserScoreBoard;

    private long startTime;
    private User user;
    private Game game;
    private BoardManager boardManager;

    public ScoreBoard(User user, Game game, BoardManager boardManager) {
        this.boardManager = boardManager;
        this.startTime = System.nanoTime();
        this.user = user;
        this.game = game;
    }

    public long getTimePlayed() {
        long endTime = System.nanoTime();
        return endTime - this.startTime;
    }

    public long getScore() {
        return (long) this.boardManager.getNumMoves() / getTimePlayed();
    }

    public void getScoreAndUpdateScoreBoard() {
        if (this.boardManager.puzzleSolved()) {
            long newScore = getScore();
            updateGameScores(newScore);
            updateUserScore(newScore);
        }
    }

    public boolean isNewGame() {
        return this.game.getGameId() >= perGameScoreBoard.size();
    }

    public boolean isNewUser() {
        return this.user.getUserId() >= perUserScoreBoard.size();
    }

    public void updateUserScore(long newScore) {
        //TODO: make sure the user will be updated after user changes
        if (newScore > this.user.getMaxScore()) {
            this.user.setMaxScore(newScore);
        }
        if (isNewUser()) {
            perUserScoreBoard.add(this.user);
        }
    }

    public void updateGameScores(long newScore) {
        if (newScore > this.game.getMaxScore()) {
            this.game.setMaxScore(newScore);
        }
        if (isNewGame()) {
            perGameScoreBoard.add(this.game);
        }
    }




}
