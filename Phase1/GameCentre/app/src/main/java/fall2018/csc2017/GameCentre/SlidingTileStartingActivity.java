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
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    /**
     * The board manager.
     */
    private GameHub gameHub;

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
                SaveAndLoad.saveGameHubPermanent(
                        gameHub,
                        appCompatActivity);
//                saveToFile(SAVE_FILENAME);
                SaveAndLoad.saveGameHubTemp(
                        gameHub, appCompatActivity);
//                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
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
    }

    /**
     * Switch to the SlidingTilesGameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
//        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTileSetting to change the setting.
     */
    private void switchToSetting() {
        Intent tmp = new Intent(this, SlidingTileSettingsActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        startActivity(tmp);
    }

    private void switchToGameScoreBoard() {
        Intent tmp = new Intent(this, GameScoreBoardActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        SaveAndLoad.saveGameHubTemp(gameHub, this);
        startActivity(tmp);
    }

    private void addGameScoreBoardButton() {
        Button saveButton = findViewById(R.id.GameScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                gameHub = new GameLaunchCentre().getBoardManager();
                switchToGameScoreBoard();
            }
        });
    }

    private void switchToSeaInvaders() {
        Intent tmp = new Intent(this, SeaInvaderGameActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        SaveAndLoad.saveGameHubTemp(gameHub, this);
        startActivity(tmp);
    }

}
