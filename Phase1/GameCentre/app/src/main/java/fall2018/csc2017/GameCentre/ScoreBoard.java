package fall2018.csc2017.GameCentre;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * used to keep track of the scores on game
 *
 * automatically calculate the score after the user finishes the game and store their score on the scoreboard.
 * You should be able to implement and show per-game and per-user scoreboards.
 * This scoreboard needs to get updated if the users finishes with higher scores. => only care about top scores
 */
public class ScoreBoard implements Serializable {
    public HashMap<String, Game> perGameScoreBoard;
    public HashMap<String, Game> perUserScoreBoard;

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
        this.perGameScoreBoard = new HashMap<>();
        this.perUserScoreBoard = new HashMap<>();
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

        return this.perGameScoreBoard.containsKey(this.game.getGameId()) == false;
    }

    public boolean isNewGameForUser() {

        return this.perUserScoreBoard.containsKey(this.game.getGameId()) == false;
    }

    public void updateUserScore(long newScore) {
        //TODO: make sure the user will be updated after user changes
        //TODO: make sure these wont be overwritten with different users
        if (isNewGameForUser()) {
            this.game.setMaxScore(newScore, getUser().getUserName());
            this.perUserScoreBoard.put(this.game.getGameId(), this.game);
        } else {
            if (newScore > getPerUserScoreBoard()
                    .get(this.game.getGameId())
                    .getMaxScore()) {
                this.perUserScoreBoard.get(this.game.getGameId()).setMaxScore(newScore, getUser().getUserName());
            }
        }
    }

    public void updateGameScores(long newScore) {
        if (isNewGame()) {
            this.game.setMaxScore(newScore, getUser().getUserName());
            this.perGameScoreBoard.put(this.game.getGameId(), this.game);
        } else {
            if (newScore > getPerGameScoreBoard()
                    .get(this.game.getGameId())
                    .getMaxScore()) {
                this.perGameScoreBoard.get(this.game.getGameId()).setMaxScore(newScore, getUser().getUserName());
            }
        }
    }

    public void move() {
        this.numMoves ++;
    }

    public HashMap<String, Game> getPerGameScoreBoard() {
        return perGameScoreBoard;
    }

    public void setPerGameScoreBoard(HashMap<String, Game> perGameScoreBoard) {
        this.perGameScoreBoard = perGameScoreBoard;
    }

    public HashMap<String, Game> getPerUserScoreBoard() {
        return perUserScoreBoard;
    }

    public void setPerUserScoreBoard(HashMap<String, Game> perUserScoreBoard) {
        this.perUserScoreBoard = perUserScoreBoard;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }
}
