package fall2018.csc2017.GameCentre;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores a username, password, and corresponding save files.
 */
public class Account {

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * A hash map storing a list of games and corresponding lists of saves.
     */
    private HashMap<String, ArrayList<String>> saves = new HashMap<>();

    Account(String name, String pass){
        username = name;
        password = pass;
    }

    /**
     * Sets the account's password to the password specified.
     */
    void setPassword(String pass){
        password = pass;
    }

    /**
     * Returns the account password.
     */
    String getPassword(){
        return password;
    }

    /**
     * Returns the account name.
     */
    String getName(){
        return username;
    }

    /**
     * Returns a list of the account saves for a game.
     */
    ArrayList<String> getSaves(String game){
        return saves.get(game);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Account){
            Account other_account = (Account)other;
            return username.equals(other_account.getName());
        }
        if(other instanceof String){
            String other_name = (String)other;
            return username.equals(other_name);
        }
        return false;
    }
}
