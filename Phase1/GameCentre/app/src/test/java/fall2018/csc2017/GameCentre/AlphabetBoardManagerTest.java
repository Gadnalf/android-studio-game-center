package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlphabetBoardManagerTest extends AbstractBoardManagerTest {

    private AlphabetTilesBoardManager boardManager;

    @Override
    @Before
    public void setUp() {
        String user = "john";
        AlphabetTilesSettings alphabetTilesSettings = new AlphabetTilesSettings(5, 2);
        boardManager = new AlphabetTilesBoardManager(user, alphabetTilesSettings);
    }

    @Override
    public void testUpdateScoreboard() {
    }

    @Test
    public void testGameOver() {
        List<Tile> gameOverTiles = AlphabetTilesTestingHelpers.makeGameOverTiles(5);
        boardManager.getBoard().setTiles(gameOverTiles);
        assertTrue(boardManager.gameOver());
    }


    @Test
    public void testPuzzleSolved() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(10));
        assertTrue(boardManager.puzzleSolved());
    }

    @Test
    public void testSwipeUp() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(20, new TileAlpha(0));
        boardManager.swipeTo(0);
        Tile tile = new TileAlpha(0);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(0, boardManager.getBoard().getTile(4, 0).getId());

    }

    @Test
    public void testSwipeDown() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(0));
        boardManager.swipeTo(1);
        Tile tile = new TileAlpha(0);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(4, 0).getId());
        assertEquals(0, boardManager.getBoard().getTile(0, 0).getId());
    }

    @Test
    public void testSwipeLeft() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(4, new TileAlpha(0));
        boardManager.swipeTo(2);
        Tile tile = new TileAlpha(0);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(0, boardManager.getBoard().getTile(0, 4).getId());

    }

    @Test
    public void testSwipeRight() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(0));
        boardManager.swipeTo(3);
        Tile tile = new TileAlpha(0);
        assertEquals("Expected Tile and the Actual Tile differs",  tile.getId(),
                boardManager.getBoard().getTile(0, 4).getId());
        assertEquals(0, boardManager.getBoard().getTile(0, 0).getId());
    }

    @Test
    public void testGetScore() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(4));
        assertEquals("The score is not correct.",32,  boardManager.getScore(),
                0);
    }


    @Test
    public void testStackTiles() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(0));
        boardManager.getBoard().updateTile(1, new TileAlpha(0));
        boardManager.swipeTo(3);
        Tile tile = new TileAlpha(1);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 4).getId());
        assertEquals(0,boardManager.getBoard().getTile(0, 0).getId());
    }
}

