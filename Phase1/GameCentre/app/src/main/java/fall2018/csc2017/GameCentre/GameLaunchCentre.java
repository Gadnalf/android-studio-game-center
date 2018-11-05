package fall2018.csc2017.GameCentre;

public class GameLaunchCentre {
    private AccountManager accountManager;
    private User user;
    private SlidingTileSettings slidingTileSettings;
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
        this.slidingTileSettings = new SlidingTileSettings(4, 2);
        this.boardManager = new BoardManager(user, slidingTileSettings);
    }

    public BoardManager getBoardManager() {
        return this.boardManager;
    }
}
