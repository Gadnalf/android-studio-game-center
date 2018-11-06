package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TestingHelpers {
    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    public static List<Tile> makeTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize*boardSize;

        if (boardSize == 3) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new TileSizeThree(tileNum+1)); //this is how they had it before (+1)
                //may not matter though
            }
        } else if (boardSize == 4) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new TileSizeFour(tileNum+1));
            }
        } else if (boardSize == 5) {
            for (int tileNum = 0; tileNum != numTiles; tileNum ++) {
                tiles.add(new TileSizeFive(tileNum+1));
            }
        }
        return tiles;
    }

    /**
     * Make a solved Board.
     */
    public static BoardManager makeWinningBoardManager(String userName, AppCompatActivity appCompatActivity,
                                                       boolean swapTiles) {
        BoardManager boardManager = SaveAndLoad.loadBoardManagerPermanent(
                userName, appCompatActivity
        );
        List<Tile> tiles = makeTiles(boardManager.getBoard().getBoardSize());
        Board board = new Board(tiles);
        boardManager.setBoard(board);
        boardManager.setUser(new User(userName));
        if (swapTiles) {
            swapFirstTwoTiles(boardManager);
            swapFirstTwoTiles(boardManager);
        }
        System.out.println(boardManager.puzzleSolved());
        return boardManager;
    }


    /**
     * Shuffle a few tiles.
     */
    public static void swapFirstTwoTiles(BoardManager boardManager) {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }


    public static void testSavingAndLoading(AppCompatActivity appCompatActivity){
        //---------add one user to scoreboard
        BoardManager tmpBoardManager = SaveAndLoad.loadBoardManagerTemp(appCompatActivity);
        String userId = tmpBoardManager.getUser().getUserName();
        BoardManager boardManager4 = makeWinningBoardManager(userId, appCompatActivity, true);
        SaveAndLoad.saveBoardManagerPermanent(boardManager4, appCompatActivity);
//        BoardManager boardManager3 = makeWinningBoardManager(userId, appCompatActivity, false);
//        SaveAndLoad.saveBoardManagerPermanent(boardManager3, appCompatActivity);
    }
}
