package fall2018.csc2017.GameCentre;

import android.os.Bundle;

import java.util.HashMap;

public class UserScoreBoardActivity extends ScoreBoardAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         SlidingTilesTestingHelpers.testSavingAndLoading(this);
        GameHub tmpGameHub = SaveAndLoad.loadGameHubTemp(
                this);
        setContentView(R.layout.activity_user_score_board);
        super.addRowsToScoreBoard(R.id.activity_user_score_board,
                tmpGameHub.getUser(),
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
