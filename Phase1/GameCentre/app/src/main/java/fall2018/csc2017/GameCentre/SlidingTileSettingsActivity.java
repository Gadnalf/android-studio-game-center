package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SlidingTileSettingsActivity extends AppCompatActivity {

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

    private BoardManager boardManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles_setting);

        boardSize = 4;
        numUndoes = 5;
        boardManager = SaveAndLoad.loadBoardManagerTemp(this);
        addStartButtonListener();
        addUnlimitedUndoListener();
        addConfirmButtonListener();
        addFiveByFiveButtonListener();
        addFourByFourButtonListener();
        addThreeByThreeButtonListener();

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
                if(numUndoes == 5) {
                    updateUndoDisplay();
                }
                switchToGame();
            }
        });
    }

    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        SaveAndLoad.saveBoardManagerTemp(
                boardManager, this);
//        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Activates the confirm button
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
     * Activate the 5x5 button.
     */
    void addFiveByFiveButtonListener() {
        Button button_5x5 = findViewById(R.id.five_by_five);
        button_5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardSize = 5;
                updateBoardSizeDisplay();
            }
        });
    }

    /**
     * Activate the 4x4 button.
     */
    void addFourByFourButtonListener() {
        Button button_4x4 = findViewById(R.id.four_by_four);
        button_4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardSize = 4;
                updateBoardSizeDisplay();
            }
        });
    }

    /**
     * Activate the 3x3 button.
     */
    void addThreeByThreeButtonListener() {
        Button button_3x3 = findViewById(R.id.three_by_three);
        button_3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardSize = 3;
                updateBoardSizeDisplay();
            }
        });
    }

    void updateBoardSizeDisplay() {
        String tmp = "Select Board Size: " + boardSize + "x" + boardSize;
        boardSizeDisplay.setText(tmp);
        boardManager.getSlidingTileSettings().setBoardSize(boardSize);
        boardManager.setBoardSize(boardSize);
    }

    void updateUndoDisplay() {
        String tmp = "Select Number of Undoes: " + numUndoes;
        undoDisplay.setText(tmp);
        boardManager.getSlidingTileSettings().setNumUndoes(numUndoes);
    }
}