package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class AlphabetTilesSettings extends GameSettings implements Serializable {
    private int numUndoes;

    public AlphabetTilesSettings(int boardSize, int numUndoes) {
        super(boardSize, numUndoes);
    }

    public String getGameId() {
        String gameId = "Alphabet\n" + "num_tiles_" + Integer.toString(getBoardSize()) + "\nnum_undoes_" + Integer.toString(numUndoes);
        return gameId;
    }

    public int getNumUndoes() {
        return numUndoes;
    }

    public void setNumUndoes(int numUndoes) {
        this.numUndoes = numUndoes;
    }

    @Override
    public GameSettings copy() {
        return new AlphabetTilesSettings(getBoardSize(), numUndoes);
    }
}
