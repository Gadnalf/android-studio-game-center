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
public class SlidingTilesStartingActivity extends AppCompatActivity {

//    /**
//     * where we store the scoreboards
//     */
//    public final String APP_DATA_DIR = this.getExternalCacheDir().toString();
//    public final String APP_DATA_DIR = this.getFilesDir().toString();
    /**
     * An account manager save file.
     */
    public static final String ACCOUNT_SAVE_FILENAME = "save_file_accounts.ser";

    /**
     * The main save file name.
     */
    public static String saveFilename = "save_file_st.ser";

    /**
     * The temporary save file name.
     */
    public static String tempSaveFilename = "temp_save_file_st.ser";

    /**
     * The board manager.
     */
    private SlidingTilesBoardManager slidingTilesBoardManager;

    /**
     * User name.
     */
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_slidingtiles);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        user = savedInstanceState.getString("user");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        saveFilename = user.concat("_save_file_st.ser");
        tempSaveFilename = user.concat("_temp_save_file_st.ser");
    }

//    private void setupStartingActivity() {
        //load in the accounts
//        loadAccountsFromFile(ACCOUNT_SAVE_FILENAME);
//        if (accountManager == null) {
//            accountManager = new AccountManager();
//        }
//        saveAccountsToFile(ACCOUNT_SAVE_FILENAME);

        //make a temp file just in case there is none yet
//        String user = accountManager.getName();
//        SaveAndLoad.saveAllTemp(
//                new GameHub(
//                        new SlidingTilesBoardManager(
//                                user,
//                                new SlidingTilesSettings(4,4)),
//                        new SeaInvadersBoardManager(user,
//                                new SeaInvadersSettings(.5, .5)),
//                        new ZTileBoardManager( user,
//                                new ZTileSettings(4 ,4)),
//
//                        user),
//                this);

        //load the board manager if it exists if not load the temp file
//        slidingTilesBoardManager = SaveAndLoad.loadGameHubPermanent(
//                user,
//                this);
//        //save the new board manager as temp if its been loaded
//        SaveAndLoad.saveGameHubTemp(
//                slidingTilesBoardManager,
//                this); //this will save the loaded board manager to tmp to be used in the other activities
//    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.start_game_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSetting();
            }
        });
    }

//    /**
//     * Activate the load button.
//     */
//    private void addLoadButtonListener(final AppCompatActivity appCompatActivity) {
//        Button loadButton = findViewById(R.id.load_game_button);
//        loadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slidingTilesBoardManager = SaveAndLoad.loadGameHubPermanent(
//                        slidingTilesBoardManager.getUser(),
//                        appCompatActivity);
////                loadFromFile(SAVE_FILENAME);
//                SaveAndLoad.saveGameHubTemp(
//                        slidingTilesBoardManager, appCompatActivity);
////                saveToFile(TEMP_SAVE_FILENAME);
//                makeToastLoadedText();
//                switchToGame();
//            }
//        });
//    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(saveFilename);
                saveToFile(tempSaveFilename);
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

//    /**
//     * Activate the save button.
//     */
//    private void addSaveButtonListener(final AppCompatActivity appCompatActivity) {
//        Button saveButton = findViewById(R.id.save_game_button);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SaveAndLoad.saveGameHubPermanent(
//                        slidingTilesBoardManager,
//                        appCompatActivity);
////                saveToFile(SAVE_FILENAME);
//                SaveAndLoad.saveGameHubTemp(
//                        slidingTilesBoardManager, appCompatActivity);
////                saveToFile(TEMP_SAVE_FILENAME);
//                makeToastSavedText();
//            }
//        });
//    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(saveFilename);
                saveToFile(tempSaveFilename);
                makeToastSavedText();
            }
        });
    }

//    /**
//     * Activate the game-specific scoreboard button.
//     */
//    private void addGameScoreBoardButton() {
//        Button saveButton = findViewById(R.id.GameScoreBoardButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToGameScoreBoard();
//            }
//        });
//    }
//
//    /**
//     * Activate the game-specific scoreboard button.
//     */
//    private void addUserScoreBoardButton() {
//        Button saveButton = findViewById(R.id.UserScoreBoardButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToUserScoreBoard();
//            }
//        });
//    }

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
        setupStartingActivity();
    }

//    /**
//     * Switch to the SlidingTileSetting to change the setting.
//     */
//    private void switchToSetting() {
//        Intent tmp = new Intent(this, SlidingTilesSettingsActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                slidingTilesBoardManager, this);
//        startActivity(tmp);
//    }

//    /**
//     * Read the temporary board from disk.
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        saveFilename = GameHubActivity.accountManager.getName().concat("_save_file.ser");
//        tempSaveFilename = GameHubActivity.accountManager.getName().concat("_temp_save_file.ser");
//        loadFromFile(tempSaveFilename);
//    }
//
//    /**
//     * Switch to the SlidingTilesGameActivity view to play the game.
//     */
//    private void switchToGame() {
//        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
//        saveToFile(tempSaveFilename);
//        startActivity(tmp);
//    }
//
    /**
     * Switch to the SlidingTilesSettings to set up a new game.
     */
    private void switchToSetting() {
        Intent tmp = new Intent(this, SlidingTilesSettingsActivity.class);
        saveToFile(tempSaveFilename);
        startActivity(tmp);
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
                slidingTilesBoardManager = (SlidingTilesBoardManager) input.readObject();
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
            outputStream.writeObject(slidingTilesBoardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}