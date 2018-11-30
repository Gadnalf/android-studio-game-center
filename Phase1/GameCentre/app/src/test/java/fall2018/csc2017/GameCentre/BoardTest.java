package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void TestImpossible(){
        List<Tile> tiles = SlidingTilesTestingHelpers.makeTiles(3);
        Board board = new Board(tiles);
        assertFalse(board.isImpossible(tiles));
        tiles.set(7,new TileNum(7));
        tiles.set(6,new TileNum(8));
        assertTrue(board.isImpossible(tiles));
        tiles = SlidingTilesTestingHelpers.makeTiles(4);
        assertFalse(board.isImpossible(tiles));
        tiles.set(14,new TileNum(14));
        tiles.set(13,new TileNum(15));
        assertTrue(board.isImpossible(tiles));
        tiles = SlidingTilesTestingHelpers.makeTiles(5);
        assertFalse(board.isImpossible(tiles));
        tiles.set(24,new TileNum(24));
        tiles.set(23,new TileNum(25));
        assertTrue(board.isImpossible(tiles));
    }
}
