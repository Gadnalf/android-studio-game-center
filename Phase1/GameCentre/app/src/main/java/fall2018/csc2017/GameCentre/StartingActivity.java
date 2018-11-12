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
     * An account manager save file.
     */
    public static final String ACCOUNT_SAVE_FILENAME = "save_file_accounts.ser";

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The account manager.
     */
    private AccountManager accountManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load in the accounts
        loadAccountsFromFile(ACCOUNT_SAVE_FILENAME);
        if (accountManager == null) {
            accountManager = new AccountManager();
        }
        saveAccountsToFile(ACCOUNT_SAVE_FILENAME);

        //make a temp file just in case there is none yet
        SaveAndLoad.saveBoardManagerTemp(new BoardManager(new User(accountManager.getName()),
                new SlidingTileSettings(4,4)),
                this);

        //load the board manager if it exists if not load the temp file
        boardManager = SaveAndLoad.loadBoardManagerPermanent(accountManager.getName(), this);

//        if (boardManager == null) {
//            SlidingTileSettings slidingTileSettings = new SlidingTileSettings(4,4);
//            //these will be altered if the user decides change them in the next activity
//            boardManager = new BoardManager(new User(accountManager.getName()),
//                    slidingTileSettings);
//        }

        //save the new board manager as temp if its loaded
        SaveAndLoad.saveBoardManagerTemp(
                boardManager,
                this);
        //save the baord manager as perm too (to save the temp as permanent in save it doesnt exist yet)
        SaveAndLoad.saveBoardManagerPermanent(
                boardManager,
                this);

        setContentView(R.layout.activity_starting_);

        addStartButtonListener();
        addLoadButtonListener(this);
        addSaveButtonListener(this);
        addChangeAccountListener();
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
     * Activates the change account button.
     */
    private void addChangeAccountListener() {
        Button changeAccountButton = findViewById(R.id.account_change_button);
        changeAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToAccounts();
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
        loadAccountsFromFile(ACCOUNT_SAVE_FILENAME);
        boardManager = SaveAndLoad.loadBoardManagerTemp(this);
        boardManager.setUser(new User(accountManager.getName()));
        SaveAndLoad.saveBoardManagerTemp(boardManager, this); // => when we try and load the perm one below
        // if its not defined it will load this one instead of the last users tmp save
//        boardManager.setUser(new User(accountManager.getName()));
        String accName = accountManager.getName();
        boardManager = SaveAndLoad.loadBoardManagerPermanent(accName, this);
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
     * Switch to the SlidingTileSetting to change the setting.
     */
    private void switchToSetting() {
        Intent tmp = new Intent(this, SlidingTileSettingsActivity.class);
        SaveAndLoad.saveBoardManagerTemp(
                boardManager, this);
        startActivity(tmp);
    }
    /**
     * Switch to the LoginActivity view to change the user account.
     */
    private void switchToAccounts() {
        Intent tmp = new Intent(this, LoginActivity.class);
        saveAccountsToFile(ACCOUNT_SAVE_FILENAME);
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
        SaveAndLoad.saveBoardManagerTemp(boardManager, this);
        startActivity(tmp);
    }

    private void addUserScoreBoardButton() {
        Button saveButton = findViewById(R.id.UserScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boardManager = new GameLaunchCentre().getBoardManager();
                switchToUserScoreBoard();
            }
        });
    }

    private void addGameScoreBoardButton() {
        Button saveButton = findViewById(R.id.GameScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boardManager = new GameLaunchCentre().getBoardManager();
                switchToGameScoreBoard();
            }
        });
    }


    /**
     * Load the account manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadAccountsFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                accountManager = (AccountManager) input.readObject();
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
     * Save the account manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveAccountsToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(accountManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
