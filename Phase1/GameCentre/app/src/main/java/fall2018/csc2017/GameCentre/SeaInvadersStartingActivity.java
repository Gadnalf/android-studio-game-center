package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SeaInvadersStartingActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
    private GameHub gameHub;

    /**
     * User name.
     */
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_seainvaders);
        addNewGameButtonListener();
        addLoadButtonListener(this);
        addSaveButtonListener(this);
        user = getIntent().getStringExtra("user");
        setupStartingActivity();
        addGameScoreBoardButton();
        addUserScoreBoardButton();
        addReturnButtonListener();
    }

    private void setupStartingActivity() {
//        make a temp file just in case there is none yet
        SaveAndLoad.saveAllTemp(
                new GameHub(
                        new SlidingTilesBoardManager(
                                user,
                                new SlidingTilesSettings(4,4)),
                        new SeaInvadersBoardManager(user,
                                new SeaInvadersSettings(4, 4,
                                        12, 4)),
                        new AlphabetTilesBoardManager(user,
                                new AlphabetTilesSettings(4 ,4)),

                        user),
                this);

//        load the board manager if it exists if not load the temp file
        gameHub = SaveAndLoad.loadGameHubPermanent(
                user,
                this);
        //save the new board manager as temp if its been loaded
        SaveAndLoad.saveGameHubTemp(
                gameHub,
                this); //this will save the loaded board manager to tmp to be used in the other activities
    }

    /**
     * Activate the new game button.
     */
    private void addNewGameButtonListener() {
        Button startButton = findViewById(R.id.new_game_button);
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
        Button loadButton = findViewById(R.id.load_game_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameHub = SaveAndLoad.loadGameHubPermanent(
                        user,
                        appCompatActivity);
//                loadFromFile(SAVE_FILENAME);
                SaveAndLoad.saveGameHubTemp(
                        gameHub, appCompatActivity);
//                saveToFile(TEMP_SAVE_FILENAME);
                makeToastLoadedText();
                switchToGame();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener(final AppCompatActivity appCompatActivity) {
        Button saveButton = findViewById(R.id.save_game_button);
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
     * Activate the game-specific scoreboard button.
     */
    private void addGameScoreBoardButton() {
        Button saveButton = findViewById(R.id.game_scoreboard_button);
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
        Button saveButton = findViewById(R.id.user_scoreboard_button);
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
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        setupStartingActivity();
    }

    /**
     * Switch to the settings view.
     */
    private void switchToSetting() {
        Intent tmp = new Intent(this,
                SeaInvaderSettingActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        startActivity(tmp);
    }

    /**
     * Switch to the game view.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, SeaInvadersGameActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTileSetting to change the setting.
     */
    private void switchToGameScoreBoard() {
        Intent tmp = new Intent(this, GameScoreBoardActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        startActivity(tmp);
    }

    /**
     * Switch to the SlidingTileSetting to change the setting.
     */
    private void switchToUserScoreBoard() {
        Intent tmp = new Intent(this, UserScoreBoardActivity.class);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
        startActivity(tmp);
    }

    /**
     * Activates the return button.
     */
    private void addReturnButtonListener() {
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}