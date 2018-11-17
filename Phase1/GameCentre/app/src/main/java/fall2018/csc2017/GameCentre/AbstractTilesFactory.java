package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.List;

abstract public class AbstractTilesFactory implements Serializable {

    abstract public List<Tile> getTiles(int boardSize);
}