package fall2018.csc2017.GameCentre;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import java.io.Serializable;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        showScore();



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
                if(zTileBoardManager.getZTileSettings().getNumUndoes() == -1){
                    zTileBoardManager.undo();
                }else{zTileBoardManager.undo();
                    Toast.makeText(getBaseContext(), "You have " +
                            String.valueOf(zTileBoardManager.getZTileSettings().getNumUndoes())+
                            " undoes left", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    protected void showScore() {
        Button score = findViewById(R.id.score);
        score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Score: " +
                        String.valueOf(zTileBoardManager.getScore()), Toast.LENGTH_SHORT).show();
            }
        });
    }




}
