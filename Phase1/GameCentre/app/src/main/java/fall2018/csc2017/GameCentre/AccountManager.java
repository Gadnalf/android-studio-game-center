package fall2018.csc2017.GameCentre;

import java.util.ArrayList;

/**
 * Stores and manages a list of user accounts, including account creation and deletion.
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
    }

    /**
     * If name is not in use, adds a new Account with name and password and returns true. Else, returns false.
     */
    boolean addAccount(String name, String pass){
        if (accounts.contains(name)) {
            accounts.add(new Account(name, pass));
            return true;
        }
        return false;
    }

    /**
     * Removes the account with the specified username and returns true.
     */
    boolean removeAccount(String user) {
        if (accounts.contains(user)) {
            accounts.remove(user);
            return true;
        }
        return false;
    }

    /**
     * If an account with the given username and password is found, set it as active and return true. Else, return false.
     */
    boolean login(String user, String password){
        for(Account selected: accounts){
            if(selected.equals(user)){
                if(selected.getPassword().equals(password)){
                    current = selected;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the save for the provided game.
     */
    ArrayList<String> getSaves(String game){
        return current.getSaves(game);
    }
}