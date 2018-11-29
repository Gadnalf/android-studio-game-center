package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlphabetTilesSettingsActivity extends AppCompatActivity {
    /**
     * The board size display.
     */
    private TextView boardSizeDisplay;

    /**
     * The undo number input field.
     */
    private TextView undoDisplay;

    /**
     * The undoes input field.
     */
    EditText undoInput;

    /**
     * The board size setting.
     */
    private int boardSize;

    /**
     * The undo setting. (-1 indicates infinite)
     */
    private int numUndoes;

    private AlphabetTilesBoardManager alphabetTilesBoardManager;
    private GameHub gameHub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_settings);

        boardSize = 4;
        numUndoes = 3;
        gameHub = SaveAndLoad.loadGameHubTemp(this);
        alphabetTilesBoardManager = gameHub.getZTileBoardManager();
        addStartButtonListener();
        addUnlimitedUndoListener();
        addConfirmButtonListener();
        addFiveByFiveButtonListener();
        addFourByFourButtonListener();
        addSixBySixButtonListener();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.boardSizeDisplay = findViewById(R.id.board_size);
        this.undoDisplay = findViewById(R.id.set_undoes_available);
        this.undoInput = findViewById(R.id.undo_input);
    }

    /**
     * Activate the login button.
     */
    void addStartButtonListener() {
        Button loginButton = findViewById(R.id.start_game_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(boardSize == 4) {
                    updateBoardSizeDisplay();
                }
                if(numUndoes == 3) {
                    updateUndoDisplay();
                }
                switchToGame();
            }
        });
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, AlphabetTilesGameActivity.class);
        gameHub.setZTileBoardManager(alphabetTilesBoardManager);
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
//        saveToFile(TEMP_SAVE_FILENAME);
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
                if(numUndoes == -1) {
                    updateUndoDisplay();
                }else {
                    String input = undoInput.getText().toString().trim();
                    if(input.equals("")){
                        updateUndoDisplay();
                    }else{
                        numUndoes = Integer.valueOf(input);
                        updateUndoDisplay();
                    }
                }
            }
        });
    }

    /**
     * Activate the unlimited undo button.
     */
    void addUnlimitedUndoListener(){
        Button infiniteUndoButton = findViewById(R.id.unlimited_undo_button);
        infiniteUndoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numUndoes = -1;
            }
        });
    }

    /**
     * Activate the 4 by 4 button
     */
    void addFourByFourButtonListener() {
        Button button_4x4 = findViewById(R.id.four_by_four);
        button_4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardSize = 4;
                alphabetTilesBoardManager.setBoardSize(boardSize);
                updateBoardSizeDisplay();
            }
        });
    }

    /**
     * Activate the 5 by 5 button
     */
    void addFiveByFiveButtonListener() {
        Button button_5x5 = findViewById(R.id.five_by_five);
        button_5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardSize = 5;
                alphabetTilesBoardManager.setBoardSize(boardSize);
                updateBoardSizeDisplay();
            }
        });
    }

    /**
     * Activate the 6 by 6 button
     */
    void addSixBySixButtonListener() {
        Button button_6x6 = findViewById(R.id.six_by_six);
        button_6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardSize = 6;
                alphabetTilesBoardManager.setBoardSize(boardSize);
                updateBoardSizeDisplay();
            }
        });
    }

    void updateBoardSizeDisplay() {
        String tmp = "Select Board Size: " + boardSize + "x" + boardSize;
        boardSizeDisplay.setText(tmp);
        alphabetTilesBoardManager.getAlphabetTilesSettings().setBoardSize(boardSize);

    }

    void updateUndoDisplay() {
        String tmp = "Select Number of Undoes: " + numUndoes;
        undoDisplay.setText(tmp);
        alphabetTilesBoardManager.getAlphabetTilesSettings().setNumUndoes(numUndoes);
    }
}


