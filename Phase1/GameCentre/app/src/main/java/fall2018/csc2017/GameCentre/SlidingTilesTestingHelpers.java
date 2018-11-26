package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SlidingTilesTestingHelpers {
    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    public static List<Tile> makeTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize*boardSize;

        if (boardSize == 3) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                tiles.add(new TileSizeThree(tileNum)); //this is how they had it before (+1)
                //may not matter though
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
     * Make a game hub w winnning scoreboard
     * @param userName
     * @param appCompatActivity
     * @param swapTiles
     * @return
     */
    public static GameHub makeWinningBoardManager(String userName, AppCompatActivity appCompatActivity,
                                                                   boolean swapTiles) {
        GameHub gameHub = SaveAndLoad.loadGameHubPermanent(
                userName, appCompatActivity
        );
        SlidingTilesBoardManager slidingTilesBoardManager = gameHub.getSlidingTilesBoardManager();
        slidingTilesBoardManager.setAppCompatActivity(appCompatActivity);
        List<Tile> tiles = makeTiles(slidingTilesBoardManager.getBoard().getBoardSize());
        Board board = new Board(tiles);
        slidingTilesBoardManager.setBoard(board);
        slidingTilesBoardManager.setUser(new User(userName));
        if (swapTiles) {
            swapFirstTwoTiles(slidingTilesBoardManager);
            swapFirstTwoTiles(slidingTilesBoardManager);
        }
        System.out.println(slidingTilesBoardManager.puzzleSolved());
        gameHub.setSlidingTilesBoardManager(slidingTilesBoardManager);
        return gameHub;
    }


    /**
     * Shuffle a few tiles.
     */
    public static void swapFirstTwoTiles(SlidingTilesBoardManager slidingTilesBoardManager) {
        slidingTilesBoardManager.getBoard().swapTiles(0, 0, 0, 1);
        slidingTilesBoardManager.moveCount += 1;
//        int numTiles = slidingTilesBoardManager.getBoard().getBoardSize() * slidingTilesBoardManager.getBoard().getBoardSize();
//        slidingTilesBoardManager.touchMove(numTiles);
//        slidingTilesBoardManager.touchMove(numTiles-1);
        //TODO: implement like above if time

    }

    /**
     * test the saving and loading for scoreboard
     * - after calling this you can look at the scoreboard activities and ensure they update
     * @param appCompatActivity
     */
    public static void testSavingAndLoading(AppCompatActivity appCompatActivity){
        //---------add one user to scoreboard
        GameHub tmpGameHub = SaveAndLoad.loadGameHubTemp(appCompatActivity);
        String userId = tmpGameHub.getUser().getUserName();
        GameHub gameHub = makeWinningBoardManager(userId, appCompatActivity, true);
        SaveAndLoad.saveGameHubPermanent(gameHub, appCompatActivity);
//        SlidingTilesBoardManager boardManager3 = makeWinningBoardManager(userId, appCompatActivity, false);
//        SaveAndLoad.saveGameHubPermanent(boardManager3, appCompatActivity);
    }
}