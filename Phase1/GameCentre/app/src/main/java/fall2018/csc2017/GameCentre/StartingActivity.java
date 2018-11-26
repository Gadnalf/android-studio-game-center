//package fall2018.csc2017.GameCentre;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//
///**
// * The initial activity for the sliding puzzle tile game.
// */
//public class StartingActivity extends AppCompatActivity {
//
//    /**
//     * The main save file.
//     */
//    public static final String SAVE_FILENAME = "save_file.ser";
//    /**
//     * A temporary save file.
//     */
//    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
//    /**
//     * where we store the scoreboards
//     */
////    public final String APP_DATA_DIR = this.getExternalCacheDir().toString();
////    public final String APP_DATA_DIR = this.getFilesDir().toString();
//    /**
//     * A game score board save file.
//     */
//    public static final String GAME_SCORE_BOARD_FILEPREFIX =   "game_score_board_";
//    /**
//     * A user score board save file.
//     */
//    public static final String USER_SCORE_BOARD_FILEPREFIX =  "_user_score_board_";
//    /**
//     * An account manager save file.
//     */
//    public static final String ACCOUNT_SAVE_FILENAME = "save_file_accounts.ser";
//
//    /**
//     * The board manager.
//     */
//    private GameHub gameHub;
//
//    /**
//     * The account manager.
//     */
//    private AccountManager accountManager;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setupStartingActivity();
//        setContentView(R.layout.activity_starting_slidingtiles);
//        addStartButtonListener();
//        addLoadButtonListener(this);
//        addSaveButtonListener(this);
//        addChangeAccountListener();
//        addGameScoreBoardButton();
//        addUserScoreBoardButton();
//        addSeaInvaderButton();
//        addZTileButton();
//    }
//
//    private void setupStartingActivity() {
//        //load in the accounts
//        loadAccountsFromFile(ACCOUNT_SAVE_FILENAME);
//        if (accountManager == null) {
//            accountManager = new AccountManager();
//        }
//        saveAccountsToFile(ACCOUNT_SAVE_FILENAME);
//
//        //make a temp file just in case there is none yet
//        User user = new User(accountManager.getName());
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
//
//        //load the board manager if it exists if not load the temp file
//        gameHub = SaveAndLoad.loadGameHubPermanent(
//                accountManager.getName(),
//                this);
//        //save the new board manager as temp if its been loaded
//        SaveAndLoad.saveGameHubTemp(
//                gameHub,
//                this); //this will save the loaded board manager to tmp to be used in the other activities
//    }
//
//    /**
//     * Activate the start button.
//     */
//    private void addStartButtonListener() {
//        Button startButton = findViewById(R.id.StartButton);
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToSetting();
//            }
//        });
//    }
//
//    /**
//     * Activate the load button.
//     */
//    private void addLoadButtonListener(final AppCompatActivity appCompatActivity) {
//        Button loadButton = findViewById(R.id.LoadButton);
//        loadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameHub = SaveAndLoad.loadGameHubPermanent(
//                        gameHub.getUser().getUserName(),
//                        appCompatActivity);
////                loadFromFile(SAVE_FILENAME);
//                SaveAndLoad.saveGameHubTemp(
//                        gameHub, appCompatActivity);
////                saveToFile(TEMP_SAVE_FILENAME);
//                makeToastLoadedText();
//                switchToGame();
//            }
//        });
//    }
//
//    /**
//     * Display that a game was loaded successfully.
//     */
//    private void makeToastLoadedText() {
//        Toast.makeText(this, "Loaded SlidingTileSettings", Toast.LENGTH_SHORT).show();
//    }
//
//    /**
//     * Activate the save button.
//     */
//    private void addSaveButtonListener(final AppCompatActivity appCompatActivity) {
//        Button saveButton = findViewById(R.id.SaveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SaveAndLoad.saveGameHubPermanent(
//                        gameHub,
//                        appCompatActivity);
////                saveToFile(SAVE_FILENAME);
//                SaveAndLoad.saveGameHubTemp(
//                        gameHub, appCompatActivity);
////                saveToFile(TEMP_SAVE_FILENAME);
//                makeToastSavedText();
//            }
//            });
//    }
//
//    /**
//     * Activates the change account button.
//     */
//    private void addChangeAccountListener() {
//        Button changeAccountButton = findViewById(R.id.account_change_button);
//        changeAccountButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToAccounts();
//            }
//        });
//    }
//
//    /**
//     * Display that a game was saved successfully.
//     */
//    private void makeToastSavedText() {
//        Toast.makeText(this, "SlidingTileSettings Saved", Toast.LENGTH_SHORT).show();
//    }
//    /**
//     * Read the temporary board from disk.
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setupStartingActivity();
//    }
//
//
//    /**
//     * Switch to the SlidingTilesGameActivity view to play the game.
//     */
//    private void switchToGame() {
//        Intent tmp = new Intent(this, SlidingTilesGameActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                gameHub, this);
////        saveToFile(TEMP_SAVE_FILENAME);
//        startActivity(tmp);
//    }
//
//    /**
//     * Switch to the SlidingTileSetting to change the setting.
//     */
//    private void switchToSetting() {
//        Intent tmp = new Intent(this, SlidingTilesSettingsActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                gameHub, this);
//        startActivity(tmp);
//    }
//    /**
//     * Switch to the LoginActivity view to change the user account.
//     */
//    private void switchToAccounts() {
//        Intent tmp = new Intent(this, LoginActivity.class);
//        saveAccountsToFile(ACCOUNT_SAVE_FILENAME);
//        startActivity(tmp);
//    }
//
//    /**
//     * Switch to the UserScoreBoard view
//     */
//    private void switchToUserScoreBoard() {
//        Intent tmp = new Intent(this, UserScoreBoardActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                gameHub, this);
////        saveToFile(TEMP_SAVE_FILENAME);
////        TODO: user proper file paths
//        startActivity(tmp);
//    }
//
//    private void switchToGameScoreBoard() {
//        Intent tmp = new Intent(this, GameScoreBoardActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                gameHub, this);
//        SaveAndLoad.saveGameHubTemp(gameHub, this);
//        startActivity(tmp);
//    }
//
//    private void addUserScoreBoardButton() {
//        Button saveButton = findViewById(R.id.UserScoreBoardButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                gameHub = new GameLaunchCentre().getBoardManager();
//                switchToUserScoreBoard();
//            }
//        });
//    }
//
//    private void addGameScoreBoardButton() {
//        Button saveButton = findViewById(R.id.GameScoreBoardButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                gameHub = new GameLaunchCentre().getBoardManager();
//                switchToGameScoreBoard();
//            }
//        });
//    }
//
//    private void switchToSeaInvaders() {
//        Intent tmp = new Intent(this, SeaInvadersGameActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                gameHub, this);
//        SaveAndLoad.saveGameHubTemp(gameHub, this);
//        startActivity(tmp);
//    }
//
////    private void addSeaInvaderButton() {
////        Button saveButton = findViewById(R.id.sea_invader_button);
////        saveButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                switchToSeaInvaders();
////            }
////        });
////    }
//
//    private void switchToZTile() {
//        Intent tmp = new Intent(this, ZTileActivity.class);
//        SaveAndLoad.saveGameHubTemp(
//                gameHub, this);
//        SaveAndLoad.saveGameHubTemp(gameHub, this);
//        startActivity(tmp);
//    }
//
//    private void addZTileButton() {
//        Button saveButton = findViewById(R.id.z_tile_button);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchToZTile();
//            }
//        }) ;
//    }
//
//
//    /**
//     * Load the account manager from fileName.
//     *
//     * @param fileName the name of the file
//     */
//    private void loadAccountsFromFile(String fileName) {
//
//        try {
//            InputStream inputStream = this.openFileInput(fileName);
//            if (inputStream != null) {
//                ObjectInputStream input = new ObjectInputStream(inputStream);
//                accountManager = (AccountManager) input.readObject();
//                inputStream.close();
//            }
//        } catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
//        }
//    }
//
//    /**
//     * Save the account manager to fileName.
//     *
//     * @param fileName the name of the file
//     */
//    public void saveAccountsToFile(String fileName) {
//        try {
//            ObjectOutputStream outputStream = new ObjectOutputStream(
//                    this.openFileOutput(fileName, MODE_PRIVATE));
//            outputStream.writeObject(accountManager);
//            outputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//}
