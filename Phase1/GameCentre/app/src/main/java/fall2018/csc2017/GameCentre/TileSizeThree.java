package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

import fall2018.csc2017.GameCentre.R;

public class TileSizeThree extends Tile{

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public TileSizeThree(int id, int background) {
        super(id, background);
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    public TileSizeThree(int backgroundId) {
        super(backgroundId);
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_3x3_1;
                break;
            case 2:
                background = R.drawable.tile_3x3_2;
                break;
            case 3:
                background = R.drawable.tile_3x3_2;
                break;
            case 4:
                background = R.drawable.tile_3x3_4;
                break;
            case 5:
                background = R.drawable.tile_3x3_5;
                break;
            case 6:
                background = R.drawable.tile_3x3_6;
                break;
            case 7:
                background = R.drawable.tile_3x3_7;
                break;
            case 8:
                background = R.drawable.tile_3x3_8;
                break;
            case 9:
                background = R.drawable.tile_3x3_9;
                break;
            default:
                background = R.drawable.tile_3x3_9;
        }
    }
}
