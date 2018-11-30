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
        List<Tile> tiles = SlidingTilesTestingHelpers.makeTiles(boardSize);
        Board board = new Board(tiles);
        String user = "phil";
        SlidingTilesSettings slidingTileSettings =  new SlidingTilesSettings(boardSize, 4);
        boardManager = new SlidingTilesBoardManager(board, user, slidingTileSettings);

    }

//    public void testScoreUpating(Method updater) {
//        updater();
//        assertEquals((long) boardManager.computeScore(), (long) 10.0);
//        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
//        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
//        updateScoreboard();
//        assert boardManager.computeScore() < 10.0;
//    }

    @Override
    public void testUpdateScoreboard() {
        boardManager.setStartTime(System.nanoTime() - (long) (Math.pow(10, 10)*30.0)); //-30 seconds
        long initScore = (long) boardManager.computeScore();
        assertEquals(initScore, (long) 9589);
        boardManager.updateScoreboard();
        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        boardManager.updateScoreboard();
        assertTrue((long) boardManager.getScore() == (long) 6481);
    }

    @Test
    public void testScoreComputation() {
        boardManager.setStartTime(System.nanoTime() - (long) (Math.pow(10, 10)*30.0)); //-30 seconds
        long initScore = (long) boardManager.computeScore();
        assertEquals(initScore, (long) 9589);
        boardManager.setMoveCount(1);
        long move1Score = (long) boardManager.computeScore();
        assertEquals(move1Score, (long) 7734);
        boardManager.setMoveCount(2);
        long move2Score = (long) boardManager.computeScore();
        assertEquals(move2Score, (long) 6481);
        boardManager.setStartTime(System.nanoTime() - (long) (Math.pow(10, 10)*60.0)); //-60 secs
        boardManager.setMoveCount(1);
        long move1Sec60Score = (long) boardManager.computeScore();
        assertEquals(move1Sec60Score, (long) 7486);
        boardManager.setMoveCount(2);
        long move2Sec60Score = (long) boardManager.computeScore();
        assertEquals(move2Sec60Score, (long) 6306);
    }

    @Test
    public void testSetBoardSize() {
        super.testSetBoardSize(boardManager);
    }

    @Test
    public void testPuzzleSolved() {
        boardManager.setStartTime(System.nanoTime() - (long) (Math.pow(10, 10)*30.0)); //-30 seconds
        System.out.println(boardManager.puzzleSolved());
        assertEquals((long) boardManager.getScore(), (long) 9589);
        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        SlidingTilesTestingHelpers.swapFirstTwoTiles((SlidingTilesBoardManager) boardManager);
        System.out.println(boardManager.puzzleSolved());
        assertTrue((long) boardManager.getScore() == (long) 6481);
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