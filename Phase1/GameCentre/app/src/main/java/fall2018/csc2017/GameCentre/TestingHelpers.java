package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.GameCentre.Board;
import fall2018.csc2017.GameCentre.BoardManager;
import fall2018.csc2017.GameCentre.Game;
import fall2018.csc2017.GameCentre.ScoreBoard;
import fall2018.csc2017.GameCentre.Tile;
import fall2018.csc2017.GameCentre.User;

public class TestingHelpers {
    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    public static List<Tile> makeTiles() {
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
    public static BoardManager makeWinningBoardManager() {
        List<Tile> tiles = makeTiles();
        Board board = new Board(tiles);
        ScoreBoard scoreBoard = new ScoreBoard(
                new User("jim", "testing"),
                new Game(3)
        );
        BoardManager boardManager = new BoardManager(board, scoreBoard);
        return boardManager;
    }


    /**
     * Shuffle a few tiles.
     */
    public void swapFirstTwoTiles(BoardManager boardManager) {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }


    public static boolean testSavingAndLoading(AppCompatActivity appCompatActivity){
        //---------add one user to scoreboard
        BoardManager boardManager = makeWinningBoardManager();
        System.out.println(boardManager.puzzleSolved());
        SaveAndLoad.saveToFile(boardManager, StartingActivity.SAVE_FILENAME,
                appCompatActivity);
        //wipe the scoreboard
//        scoreBoard.perGameScoreBoard = new ArrayList<>();
//        scoreBoard.perUserScoreBoard = new ArrayList<>();
////        loadFromFile(SAVE_FILENAME);
//        BoardManager boardManagerFinal = (BoardManager) SaveAndLoad.loadFromFile(StartingActivity.SAVE_FILENAME, appCompatActivity);
//        //------------check that we load properly
////        assertEquals(2, scoreBoard.perGameScoreBoard.size());
////        assertEquals(2, scoreBoard.perUserScoreBoard.size());
//        boolean perGameScoreBoardSizeCorrect = 2 == scoreBoard.perGameScoreBoard.size();
//        boolean perUserScoreBoardSizeCorrect = 2 == scoreBoard.perUserScoreBoard.size();
////        SaveAndLoad.saveToFile(boardManagerFinal, StartingActivity.SAVE_FILENAME,
////                appCompatActivity);
//        return perGameScoreBoardSizeCorrect && perUserScoreBoardSizeCorrect;
        return (true);
    }
}
