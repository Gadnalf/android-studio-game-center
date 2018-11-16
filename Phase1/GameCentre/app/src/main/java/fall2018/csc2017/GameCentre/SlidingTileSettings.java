package fall2018.csc2017.GameCentre;

import java.io.Serializable;

//public class SlidingTileSettings implements Serializable, GameSettings {
public class SlidingTileSettings extends GameSettings implements Serializable {
    private int numUndoes;

    public SlidingTileSettings(int boardSize, int numUndoes) {
        this.numUndoes = numUndoes;
    }

    public String getGameId() {
        String gameId = "SlidingTiles\n" + "num_tiles_" + Integer.toString(getBoardSize()) + "\nnum_undoes_" + Integer.toString(numUndoes);
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
        return new SlidingTileSettings(getBoardSize(), numUndoes);
    }
}
