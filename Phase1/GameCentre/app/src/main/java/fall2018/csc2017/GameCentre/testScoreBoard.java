package fall2018.csc2017.GameCentre;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestScoreBoard extends StartingActivity  {

    /** The board manager for testing. */
    BoardManager boardManager;
    BoardManager winningBoardManager;
    ScoreBoard scoreBoard;
    StartingActivity startingActivity;
    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    public static void main(String[] args) {
        TestScoreBoard testScoreBoard = new TestScoreBoard();
        System.out.println(testScoreBoard.testSavingAndLoading());
    }

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    public List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     * Make a solved Board.
     */
    public void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        Board board = new Board(tiles);
        scoreBoard = new ScoreBoard(
                new User("jim", "testing"),
                new Game(3)
        );
        boardManager = new BoardManager(board, scoreBoard);
        winningBoardManager = new BoardManager(board, scoreBoard);
        startingActivity = new StartingActivity(boardManager);
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (BoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Shuffle a few tiles.
     */
    public void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }


    public boolean testSavingAndLoading(){
        //---------add one user to scoreboard
        setUpCorrect();
        System.out.println(boardManager.puzzleSolved());
        //puzzle is solved so will add to the scoreboard
        //---------add another user to the scoreboard
        List<Tile> tiles = makeTiles();
        Board board = new Board(tiles);
        scoreBoard = new ScoreBoard(
                new User("bill", "testing"),
                new Game(4)
        );
        boardManager = new BoardManager(board, scoreBoard);
        //puzzle solved so we add to the scoreboard
        System.out.println(boardManager.puzzleSolved());
        //save this scoreboard
//        saveToFile(SAVE_FILENAME);
        saveToFile(SAVE_FILENAME);
        //wipe the scoreboard
        ScoreBoard.perGameScoreBoard = new ArrayList<>();
        ScoreBoard.perUserScoreBoard = new ArrayList<>();
//        loadFromFile(SAVE_FILENAME);
        loadFromFile(SAVE_FILENAME);
        //------------check that we load properly
//        assertEquals(2, scoreBoard.perGameScoreBoard.size());
//        assertEquals(2, scoreBoard.perUserScoreBoard.size());
        boolean perGameScoreBoardSizeCorrect = 2 == scoreBoard.perGameScoreBoard.size();
        boolean perUserScoreBoardSizeCorrect = 2 == scoreBoard.perUserScoreBoard.size();
        return perGameScoreBoardSizeCorrect;
    }
}
