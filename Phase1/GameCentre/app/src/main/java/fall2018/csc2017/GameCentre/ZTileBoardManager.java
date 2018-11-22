package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import java.util.Random;


import java.io.Serializable;
import java.util.Iterator;


import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Stack;

/**
 * Need Comment
 */
public class ZTileBoardManager extends  AbstractBoardManager implements Serializable{


    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    ZTileBoardManager(Board board, User user, ZTileSettings zTileSettings,
                             AppCompatActivity appCompatActivity) {

        super(board, user, zTileSettings,
                appCompatActivity);

    }


    ZTileBoardManager(User user, ZTileSettings zTileSettings) {
        super(user, zTileSettings, new ZTileFactory());
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
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    boolean isValidTap(int position) {
        return true;
    }

    @Override
    boolean isValidSwipe(int direction) {
        return false;
    }

    @Override
    void swipeTo(int direction) {
    }
    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    @Override
    void touchMove(int position) {


        for (int i = board.numTiles() - 1; i >= 0; i-- ){
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            if (board.getTile(row,col).getId() == 2 || board.getTile(row,col).getId() == 4) {

                for (int j = col + 1; j < board.getBoardSize(); j ++) {
                    if ((j == 4)){
                        break;
                    } else if ((board.getTile(row, j).getId() == 2) && (board.getTile(row, col).getId() == 2)) {
                        int p = row * board.getBoardSize() + j;
                        board.updateTile(i, new TileAlpha(0));
                        board.updateTile(p, new TileAlpha(3));
                        System.out.println(j);
                    } else if ((((board.getTile(row, j).getId()) == 4 && (board.getTile(row, col)).getId() == 2)) ||
                            ((board.getTile(row, j).getId() == 2) && board.getTile(row, col).getId() == 4)) {
                        int new_position = (row * board.getBoardSize() + j) - 1;
                        if (new_position == i) {
                            break;
                        }
                        board.updateTile(new_position, board.getTile(row, col));
                        board.updateTile(i, new TileAlpha(0));
                    }

                }
                if ((board.getTile(row, 3).getId() != 2) && (board.getTile(row, 3).getId() != 4)) {
                    board.swapTiles(row, col, row, 3);
                    System.out.println("Testing checking 2 and 4");
                }


            }
        }

        Random rand = new Random();
        int n = rand.nextInt(board.numTiles());
        int row = n / this.getGameSettings().getBoardSize();
        int col = n % this.getGameSettings().getBoardSize();
        while (board.getTile(row,col).getId() == 2 || board.getTile(row, col).getId() == 4) {
            n = rand.nextInt(board.numTiles());
            row = n / this.getGameSettings().getBoardSize();
            col = n % this.getGameSettings().getBoardSize();
        }
        System.out.print("This is the random number: ");
        System.out.println(n);
        board.updateTile(n, new TileAlpha(1));


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
            int numUndoes = ((ZTileSettings) gameSettings).getNumUndoes();
            int[] lastMove = moves.pop();
            int row1 = lastMove[0];
            int col1 = lastMove[1];
            int row2 = lastMove[2];
            int col2 = lastMove[3];
            board.swapTiles(row1,col1,row2,col2);
            moveCount += 1;
            if(numUndoes > 0) {
                ((SlidingTileSettings) gameSettings).setNumUndoes(numUndoes - 1);
            }
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
        double numUndosWeight = getZTileSettings().getNumUndoes();
        double b = (double) (timeWeight + numUndosWeight);
        return 10-(a/b); //want to maximize this
    }


    public ZTileSettings getZTileSettings() {
        return (ZTileSettings) getGameSettings();
    }

}



