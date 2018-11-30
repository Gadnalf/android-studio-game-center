package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.List;

class AlphabetTilesTestingHelpers {
    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    static List<Tile> makeEmptyTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize * boardSize;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileAlpha(-1));
        }
        return tiles;
    }

    static List<Tile> makeGameOverTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize * boardSize;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileAlpha(tileNum));
        }
        return tiles;

    }
}

//    /**
//     * Make a game hub w winnning scoreboard
//     * @param userName
//     * @param appCompatActivity
//     * @param swapTiles
//     * @return
//     */
//    public static GameHub makeWinningBoardManager(String userName, AppCompatActivity appCompatActivity,
//                                                  boolean swapTiles) {
//        GameHub gameHub = SaveAndLoad.loadGameHubPermanent(
//                userName, appCompatActivity
//        );
//        SlidingTilesBoardManager slidingTilesBoardManager = gameHub.getSlidingTilesBoardManager();
//        slidingTilesBoardManager.setAppCompatActivity(appCompatActivity);
//        List<Tile> tiles = makeTiles(slidingTilesBoardManager.getBoard().getBoardSize());
//        Board board = new Board(tiles);
//        slidingTilesBoardManager.setBoard(board);
//        slidingTilesBoardManager.setUser(userName);
//        if (swapTiles) {
//            swapFirstTwoTiles(slidingTilesBoardManager);
//            swapFirstTwoTiles(slidingTilesBoardManager);
//        }
//        System.out.println(slidingTilesBoardManager.puzzleSolved());
//        gameHub.setSlidingTilesBoardManager(slidingTilesBoardManager);
//        return gameHub;
//    }
//}
