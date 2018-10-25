package fall2018.csc2017.GameCentre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ScoreBoardTest extends TestingGameLaunch{

//    @Test
//    public void testIsSolved2() {
////        super.testIsSolved();
//        setUpCorrect();
//        System.out.println(boardManager.puzzleSolved());
//        assertEquals(true, boardManager.puzzleSolved());
//        swapFirstTwoTiles();
//        assertEquals(false, boardManager.puzzleSolved());
//    }

    public void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        Board board = new Board(tiles);
        scoreBoard = new ScoreBoard(
                new User("jim", "testing"),
                new Game(3)
        );
        boardManager = new BoardManager(board, scoreBoard);
        winningBoardManager = new BoardManager(board, scoreBoard);
        System.out.println(boardManager.puzzleSolved());
    }

    @Test
    public void testAddNewUserAndGame() {
        setUpCorrect();
        System.out.println(scoreBoard.getScore());
        assertEquals(1, scoreBoard.perGameScoreBoard.size());
        assertEquals(1, scoreBoard.perUserScoreBoard.size());
    }

    @Test
    public void testSizesCorrect() {
        setUpCorrect();
        assertEquals(10, scoreBoard.perGameScoreBoard.get(0).getMaxScore());
        assertEquals(10, scoreBoard.perUserScoreBoard.get(0).getMaxScore());
    }

//    @Test
//    public void testUpdating() {
//        setUpCorrect();
//
//        assertEquals(10, scoreBoard.perGameScoreBoard.get(0).getMaxScore());
//        assertEquals(10, scoreBoard.perUserScoreBoard.get(0).getMaxScore());
//    }


    @Test
    public void testTimeResets() {
        setUpCorrect();


    }

    @Test
    public void testScoreUpdatesForUserAndGame() {
        setUpCorrect();

    }
}

