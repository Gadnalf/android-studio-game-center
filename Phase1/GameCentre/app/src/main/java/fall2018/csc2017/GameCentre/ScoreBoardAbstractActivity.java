package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public abstract class ScoreBoardAbstractActivity extends AppCompatActivity {


    abstract public HashMap<String, SlidingTileSettings> getScoreBoard(BoardManager boardManager);
//    @Override
//    abstract protected void onCreate(Bundle savedInstanceState);

    public void addRowsToScoreBoard(int scoreBoardId,
                                    BoardManager boardManager,
                                    AppCompatActivity appCompatActivity) { //might not be an int?
        // https://technotzz.wordpress.com/2011/11/04/android-dynamically-add-rows-to-table-layout/
        HashMap<String, SlidingTileSettings> scoreBoard = getScoreBoard(boardManager);
        TableLayout tl = (TableLayout) findViewById(scoreBoardId);

        Iterator<String> scoreBoarKeysIterator = scoreBoard.keySet().iterator();
        int i = 0;
        while (scoreBoarKeysIterator.hasNext()) {
            TableRow tr1 = new TableRow(appCompatActivity);
            tr1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));


            SlidingTileSettings row = scoreBoard.get(scoreBoarKeysIterator.next());

            TextView textview1 = new TextView(appCompatActivity);
            textview1.setText(row.getMaxScoreSetBy());
            tr1.addView(textview1);

            TextView textview2 = new TextView(appCompatActivity);
            textview2.setText(Long.toString(row.getMaxScore()));
            tr1.addView(textview2);

            TextView textview3 = new TextView(appCompatActivity);
            textview3.setText(
                    Integer.toString(row.getNumUndos())
            );
            tr1.addView(textview3);

            TextView textview4 = new TextView(appCompatActivity);
            textview4.setText(Integer.toString(row.getNumTiles()));
            tr1.addView(textview4);

            tl.addView(tr1, i);
            i++;
        }

        TableRow trHead = new TableRow(appCompatActivity);
        trHead.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView textviewHead1 = new TextView(appCompatActivity);
        textviewHead1.setText("User Name");
        trHead.addView(textviewHead1);

        TextView textviewHead2 = new TextView(appCompatActivity);
        textviewHead2.setText("Score");
        trHead.addView(textviewHead2);

        TextView textviewHead3 = new TextView(appCompatActivity);
        textviewHead3.setText("Num Undos");
        trHead.addView(textviewHead3);

        TextView textviewHead4 = new TextView(appCompatActivity);
        textviewHead4.setText("             Board Width and Height");
        trHead.addView(textviewHead4);
        tl.addView(trHead, 0);
    }
}
