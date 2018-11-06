package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.R;

public class TileSizeFive extends Tile{

    public TileSizeFive(int id, int background) {
        super(id, background);
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     */
    public TileSizeFive(int backgroundId) {
        super(backgroundId);
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_5x5_1;
                break;
            case 2:
                background = R.drawable.tile_5x5_2;
                break;
            case 3:
                background = R.drawable.tile_5x5_3;
                break;
            case 4:
                background = R.drawable.tile_5x5_4;
                break;
            case 5:
                background = R.drawable.tile_5x5_6;
                break;
            case 6:
                background = R.drawable.tile_5x5_6;
                break;
            case 7:
                background = R.drawable.tile_5x5_7;
                break;
            case 8:
                background = R.drawable.tile_5x5_8;
                break;
            case 9:
                background = R.drawable.tile_5x5_9;
                break;
            case 10:
                background = R.drawable.tile_5x5_10;
                break;
            case 11:
                background = R.drawable.tile_5x5_11;
                break;
            case 12:
                background = R.drawable.tile_5x5_12;
                break;
            case 13:
                background = R.drawable.tile_5x5_13;
                break;
            case 14:
                background = R.drawable.tile_5x5_15;
                break;
            case 15:
                background = R.drawable.tile_5x5_16;
                break;
            case 16:
                background = R.drawable.tile_5x5_16;
                break;
            case 17:
                background = R.drawable.tile_5x5_17;
                break;
            case 18:
                background = R.drawable.tile_5x5_18;
                break;
            case 19:
                background = R.drawable.tile_5x5_19;
                break;
            case 20:
                background = R.drawable.tile_5x5_20;
                break;
            case 21:
                background = R.drawable.tile_5x5_21;
                break;
            case 22:
                background = R.drawable.tile_5x5_22;
                break;
            case 23:
                background = R.drawable.tile_5x5_23;
                break;
            case 24:
                background = R.drawable.tile_5x5_24;
                break;
            case 25:
                background = R.drawable.tile_5x5_25;
                break;
            default:
                background = R.drawable.tile_5x5_25;

        }
    }
}
