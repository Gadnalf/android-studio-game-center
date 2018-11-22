package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    private int boardSize;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        setTiles(tiles);

    }

    public void setTiles(List<Tile> tiles) {
        boardSize = (int) Math.sqrt(tiles.size());
        this.tiles = new Tile[boardSize][boardSize];
        Iterator<Tile> iter = tiles.iterator();


        for (int row = 0; row != boardSize; row++) {
            for (int col = 0; col != boardSize; col++) {
                this.tiles[row][col] = iter.next();
            }
        }

    }

    public void setBoardSize (int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize () {
        return this.boardSize;
    }

    @Override
    @NonNull
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * Iterate over a board of tiles.
     */
    private class BoardIterator implements Iterator<Tile> {

        /**
         * The id of the next tile on the board.
         */
        private int next_id = 0;

        @Override
        public boolean hasNext(){
            return next_id < numTiles();
        }

        @Override
        public Tile next() {
            Tile result = getTile(next_id/boardSize, next_id%boardSize);
            next_id++;
            return result;
        }

    }

    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board
     */
    int numTiles() {
        return boardSize*boardSize;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        setChanged();
        notifyObservers();
    }

    void swapTiles(int row1, int col1, int row2, int col2, boolean notifyObservers) {
        Tile temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        setChanged();
        if (notifyObservers){
            notifyObservers();
        }
    }

    /**
     * update tile at pos with newTile
     * @param pos
     * @param newTile
     */
    void updateTile(int pos, Tile newTile) {
        int row1 = pos / getBoardSize();
        int col1 = pos % getBoardSize();
        tiles[row1][col1] = newTile;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}
