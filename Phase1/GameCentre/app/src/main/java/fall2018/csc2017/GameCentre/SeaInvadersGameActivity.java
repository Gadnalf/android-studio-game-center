package fall2018.csc2017.GameCentre;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

import java.io.Serializable;

public class SeaInvadersGameActivity extends AbstractGameActivity implements Serializable {

    SeaInvadersBoardManager seaInvadersBoardManager;
    AppCompatActivity appCompatActivity = this;

    //https://stackoverflow.com/questions/4597690/android-timer-how-to

//    final Handler timerHandler = new Handler();
    final HandlerSerializable timerHandler = new HandlerSerializable();
    Runnable timerRunnable = new RunnerSerializable() {

        @Override
        public void run() {
             seaInvadersBoardManager.setAppCompatActivity(appCompatActivity);
            seaInvadersBoardManager.swim();
            seaInvadersBoardManager.spawnTheInvaders();
             if (seaInvadersBoardManager.isGameOver()) {
                 seaInvadersBoardManager.resetGame();
             }
//            seaInvadersBoardManager.board.notifyObservers();
//                timerHandler.postDelayed(this, 5000);
            timerHandler.postDelayed(this,
                    1000 * (int) ((SeaInvadersSettings) seaInvadersBoardManager.gameSettings).getSecsBeforeMove());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        seaInvadersBoardManager = SaveAndLoad.loadGameHubTemp(this).getSeaInvadersBoardManager();
        setAbstractBoardManager(seaInvadersBoardManager);
        super.onCreate(savedInstanceState);
         seaInvadersBoardManager.setAppCompatActivity(this);

        createTileButtons(this);
        setContentView(R.layout.activity_sea_invader);

        // Add View to activity
        gridView = findViewById(R.id.activity_sea_invader);
        gridView.setNumColumns(seaInvadersBoardManager.getGameSettings().getBoardSize());
        gridView.setAbstractBoardManager(seaInvadersBoardManager);
        seaInvadersBoardManager.getBoard().addObserver(this);
//        seaInvadersBoardManager.startGame();
        timerHandler.postDelayed(timerRunnable, 0);
        // Observer sets up desired dimensions as well as calls our display function
        final int COL_FINAL = seaInvadersBoardManager.getGameSettings().getBoardSize();
        final int ROW_FINAL = seaInvadersBoardManager.getGameSettings().getBoardSize();

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / COL_FINAL;
                        columnHeight = displayHeight / ROW_FINAL;

                        display();
                    }
                });
    }

    @Override
    protected void autoSave() {
        GameHub gameHub = SaveAndLoad.loadGameHubTemp(this);
        gameHub.setSeaInvadersBoardManager(seaInvadersBoardManager);
        SaveAndLoad.saveGameHubPermanent(gameHub, this);
        SaveAndLoad.saveGameHubTemp(gameHub, this);
    }
}
