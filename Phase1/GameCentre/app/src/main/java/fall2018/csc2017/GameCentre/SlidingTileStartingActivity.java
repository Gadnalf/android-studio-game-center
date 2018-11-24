package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTileStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static String saveFilename = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static String tempSaveFilename = "save_file_tmp.ser";

    /**
     * The board manager.
     */
    private SlidingTilesBoardManager boardManager;

    /**
     * A user score board save file.
     */
    public static final String USER_SCORE_BOARD_FILEPREFIX =  "user_score_board_";

    /**
     * A game score board save file.
     */
    public static final String GAME_SCORE_BOARD_FILEPREFIX =   "game_score_board_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_slidingtiles);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addGameScoreBoardButton();
        addUserScoreBoardButton();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        saveFilename = GameHubActivity.accountManager.getName().concat("_save_file.ser");
        tempSaveFilename = GameHubActivity.accountManager.getName().concat("_temp_save_file.ser");
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSetting();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(saveFilename);
                saveToFile(tempSaveFilename);
                makeToastLoadedText();
                switchToGame();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded SlidingTileSettings", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(saveFilename);
                saveToFile(tempSaveFilename);
                makeToastSavedText();
            }
            });
    }

    /**
     * Activate the game-specific scoreboard button.
     */
    private void addGameScoreBoardButton() {
        Button saveButton = findViewById(R.id.GameScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGameScoreBoard();
            }
        });
    }

    /**
     * Activate the game-specific scoreboard button.
     */
    private void addUserScoreBoardButton() {
        Button saveButton = findViewById(R.id.UserScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserScoreBoard();
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "SlidingTileSettings Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        saveFilename = GameHubActivity.accountManager.getName().concat("_save_file.ser");
        tempSaveFilename = GameHubActivity.accountManager.getName().concat("_temp_save_file.ser");
        loadFromFile(tempSaveFilename);
    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        saveToFile(tempSaveFilename);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTileSetting to change the setting.
     */
    private void switchToSetting() {
        Intent tmp = new Intent(this, SlidingTileSettingsActivity.class);
        saveToFile(tempSaveFilename);
        startActivity(tmp);
    }


    /**
     * Switch to the UserScoreBoard view
     */
    private void switchToUserScoreBoard() {
        Intent tmp = new Intent(this, UserScoreBoardActivity.class);
        saveToFile(tempSaveFilename);
        startActivity(tmp);
    }

    /**
     * Switch to the GameScoreBoard view
     */
    private void switchToGameScoreBoard() {
        Intent tmp = new Intent(this, GameScoreBoardActivity.class);
        saveToFile(tempSaveFilename);
        startActivity(tmp);
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
