package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores a username, password, personal high scores, and corresponding save files.
 */
public class Account implements Serializable {

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * A hash map storing a list of games and corresponding high scores.
     */
    private HashMap<String, Long> max_scores = new HashMap<>();

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
     * Stores the given filename under the specified game.
     */
    void addSave(String game, String file_name){
        ArrayList<String> new_saves = new ArrayList<>();
        if(saves.containsKey(game)){
            new_saves = saves.get(game);
            new_saves.add(file_name);
            saves.put(game, new_saves);
        }
        else {
            new_saves.add(file_name);
        }
        saves.put(game, new_saves);
    }

    /**
     * Returns a list of the account saves for a game.
     */
    ArrayList<String> getSaves(String game){
        return saves.get(game);
    }

    /**
     * Sets the maximum score for the specified game.
     */
    void setMaxScore(String game, Long score){
        max_scores.put(game, score);
    }

    /**
     * Gets the maximum score for the specified game.
     */
    Long getMaxScore(String game){
        if(max_scores.containsKey(game)){
            return max_scores.get(game);
        }
        return Long.valueOf(0);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Account){
            Account other_account = (Account)other;
            return username.equalsIgnoreCase(other_account.getName());
        }
        if(other instanceof String){
            String other_name = (String)other;
            return username.equalsIgnoreCase(other_name);
        }
        return false;
    }
}
