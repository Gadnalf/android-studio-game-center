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
     * An account object.
     *
     * @param name username of the account.
     * @param pass password of the account.
     */
    Account(String name, String pass){
        username = name;
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
     * Returns whether inputted object and this account is equal.
     *
     * @param other any object but expect account object.
     * @return True if other is same as this class else false.
     */
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
