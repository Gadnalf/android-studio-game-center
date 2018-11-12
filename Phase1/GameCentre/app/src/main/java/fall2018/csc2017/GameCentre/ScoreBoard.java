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

    public double getTimePlayed() {
        long endTime = System.nanoTime();
        return (double) (endTime - this.startTime) / 1000000000.0;
    }

    /**
     * notice higher a/b => lower score
     * higher b => lower score
     * lower a => lower score
     * min a and max b => higher score
     * we go with 10 bc I forget :)
     * TODO: add why I went with 10
     * - something to do with easier implementation
     * @return
     */
    public double getScore() {
        double a = (double) getNumMoves();
        double timeWeight = (getTimePlayed()/1000);
        double numUndosWeight = getSlidingTileSettings().getNumUndoes();
        double b = (double) (timeWeight + numUndosWeight);
        return 10-(a/b); //want to maximize this
    }

    public double getScoreAndUpdateScoreBoard() {
        double newScore = getScore();
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

    public void updateUserScore(double newScore) {
        //TODO: make sure the user will be updated after user changes
        //TODO: make sure these wont be overwritten with different users
        if (isNewGameForUser()) {
            SlidingTileSettings slidingTileSettingsCopy = new SlidingTileSettings(this.slidingTileSettings.getBoardSize(),
                    this.slidingTileSettings.getNumUndoes());
            slidingTileSettingsCopy.setMaxScore(newScore, getUser().getUserName());
            this.perUserScoreBoard.put(this.slidingTileSettings.getGameId(), slidingTileSettingsCopy);
        } else {
            if (newScore > getPerUserScoreBoard()
                    .get(this.slidingTileSettings.getGameId())
                    .getMaxScore()) {
                SlidingTileSettings updatedSettings = this.perUserScoreBoard.get(
                        this.slidingTileSettings.getGameId());
                updatedSettings.setMaxScore(newScore, getUser().getUserName());
                this.perUserScoreBoard.put(this.slidingTileSettings.getGameId(), updatedSettings);
            }
        }
    }

    public void updateGameScores(double newScore) {
        if (isNewGame()) {
            this.slidingTileSettings.setMaxScore(newScore, getUser().getUserName());
            this.perGameScoreBoard.put(this.slidingTileSettings.getGameId(), this.slidingTileSettings);
        } else {
            if (newScore > getPerGameScoreBoard()
                    .get(this.slidingTileSettings.getGameId())
                    .getMaxScore()) {
                SlidingTileSettings updatedSettings = this.perGameScoreBoard.get(
                        this.slidingTileSettings.getGameId());
                updatedSettings.setMaxScore(newScore, getUser().getUserName());
                this.perGameScoreBoard.put(this.slidingTileSettings.getGameId(), updatedSettings);
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
        return this.user;
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
