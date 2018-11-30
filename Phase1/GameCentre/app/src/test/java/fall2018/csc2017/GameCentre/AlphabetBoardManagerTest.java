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
    }

    @Test
    public void testGameOver() {
    }


    @Test
    public void puzzleSolved() {
    }

    @Test
    public void isValidSwipe() {
    }


}