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
        numUndoes = 4;
        boardManager = SaveAndLoad.loadBoardManagerTemp(this);
        addStartButtonListener();
//        addUndoInputListener();
        addUnlimitedUndoListener();
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
                //TODO: Link this to GameActivity, pass boardSize and numUndo parameters?
                // It probably needs to initiate the new BoardManager from here, since handling
                // half of it in StartingActivity and half of it here would be super janky.
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


//    /**
//     * Activate the undo text field listener.
//     */
//    void addUndoInputListener() {
//        //TODO: I'll take care of this when I'm conscious again tomorrow.
//    }

    /**
     * Activate the unlimited undo button.
     */
    void addUnlimitedUndoListener(){
        Button infiniteUndoButton = findViewById(R.id.unlimited_undo_button);
        infiniteUndoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numUndoes = -1;
                updateUndoDisplay();
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
    }

    void updateUndoDisplay() {
        String tmp = "Select Number of Undoes: " + numUndoes;
        undoDisplay.setText(tmp);
        boardManager.getSlidingTileSettings().setNumUndoes(numUndoes);
    }
}