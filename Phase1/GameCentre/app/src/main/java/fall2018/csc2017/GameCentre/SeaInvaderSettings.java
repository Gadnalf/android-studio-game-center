package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class SeaInvaderSettings extends GameSettings implements Serializable {
    private double secsBeforeSpawn;
    private double secsBeforeMove;
    private int numRounds;
    private int numSpawn; //TODO: actually implement this with the SeaInvadersTileFactory

    public SeaInvaderSettings(double startingScore, int boardSize,
                              int numUndoes, double secsBeforeSpawn,
                              double secsBeforeMove,
                              int numRounds,
                              int numSpawn) {
        super(startingScore, boardSize, numUndoes);
        this.secsBeforeSpawn = secsBeforeSpawn;
        this.secsBeforeMove = secsBeforeMove;
        this.numRounds = numRounds;
        this.numSpawn = numSpawn;
    }

    public SeaInvaderSettings(double secsBeforeSpawn,
                              double secsBeforeMove,
                              int numRounds,
                              int numSpawn) {
        super(0, 5, 0);
        this.secsBeforeSpawn = secsBeforeSpawn;
        this.secsBeforeMove = secsBeforeMove;
        this.numRounds = numRounds;
        this.numSpawn = numSpawn;
    }

    public SeaInvaderSettings(double secsBeforeSpawn,
                              double secsBeforeMove) {
        super(0, 5, 0);
        this.secsBeforeSpawn = secsBeforeSpawn;
        this.secsBeforeMove = secsBeforeMove;
        this.numRounds = 10;
        this.numSpawn = 5;
    }

    @Override
    public String getGameId() {
        return ("SeaInvaders \nBoardSize=" + Integer.toString(getBoardSize()) +
                "\nsecsBeforeSpawn=" + getSecsBeforeSpawn() +
                "\nsecsBeforeMove=" + getSecsBeforeMove() +
                "\nnumUndos=" + getNumUndoes() +
                "\nnumRounds=" + getNumRounds() +
                "\nnumSpawn" + getNumSpawn()
        );
    }

    @Override
    public GameSettings copy() {
        return new SeaInvaderSettings(
                getMaxScore(),
                getBoardSize(),
                getNumUndoes(),
                getSecsBeforeSpawn(),
                getSecsBeforeMove(),
                getNumRounds(),
                getNumSpawn()
        );
    }

    public double getSecsBeforeSpawn() {
        return secsBeforeSpawn;
    }

    public void setSecsBeforeSpawn(double secsBeforeSpawn) {
        this.secsBeforeSpawn = secsBeforeSpawn;
    }

    public double getSecsBeforeMove() {
        return secsBeforeMove;
    }

    public void setSecsBeforeMove(double secsBeforeMove) {
        this.secsBeforeMove = secsBeforeMove;
    }

    public int getNumSpawn() {
        return numSpawn;
    }

    public void setNumSpawn(int numSpawn) {
        this.numSpawn = numSpawn;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setNumRounds(int numRounds) {
        this.numRounds = numRounds;
    }
}
