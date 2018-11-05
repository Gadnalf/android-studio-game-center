package fall2018.csc2017.GameCentre;

import java.util.ArrayList;

/**
 * Stores and manages a list of user accounts, including data retrieval, account creation, and deletion.
 */

class AccountManager {

    /**
     * A list of user accounts.
     */
    private ArrayList<Account> accounts;

    /**
     * Current user account.
     */
    private Account current;

    AccountManager(){
        accounts = new ArrayList<>();
        current = null;
    }

    /**
     * If username is not in use, adds a new Account with username and password and returns true. Else, returns false.
     */
    boolean addAccount(String username, String password){
        if (accounts.contains(username)) {
            return false;
        }
        if(username.equals("") || password.equals("")){
            return false;
        }
        else{
            try {
                accounts.add(new Account(username, password));
                this.login(username, password);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }

    }

    /**
     * Removes the account with the specified username and returns true. If the account doesn't exist, returns false.
     */
    boolean removeAccount(String username) {
        if (accounts.contains(username)) {
            accounts.remove(username);
            return true;
        }
        return false;
    }

    /**
     * If an account with the given username and password is found, set it as active and return true. Else, return false.
     */
    boolean login(String username, String password){
        for(Account selected: accounts){
            if(selected.equals(username)){
                if(selected.getPassword().equals(password)){
                    current = selected;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Logs out of the current account.
     */
    void logout(){
        current = null;
    }

    /**
     * Adds the given filename under the specified game under the current account.
     */
    void getSaves(String game, String filename){
        current.addSave(game, filename);
    }

    /**
     * Returns a list of saves for the specified game under the current account.
     */
    ArrayList<String> getSaves(String game){
        return current.getSaves(game);
    }

    /**
     * Sets the current account's max score for the specified game.
     */
    public void setMaxScore(String game, Long max_score) {
        current.setMaxScore(game, max_score);
    }

    /**
     * Gets the current account's max score for the specified game.
     */
    public long getMaxScore(String game) {
        return current.getMaxScore(game);
    }

    /**
     * Gets the current account's username.
     */
    public String getName(){
        if(current == null){
            return "Guest";
        }
        return current.getName();
    }

}
