package fall2018.csc2017.GameCentre;

public class GameLaunchCentre {
    private User user;
    private Game game;
    private StartingActivity startingActivity;
    private ScoreBoard scoreBoard;

    public GameLaunchCentre(User user, Game game) {
        this.user = user;
        this.game = game;
        this.startingActivity = new StartingActivity();
        this.scoreBoard = new ScoreBoard(user, game, this.startingActivity.getBoardManager());
    }
}
