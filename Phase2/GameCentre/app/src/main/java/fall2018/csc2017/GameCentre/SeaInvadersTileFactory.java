package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.List;

public class SeaInvadersTileFactory extends AbstractTilesFactory {
    public List<Tile> getTiles(int boardSize) {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = boardSize * boardSize;

        if (boardSize == 5) { //TODO: make bigger boards
            for (int tileNum = 0; tileNum < boardSize; tileNum++) {
                tiles.add(new InvaderTile());
//                tiles.add(new TileSizeFive(1));

            }
            for (int tileNum = boardSize; tileNum < numTiles - 1; tileNum++) {
                tiles.add(new TileSeaInvader(- 1));
            }
            tiles.add(new TileSeaInvader(1));

        }
        return tiles;
    }
}
