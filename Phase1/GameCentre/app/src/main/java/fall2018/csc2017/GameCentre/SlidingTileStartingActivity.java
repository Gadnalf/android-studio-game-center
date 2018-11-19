package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingTileStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    /**
     * The board manager.
     */
    private SlidingTilesBoardManager boardManager;

    /**
     * A user score board save file.
     */
    public static final String USER_SCORE_BOARD_FILEPREFIX =  "_user_score_board_";

    /**
     * A game score board save file.
     */
    public static final String GAME_SCORE_BOARD_FILEPREFIX =   "game_score_board_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_slidingtiles);
        addStartButtonListener();
        addLoadButtonListener(this);
        addSaveButtonListener(this);
        addGameScoreBoardButton();
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
    private void addLoadButtonListener(final AppCompatActivity appCompatActivity) {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    private void addSaveButtonListener(final AppCompatActivity appCompatActivity) {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
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
    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTileSetting to change the setting.
     */
    private void switchToSetting() {
        Intent tmp = new Intent(this, SlidingTileSettingsActivity.class);
        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }


    /**
     * Switch to the UserScoreBoard view
     */
    private void switchToUserScoreBoard() {
        Intent tmp = new Intent(this, UserScoreBoardActivity.class);
        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Switch to the GameScoreBoard view
     */
    private void switchToGameScoreBoard() {
        Intent tmp = new Intent(this, GameScoreBoardActivity.class);
        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }
}
