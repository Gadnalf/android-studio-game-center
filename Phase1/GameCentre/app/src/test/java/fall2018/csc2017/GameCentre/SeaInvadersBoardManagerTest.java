package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SeaInvadersBoardManagerTest extends AbstractBoardManagerTest {

    SeaInvadersBoardManager boardManager;

    @Override
    @Before
    public void setUp() {
        User user = new User("phil");
        SeaInvaderSettings seaInvaderSettings = new SeaInvaderSettings(1, 1);
        boardManager = new SeaInvadersBoardManager(user, seaInvaderSettings);
    }

    @Override
    public void testUpdateScoreboard() {
    }

    @Test
    public void testGameOver() {

    }

    public void removeAllInvaders(SeaInvadersBoardManager seaInvadersBoardManager) {
        ArrayList<Integer> invaders = seaInvadersBoardManager.getInvaderPositions();
        for (int pos : invaders) {
            seaInvadersBoardManager.board.updateTile(pos, new EmptyTile());
        }
    }

    @Test
    public void puzzleSolved() {
        boardManager.setGameOver(true);
        assertEquals((long) boardManager.score, (long) 0.0); // start w 0
        boardManager.setCurrentRound(5);
        assertEquals(boardManager.puzzleSolved(), false); // 5 moves => not finished
        double scoreMove5 = boardManager.getScore();
        assertTrue(scoreMove5 > 0);
        boardManager.setCurrentRound(10);
        assertEquals(boardManager.puzzleSolved(), false);
        boardManager.setGameOver(false); // puzzle isnt solved if the game is over
        boolean puzzleSolved = boardManager.puzzleSolved();
        assertEquals(puzzleSolved, false); // still inavders in there
        removeAllInvaders(boardManager);
        assertEquals(boardManager.puzzleSolved(), true);
        assertTrue(boardManager.score > scoreMove5);
        boardManager.board.updateTile(
                5*5-1,
                new InvaderTile()
        );
        assertFalse(boardManager.puzzleSolved()); //invader at end => puzzle not solved
    }

    @Test
    public void isValidTap() {
        assertFalse(boardManager.isValidTap(0));
        assertEquals(boardManager.lastOccupiedColumn, 4); //starting in right spot
        assertFalse(boardManager.isValidTap(24)); // starting spot not a valid tap
        assertTrue(boardManager.isValidTap(21));
    }

    @Test
    public void getClosestEnemyPosInThisCol() {
        int enemyPos = boardManager.getClosestEnemyPosInThisCol(20);
        assertEquals(enemyPos, 0); //1st col 2nd row
        boardManager.board.updateTile(5, new InvaderTile()); //remove this one
        int enemyPos2 = boardManager.getClosestEnemyPosInThisCol(20);
        assertEquals(enemyPos2, 5); //1st col 2nd row
    }

    @Test
    public void isValidShoot() {
        
    }

    @Test
    public void fireAndUpdate() {
    }

    @Test
    public void spawnTheInvaders() {
    }

    @Test
    public void swim() {
    }

    @Test
    public void getInvaderPositions() {
    }

    @Test
    public void getInvaderSpawnPositions() {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void isGameOver() {
    }
}