package fall2018.csc2017.GameCentre;

public class TileSeaInvader extends Tile{
    public TileSeaInvader(int id, int background) {
        super(id, background);
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     */
    public TileSeaInvader(int backgroundId) {
        super(backgroundId);
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 0:
                background = R.drawable.tile_sea_inv_background;
                break;
            case 1:
                background = R.drawable.tile_sea_inv_kraken;
                break;
            case 2:
                background = R.drawable.tile_sea_inv_ship;
                break;
        }
    }
}
