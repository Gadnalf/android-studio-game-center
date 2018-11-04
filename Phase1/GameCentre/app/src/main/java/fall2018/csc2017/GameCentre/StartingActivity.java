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
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
    /**
     * A game score board save file.
     */
    public static final String GAME_SCORE_BOARD_FILEPREFIX = "game_score_board_";
    /**
     * The board manager.
     */
    private BoardManager boardManager;
//    private ScoreBoard scoreBoard;

    public StartingActivity() {};

    public StartingActivity(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameLaunchCentre gameLaunchCentre = new GameLaunchCentre();
        boardManager = gameLaunchCentre.getBoardManager();
        SaveAndLoad.saveBoardManagerTemp(
                boardManager,
                this);
//        saveToFile(TEMP_SAVE_FILENAME);

        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener(this);
        addSaveButtonListener(this);
        addGameScoreBoardButton();
        addUserScoreBoardButton();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new GameLaunchCentre().getBoardManager();
                switchToGame();
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
                boardManager = SaveAndLoad.loadBoardManagerPermanent(
                        boardManager.getUser().getUserName(),
                        appCompatActivity);
//                loadFromFile(SAVE_FILENAME);
                SaveAndLoad.saveBoardManagerTemp(
                        boardManager, appCompatActivity);
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
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveAndLoad.saveBoardManagerPermanent(
                        boardManager,
                        appCompatActivity);
//                saveToFile(SAVE_FILENAME);
                SaveAndLoad.saveBoardManagerTemp(
                        boardManager, appCompatActivity);
//                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
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
        boardManager = SaveAndLoad.loadBoardManagerTemp(this);
        SaveAndLoad.saveBoardManagerTemp(boardManager, this);
    }


    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        SaveAndLoad.saveBoardManagerTemp(
                boardManager, this);
//        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    /**
     * Switch to the UserScoreBoard view
     */
    private void switchToUserScoreBoard() {
        Intent tmp = new Intent(this, UserScoreBoardActivity.class);
        SaveAndLoad.saveBoardManagerTemp(
                boardManager, this);
//        saveToFile(TEMP_SAVE_FILENAME);
//        TODO: user proper file paths
        startActivity(tmp);
    }

    private void switchToGameScoreBoard() {
        Intent tmp = new Intent(this, GameScoreBoardActivity.class);
        SaveAndLoad.saveBoardManagerTemp(
                boardManager, this);
        saveToFile(TEMP_SAVE_FILENAME);
        startActivity(tmp);
    }

    private void addUserScoreBoardButton() {
        Button saveButton = findViewById(R.id.UserScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new GameLaunchCentre().getBoardManager();
                switchToUserScoreBoard();
            }
        });
    }

    private void addGameScoreBoardButton() {
        Button saveButton = findViewById(R.id.GameScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = new GameLaunchCentre().getBoardManager();
                switchToGameScoreBoard();
            }
        });
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
                boardManager = (BoardManager) input.readObject();
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
//            outputStream.writeObject(scoreBoard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
