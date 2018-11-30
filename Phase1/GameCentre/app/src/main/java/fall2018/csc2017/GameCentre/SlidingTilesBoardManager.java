package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import java.io.Serializable;
import java.util.Iterator;


/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class SlidingTilesBoardManager extends AbstractBoardManager implements Serializable {



    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    SlidingTilesBoardManager(Board board, String user, SlidingTilesSettings slidingTilesSettings,
                             AppCompatActivity appCompatActivity) {
        super(board, user, slidingTilesSettings,
                appCompatActivity, new SlidingTilesTileFactory());
    }

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     * @param user the user
     * @param slidingTilesSettings the setting of the sliding tile games.
     */
    SlidingTilesBoardManager(Board board, String user, SlidingTilesSettings slidingTilesSettings) {
        super(board, user, slidingTilesSettings, new SlidingTilesTileFactory());
    }


    /**
     * manage a new board
     * @param user user
     * @param slidingTilesSettings the setting of the sliding tiles games.
     */
    SlidingTilesBoardManager(String user, SlidingTilesSettings slidingTilesSettings) {
        super(user, slidingTilesSettings, new SlidingTilesTileFactory());
        board.shuffleTiles();

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
            if (getAppCompatActivity() != null) {
                updateScoreboard();
            }
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    boolean isValidTap(int position) {

        int row = position / getGameSettings().getBoardSize();
        int col = position % getGameSettings().getBoardSize();
        int blankId = 25;
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == getGameSettings().getBoardSize() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == getGameSettings().getBoardSize()- 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
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
        int blankId = 25;
        if(isValidTap(position)){
            Tile above = row == 0 ? null : board.getTile(row - 1, col);
            Tile below = row == this.getGameSettings().getBoardSize() - 1 ? null : board.getTile(row + 1, col);
            Tile left = col == 0 ? null : board.getTile(row, col - 1);
            Tile right = col == this.getGameSettings().getBoardSize() - 1 ? null : board.getTile(row, col + 1);
            if(below != null && below.getId() == blankId){
                board.swapTiles(row,col,row+1,col);
                int[] move = {row, col, row + 1, col};
                moves.push(move);
                moveCount += 1;

            }
            if(above != null && above.getId() == blankId){
                board.swapTiles(row,col,row-1,col);
                int[] move = {row, col, row - 1, col};
                moves.push(move);
                moveCount += 1;
            }
            if(left != null && left.getId() == blankId){
                board.swapTiles(row,col,row,col-1);
                int[] move = {row, col, row, col - 1};
                moves.push(move);
                moveCount += 1;
            }
            if(right != null && right.getId() == blankId){
                board.swapTiles(row,col,row,col+1);
                int[] move = {row, col, row, col + 1};
                moves.push(move);
                moveCount += 1;
            }
        }
    }

    @Override
    boolean gameOver(){
        return false;
    }

    @Override
    void swipeTo(int direction) {
    }

    /**
     * Return whether the tile is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is the blank tile
     */
    @Override
    boolean isValidUndo(int position) {
        int row = position / getGameSettings().getBoardSize();
        int col = position % getGameSettings().getBoardSize();
        int blankId = getBoard().numTiles();
        Tile current = getBoard().getTile(row, col);
        return (current.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, redo last move if appropriate.
     *
     * @param position the position
     */
    @Override
    void tapUndo(int position) {
        if(isValidUndo(position)){
            int numUndoes = ((SlidingTilesSettings) gameSettings).getNumUndoes();
            int[] lastMove = moves.pop();
            int row1 = lastMove[0];
            int col1 = lastMove[1];
            int row2 = lastMove[2];
            int col2 = lastMove[3];
            board.swapTiles(row1,col1,row2,col2);
            moveCount += 1;
            if(numUndoes > 0) {
                ((SlidingTilesSettings) gameSettings).setNumUndoes(numUndoes - 1);
            }
        }

    }

    /**
     * Sets the board size and generates a new board for the sliding tiles game.
     *
     * @param boardSize size of the board indicated by integer.
     */
    @Override
    public void setBoardSize(int boardSize) {
        super.setBoardSize(boardSize);
        board.shuffleTiles();
    }

    /**
     * notice higher a/b => lower score
     * higher b => lower score
     * lower a => lower score
     * min a and max b => higher score
     * we go with 10 bc I forget :)
     * TODO: add why I went with 10
     * - something to do with easier implementation
     *
     * @return double that represent score of the game
     */
    @Override
    public double getScore() {
        double a = (double) getMoveCount();
        double timeWeight = (getTimePlayed()/1000);
        double numUndoesWeight = getSlidingTileSettings().getNumUndoes();
        double b = (timeWeight + numUndoesWeight);
        return 10-(a/b); //want to maximize this
    }

    /**
     * Returns the settings of the sliding tiles game currently being played
     *
     * @return SlidingTileSettings class
     */

    SlidingTilesSettings getSlidingTileSettings() {
        return (SlidingTilesSettings) getGameSettings();
    }

}
