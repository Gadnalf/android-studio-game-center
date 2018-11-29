package fall2018.csc2017.GameCentre;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import java.io.Serializable;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class AlphabetTilesGameActivity extends AbstractGameActivity implements Serializable{
    AlphabetTilesBoardManager alphabetTilesBoardManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        alphabetTilesBoardManager = SaveAndLoad.loadGameHubTemp(this).getZTileBoardManager();
        setAbstractBoardManager(alphabetTilesBoardManager);
        super.onCreate(savedInstanceState);
        alphabetTilesBoardManager.setAppCompatActivity(this);



        createTileButtons(this);
        setContentView(R.layout.activity_alphabet_tile);

        // Add View to activity


        gridView = findViewById(R.id.board);
        gridView.setNumColumns(alphabetTilesBoardManager.getZTileSettings().getBoardSize());
        gridView.setAbstractBoardManager(alphabetTilesBoardManager);
        alphabetTilesBoardManager.getBoard().addObserver(this);


        final int COL_FINAL = alphabetTilesBoardManager.getZTileSettings().getBoardSize();
        final int ROW_FINAL = alphabetTilesBoardManager.getZTileSettings().getBoardSize();



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
        gameHub.setZTileBoardManager(alphabetTilesBoardManager);
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
                if(alphabetTilesBoardManager.moveIsEmpty()){
                    Toast.makeText(getBaseContext(), "You have no moves to undo",
                            Toast.LENGTH_SHORT).show();
                }else if(alphabetTilesBoardManager.getZTileSettings().getNumUndoes() == -1){
                    alphabetTilesBoardManager.undo();
                }else{
                    alphabetTilesBoardManager.undo();
                    Toast.makeText(getBaseContext(), "You have " +
                            String.valueOf(alphabetTilesBoardManager.getZTileSettings().getNumUndoes())+
                            " undoes left", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    protected void showScore() {
        Button score = findViewById(R.id.score);
        score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Score: " +
                        String.valueOf((int) alphabetTilesBoardManager.getScore()), Toast.LENGTH_SHORT).show();
            }
        });
    }




}
