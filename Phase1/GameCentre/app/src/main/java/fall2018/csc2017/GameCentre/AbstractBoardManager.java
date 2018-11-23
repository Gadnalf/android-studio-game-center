package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

abstract public class AbstractBoardManager implements Serializable {

    /**
     * The board being managed.
     */
    protected Board board;
    private double score;
    private User user;
    protected GameSettings gameSettings;
    protected Stack<int[]> moves = new Stack<>();
    protected int moveCount;
    private long startTime;
    private AppCompatActivity appCompatActivity;
    private AbstractTilesFactory tilesFactory;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     * @param user instance of class user
     * @param gameSettings child class of class gameSettings
     * @param appCompatActivity from the activity you're working on
     *                          allows us to do saving and loading to scoreboard
     */
    AbstractBoardManager(Board board, User user, GameSettings gameSettings,
                 AppCompatActivity appCompatActivity,
                         AbstractTilesFactory tilesFactory) {
        this.board = board;
        this.user = user;
        this.gameSettings = gameSettings;
        this.moveCount = 0;
        this.moves = new Stack<>();
        this.startTime = System.nanoTime();
        this.appCompatActivity = appCompatActivity;
        this.tilesFactory = tilesFactory;
    }

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     * @param user instance of class user
     * @param gameSettings child class of class gameSettings
     */
    AbstractBoardManager(Board board, User user, GameSettings gameSettings,
                         AbstractTilesFactory tilesFactory) {
        this.board = board;
        this.user = user;
        this.gameSettings = gameSettings;
        this.moveCount = 0;
        this.moves = new Stack<>();
        this.startTime = System.nanoTime();
        this.tilesFactory = tilesFactory;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new board.
     */
    AbstractBoardManager(User user, GameSettings gameSettings,  AbstractTilesFactory tilesFactory) {
        List<Tile> tiles;
        tiles = tilesFactory.getTiles(gameSettings.getBoardSize());

        this.tilesFactory = tilesFactory;
        this.board = new Board(tiles);
        this.user = user;
        this.gameSettings = gameSettings;
    }


//    public void setBoardSize(int boardSize) throws Exception {
//        if (this.tilesFactory != null) {
//            List<Tile> tiles = this.tilesFactory.getTiles(boardSize);
//            this.gameSettings.setBoardSize(boardSize);
//            this.board.setTiles(tiles);
//        } else {
//            throw new Exception("tileFactory is null");
//        }
//    }

    public void setBoardSize(int boardSize) {
        List<Tile> tiles = this.tilesFactory.getTiles(boardSize);
        this.gameSettings.setBoardSize(boardSize);
        this.board.setTiles(tiles);
    }


    /**
     * implement
     *
     * @return whether the game is won
     */
    abstract boolean puzzleSolved();


    abstract boolean isValidTap(int position);

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    abstract void touchMove(int position);

    boolean isValidUndo(int position) {
        return false;
    }

    void tapUndo(int position) {
    }

    boolean isValidShoot(int position) {return false;}

    public void fireAndUpdate(int position) {};

    public boolean moveIsEmpty() {
        return moves.empty();
    }


    public int getMoveCount() {
        return this.moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }



    public void setBoard(Board board) {
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public double getTimePlayed() {
        long endTime = System.nanoTime();
        return (double) (endTime - this.startTime) / 1000000000.0;
    }

    /**
     * your scoring fucntion
     * @return
     */
    abstract public double getScore();

    /**
     * load update and then save the scoreboard
     */
    public void updateScoreboard() {
        this.score = getScore();
        if (this.appCompatActivity != null) {
            ScoreBoard.addScoreToSavedScoreboard(this.user,
                    this.score,
                    this.gameSettings,
                    this.appCompatActivity);
        }
    }
}
