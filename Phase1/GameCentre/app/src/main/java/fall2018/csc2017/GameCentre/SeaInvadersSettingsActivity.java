package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class SeaInvadersSettingsActivity extends AppCompatActivity {
    /**
     * The board size display.
     */
    private TextView secSpawnAndMoveDisplay;

    /**
     * The undo number input field.
     */
    private TextView numRoundsDisply;

    /**
     * The undoes input field.
     */
    EditText numRoundsInput;

    /**
     * The board size setting.
     */
    private int secSpawnAndMove;

    /**
     * The undo setting. (-1 indicates infinite)
     */
    private int numRoundses;

    private SeaInvadersBoardManager seaInvadersBoardManager;
    /**
     * The board manager.
     */
    private GameData gameData;

    /**
     *
     *
     * User name.
     */
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_invader_setting);
        secSpawnAndMove = 4;
        numRoundses = 12;
        gameData = SaveAndLoad.loadGameHubTemp(this);
        seaInvadersBoardManager = gameData.getSeaInvadersBoardManager();
        addStartButtonListener();
        addConfirmButtonListener();
        addFiveSecSpawnAndMoveButtonListener();
        addFourSecSpawnAndMoveButtonListener();
        addThreeSecSpawnAndMoveButtonListener();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.secSpawnAndMoveDisplay = findViewById(R.id.secs_to_spawn_and_move);
        this.numRoundsDisply = findViewById(R.id.set_num_rounds);
        this.numRoundsInput = findViewById(R.id.num_rounds);
    }

    /**
     * Activate the login button.
     */
    void addStartButtonListener() {
        Button loginButton = findViewById(R.id.start_game_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secSpawnAndMove == 4) {
                    updateSecSpawnAndMoveDisplay();
                }
                if (numRoundses == 12) {
                    updateRoundsDisplay();
                }
                switchToGame();
            }
        });
    }

    private void setupStartingActivity() {
//        make a temp file just in case there is none yet
        GameData tmpGameHib = new GameData(
                new SlidingTilesBoardManager(
                        user,
                        new SlidingTilesSettings(4,4)),
                new SeaInvadersBoardManager(user,
                        new SeaInvadersSettings(4, 4,
                                12, 3)),
                new AlphabetTilesBoardManager(user,
                        new AlphabetTilesSettings(4 ,4)),

                user);
        SaveAndLoad.saveAllTemp(tmpGameHib,
                this);

//        load the board manager if it exists if not load the temp file
        gameData = SaveAndLoad.loadGameHubPermanent(
                user,
                this);
        //save the new board manager as temp if its been loaded
        SaveAndLoad.saveGameHubTemp(
                gameData,
                this); //this will save the loaded board manager to tmp to be used in the other activities
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, SeaInvadersGameActivity.class);
        gameData.setSeaInvadersBoardManager(seaInvadersBoardManager);
        SaveAndLoad.saveGameHubTemp(
                gameData, this);
        startActivity(tmp);
    }

    /**
     * Activates the confirm button
     *
     * Code adapted from "Android Studio Studio Tutorials - 15 : EditText and TextView Example"
     * (Source: https://www.youtube.com/watch?v=sXWFuar2Oq4 retrieved in November 2018)
     * and "TextInputLayout (Floating Label EditText) - Android Studio Tutorial"
     * (Source: https://www.youtube.com/watch?v=veOZTvAdzJ8 retrieved in November 2018)
     */
    void addConfirmButtonListener() {
        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numRoundsInput.getText().toString().trim();
                if(input.equals("")){
                    updateRoundsDisplay();
                }else {
                    numRoundses = Integer.valueOf(input);
                    updateRoundsDisplay();
                }
            }
        });
    }

    /**
     * Activate the 5x5 button.
     */
    void addFiveSecSpawnAndMoveButtonListener() {
        Button button_5x5 = findViewById(R.id.five);
        button_5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secSpawnAndMove = 5;
                updateSecSpawnAndMoveDisplay();
            }
        });
    }

    /**
     * Activate the 4x4 button.
     */
    void addFourSecSpawnAndMoveButtonListener() {
        Button button_4x4 = findViewById(R.id.four);
        button_4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secSpawnAndMove = 4;
                updateSecSpawnAndMoveDisplay();
            }
        });
    }

    /**
     * Activate the 3x3 button.
     */
    void addThreeSecSpawnAndMoveButtonListener() {
        Button button_3x3 = findViewById(R.id.three);
        button_3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secSpawnAndMove = 3;
                updateSecSpawnAndMoveDisplay();
            }
        });
    }

    void updateSecSpawnAndMoveDisplay() {
        String tmp = "Select seconds between spawning and swimming in game: " + secSpawnAndMove;
        secSpawnAndMoveDisplay.setText(tmp);
        ((SeaInvadersSettings) seaInvadersBoardManager.getGameSettings()).setSecsBeforeMove(secSpawnAndMove);
        ((SeaInvadersSettings) seaInvadersBoardManager.getGameSettings()).setSecsBeforeSpawn(secSpawnAndMove);
        seaInvadersBoardManager.resetGame();
    }

    void updateRoundsDisplay() {
        String tmp = "Select Number of Rounds: " + numRoundses;
        numRoundsDisply.setText(tmp);
        ((SeaInvadersSettings)
                seaInvadersBoardManager.getGameSettings()).setNumRounds(numRoundses);
        seaInvadersBoardManager.resetGame();
    }
}
