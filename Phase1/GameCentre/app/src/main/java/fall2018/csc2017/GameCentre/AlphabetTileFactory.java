
package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphabetTileFactory extends AbstractTilesFactory{
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
