package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
