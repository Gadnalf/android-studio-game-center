package fall2018.csc2017.GameCentre;

import java.io.Serializable;

/**
 * Settings being implemeted for the AlphabetTiles game.
 */
public class AlphabetTilesSettings extends GameSettings implements Serializable {

    /**
     * number of possible undoes.
     */
    private int numUndoes;

    /**
     * The setting class for the Alphabet Tiles Settings. Stores all the information required to
     * play the game
     *
     * @param boardSize size of the board represented by an integer.
     * @param numUndoes number of possible undoes.
     */
    AlphabetTilesSettings(int boardSize, int numUndoes) {
        super(boardSize, numUndoes);
    }

    /**
     * Returns the String format of Alphabet Tiles Settings.
     *
     * @return Id - the unique in the string form for each game.
     */
    public String getGameId() {
        return "Alphabet\n" + "num_tiles_" + Integer.toString(getBoardSize()) + "\nnum_undoes_" + Integer.toString(numUndoes);
    }

    /**
     * Returns the possible number of undoes.
     *
     * @return possible number of undoes.
     */
    public int getNumUndoes() {
        return numUndoes;
    }

    /**
     * Sets the possible number of undoes.
     *
     * @param numUndoes possible
     */
    void setNumUndoes(int numUndoes) {
        this.numUndoes = numUndoes;
    }

    /**
     * Returns an exact copy of this settings class.
     *
     * @return AlphabetTilesSettings class which is identical.
     */
    @Override
    public GameSettings copy() {
        return new AlphabetTilesSettings(getBoardSize(), numUndoes);
    }
}
