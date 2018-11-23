package fall2018.csc2017.GameCentre;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import java.io.Serializable;


public class ZTileActivity extends AbstractGameActivity implements Serializable{
    ZTileBoardManager zTileBoardManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        zTileBoardManager = SaveAndLoad.loadGameHubTemp(this).getZTileBoardManager();
        zTileBoardManager.setBoardSize(zTileBoardManager.getZTileSettings().getBoardSize());
        setAbstractBoardManager(zTileBoardManager);
        super.onCreate(savedInstanceState);


        createTileButtons(this);
        setContentView(R.layout.activity_z_tile);

        // Add View to activity


        gridView = findViewById(R.id.board);
        gridView.setAbstractBoardManager(zTileBoardManager);
        zTileBoardManager.getBoard().addObserver(this);
        gridView.setNumColumns(4);

        final int COL_FINAL = 4;
        final int ROW_FINAL = 4;

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
