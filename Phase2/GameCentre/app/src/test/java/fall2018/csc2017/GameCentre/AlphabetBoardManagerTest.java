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
        boardManager.getBoard().updateTile(5, new TileAlpha(-1));
        boardManager.getBoard().updateTile(10, new TileAlpha(-1));
        boardManager.getBoard().updateTile(15, new TileAlpha(-1));
        boardManager.getBoard().updateTile(20, new TileAlpha(3));
        boardManager.swipeTo(0);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(1, 0).getId());
        boardManager.getBoard().updateTile(10, new TileAlpha(5));
        boardManager.swipeTo(0);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(1, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 6,
                boardManager.getBoard().getTile(2,0).getId());

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
        boardManager.getBoard().updateTile(0, new TileAlpha(3));
        boardManager.getBoard().updateTile(15, new TileAlpha(-1));
        boardManager.getBoard().updateTile(10, new TileAlpha(-1));
        boardManager.getBoard().updateTile(5, new TileAlpha(-1));
        boardManager.swipeTo(1);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(4, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(3, 0).getId());
        boardManager.getBoard().updateTile(10, new TileAlpha(5));
        boardManager.swipeTo(1);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(4, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(3, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 6,
                boardManager.getBoard().getTile(2,0).getId());

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
        boardManager.getBoard().updateTile(4, new TileAlpha(3));
        boardManager.getBoard().updateTile(3, new TileAlpha(-1));
        boardManager.getBoard().updateTile(2, new TileAlpha(-1));
        boardManager.getBoard().updateTile(1, new TileAlpha(-1));
        boardManager.swipeTo(2);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(0, 1).getId());
        boardManager.getBoard().updateTile(2, new TileAlpha(5));
        boardManager.swipeTo(2);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(0, 1).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 6,
                boardManager.getBoard().getTile(0,2).getId());

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
        boardManager.getBoard().updateTile(0, new TileAlpha(3));
        boardManager.getBoard().updateTile(1, new TileAlpha(-1));
        boardManager.getBoard().updateTile(2, new TileAlpha(-1));
        boardManager.getBoard().updateTile(3, new TileAlpha(-1));
        boardManager.swipeTo(3);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 4).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(0, 3).getId());
        boardManager.getBoard().updateTile(2, new TileAlpha(5));
        boardManager.swipeTo(3);
        assertEquals("Expected Tile and the Actual Tile differs", tile.getId(),
                boardManager.getBoard().getTile(0, 4).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 4,
                boardManager.getBoard().getTile(0, 3).getId());
        assertEquals("Expected Tile and the Actual Tile differs", 6,
                boardManager.getBoard().getTile(0,2).getId());
    }

    @Test
    public void testComputeScore() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(4));
        assertEquals("The score is not correct.",32,  boardManager.computeScore(),
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
    }

    @Test
    public void testUndo() {
        List<Tile> emptyTiles = AlphabetTilesTestingHelpers.makeEmptyTiles(5);
        boardManager.getBoard().setTiles(emptyTiles);
        boardManager.getBoard().updateTile(0, new TileAlpha(0));
        boardManager.getBoard().updateTile(1, new TileAlpha(1));
        Tile tile1 = new TileAlpha(0);
        Tile tile2 = new TileAlpha(1);
        boardManager.swipeTo(0);
        boardManager.undo();
        assertEquals("Expected Tile and the Actual Tile differs", tile1.getId(),
                boardManager.getBoard().getTile(0,0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", tile2.getId(),
                boardManager.getBoard().getTile(0,1).getId());
        boardManager.swipeTo(1);
        boardManager.undo();
        assertEquals("Expected Tile and the Actual Tile differs", tile1.getId(),
                boardManager.getBoard().getTile(0,0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", tile2.getId(),
                boardManager.getBoard().getTile(0,1).getId());
        boardManager.swipeTo(2);
        boardManager.undo();
        assertEquals("Expected Tile and the Actual Tile differs", tile1.getId(),
                boardManager.getBoard().getTile(0,0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", tile2.getId(),
                boardManager.getBoard().getTile(0,1).getId());
        boardManager.swipeTo(3);
        boardManager.undo();
        assertEquals("Expected Tile and the Actual Tile differs", tile1.getId(),
                boardManager.getBoard().getTile(0,0).getId());
        assertEquals("Expected Tile and the Actual Tile differs", tile2.getId(),
                boardManager.getBoard().getTile(0,1).getId());
    }



}

