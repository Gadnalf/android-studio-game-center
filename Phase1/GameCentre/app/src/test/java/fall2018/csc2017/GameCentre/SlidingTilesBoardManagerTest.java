package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        List<Tile> tiles = TestingHelpers.makeTiles(boardSize);
        Board board = new Board(tiles);
        User user = new User("jim");
        SlidingTilesSettings slidingTilesSettings =  new SlidingTilesSettings(boardSize, 4);
        boardManager = new SlidingTilesBoardManager(board, user, slidingTilesSettings);
    }

//    public void testScoreUpating(Method updater) {
//        updater();
//        assertEquals((long) boardManager.getScore(), (long) 10.0);
//        TestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
//        TestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
//        updateScoreboard();
//        assert boardManager.getScore() < 10.0;
//    }

    @Override
    public void testUpdateScoreboard() {
        boardManager.updateScoreboard();
        assertEquals((long) boardManager.getScore(), (long) 10.0);
        TestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        TestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        boardManager.updateScoreboard();
        assertTrue(boardManager.getScore() < 10.0);
    }

    @Test
    public void testSetBoardSize() {
        super.testSetBoardSize(boardManager);
    }

    @Test
    public void testPuzzleSolved() {
        System.out.println(boardManager.puzzleSolved());
        assertEquals((long) boardManager.getScore(), (long) 10.0);
        TestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        TestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        System.out.println(boardManager.puzzleSolved());
        assertTrue(boardManager.getScore() < 10.0);
    }


    @Test
    public void touchMove() {
    }

    @Test
    public void isValidUndo() {
    }

    @Test
    public void tapUndo() {
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
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        assertEquals(16, boardManager.getBoard().getTile(3, 2).getId());
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