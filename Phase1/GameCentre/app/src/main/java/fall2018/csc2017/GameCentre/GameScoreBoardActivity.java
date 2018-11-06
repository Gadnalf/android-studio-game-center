package fall2018.csc2017.GameCentre;

import android.os.Bundle;

import java.util.HashMap;

public class GameScoreBoardActivity extends ScoreBoardAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        boolean testsPass = TestingHelpers.testSavingAndLoading(this);
//        System.out.println(testsPass);
        BoardManager boardManager = SaveAndLoad.loadBoardManagerTemp(
                this);
        setContentView(R.layout.activity_game_score_board);
        super.addRowsToScoreBoard(R.id.activity_game_score_board, boardManager, this);
    }

    @Override
    public HashMap<String, SlidingTileSettings> getScoreBoard(BoardManager boardManager) {
        return boardManager.getScoreBoard().getPerGameScoreBoard();
    }
}
