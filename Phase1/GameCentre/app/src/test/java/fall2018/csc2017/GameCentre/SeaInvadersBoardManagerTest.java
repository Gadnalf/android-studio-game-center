package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SeaInvadersBoardManagerTest extends AbstractBoardManagerTest {

    SeaInvadersBoardManager boardManager;

    @Override
    @Before
    public void setUp() {
        String user = "phil";
        SeaInvadersSettings seaInvaderSettings = new SeaInvadersSettings(1, 1);
        boardManager = new SeaInvadersBoardManager(user, seaInvaderSettings);
    }

    @Override
    public void testUpdateScoreboard() {
    }

    @Test
    public void testGameOver() {
        assertFalse(boardManager.gameOver());
        boardManager.board.swapTiles(4, 0, 0, 0);
        boardManager.swim();
        assertTrue(boardManager.gameOver());
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
                5 * 5 - 1,
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
        //not on the bottom row:
        assertFalse(boardManager.isValidShoot(0));
        //on bottom but not shooter
        assertFalse(boardManager.isValidShoot(23));
        //valid shooter
        assertTrue(boardManager.isValidShoot(24));
        //valid shooter after moving
        boardManager.touchMove(23);
        assertFalse(boardManager.isValidShoot(24));
        assertTrue(boardManager.isValidShoot(23));
    }

    @Test
    public void fireAndUpdate() {
        //make sure we can delete all the invaders
        assertEquals(boardManager.getInvaderPositions().size(), 5);
        for (int i = 24; i > 19; i--) {
            boardManager.fireAndUpdate(i);
            assertEquals(boardManager.getClosestEnemyPosInThisCol(i), -1); //-1 => no enemy
        }
        assertEquals(boardManager.getInvaderPositions().size(), 0);

        //ensure we can delete stacks of invaders
        boardManager.board.updateTile(0, new InvaderTile());
        boardManager.board.updateTile(5, new InvaderTile());
        assertEquals(boardManager.getInvaderPositions().size(), 2);
        boardManager.fireAndUpdate(20);
        boardManager.fireAndUpdate(20);
        assertEquals(boardManager.getClosestEnemyPosInThisCol(20), -1);
        assertEquals(boardManager.getInvaderPositions().size(), 0);
    }

    @Test
    public void spawnTheInvaders() {
        removeAllInvaders(boardManager);
        assertEquals(boardManager.getInvaderPositions().size(), 0);
        boardManager.spawnTheInvaders();
        assertEquals(boardManager.getInvaderPositions().size(), 5);
    }

    @Test
    public void swim() {
        //starting right
        ArrayList invaders = boardManager.getInvaderPositions();
        Integer[] invaderArray = ((List<Integer>) invaders).toArray(new Integer[5]);
        assertArrayEquals(
                invaderArray,
                new Integer[]{4,3,2,1,0});
        //simple swim
        boardManager.swim();
        assertArrayEquals(
                boardManager.getInvaderPositions().toArray(new Integer[5]),
                new Integer[]{9,8,7,6,5});
        //swim again after removing one
        boardManager.fireAndUpdate(24);
        boardManager.swim();
        assertArrayEquals(
                boardManager.getInvaderPositions().toArray(new Integer[4]),
                new Integer[]{13,12,11,10});
    }
}