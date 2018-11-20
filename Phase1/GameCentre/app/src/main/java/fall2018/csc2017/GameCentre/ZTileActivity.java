package fall2018.csc2017.GameCentre;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import java.io.Serializable;


public class Z_TileActivity extends AbstractGameActivity implements Serializable{
    SlidingTilesBoardManager slidingTilesBoardManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        slidingTilesBoardManager = SaveAndLoad.loadGameHubTemp(this).getSlidingTilesBoardManager();
        setAbstractBoardManager(slidingTilesBoardManager);
        super.onCreate(savedInstanceState);


        createTileButtons(this);
        setContentView(R.layout.activity_z_tile);

        // Add View to activity


        gridView = findViewById(R.id.something);
        gridView.setAbstractBoardManager(slidingTilesBoardManager);
        slidingTilesBoardManager.getBoard().addObserver(this);
        gridView.setNumColumns(5);

        final int COL_FINAL = 5;
        final int ROW_FINAL = 5;

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

        SaveAndLoad.saveGameHubPermanent(gameHub, this);
        SaveAndLoad.saveGameHubTemp(gameHub, this);
    }
}
