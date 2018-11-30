package fall2018.csc2017.GameCentre;

import java.util.Random;
import java.io.Serializable;


/**
 * Manages the board of the Alphabet Tiles game.
 */
public class AlphabetTilesBoardManager extends  AbstractBoardManager implements Serializable{

    /**
     * Manage a board that has been pre-populated for the Alphabet Tiles game.
     *
     * @param user User of the game.
     * @param alphabetTilesSettings Settings for the game.
     */
    AlphabetTilesBoardManager(String user, AlphabetTilesSettings alphabetTilesSettings) {
        super(user, alphabetTilesSettings, new AlphabetTileFactory());
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     *
     */
    @Override
    boolean puzzleSolved() {
        boolean solved = false;
        int boardSize = board.getBoardSize();
        for(int i = 0; i < boardSize; i ++) {
            for(int j = 0; j < boardSize; j ++){
                if(board.getTile(i , j).getId() == 11){
                    solved = true;
                }
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
     * Informs whether the game is over or not. The game is over if the board contains a Z tile or
     * any move will not change the board
     *
     * @return True if the game is over false otherwise.
     */
    boolean gameOver() {
        boolean over = true;
        int boardSize = board.getBoardSize();
        for(int i = 0; i < boardSize; i ++) {
            for(int j = 0; j < boardSize; j ++){
                if(board.getTile(i , j).getId() == 0){
                    over = false;
                    break;
                } else if (board.getTile(i , j).getId() == 26) {
                    over = true;
                    break;
                } else {
                    Tile current_tile = board.getTile(i, j);
                    Tile above = i == 0 ? null : board.getTile( i - 1, j);
                    Tile below = i == getGameSettings().getBoardSize() - 1 ? null : board.getTile(i + 1, j);
                    Tile left = j == 0 ? null : board.getTile(i, j - 1);
                    Tile right = j == getGameSettings().getBoardSize()- 1 ? null : board.getTile(i, j + 1);
                    int current_id = current_tile.getId();
                    if((below != null && below.getId() == current_id)
                            || (above != null && above.getId() == current_id)
                            || (left != null && left.getId() == current_id)
                            || (right != null && right.getId() == current_id)){
                        over = false;
                        break;
                    }
                }
            }
        }
        return over;
    }

    /**
     * Place holder for the method from the AbstractBoardManager.
     *
     * @param position the tile to check
     * @return True only.
     */
    @Override
    boolean isValidTap(int position) {
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
        updateScoreboard();
    }

    /**
     * Saves each state of the game upon each move.
     */
    private void saveState(){
        int [] state = new int[board.numTiles()];
        int boardSize = board.getBoardSize();
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                state[i * boardSize + j] = board.getTile(i, j).getId();
            }
        }
        moves.push(state);
        moveCount +=1;
    }


    /**
     * Randomly spawns A or B tile to the board.
     */
    private void randomSpawn(){

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

    /**
     * Updates the board when swiped up. If there are same tiles adjacent to each other in vertical
     * directions, stacks the tile together and push all the tiles up. Also updates the
     *
     */
    private void swipeUp() {

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


    /**
     * Updates the board when swiped down. If there are same tiles adjacent to each other in vertical
     * directions, stacks the tile together and push all the tiles down.
     *
     */
    private void swipeDown() {
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

    /**
     * Updates the board when swiped left. If there are same tiles adjacent to each other in horizontal
     * directions, stacks the tile together and push all the tiles left.
     *
     */
    private void swipeLeft () {
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


    /**
     * Updates the board when swiped right. If there are same tiles adjacent to each other in horizontal
     * directions, stacks the tile together and push all the tiles right.
     *
     */
    private void swipeRight () {
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
    }



    /**
     * Function used to calculate score
     *
     *
     */
    @Override
    public double getScore() {
        int total_score = 0;
        int boardSize = board.getBoardSize();
        for(int i = 0; i < boardSize; i ++) {
            for(int j = 0; j < boardSize; j ++){
                if (board.getTile(i, j).getId() == 0) {
                    total_score += 0;
                } else {
                    total_score += Math.pow(2, board.getTile(i, j).getId());
                }
            }
        }
        return total_score;

    }
    @Override
    public boolean moveIsEmpty() {
        return moves.empty();
    }

    /**
     * This method returns the AlphabetTilesSettings which was set up before the start of the game
     *
     * @return settings class initiated before the start of the game.
     */
    AlphabetTilesSettings getAlphabetTilesSettings() {
        return (AlphabetTilesSettings) getGameSettings();
    }


    /**
     * Processes undo. Updates the board to the state prior to the most recent move.
     */
    public void undo() {
        int numUndoes = (gameSettings).getNumUndoes();
        int[] lastMove = moves.pop();
        int boardSize = board.getBoardSize();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int pos = i * boardSize + j;
                int old = lastMove[pos];
                TileAlpha oldTile = new TileAlpha(old - 1);
                board.updateTile(pos, oldTile);
            }
        }
        if (numUndoes > 0) {
            ((AlphabetTilesSettings) gameSettings).setNumUndoes(numUndoes - 1);
        }
    }
}

