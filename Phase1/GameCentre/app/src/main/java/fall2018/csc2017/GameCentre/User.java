package fall2018.csc2017.GameCentre;

public class User {

    private String userName;
    private String userPassword;
    private long maxScore;
    private static int numUsers = -1;
    private static int userId;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.maxScore = 0;
        this.userId = numUsers + 1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }

    public static int getUserId() {
        return userId;
    }

}
