package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import java.io.Serializable;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends AbstractGameActivity implements Serializable {
    SlidingTilesBoardManager slidingTilesBoardManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        slidingTilesBoardManager = SaveAndLoad.loadGameHubTemp(this).getSlidingTilesBoardManager();
        setAbstractBoardManager(slidingTilesBoardManager);
        super.onCreate(savedInstanceState);
//        SlidingTilesGameActivity slidingTilesGameActivity = new SlidingTilesGameActivity(abstractBoardManager);
        // Will not work w out the line below
        slidingTilesBoardManager.setAppCompatActivity(this);

        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(slidingTilesBoardManager.getSlidingTileSettings().getBoardSize());
        gridView.setAbstractBoardManager(slidingTilesBoardManager);
        slidingTilesBoardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        final int COL_FINAL = slidingTilesBoardManager.getSlidingTileSettings().getBoardSize();
        final int ROW_FINAL = slidingTilesBoardManager.getSlidingTileSettings().getBoardSize();

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
        GameData gameData = SaveAndLoad.loadGameHubTemp(this);
        gameData.setSlidingTilesBoardManager(slidingTilesBoardManager);
        SaveAndLoad.saveGameHubPermanent(gameData, this);
        SaveAndLoad.saveGameHubTemp(gameData, this);
    }
}
