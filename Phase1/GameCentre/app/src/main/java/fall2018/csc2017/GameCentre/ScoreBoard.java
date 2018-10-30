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
    public static ArrayList<Game> perGameScoreBoard = new ArrayList<>();
    public static ArrayList<Game> perUserScoreBoard = new ArrayList<>();

    private long startTime;
    private User user;
    private Game game;
    private BoardManager boardManager;
    private int numMoves;

    public ScoreBoard(User user, Game game) {
        this.startTime = System.nanoTime();
        this.user = user;
        this.game = game;
        this.numMoves = 0;
    }

    public long getTimePlayed() {
        long endTime = System.nanoTime();
        return endTime - this.startTime;
    }


    public long getScore() {
        long a = (long) getNumMoves();
        long b = getTimePlayed();
        return 10-(a/b); //want to maximize this
    }

    public long getScoreAndUpdateScoreBoard() {
        long newScore = getScore();
        updateGameScores(newScore);
        updateUserScore(newScore);
        return newScore;
    }

    public boolean isNewGame() {
        return this.game.getGameId() >= perGameScoreBoard.size();
    }

    public boolean isNewUser() {
        return this.user.getUserId() >= perUserScoreBoard.size();
    }

    public void updateUserScore(long newScore) {
        //TODO: make sure the user will be updated after user changes
        //TODO: make sure these wont be overwritten with different users
        if (newScore > this.game.getMaxScore()) {
            this.game.setMaxScore(newScore);
        }
        if (isNewUser()) {
            perUserScoreBoard.add(this.game);
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

    public void move() {
        this.numMoves ++;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public static ArrayList<Game> getPerGameScoreBoard() {
        return perGameScoreBoard;
    }

    public static ArrayList<Game> getPerUserScoreBoard() {
        return perUserScoreBoard;
    }

    public User getUser() {
        return user;
    }
}
