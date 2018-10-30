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

public class UserScoreBoardActivity extends AppCompatActivity {

    BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile(StartingActivity.TEMP_SAVE_FILENAME);
        addRowsToUserScoreBoard();
        setContentView(R.layout.activity_user_score_board);

    }

    private void addRowsToUserScoreBoard() {
        // https://technotzz.wordpress.com/2011/11/04/android-dynamically-add-rows-to-table-layout/
        ScoreBoard scoreBoard = boardManager.getScoreBoard();
        ArrayList<Game> userScoreBoard = scoreBoard.getPerUserScoreBoard();
        TableLayout tl = (TableLayout) findViewById(R.id.user_score_board);

        for (int i = 0; i < userScoreBoard.size(); i++) {
            TableRow tr1 = new TableRow(this);
            tr1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            Game row = userScoreBoard.get(i);

            TextView textview1 = new TextView(this);
            textview1.setText(boardManager.getUser().getUserName());
            tr1.addView(textview1);

            TextView textview2 = new TextView(this);
            textview2.setText(Long.toString(row.getMaxScore()));
            tr1.addView(textview2);

            TextView textview3 = new TextView(this);
            textview3.setText(
                    Integer.toString(row.getNumUndos())
                    + ", "
                    + Integer.toString(row.getNumTiles())
            );
            tr1.addView(textview3);
        }
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (BoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
