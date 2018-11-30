package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AlphabetTilesTestingHelpers {
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

    /**
     * Generates the winning board for the Alphabet Tiles game.
     *
     * @param boardSize integer indicating the size of the board.
     * @return List of Tiles to be utilized for the test cases.
     */
    static List<Tile> makeGameOverTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize * boardSize;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TileAlpha(tileNum));
        }
        return tiles;

    }
}

