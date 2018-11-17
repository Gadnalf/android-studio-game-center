package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Iterator;


public class SeaInvadersBoardManager extends AbstractBoardManager implements Serializable {


    int lastOccupiedColumn = -1; //TODO: make sure he starts here

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    SeaInvadersBoardManager(Board board, User user, SeaInvaderSettings seaInvaderSettings,
                             AppCompatActivity appCompatActivity) {
        super(board, user, seaInvaderSettings,
                appCompatActivity);
        this.lastOccupiedColumn = seaInvaderSettings.getBoardSize()-1;
    }


    SeaInvadersBoardManager(User user, SeaInvaderSettings seaInvaderSettings) {
        super(user, seaInvaderSettings, new SeaInvadersTileFactory());
        this.lastOccupiedColumn = seaInvaderSettings.getBoardSize()-1;
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    boolean puzzleSolved() {
        boolean solved = true;
        Iterator<Tile> selected = getBoard().iterator();
        Tile last_tile;
        Tile current_tile = selected.next();
        while(selected.hasNext()){
            last_tile = current_tile;
            current_tile = selected.next();
            if (last_tile.compareTo(current_tile)<0){
                solved = false;
            }
        }
        if (solved) {
            updateScoreboard();
        }
        return solved;
    }

    /**
     * Return whether tap is in 1st row, and col is a new col
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    boolean isValidTap(int position) {
        if (this.lastOccupiedColumn == -1) {
            setLastOccupiedColumnToStart();
        }
        int row = position / getGameSettings().getBoardSize();
        int col = position % getGameSettings().getBoardSize();
        if (row == getGameSettings().getBoardSize()-1 && col != this.lastOccupiedColumn) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    @Override
    void touchMove(int position) {

        int row = position / this.getGameSettings().getBoardSize();
        int col = position % this.getGameSettings().getBoardSize();
        int blankId = board.numTiles();
        if(isValidTap(position)){
            board.swapTiles(
                    this.getGameSettings().getBoardSize()-1,
                    col,
                    this.getGameSettings().getBoardSize()-1,
                    this.lastOccupiedColumn);
            this.lastOccupiedColumn = col;
        }
    }


    /**
     * - something to do with easier implementation
     * @return
     */
    @Override
    public double getScore() {
        //TODO: implement
        return 1.0;
    }


    public SeaInvaderSettings getSeaInvaderSettings() {
        return (SeaInvaderSettings) getGameSettings();
    }

    public void setLastOccupiedColumnToStart() {
        this.lastOccupiedColumn =  getSeaInvaderSettings().getBoardSize()-1;
    }

}
