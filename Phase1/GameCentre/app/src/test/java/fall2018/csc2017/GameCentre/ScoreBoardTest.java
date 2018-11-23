package fall2018.csc2017.GameCentre;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreBoardTest {

    User user1 = new User("bill");
    User user2 = new User("bob");
    GameSettings gameSettings1 = new SlidingTileSettings(4,4);
    GameSettings gameSettings2 = new SeaInvaderSettings(4,4);
    GameSettings gameSettings3 = new SlidingTileSettings(3,3);
    ScoreBoard scoreBoard;

    @Before
    public void setUp() {
        scoreBoard = new ScoreBoard(user1, gameSettings1);
    }

    @Test
    public void testUpdateScoreBoard() {
    }

    @Test
    public void testIsNewGame() {
        scoreBoard.setGameSettings(gameSettings2);
        assertTrue(scoreBoard.isNewGame());
        scoreBoard.updateScoreBoard(2.0);
        assertFalse(scoreBoard.isNewGame());
        scoreBoard.setUser(user2);
        assertFalse(scoreBoard.isNewGame());
    }

    @Test
    public void testIsNewGameForUser() {
        assertTrue(scoreBoard.isNewGameForUser());
        scoreBoard.updateScoreBoard(2.0);
        assertFalse(scoreBoard.isNewGameForUser());
        scoreBoard.setUser(user2);
        assertTrue(scoreBoard.isNewGameForUser());
    }



    public void testUpdateGameScoreboard(long score, int size) {
        boolean isNewGame = scoreBoard.isNewGame();
        scoreBoard.updateGameScoreBoard(score);
        assertEquals(scoreBoard.getPerGameScoreBoard().size(), size);
        if (isNewGame) {
            assertEquals((long) scoreBoard.getPerGameScoreBoard().get(gameSettings1.getGameId()).getMaxScore(),
                    score);
        }
    }

    public void testUpdateUserScoreboard(long score, int size) {
        boolean isNewGame = scoreBoard.isNewGameForUser();
        scoreBoard.updateUserScoreBoard(score);
        assertEquals(scoreBoard.getPerUserScoreBoard().size(), size);
        if (isNewGame) {
            long storedScore = (long) scoreBoard.getPerUserScoreBoard().get(gameSettings1.getGameId()).getMaxScore();
            assertEquals(storedScore,
                    score);
        }
    }

    @Test
    public void testUpdateUserScoreBetterScore() {
        testUpdateUserScoreboard(1, 1);
        testUpdateUserScoreboard(2, 1);
        assertEquals(
                (long) scoreBoard.getPerUserScoreBoard().get(gameSettings1.getGameId()).getMaxScore(),
                (long) 2);
    }

    @Test
    public void testUpdateUserScoreWorseScore() {
        testUpdateUserScoreboard(1,
                1);
        testUpdateUserScoreboard((long) .5, 1);
        assertEquals(
                (long) scoreBoard.getPerUserScoreBoard().get(gameSettings1.getGameId()).getMaxScore(),
                (long) 1);
    }

    @Test
    public void testUpdateGameScoresBetterScore() {
        testUpdateGameScoreboard(1, 1);
        testUpdateGameScoreboard(2, 1);
        assertEquals(
                (long) scoreBoard.getPerGameScoreBoard().get(gameSettings1.getGameId()).getMaxScore(),
                (long) 2);
    }

    @Test
    public void testGameScoreboardAddingNewGames() {
        testUpdateGameScoreboard(1, 1);
        scoreBoard.setGameSettings(gameSettings3);
        testUpdateGameScoreboard(1, 2);
        scoreBoard.setGameSettings(gameSettings2);
        testUpdateGameScoreboard(1, 3);
    }

    @Test
    public void testUpdateGameScoresWorseScore() {
        testUpdateGameScoreboard(1, 1);
        testUpdateGameScoreboard((long) .5, 1);
        assertEquals(
                (long) scoreBoard.getPerGameScoreBoard().get(gameSettings1.getGameId()).getMaxScore(),
                1);
    }
}