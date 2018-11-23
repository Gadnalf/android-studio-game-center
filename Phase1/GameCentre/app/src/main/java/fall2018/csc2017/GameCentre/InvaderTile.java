package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class InvaderTile extends Tile implements Serializable {

//    public InvaderTile(int id) {
//        super(id);
//        background = background = R.drawable.tile_5x5_1;
//    }

    public InvaderTile() {
        super(4, 4);
        background = R.drawable.tile_5x5_4;
    }
}
