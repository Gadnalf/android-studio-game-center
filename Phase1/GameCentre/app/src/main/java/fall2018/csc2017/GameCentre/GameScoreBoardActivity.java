package fall2018.csc2017.GameCentre;

import android.os.Bundle;

import java.util.HashMap;

public class GameScoreBoardActivity extends ScoreBoardAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         TestingHelpers.testSavingAndLoading(this);
        BoardManager tmpBoardManager = SaveAndLoad.loadBoardManagerTemp(
                this);
        setContentView(R.layout.activity_game_score_board);
        super.addRowsToScoreBoard(R.id.activity_game_score_board,
                tmpBoardManager.getUser().getUserName(),
                this);
    }

    @Override
    public HashMap<String, GameSettings> getScoreBoard(String userName) {
        HashMap<String, GameSettings> gameScoreBoard = SaveAndLoad.loadPermGameScoreboard(
                this);
        return gameScoreBoard;
    }
}
