package fall2018.csc2017.GameCentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The game hub activity.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * A user score board save file.
     */
    public static final String USER_SCORE_BOARD_FILEPREFIX =  "_user_score_board_";

    /**
     * An account manager save file.
     */
    public static final String ACCOUNT_SAVE_FILENAME = "save_file_accounts.ser";

    /**
     * The account manager.
     */
    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hub);

        addSeaInvaderButtonListener();
        add2048ButtonListener();
        addSlidingTileButtonListener();
        addChangeAccountButtonListener();
        addUserScoreBoardButton();
    }


    private void addSeaInvaderButton() {
        Button saveButton = findViewById(R.id.sea_invader_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSeaInvaders();
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

    private void addUserScoreBoardButton() {
        Button saveButton = findViewById(R.id.UserScoreBoardButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserScoreBoard();
            }
        });
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
        SaveAndLoad.saveGameHubTemp(
                gameHub, this);
//        saveToFile(TEMP_SAVE_FILENAME);
//        TODO: user proper file paths
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
