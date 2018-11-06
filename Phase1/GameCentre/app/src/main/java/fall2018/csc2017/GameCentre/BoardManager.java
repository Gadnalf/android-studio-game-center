package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;
    public ScoreBoard scoreBoard;
    private long score;
    private User user;
    private SlidingTileSettings slidingTileSettings;
    private Stack<int[]> moves = new Stack<>();
    private int moveCount;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    BoardManager(Board board, User user, SlidingTileSettings slidingTileSettings) {
        this.board = board;
        this.scoreBoard = new ScoreBoard(user, slidingTileSettings);
        this.user = user;
        this.slidingTileSettings = slidingTileSettings;
        this.moveCount = 0;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board.
     */
    BoardManager(User user, SlidingTileSettings slidingTileSettings) {
        List<Tile> tiles;
        TilesFactory tilesFactory = new TilesFactory();
        tiles = tilesFactory.getTiles(slidingTileSettings.getBoardSize());

        Collections.shuffle(tiles);
        this.board = new Board(tiles);
        this.user = user;
        this.slidingTileSettings = slidingTileSettings;
        this.scoreBoard = new ScoreBoard(user, slidingTileSettings);
    }


    public void setBoardSize(int boardSize) {
        List<Tile> tiles;
        TilesFactory tilesFactory = new TilesFactory();
        tiles = tilesFactory.getTiles(boardSize);

        Collections.shuffle(tiles);
        this.board.setBoardSize(boardSize);
        this.board.setTiles(tiles);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
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
            this.score = this.scoreBoard.getScoreAndUpdateScoreBoard();
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / this.getSlidingTileSettings().getBoardSize();
        int col = position % this.getSlidingTileSettings().getBoardSize();
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == this.getSlidingTileSettings().getBoardSize() - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == this.getSlidingTileSettings().getBoardSize()- 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Return whether the tile is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is the blank tile
     */
    boolean isValidUndo(int position) {
        int row = position / this.getSlidingTileSettings().getBoardSize();
        int col = position % this.getSlidingTileSettings().getBoardSize();
        int blankId = board.numTiles();
        Tile current = board.getTile(row, col);
        return (current.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, redo last move if appropriate.
     *
     * @param position the position
     */
    void tapUndo(int position) {
        if(isValidUndo(position)){
            int numUndoes = slidingTileSettings.getNumUndoes();
            if(numUndoes > 0) {
                int[] lastMove = moves.pop();
                int row1 = lastMove[0];
                int col1 = lastMove[1];
                int row2 = lastMove[2];
                int col2 = lastMove[3];
                board.swapTiles(row1,col1,row2,col2);
                slidingTileSettings.setNumUndoes(numUndoes - 1);
                moveCount += 1;
            }else if(numUndoes == -1) {
                int[] lastMove = moves.pop();
                int row1 = lastMove[0];
                int col1 = lastMove[1];
                int row2 = lastMove[2];
                int col2 = lastMove[3];
                board.swapTiles(row1,col1,row2,col2);
                moveCount += 1;
            }
        }

    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / this.getSlidingTileSettings().getBoardSize();
        int col = position % this.getSlidingTileSettings().getBoardSize();
        int blankId = board.numTiles();
        if(isValidTap(position)){
            this.scoreBoard.move();
            Tile above = row == 0 ? null : board.getTile(row - 1, col);
            Tile below = row == this.getSlidingTileSettings().getBoardSize() - 1 ? null : board.getTile(row + 1, col);
            Tile left = col == 0 ? null : board.getTile(row, col - 1);
            Tile right = col == this.getSlidingTileSettings().getBoardSize() - 1 ? null : board.getTile(row, col + 1);
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

    public boolean moveIsEmpty() {
        return moves.empty();
    }


    public long getScore() {
        return this.score;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }


    public void setBoard(Board board) {
        this.board = board;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.scoreBoard.setUser(user);
    }

    public SlidingTileSettings getSlidingTileSettings() {
        return slidingTileSettings;
    }

    public void setSlidingTileSettings(SlidingTileSettings slidingTileSettings) {
        this.slidingTileSettings = slidingTileSettings;
        this.scoreBoard.setSlidingTileSettings(slidingTileSettings);
    }

    public class TilesFactory {
        public List<Tile> getTiles(int boardSize) {
            List<Tile> tiles = new ArrayList<>();
            int numTiles = boardSize*boardSize;

            if (boardSize == 3) {
                for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                    tiles.add(new TileSizeThree(tileNum));
                }
            } else if (boardSize == 4) {
                for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                    tiles.add(new TileSizeFour(tileNum));
                }
            } else if (boardSize == 5) {
                for (int tileNum = 0; tileNum != numTiles; tileNum ++) {
                    tiles.add(new TileSizeFive(tileNum));
                }
            }

            return tiles;


        }
    }
}
