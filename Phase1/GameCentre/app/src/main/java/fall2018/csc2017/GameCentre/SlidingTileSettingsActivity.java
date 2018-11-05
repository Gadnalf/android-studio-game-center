package fall2018.csc2017.GameCentre;

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
     * The undos input field.
     */
    EditText undoInput;

    /**
     * The board size setting.
     */
    private int boardSize;

    /**
     * The undo setting. (-1 indicates infinite)
     */
    private int numUndos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles_setting);

        boardSize = 4;
        numUndos = 0;
        addStartButtonListener();
        addUndoInputListener();
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
            }
        });
    }

    /**
     * Activate the undo text field listener.
     */
    void addUndoInputListener() {
        //TODO: I'll take care of this when I'm conscious again tomorrow.
    }

    /**
     * Activate the unlimited undo button.
     */
    void addUnlimitedUndoListener(){
        Button infiniteUndoButton = findViewById(R.id.unlimited_undo_button);
        infiniteUndoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numUndos = -1;
                updateBoardSizeDisplay();
            }
        });
    }

    /**
     * Activate the 5x5 button.
     */
    void addFiveByFiveButtonListener() {
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
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
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
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
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
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
    }

    void updateUndoDisplay() {
        String tmp = "Select Number of Undoes: " + numUndos;
        undoDisplay.setText(tmp);
    }
}