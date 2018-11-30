package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SlidingTilesBoardManagerTest extends AbstractBoardManagerTest {

    static int boardSize = 4;
    SlidingTilesBoardManager boardManager;

    @Override
    @Before
    public void setUp(){
        List<Tile> tiles = SlidingTilesTestingHelpers.makeTiles(boardSize);
        Board board = new Board(tiles);
        String user = "phil";
        SlidingTilesSettings slidingTileSettings =  new SlidingTilesSettings(boardSize, 4);
        boardManager = new SlidingTilesBoardManager(board, user, slidingTileSettings);
    }

//    public void testScoreUpating(Method updater) {
//        updater();
//        assertEquals((long) boardManager.getScore(), (long) 10.0);
//        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
//        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
//        updateScoreboard();
//        assert boardManager.getScore() < 10.0;
//    }

    @Override
    public void testUpdateScoreboard() {
        boardManager.updateScoreboard();
        SlidingTilesTestingHelpers.swapFirstTwoTiles(boardManager);
        SlidingTilesTestingHelpers.swapFirstTwoTiles(boardManager);
        boardManager.updateScoreboard();
        assertTrue(boardManager.getScore() > 0);
    }

    @Test
    public void testSetBoardSize() {
        super.testSetBoardSize(boardManager);
    }

    @Test
    public void testPuzzleSolved() {
        System.out.println(boardManager.puzzleSolved());
        SlidingTilesTestingHelpers.swapFirstTwoTiles(boardManager);
        SlidingTilesTestingHelpers.swapFirstTwoTiles(boardManager);
        System.out.println(boardManager.puzzleSolved());
        assertTrue(boardManager.getScore() > 0);
    }


    @Test
    public void touchMove() {
    }

    @Test
    public void isValidUndo() {
    }

    @Test
    public void tapUndo() {
        boardManager.board.updateTile(0, new TileNum(24));
        boardManager.board.updateTile(1, new TileNum(15));
        SlidingTilesTestingHelpers.swapFirstTwoTiles(boardManager);
        Tile secondTile = boardManager.board.getTile(0, 1);
        assertEquals(25, secondTile.getId());
        boardManager.tapUndo(1);
        Tile newSecond = boardManager.board.getTile(0, 1);
        assertEquals(16, newSecond.getId());
         }

    @Test
    public void setBoardSize() {
    }

    @Test
    public void getScore() {
    }

    /**
     * Shuffle a few tiles.
     */
    public void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        System.out.println(boardManager.puzzleSolved());
        assertEquals(true, boardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertEquals(false, boardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getTile(0, 1).getId());
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        assertEquals(15, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(25, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        assertEquals(25, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        assertEquals(true, boardManager.isValidTap(11));
        assertEquals(true, boardManager.isValidTap(14));
        assertEquals(false, boardManager.isValidTap(10));
    }

}