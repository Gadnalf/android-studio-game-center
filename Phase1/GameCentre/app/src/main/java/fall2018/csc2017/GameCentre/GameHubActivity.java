package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The game hub activity.
 */
public class GameHubActivity extends AppCompatActivity {

    /**
     * An account manager save file.
     */
    public static final String ACCOUNT_SAVE_FILENAME = "save_file_accounts.ser";

    /**
     * The account manager.
     */
    protected static AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hub);

        loadAccountsFromFile(ACCOUNT_SAVE_FILENAME);
        if (accountManager == null) {
            accountManager = new AccountManager();
        }
        saveAccountsToFile(ACCOUNT_SAVE_FILENAME);

        addSeaInvadersButtonListener();
        add2048ButtonListener();
        addSlidingTilesButtonListener();
        addChangeAccountButtonListener();
    }

    /**
     * Activates the Sea Invader button.
     */
    private void addSeaInvadersButtonListener() {
        Button seaInvadersButton = findViewById(R.id.sea_invaders_button);
        seaInvadersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSeaInvaders();
            }
        });
    }

    /**
     * Activates the 2048 button.
     */
    private void add2048ButtonListener(){
        Button tfeButton = findViewById(R.id.game_2048_button);
        tfeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchTo2048();
            }
        });
    }

    /**
     * Activates the Sliding Tiles button.
     */
    private void addSlidingTilesButtonListener(){
        Button tfeButton = findViewById(R.id.sliding_tile_button);
        tfeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchToSlidingTiles();
            }
        });
    }

    /**
     * Activates the change account button.
     */
    private void addChangeAccountButtonListener() {
        Button changeAccountButton = findViewById(R.id.account_change_button);
        changeAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToAccounts();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAccountsFromFile(ACCOUNT_SAVE_FILENAME);
    }

    /**
     * Switch to the SeaInvadersStartingActivity view to start a Sea Invaders game.
     */
    private void switchToSeaInvaders() {
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
        //TODO: update to go where it's supposed to
        startActivity(tmp);
    }

    /**
     * Switch to the 2048StartingActivity view to start a 2048 game.
     */
    private void switchTo2048() {
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
        //TODO: update to go where it's supposed to
        startActivity(tmp);
    }

    /**
     * Switch to SlidingTileActivity view to start a 2048 game.
     */
    private void switchToSlidingTiles() {
        Intent tmp = new Intent(this, SlidingTileStartingActivity.class);
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
