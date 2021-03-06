package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
abstract class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    protected int background;

    /**
     * The unique id.
     */
    protected int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    public Tile(int id, int background) {
        this.id = id;
        this.background = background;
    }
    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId backgroundId of the tile
     */
    Tile(int backgroundId) {
        id = backgroundId + 1;
    }


    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }
}
