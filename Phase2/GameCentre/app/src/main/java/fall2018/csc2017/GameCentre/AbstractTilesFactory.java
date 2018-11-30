package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.List;

/**
 * This is the abstract class that all the tile factories should follow
 */
abstract public class AbstractTilesFactory implements Serializable {

    /**
     * the method that returns list of tiles that all the tiles factory generates.
     *
     * @param boardSize the board size indicated by an integer.
     * @return list of generated tiles.
     */
    abstract public List<Tile> getTiles(int boardSize);
}