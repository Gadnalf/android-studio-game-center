package fall2018.csc2017.GameCentre;

public class ZTileSettings extends GameSettings {
    private int numUndoes;

    public ZTileSettings(int boardSize, int numUndoes) {
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
        return new ZTileSettings(getBoardSize(), numUndoes);
    }
}
