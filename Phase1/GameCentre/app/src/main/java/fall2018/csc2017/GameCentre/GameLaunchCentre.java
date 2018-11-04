package fall2018.csc2017.GameCentre;
import java.util.Scanner;

public class GameLaunchCentre {
    private AccountManager accountManager;
    private User user;
    private Game game;
    private StartingActivity startingActivity;
    private ScoreBoard scoreBoard;
    private BoardManager boardManager;

    public GameLaunchCentre() {
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("enter username");
//        String userName = input.next();
//        System.out.println("enter pwd");
//        String userName = input.next();
        this.accountManager = new AccountManager();
        accountManager.addAccount("testing", "testing");
        this.user = new User("testing","testing");
        this.game = new Game(4);
        this.boardManager = new BoardManager(user, game);
    }

    public BoardManager getBoardManager() {
        return this.boardManager;
    }
}
