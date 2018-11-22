package fall2018.csc2017.GameCentre;

import android.os.Bundle;

import java.util.HashMap;

public class UserScoreBoardActivity extends ScoreBoardAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_score_board);
        super.addRowsToScoreBoard(R.id.activity_user_score_board,
                GameHubActivity.accountManager.getName(),
                this);
    }

    @Override
    public HashMap<String, GameSettings> getScoreBoard(String userName) {
        HashMap<String, GameSettings> gameUserBoard = SaveAndLoad.loadPermUserScoreboard(
                this,
                userName);
        return gameUserBoard;
    }
}
