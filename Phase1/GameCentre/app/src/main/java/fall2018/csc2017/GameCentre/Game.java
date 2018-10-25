package fall2018.csc2017.GameCentre;

public class Game {
    private int numTiles;
    private long maxScore;
    private static int numGames = -1;
    private int gameId;

    public Game(int numTiles) {
        this.numTiles = numTiles;
        this.maxScore = 1;
        this.gameId = this.numGames + 1;
    }

    public int getNumTiles() {
        return numTiles;
    }

    public void setNumTiles(int numTiles) {
        this.numTiles = numTiles;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }

    public int getGameId() {
        return gameId;
    }

}
