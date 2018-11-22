package fall2018.csc2017.GameCentre;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SeaInvaderGameActivity extends AbstractGameActivity implements Serializable {

    SeaInvadersBoardManager seaInvadersBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeaInvaderSettings seaInvadersSettings = new SeaInvaderSettings(5,5);
        seaInvadersBoardManager = new SeaInvadersBoardManager(GameHubActivity.accountManager.getName(), seaInvadersSettings);
        setAbstractBoardManager(seaInvadersBoardManager);
        super.onCreate(savedInstanceState);
        
        createTileButtons(this);
        setContentView(R.layout.activity_sea_invader);

        // Add View to activity
        gridView = findViewById(R.id.activity_sea_invader);
        gridView.setNumColumns(seaInvadersBoardManager.getGameSettings().getBoardSize());
        gridView.setAbstractBoardManager(seaInvadersBoardManager);
        seaInvadersBoardManager.getBoard().addObserver(this);
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
        //TODO: Implement once SeaInvaderStartingActivity is done
    }
}
