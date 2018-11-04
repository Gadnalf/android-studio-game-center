package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class UserScoreBoardActivity extends ScoreBoardAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        boardManager = (BoardManager) SaveAndLoad.loadFromFile(StartingActivity.TEMP_SAVE_FILENAME, this);
        boolean testsPass = TestingHelpers.testSavingAndLoading(this);
        System.out.println(testsPass);
        BoardManager boardManager = SaveAndLoad.loadBoardManagerTemp(
                this);
        setContentView(R.layout.activity_user_score_board);
        super.addRowsToScoreBoard(R.id.activity_user_score_board, boardManager, this);
    }

    @Override
    public HashMap<String, Game> getScoreBoard(BoardManager boardManager) {
        return boardManager.getScoreBoard().getPerUserScoreBoard();
    }
}
