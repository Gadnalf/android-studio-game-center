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
     * Shuffle a few tiles.
     */
    public static void swapFirstTwoTiles(SlidingTilesBoardManager slidingTilesBoardManager) {
        slidingTilesBoardManager.getBoard().swapTiles(0, 0, 0, 1);
    }
}
