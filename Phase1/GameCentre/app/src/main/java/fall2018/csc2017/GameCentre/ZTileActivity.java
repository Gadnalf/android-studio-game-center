package fall2018.csc2017.GameCentre;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import java.io.Serializable;
import android.widget.Button;
import android.view.View;

public class ZTileActivity extends AbstractGameActivity implements Serializable{
    ZTileBoardManager zTileBoardManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        zTileBoardManager = SaveAndLoad.loadGameHubTemp(this).getZTileBoardManager();
        setAbstractBoardManager(zTileBoardManager);
        super.onCreate(savedInstanceState);
        zTileBoardManager.setAppCompatActivity(this);


        createTileButtons(this);
        setContentView(R.layout.activity_z_tile);

        // Add View to activity


        gridView = findViewById(R.id.board);
        gridView.setNumColumns(zTileBoardManager.getZTileSettings().getBoardSize());
        gridView.setAbstractBoardManager(zTileBoardManager);
        zTileBoardManager.getBoard().addObserver(this);


        final int COL_FINAL = zTileBoardManager.getZTileSettings().getBoardSize();
        final int ROW_FINAL = zTileBoardManager.getZTileSettings().getBoardSize();



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

        addUndoListener();


    }
    @Override
    protected void autoSave() {
        GameHub gameHub = SaveAndLoad.loadGameHubTemp(this);
        gameHub.setZTileBoardManager(zTileBoardManager);
        SaveAndLoad.saveGameHubPermanent(gameHub, this);
        SaveAndLoad.saveGameHubTemp(gameHub, this);
    }

    /**
     * comment needed
     */

    protected void addUndoListener() {
        Button undo = findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zTileBoardManager.undo();
            }
        });
    }
}
