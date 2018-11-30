package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractBoardManagerTest {

    /**
     * define a board to work w
     */
    @Before
    abstract public void setUp();

    /**
     * test the board size is updated properly
     */
    public void testSetBoardSize(AbstractBoardManager boardManager) {
        boardManager.setBoardSize(5);
        assertEquals(boardManager.getBoard().getBoardSize(),
                5);
        boardManager.setBoardSize(4);
        assertEquals(boardManager.getBoard().getBoardSize(),
                4);
    }


    /**
     * test that your this.score is being updated
     */
    @Test
    abstract public void testUpdateScoreboard();

}