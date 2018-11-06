package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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
    public static BoardManager makeWinningBoardManager(String userName,
                                                       int numTiles,
                                                       AppCompatActivity appCompatActivity) {
        List<Tile> tiles = makeTiles();
        Board board = new Board(tiles);
        BoardManager boardManager = SaveAndLoad.loadBoardManagerPermanent(
                userName, appCompatActivity
        );
        boardManager.setBoard(board);
        boardManager.setUser(new User(userName));
        boardManager.setSlidingTileSettings(new SlidingTileSettings(numTiles, 2));
        System.out.println(boardManager.puzzleSolved());
        return boardManager;
    }


    public static boolean testSavingAndLoading(AppCompatActivity appCompatActivity){
        //---------add one user to scoreboard
        BoardManager boardManager = makeWinningBoardManager("phil", 3, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager, appCompatActivity);
        BoardManager boardManager1 = makeWinningBoardManager("felip", 50, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager1, appCompatActivity);
        BoardManager boardManager2 = makeWinningBoardManager("phil", 5, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager2, appCompatActivity);

        BoardManager tmpBoardManager = SaveAndLoad.loadBoardManagerTemp(appCompatActivity);
        String userId = tmpBoardManager.getUser().getUserName();
        BoardManager boardManager4 = makeWinningBoardManager(userId, 5, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager4, appCompatActivity);
        BoardManager boardManager5 = makeWinningBoardManager(userId, 15, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager5, appCompatActivity);
        BoardManager boardManager3 = makeWinningBoardManager("phil", 4, appCompatActivity);
        SaveAndLoad.saveBoardManagerTemp(boardManager3, appCompatActivity);
        //------------check that we load properly
        ScoreBoard scoreBoard = boardManager3.getScoreBoard();
        boolean perGameScoreBoardSizeCorrect = 4 == scoreBoard.perGameScoreBoard.size();
        boolean perUserScoreBoardSizeCorrect = 3 == scoreBoard.perUserScoreBoard.size();
        return perGameScoreBoardSizeCorrect && perUserScoreBoardSizeCorrect;
    }
}
