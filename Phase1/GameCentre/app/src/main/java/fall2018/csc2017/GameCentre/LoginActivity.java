package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The account login activity.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * The header text.
     */
    TextView greeting;

    /**
     * The username input field.
     */
    EditText username;

    /**
     * The password input field.
     */
    EditText password;

    /**
     * The account manager.
     */
    private AccountManager accountManager = new AccountManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile(StartingActivity.ACCOUNT_SAVE_FILENAME);
        setContentView(R.layout.activity_login);
        addLoginButtonListener();
        addNewAccountButtonListener();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        this.greeting = findViewById(R.id.user_greeting);
        this.username = findViewById(R.id.name_input);
        this.password = findViewById(R.id.password_input);
        String tmp = "Welcome, " + accountManager.getName();
        greeting.setText(tmp);
    }

    /**
     * Activate the login button.
     */
    void addLoginButtonListener() {
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accountManager.login(username.getText().toString(), password.getText().toString())){
//                if(accountManager.login(((EditText)(v.findViewById(R.id.name_input))).getText().toString(),
//                        ((EditText)(v.findViewById(R.id.password_input))).getText().toString())){
                    String tmp = "Welcome, " + accountManager.getName();
                    greeting.setText(tmp);
                    makeToastLoginSuccessText();
                }
                else{
                    makeToastLoginFailText();
                }
            }
        });
    }

    /**
     * Activate the new account button.
     */
    private void addNewAccountButtonListener() {
        Button newAccount = findViewById(R.id.new_account_button);
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accountManager.addAccount(username.getText().toString(), password.getText().toString())){
//                if(accountManager.login(((EditText)(v.findViewById(R.id.name_input))).getText().toString(),
//                        ((EditText)(v.findViewById(R.id.password_input))).getText().toString())){
                    String tmp = "Welcome, " + accountManager.getName();
                    greeting.setText(tmp);
                    makeToastLoginSuccessText();
                }
                else{
                    makeToastNameTakenText();
                }
            }
        });
    }

    /**
     * Display that an account was loaded successfully.
     */
    private void makeToastLoginSuccessText() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that an account was not loaded successfully.
     */
    private void makeToastLoginFailText() {
        Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();
    }

    /**
     * Display that an account was not loaded successfully.
     */
    private void makeToastNameTakenText() {
        Toast.makeText(this, "Username Already In Use", Toast.LENGTH_LONG).show();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(StartingActivity.ACCOUNT_SAVE_FILENAME);
    }

    /**
     * Save the account manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(accountManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Load the account manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {
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

}