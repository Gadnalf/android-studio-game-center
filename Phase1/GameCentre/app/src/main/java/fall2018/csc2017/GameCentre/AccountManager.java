package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores and manages a list of user accounts, including data retrieval, account creation, and deletion.
 */

class AccountManager implements Serializable {

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
        if(username.equals("") || username.equalsIgnoreCase("Guest") || password.equals("")){
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
     * Returns the account name.
     */
    String getName(){
        return current.getName();
    }

    /**
     * Logs out of the current account.
     */
    void logout(){
        current = null;
    }

}
