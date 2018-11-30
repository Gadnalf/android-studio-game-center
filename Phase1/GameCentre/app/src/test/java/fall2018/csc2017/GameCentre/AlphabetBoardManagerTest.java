package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlphabetBoardManagerTest extends AbstractBoardManagerTest {

    AlphabetTilesBoardManager boardManager;

    @Override
    @Before
    public void setUp() {
        String user = "john";
        AlphabetTilesSettings alphabetTilesSettings = new AlphabetTilesSettings(5, 2);
        boardManager = new AlphabetTilesBoardManager(user, alphabetTilesSettings);
    }

    @Override
    public void testUpdateScoreboard() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(4));
        assertEquals("The score is not correct.", 32, boardManager.getScore(), 0);
    }

    @Test
    public void testGameOver() {
        List<Tile> gameOverTiles = AlphabetTilesTestingHelpers.makeGameOverTiles(5);
        boardManager.getBoard().setTiles(gameOverTiles);
        assertTrue(boardManager.gameOver());

    }


    @Test
    public void puzzleSolved() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(10));
        assertTrue(boardManager.puzzleSolved());
    }

    @Test
    public void isValidSwipe() {
    }


}