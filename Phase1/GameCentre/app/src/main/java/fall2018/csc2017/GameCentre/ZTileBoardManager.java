package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import java.util.Random;


import java.io.Serializable;
import java.util.Iterator;


import java.util.ArrayList;

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
    ZTileBoardManager(Board board, String user, ZTileSettings zTileSettings,
                      AppCompatActivity appCompatActivity) {

        super(board, user, zTileSettings,
                appCompatActivity, new ZTileFactory());

    }


    ZTileBoardManager(String user, ZTileSettings zTileSettings) {
        super(user, zTileSettings, new ZTileFactory());
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     *
     * TODO: Need to test this out.
     */
    @Override
    boolean puzzleSolved() {
        boolean solved = false;
        Iterator<Tile> selected = getBoard().iterator();
        Tile current_tile = selected.next();
        while(selected.hasNext()){
            if (current_tile.getId() == 11){
                solved = true;
            }
            current_tile = selected.next();

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
        return true;
    }

    /**
     * Process a swipe by the direction, pushing all tiles to one side and merge as appropriate
     *
     * @param direction the direction (0 is up, 1 is down, 2 is left, 3 is right)
     */
    @Override
    void swipeTo(int direction) {
        saveState();
        moveCount += 1;
        if(direction == 0) {
            swipeUp();
        } else if (direction == 1) {
            swipeDown();
        } else if (direction == 2) {
            swipeLeft();
        } else if (direction == 3) {
            swipeRight();}
        randomSpawn();
    }

    void saveState(){
        int [] state = new int[board.numTiles()];
        int boardsize = board.getBoardSize();
        for(int i = 0; i < boardsize; i++){
            for(int j = 0; j < boardsize; j++){
                state[i * boardsize + j] = board.getTile(i, j).getId();
            }
        }
        moves.push(state);
        moveCount +=1;
    }

    void randomSpawn(){

        Random rand = new Random();
        int n = rand.nextInt(board.numTiles());
        int row = n / this.getGameSettings().getBoardSize();
        int col = n % this.getGameSettings().getBoardSize();
        while (board.getTile(row, col).getId() != 0) {
            n = rand.nextInt(board.numTiles());
            row = n / this.getGameSettings().getBoardSize();
            col = n % this.getGameSettings().getBoardSize();
        }
        int k = rand.nextInt(2);
        board.updateTile(n, new TileAlpha(k));
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */

    @Override
    void touchMove(int position) {
    }

    void swipeUp() {

        for (int i = 0; i < board.numTiles(); i++ ){
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            if (board.getTile(row,col).getId() != 0) {
                for (int j = row - 1; j >= 0; j --) {
                    if (row != 0) {
                        if ((board.getTile(j, col).getId() == board.getTile(row, col).getId())) {
                            int p = j * board.getBoardSize() + col;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(j, col).getId()));
                            break;
                        } else if ((board.getTile(j, col).getId() != board.getTile(row, col).getId()) &&
                                (board.getTile(j,col).getId() != 0)) {
                            int new_position = ((j+1) * board.getBoardSize() + col);
                            if (new_position == i) {
                                break;
                            }

                            board.updateTile(new_position, board.getTile(row, col));
                            board.updateTile(i, new TileAlpha(-1));
                            break;
                        }

                    }
                }

                if ((board.getTile(0,  col).getId() == 0)) {
                    board.updateTile(col, board.getTile(row, col));
                    board.updateTile(i, new TileAlpha(-1));
                }
            }
        }
    }

    void swipeDown() {
        for (int i = board.numTiles() - 1; i >= 0; i--) {
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            if (board.getTile(row,col).getId() != 0) {
                if (row != board.getBoardSize() - 1) {
                    for (int j = row + 1; j < board.getBoardSize(); j++) {

                        if ((board.getTile(j, col).getId() == board.getTile(row, col).getId())) {
                            int p = j * board.getBoardSize() + col;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(j, col).getId()));
                            break;
                        } else if ((board.getTile(j, col).getId() != board.getTile(row, col).getId()) &&
                                (board.getTile(j,col).getId() != 0)) {
                            int new_position = ((j-1) * board.getBoardSize() + col) ;
                            if (new_position == i) {
                                break;
                            }
                            board.updateTile(new_position, board.getTile(row, col));
                            board.updateTile(i, new TileAlpha(-1));
                            break;
                        }

                    }


                }
                if ((board.getTile(board.getBoardSize() - 1, col).getId() == 0)) {
                    int to_replace = (board.getBoardSize() - 1) * board.getBoardSize() + col;
                    board.updateTile(to_replace, board.getTile(row, col));
                    board.updateTile(i, new TileAlpha(-1));
                }
            }
        }
    }
    void swipeLeft () {
        for (int i = 0; i < board.numTiles(); i++) {
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            if (board.getTile(row,col).getId() != 0) {
                if (col != 0) {
                    for (int j = col - 1; j >= 0; j--) {
                        if ((board.getTile(row, j).getId() == board.getTile(row, col).getId())) {
                            int p = row * board.getBoardSize() + j;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(row, j).getId()));
                            break;
                        } else if ((board.getTile(row, col).getId() != board.getTile(row, j).getId()) &&
                                (board.getTile(row,j).getId() != 0)) {
                            int new_position = (row * board.getBoardSize() + j) + 1;
                            if (new_position == i) {
                                break;
                            }
                            board.updateTile(new_position, board.getTile(row, col));
                            board.updateTile(i, new TileAlpha(-1));
                            break;
                        }

                    }
                }


                if ((board.getTile(row, 0).getId() == 0)) {
                    int to_replace = row * board.getBoardSize();
                    board.updateTile(to_replace, board.getTile(row, col));
                    board.updateTile(i, new TileAlpha(-1));
                }
            }
        }
    }

    void swipeRight () {
        for (int i = board.numTiles() - 1; i >= 0; i--) {
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            if (board.getTile(row,col).getId() != 0) {
                if (col != board.getBoardSize() - 1) {
                    for (int j = col + 1; j < board.getBoardSize(); j++) {
                        if ((board.getTile(row, j).getId() == board.getTile(row, col).getId())) {
                            int p = row * board.getBoardSize() + j;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(row, j).getId()));
                            break;
                        } else if ((board.getTile(row, col).getId() != board.getTile(row, j).getId()) &&
                                (board.getTile(row,j).getId() != 0)) {
                            int new_position = (row * board.getBoardSize() + j) - 1;
                            if (new_position == i) {
                                break;
                            }
                            board.updateTile(new_position, board.getTile(row, col));
                            board.updateTile(i, new TileAlpha(-1));
                            break;
                        }
                    }

                }
                if ((board.getTile(row, board.getBoardSize() - 1).getId() == 0)) {
                    int to_replace = row * board.getBoardSize() + (board.getBoardSize() - 1);
                    board.updateTile(to_replace, board.getTile(row, col));
                    board.updateTile(i, new TileAlpha(-1));
                }
            }
        }



    }

    /**
     * Process a touch at position in the board, redo last move if appropriate.
     *
     * @param position the position
     */
    @Override
    void tapUndo(int position) {
        int numUndoes = ((ZTileSettings) gameSettings).getNumUndoes();
        int[] lastMove = moves.pop();
        moveCount += 1;
        if (numUndoes > 0) {
            ((ZTileSettings) gameSettings).setNumUndoes(numUndoes - 1);
            int boardsize = board.getBoardSize();
            for (int i = 0; i <= boardsize; i++) {
                for (int j = 0; j <= boardsize; j++) {
                    board.updateTile(i * boardsize + j,
                            new TileAlpha(lastMove[i * boardsize + j]));
                }
            }
            moveCount += 1;
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

