package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.List;

public class SlidingTilesTileFactory extends AbstractTilesFactory {
    public List<Tile> getTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize * boardSize;

        if (boardSize == 3) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum == numTiles - 1) {
                    tiles.add(new TileNum(24));
                } else {
                    tiles.add(new TileNum(tileNum));
                }
            }
        } else if (boardSize == 4) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum == numTiles - 1) {
                    tiles.add(new TileNum(24));
                } else {
                    tiles.add(new TileNum(tileNum));
                }
            }
        } else if (boardSize == 5) {
            for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                if (tileNum == numTiles - 1){
                    tiles.add(new TileNum(24));
                } else {
                    tiles.add(new TileNum(tileNum));
                }
            }
        }
        return tiles;
    }
}