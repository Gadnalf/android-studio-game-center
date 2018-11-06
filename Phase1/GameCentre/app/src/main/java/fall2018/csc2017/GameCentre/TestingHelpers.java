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


        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileSizeFive(tileNum));
        }

        if (boardSize == 3) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new TileSizeThree(tileNum));
            }
        } else if (boardSize == 4) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new TileSizeFour(tileNum));
            }
        } else if (boardSize == 5) {
            for (int tileNum = 0; tileNum != numTiles; tileNum ++) {
                tiles.add(new TileSizeFive(tileNum));
            }
        }
        return tiles;
    }

    /**
     * Make a solved Board.
     */
    public static BoardManager makeWinningBoardManager(String userName,
                                                       int boardSize,
                                                       AppCompatActivity appCompatActivity) {
        List<Tile> tiles = makeTiles(boardSize);
        Board board = new Board(tiles);
        BoardManager boardManager = SaveAndLoad.loadBoardManagerPermanent(
                userName, appCompatActivity
        );
        boardManager.setBoard(board);
        boardManager.setUser(new User(userName));
        boardManager.setSlidingTileSettings(new SlidingTileSettings(boardSize, 2));
        System.out.println(boardManager.puzzleSolved());
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
        BoardManager boardManager = makeWinningBoardManager("phil", 3, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager, appCompatActivity);
        BoardManager boardManager1 = makeWinningBoardManager("felip", 5, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager1, appCompatActivity);
        BoardManager boardManager2 = makeWinningBoardManager("phil", 5, appCompatActivity);
        SaveAndLoad.saveBoardManagerPermanent(boardManager2, appCompatActivity);
        BoardManager boardManager3 = makeWinningBoardManager("phil", 4, appCompatActivity);
        SaveAndLoad.saveBoardManagerTemp(boardManager3, appCompatActivity);
        //------------check that we load properly
        ScoreBoard scoreBoard = boardManager3.getScoreBoard();
        boolean perGameScoreBoardSizeCorrect = 4 == scoreBoard.perGameScoreBoard.size();
        boolean perUserScoreBoardSizeCorrect = 3 == scoreBoard.perUserScoreBoard.size();
        return perGameScoreBoardSizeCorrect && perUserScoreBoardSizeCorrect;
    }
}
