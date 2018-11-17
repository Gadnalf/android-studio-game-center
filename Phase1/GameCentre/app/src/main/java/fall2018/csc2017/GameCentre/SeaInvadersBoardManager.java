package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Iterator;


public class SeaInvadersBoardManager extends AbstractBoardManager implements Serializable {


    int lastOccupiedColumn = 0; //TODO: make sure he starts here

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    SeaInvadersBoardManager(Board board, User user, SeaInvaderSettings seaInvaderSettings,
                             AppCompatActivity appCompatActivity) {
        super(board, user, seaInvaderSettings,
                appCompatActivity);
    }


    SeaInvadersBoardManager(User user, SeaInvaderSettings seaInvaderSettings) {
        super(user, seaInvaderSettings, new SeaInvadersTileFactory());
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
        int row = position / getGameSettings().getBoardSize();
        int col = position % getGameSettings().getBoardSize();
        if (row == 0 && col != lastOccupiedColumn) {
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
            board.swapTiles(1, lastOccupiedColumn, 1, col);
        }
    }


    /**
     * notice higher a/b => lower score
     * higher b => lower score
     * lower a => lower score
     * min a and max b => higher score
     * we go with 10 bc I forget :)
     * TODO: add why I went with 10
     * - something to do with easier implementation
     * @return
     */
    @Override
    public double getScore() {
        double a = (double) getMoveCount();
        double timeWeight = (getTimePlayed()/1000);
        double numUndosWeight = getSeaInvaderSettings().getNumUndoes();
        double b = (double) (timeWeight + numUndosWeight);
        return 10-(a/b); //want to maximize this
    }


    public SeaInvaderSettings getSeaInvaderSettings() {
        return (SeaInvaderSettings) getGameSettings();
    }

}
