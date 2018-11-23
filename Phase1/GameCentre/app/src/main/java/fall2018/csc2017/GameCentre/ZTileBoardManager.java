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
        return true;
    }

    /**
     * Process a swipe by the direction, pushing all tiles to one side and merge as appropriate
     *
     * @param direction the direction (0 is up, 1 is down, 2 is left, 3 is right)
     */
    @Override
    void swipeTo(int direction) {
        if(direction == 0) {
            swipeUp();
        } else if (direction == 1) {
            swipeDown();
        } else if (direction == 2) {
            swipeLeft();
        } else if (direction == 3) {
            swipeRight();}
    }
    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */

    @Override
    void touchMove(int position) {
    }

    /**
     * TODO: insert your code here John
     */
    void swipeUp() {

        for (int i = 0; i < board.numTiles(); i++ ){
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            System.out.print("i: ");
            System.out.println(i);
            if (board.getTile(row,col).getId() != 0) {
                for (int j = row - 1; j >= 0; j --) {
                    System.out.print("j: ");
                    System.out.println(j);

                    if (row != 0) {
                        System.out.print("for j position: ");
                        System.out.println((board.getTile(j, col).getId()));
                        System.out.print("for i position: ");
                        System.out.println(board.getTile(row, col).getId());
                        if ((board.getTile(j, col).getId() == board.getTile(row, col).getId())) {
                            int p = j * board.getBoardSize() + col;
                            board.updateTile(i, new TileAlpha(-1));
                            System.out.print("board backgroundId n : ");
                            System.out.println(board.getTile(j, col).getId());
                            board.updateTile(p, new TileAlpha(board.getTile(j, col).getId()));
                            System.out.print("this spanns row (adding): ");
                            System.out.println(j);
                            break;
                        } else if ((board.getTile(j, col).getId() != board.getTile(row, col).getId()) &&
                                (board.getTile(j,col).getId() != 0)) {
                            System.out.print("this spanns row (juxtapose): ");
                            System.out.println(j);
                            int new_position = ((j+1) * board.getBoardSize() + col);
                            if (new_position == i) {
                                break;
                            }

                            board.swapTiles(row, col, j + 1, col);

                            break;
                        }

                    }
                }
                if ((board.getTile(0,  col).getId() == 0)) {
                    board.swapTiles(row, col, 0, col);
                    System.out.println("Testing checking 2 and 4");
                }
            }
        }

        Random rand = new Random();
        int n = rand.nextInt(board.numTiles());
        int row = n / this.getGameSettings().getBoardSize();
        int col = n % this.getGameSettings().getBoardSize();
        while (board.getTile(row, col).getId() != 0) {
            n = rand.nextInt(board.numTiles());
            row = n / this.getGameSettings().getBoardSize();
            col = n % this.getGameSettings().getBoardSize();
        }
        System.out.print("This is the random number: ");
        System.out.println(n);
        board.updateTile(n, new TileAlpha(0));

    }

    void swipeDown() {
        for (int i = board.numTiles() - 1; i >= 0; i--) {
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            System.out.print("i: ");
            System.out.println(i);
            if (board.getTile(row,col).getId() != 0) {
                if (row != 3) {
                    for (int j = row + 1; j < 4; j++) {
                        System.out.print("j: ");
                        System.out.println(j);

                        if ((board.getTile(j, col).getId() == board.getTile(row, col).getId())) {
                            int p = j * board.getBoardSize() + col;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(j, col).getId()));
                            System.out.print("this spanns row (adding): ");
                            System.out.println(j);
                            break;
                        } else if ((board.getTile(j, col).getId() != board.getTile(row, col).getId()) &&
                                (board.getTile(j,col).getId() != 0)) {
                            System.out.print("this spanns row (juxtapose): ");
                            System.out.println(j);
                            int new_position = ((j-1) * board.getBoardSize() + col) ;
                            if (new_position == i) {
                                break;
                            }
                            board.swapTiles(row, col, j - 1, col);
                            break;
                        }

                    }


                }
                if ((board.getTile(3, col).getId() == 0)) {
                    board.swapTiles(row, col, 3, col);
                    System.out.println("Testing checking 2 and 4");
                }
            }
        }
        Random rand = new Random();
        int n = rand.nextInt(board.numTiles());
        int row = n / this.getGameSettings().getBoardSize();
        int col = n % this.getGameSettings().getBoardSize();
        while (board.getTile(row, col).getId() != 0) {
            n = rand.nextInt(board.numTiles());
            row = n / this.getGameSettings().getBoardSize();
            col = n % this.getGameSettings().getBoardSize();
        }
        System.out.print("This is the random number: ");
        System.out.println(n);
        board.updateTile(n, new TileAlpha(0));

    }
    void swipeLeft () {
        for (int i = 0; i < board.numTiles(); i++) {
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            System.out.print("i: ");
            System.out.println(i);
            if (board.getTile(row,col).getId() != 0) {
                if (col != 0) {
                    for (int j = col - 1; j >= 0; j--) {
                        System.out.print("j: ");
                        System.out.println(j);
                        if ((board.getTile(row, j).getId() == board.getTile(row, col).getId())) {
                            int p = row * board.getBoardSize() + j;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(row, j).getId()));
                            System.out.print("this spanns row (adding): ");
                            System.out.println(j);
                            break;
                        } else if ((board.getTile(row, col).getId() != board.getTile(row, j).getId()) &&
                                (board.getTile(row,j).getId() != 0)) {
                            System.out.print("this spanns row (juxtapose): ");
                            System.out.println(j);
                            int new_position = (row * board.getBoardSize() + j) + 1;
                            if (new_position == i) {
                                break;
                            }
                            board.swapTiles(row, col, row, j + 1);
                            break;
                        }

                    }
                }
                if ((board.getTile(row, 0).getId() == 0)) {
                    board.swapTiles(row, col, row, 0);
                    System.out.println("Testing checking 2 and 4");
                }
            }
        }

        Random rand = new Random();
        int n = rand.nextInt(board.numTiles());
        int row = n / this.getGameSettings().getBoardSize();
        int col = n % this.getGameSettings().getBoardSize();
        while (board.getTile(row, col).getId() != 0) {
            n = rand.nextInt(board.numTiles());
            row = n / this.getGameSettings().getBoardSize();
            col = n % this.getGameSettings().getBoardSize();
        }
        System.out.print("This is the random number: ");
        System.out.println(n);
        board.updateTile(n, new TileAlpha(0));

    }

    void swipeRight () {
        for (int i = board.numTiles() - 1; i >= 0; i--) {
            int row = i / board.getBoardSize();
            int col = i % board.getBoardSize();
            System.out.print("i: ");
            System.out.println(i);
            if (board.getTile(row,col).getId() != 0) {
                if (col != 3) {
                    for (int j = col + 1; j < board.getBoardSize(); j++) {
                        if ((board.getTile(row, j).getId() == board.getTile(row, col).getId())) {
                            int p = row * board.getBoardSize() + j;
                            board.updateTile(i, new TileAlpha(-1));
                            board.updateTile(p, new TileAlpha(board.getTile(row, j).getId()));
                            System.out.print("this spanns row (adding): ");
                            System.out.println(j);
                            break;
                        } else if ((board.getTile(row, col).getId() != board.getTile(row, j).getId()) &&
                                (board.getTile(row,j).getId() != 0)) {
                            System.out.print("this spanns row (juxtapose): ");
                            System.out.println(j);
                            int new_position = (row * board.getBoardSize() + j) - 1;
                            if (new_position == i) {
                                break;
                            }
                            board.swapTiles(row, col, row, j - 1);
                            break;

                        }

                    }
                    if ((board.getTile(row, 3).getId() == 0)) {
                        board.swapTiles(row, col, row, 3);
                        System.out.println("Testing checking 2 and 4");
                    }


                }
            }
        }

        Random rand = new Random();
        int n = rand.nextInt(board.numTiles());
        int row = n / this.getGameSettings().getBoardSize();
        int col = n % this.getGameSettings().getBoardSize();
        while (board.getTile(row, col).getId() != 0) {
            n = rand.nextInt(board.numTiles());
            row = n / this.getGameSettings().getBoardSize();
            col = n % this.getGameSettings().getBoardSize();
        }
        System.out.print("This is the random number: ");
        System.out.println(n);
        board.updateTile(n, new TileAlpha(0));


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
                ((ZTileSettings) gameSettings).setNumUndoes(numUndoes - 1);
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



