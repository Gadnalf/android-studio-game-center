package fall2018.csc2017.GameCentre;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import java.io.Serializable;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Game2048Activity extends AbstractGameActivity implements Serializable{
    SlidingTilesBoardManager slidingTilesBoardManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        slidingTilesBoardManager = SaveAndLoad.loadGameHubTemp(this).getSlidingTilesBoardManager();
        setAbstractBoardManager(slidingTilesBoardManager);
        super.onCreate(savedInstanceState);


        createTileButtons(this);
        setContentView(R.layout.activity_2048);

        // Add View to activity


        gridView = findViewById(R.id.something);
        gridView.setAbstractBoardManager(slidingTilesBoardManager);
        slidingTilesBoardManager.getBoard().addObserver(this);
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
