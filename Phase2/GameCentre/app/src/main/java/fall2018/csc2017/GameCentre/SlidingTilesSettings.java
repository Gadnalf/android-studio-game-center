package fall2018.csc2017.GameCentre;

import java.io.Serializable;

/**
 * The settings being implemented for the sliding tiles game.
 */
public class SlidingTilesSettings extends GameSettings implements Serializable {
    private int numUndoes;

    /**
     * Sliding Tiles Settings being utilized for the current game.
     *
     * @param boardSize size of the board for the game being played.
     * @param numUndoes the number of possible undoes.
     */
    SlidingTilesSettings(int boardSize, int numUndoes) {
        super(boardSize, numUndoes);
    }

    /**
     * String representation of the Sliding Tiles game settings.
     *
     * @return details of the settings in string format.
     */
    public String getGameId() {
        return "SlidingTiles\n" + "num_tiles_" + Integer.toString(getBoardSize()) + "\nnum_undoes_"
                + Integer.toString(numUndoes);
    }

    /**
     * Returns number of possible undoes.
     *
     * @return number of possible undoes in integer.
     */
    public int getNumUndoes() {
        return numUndoes;
    }

    /**
     * Set number of possible undoes
     *
     * @param numUndoes integer that indicates number of possible undoes.
     */
    void setNumUndoes(int numUndoes) {
        this.numUndoes = numUndoes;
    }

    /**
     * Returns identical copy of the this class
     *
     * @return SlidingTilesSettings class identical copy
     */
    @Override
    public GameSettings copy() {
        return new SlidingTilesSettings(getBoardSize(), numUndoes);
    }
}
