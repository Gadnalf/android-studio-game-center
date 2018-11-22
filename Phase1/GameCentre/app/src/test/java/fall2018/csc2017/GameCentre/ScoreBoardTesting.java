package fall2018.csc2017.GameCentre;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class ScoreBoardTesting extends TestingSlidingTiles {

    SlidingTileSettings slidingTileSettings;
    User user;
    ScoreBoard scoreBoard;

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
        user = new User("jim");
        slidingTileSettings =  new SlidingTileSettings(3, 4);
        slidingTilesBoardManager = new SlidingTilesBoardManager(board, user, slidingTileSettings);
        scoreBoard = slidingTilesBoardManager.getScoreBoard();
        System.out.println(slidingTilesBoardManager.puzzleSolved());
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
        System.out.println(scoreBoard.perGameScoreBoard.get(slidingTileSettings.getGameId()).getMaxScore());
        assertEquals(10, (int) scoreBoard.perGameScoreBoard.get(slidingTileSettings.getGameId()).getMaxScore());
        System.out.println(scoreBoard.perUserScoreBoard.get(user.getUserName()).getMaxScore());
        assertEquals(10, (int) scoreBoard.perUserScoreBoard.get(user.getUserName()).getMaxScore());
    }

//    @Test
//    public void testSavingAndLoading(){
//        //---------add one user to scoreboard
//        setUpCorrect();
//        System.out.println(boardManager.puzzleSolved());
//        //puzzle is solved so will add to the scoreboard
//        //---------add another user to the scoreboard
//        List<Tile> tiles = makeTiles();
//        Board board = new Board(tiles);
//        scoreBoard = new ScoreBoard(
//                new User("bill", "testing"),
//                new SlidingTileSettings(4)
//        );
//        boardManager = new SlidingTilesBoardManager(board, scoreBoard);
//        //puzzle solved so we add to the scoreboard
//        System.out.println(boardManager.puzzleSolved());
//        //save this scoreboard
//        saveToFile(saveFilename);
//        //wipe the scoreboard
//        ScoreBoard.perGameScoreBoard = new ArrayList<>();
//        ScoreBoard.perUserScoreBoard = new ArrayList<>();
//        loadFromFile(saveFilename);
//        //------------check that we load properly
//        assertEquals(2, scoreBoard.perGameScoreBoard.size());
//        assertEquals(2, scoreBoard.perUserScoreBoard.size());
//    }

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

