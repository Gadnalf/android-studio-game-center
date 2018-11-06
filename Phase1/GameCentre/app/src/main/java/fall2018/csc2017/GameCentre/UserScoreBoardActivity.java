package fall2018.csc2017.GameCentre;

import android.os.Bundle;

import java.util.HashMap;

public class UserScoreBoardActivity extends ScoreBoardAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TestingHelpers.testSavingAndLoading(this);
        BoardManager tmpBoardManager = SaveAndLoad.loadBoardManagerTemp(
                this);
        BoardManager boardManager = SaveAndLoad.loadBoardManagerPermanent(
                tmpBoardManager.getUser().getUserName(), this);
        setContentView(R.layout.activity_user_score_board);
        super.addRowsToScoreBoard(R.id.activity_user_score_board, boardManager, this);
    }

    @Override
    public HashMap<String, SlidingTileSettings> getScoreBoard(BoardManager boardManager) {
        return boardManager.getScoreBoard().getPerUserScoreBoard();
    }
}
