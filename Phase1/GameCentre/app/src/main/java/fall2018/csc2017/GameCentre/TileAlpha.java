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
            case 0:
                background = R.drawable.tile_empty;
                break;
            case 1:
                background = R.drawable.tile_a;
                break;
            case 2:
                background = R.drawable.tile_b;
                break;
            case 3:
                background = R.drawable.tile_c;
                break;
            case 4:
                background = R.drawable.tile_d;
                break;
            case 5:
                background = R.drawable.tile_e;
                break;
            case 6:
                background = R.drawable.tile_f;
                break;
            case 7:
                background = R.drawable.tile_g;
                break;
            case 8:
                background = R.drawable.tile_h;
                break;
            case 9:
                background = R.drawable.tile_k;
                break;
            default:
                background = R.drawable.tile_empty;

        }
    }
}
