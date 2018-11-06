package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.R;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class TileSizeFour extends Tile {

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public TileSizeFour(int id, int background) {
        super(id, background);
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    public TileSizeFour(int backgroundId) {
        super(backgroundId);
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_4x4_1;
                break;
            case 2:
                background = R.drawable.tile_4x4_2;
                break;
            case 3:
                background = R.drawable.tile_4x4_3;
                break;
            case 4:
                background = R.drawable.tile_4x4_4;
                break;
            case 5:
                background = R.drawable.tile_4x4_5;
                break;
            case 6:
                background = R.drawable.tile_4x4_6;
                break;
            case 7:
                background = R.drawable.tile_4x4_7;
                break;
            case 8:
                background = R.drawable.tile_4x4_8;
                break;
            case 9:
                background = R.drawable.tile_4x4_9;
                break;
            case 10:
                background = R.drawable.tile_4x4_10;
                break;
            case 11:
                background = R.drawable.tile_4x4_11;
                break;
            case 12:
                background = R.drawable.tile_4x4_12;
                break;
            case 13:
                background = R.drawable.tile_4x4_13;
                break;
            case 14:
                background = R.drawable.tile_4x4_14;
                break;
            case 15:
                background = R.drawable.tile_4x4_15;
                break;
            case 16:
                background = R.drawable.tile_4x4_16;
                break;
            default:
                background = R.drawable.tile_4x4_16;
        }
    }

}