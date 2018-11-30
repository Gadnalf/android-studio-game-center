package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TooManyListenersException;

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
        notifyObservers();

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
     *
     * @param pos position of the tile
     * @param newTile the tile to replace at the position.
     */
    void updateTile(int pos, Tile newTile) {
        int row1 = pos / getBoardSize();
        int col1 = pos % getBoardSize();
        tiles[row1][col1] = newTile;
        setChanged();
        notifyObservers();
    }

    /**
     * update tile at pos with newTile.
     *
     * @param pos position of the tile.
     * @param newTile the tile to replace at the position.
     * @param notifyObservers notifies the observer.
     */
    void updateTile(int pos, Tile newTile, boolean notifyObservers) {
        int row1 = pos / getBoardSize();
        int col1 = pos % getBoardSize();
        tiles[row1][col1] = newTile;
        setChanged();
        if (notifyObservers) {
            notifyObservers();
        }
    }

    /**
     * Shuffles the board the tiles are placed randomly on the board.
     *
     */
    void shuffleTiles() {
        List<Tile> newTiles = new ArrayList<>();
        Iterator<Tile> iterator = iterator();
        while (iterator.hasNext()) {
            newTiles.add(iterator.next());
        }
        fixedShuffle(newTiles);
        setTiles(newTiles);
    }

    void fixedShuffle(List<Tile> tiles){
        List<Integer> random = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            random.add(1);
            random.add(2);
            random.add(3);
            random.add(4);
        }
        Collections.shuffle(random);
        for(int i = 0; i < random.size(); i++){
            int move = random.get(i);
            for(int j = 0; j < tiles.size(); j ++){
                if(tiles.get(j).getId() == 25){
                    if(move == 1){
                        if(0 <= j - boardSize){
                         Tile saveTile = tiles.get(j);
                         tiles.set(j , tiles.get(j - boardSize));
                         tiles.set(j - boardSize, saveTile);
                        }
                    }else if(move == 2){
                        if( j + boardSize < tiles.size()){
                            Tile saveTile = tiles.get(j);
                            tiles.set(j , tiles.get(j + boardSize));
                            tiles.set(j + boardSize, saveTile);
                        }
                    }else if(move == 3){
                        if(0 <= j - 1 && j % boardSize != 0){
                            Tile saveTile = tiles.get(j);
                            tiles.set(j , tiles.get(j - 1));
                            tiles.set(j - 1, saveTile);
                        }
                    }else{
                        if( j + 1 < tiles.size() && j % boardSize != 3){
                            Tile saveTile = tiles.get(j);
                            tiles.set(j , tiles.get(j + 1));
                            tiles.set(j + 1, saveTile);
                        }
                    }
                }
            }
        }
    }


    /**
     * Returns the information about the board in the string form.
     *
     * @return string representation of the board.
     */
    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}
