package fall2018.csc2017.GameCentre;

public class TileAlpha extends Tile{
    public TileAlpha(int id, int background) {
        super(id, background);
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     */
    public TileAlpha(int backgroundId) {
        super(backgroundId);
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 2:
                background = R.drawable.tile_5x5_2;
                break;
            case 4:
                background = R.drawable.tile_5x5_4;
                break;
            default:
                background = R.drawable.tile_z;

        }
    }
}
