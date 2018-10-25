package fall2018.csc2017.GameCentre;
import java.util.Scanner;

public class GameLaunchCentre {
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
        this.user = new User("testing", "testing");
        this.game = new Game(4);
        scoreBoard = new ScoreBoard(user, game);
        this.boardManager = new BoardManager(scoreBoard);
    }

    public BoardManager getBoardManager() {
        return this.boardManager;
    }
}
