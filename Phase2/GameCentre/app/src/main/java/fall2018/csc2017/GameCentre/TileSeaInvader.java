package fall2018.csc2017.GameCentre;

class TileSeaInvader extends Tile{

    /**
     * A tile with a background id; look up and set the id.
     *
     */
    TileSeaInvader(int backgroundId) {
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
