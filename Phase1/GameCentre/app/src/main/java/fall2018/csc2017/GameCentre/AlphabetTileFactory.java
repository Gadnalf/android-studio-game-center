
package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates tiles for the game Alphabet Tiles for the start of the game.
 */
public class AlphabetTileFactory extends AbstractTilesFactory{

    /**
     * Returns a list of tiles of alphabets for the start of the game.
     *
     * @param boardSize the board size indicated by an integer.
     * @return list of empty tiles and two As to stack.
     */
    public List<Tile> getTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize * boardSize;

        if (boardSize == 4) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum == 1 || tileNum == 2) {
                    tiles.add(new TileAlpha(0));
                } else {
                    tiles.add(new TileAlpha(-1));
                }
            }
        } else if (boardSize == 5) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum == 1 || tileNum == 2) {
                    tiles.add(new TileAlpha(0));
                } else {
                    tiles.add(new TileAlpha(-1));
                }
            }
        } else if (boardSize == 6) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum == 1 || tileNum == 2) {
                    tiles.add(new TileAlpha(0));
                } else {
                    tiles.add(new TileAlpha(-1));
                }
            }
        }
        return tiles;
    }
}
