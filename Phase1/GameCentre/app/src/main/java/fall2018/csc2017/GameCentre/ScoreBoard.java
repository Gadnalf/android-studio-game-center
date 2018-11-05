package fall2018.csc2017.GameCentre;
import java.io.Serializable;
import java.util.HashMap;

/**
 * used to keep track of the scores on slidingTileSettings
 *
 * automatically calculate the score after the user finishes the slidingTileSettings and store their score on the scoreboard.
 * You should be able to implement and show per-slidingTileSettings and per-user scoreboards.
 * This scoreboard needs to get updated if the users finishes with higher scores. => only care about top scores
 */
public class ScoreBoard implements Serializable {
    public HashMap<String, SlidingTileSettings> perGameScoreBoard;
    public HashMap<String, SlidingTileSettings> perUserScoreBoard;

    private long startTime;
    private User user;
    private SlidingTileSettings slidingTileSettings;
    private BoardManager boardManager;
    private int numMoves;

    public ScoreBoard(User user, SlidingTileSettings slidingTileSettings) {
        this.startTime = System.nanoTime();
        this.user = user;
        this.slidingTileSettings = slidingTileSettings;
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

        return this.perGameScoreBoard.containsKey(this.slidingTileSettings.getGameId()) == false;
    }

    public boolean isNewGameForUser() {

        return this.perUserScoreBoard.containsKey(this.slidingTileSettings.getGameId()) == false;
    }

    public void updateUserScore(long newScore) {
        //TODO: make sure the user will be updated after user changes
        //TODO: make sure these wont be overwritten with different users
        if (isNewGameForUser()) {
            this.slidingTileSettings.setMaxScore(newScore, getUser().getUserName());
            this.perUserScoreBoard.put(this.slidingTileSettings.getGameId(), this.slidingTileSettings);
        } else {
            if (newScore > getPerUserScoreBoard()
                    .get(this.slidingTileSettings.getGameId())
                    .getMaxScore()) {
                this.perUserScoreBoard.get(this.slidingTileSettings.getGameId()).setMaxScore(newScore, getUser().getUserName());
            }
        }
    }

    public void updateGameScores(long newScore) {
        if (isNewGame()) {
            this.slidingTileSettings.setMaxScore(newScore, getUser().getUserName());
            this.perGameScoreBoard.put(this.slidingTileSettings.getGameId(), this.slidingTileSettings);
        } else {
            if (newScore > getPerGameScoreBoard()
                    .get(this.slidingTileSettings.getGameId())
                    .getMaxScore()) {
                this.perGameScoreBoard.get(this.slidingTileSettings.getGameId()).setMaxScore(newScore, getUser().getUserName());
            }
        }
    }

    public void move() {
        this.numMoves ++;
    }

    public HashMap<String, SlidingTileSettings> getPerGameScoreBoard() {
        return perGameScoreBoard;
    }

    public void setPerGameScoreBoard(HashMap<String, SlidingTileSettings> perGameScoreBoard) {
        this.perGameScoreBoard = perGameScoreBoard;
    }

    public HashMap<String, SlidingTileSettings> getPerUserScoreBoard() {
        return perUserScoreBoard;
    }

    public void setPerUserScoreBoard(HashMap<String, SlidingTileSettings> perUserScoreBoard) {
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

    public SlidingTileSettings getSlidingTileSettings() {
        return slidingTileSettings;
    }

    public void setSlidingTileSettings(SlidingTileSettings slidingTileSettings) {
        this.slidingTileSettings = slidingTileSettings;
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
