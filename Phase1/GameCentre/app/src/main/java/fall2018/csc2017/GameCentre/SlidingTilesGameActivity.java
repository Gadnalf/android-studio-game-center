package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends AbstractGameActivity implements Serializable {

    SlidingTilesBoardManager boardManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadFromFile(SlidingTileStartingActivity.tempSaveFilename);
        setAbstractBoardManager(boardManager);
        super.onCreate(savedInstanceState);
//        SlidingTilesGameActivity slidingTilesGameActivity = new SlidingTilesGameActivity(abstractBoardManager);
        // Will not work w out the line below
        boardManager.setAppCompatActivity(this);

        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.getSlidingTileSettings().getBoardSize());
        gridView.setAbstractBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        final int COL_FINAL = boardManager.getSlidingTileSettings().getBoardSize();
        final int ROW_FINAL = boardManager.getSlidingTileSettings().getBoardSize();

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / COL_FINAL;
                        columnHeight = displayHeight / ROW_FINAL;

                        display();
                    }
                });
    }

    @Override
    protected void autoSave() {
        saveToFile(SlidingTileStartingActivity.tempSaveFilename);
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
                boardManager = (SlidingTilesBoardManager) input.readObject();
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
}
