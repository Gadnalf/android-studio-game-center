package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

abstract public class AbstractGameActivity extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private AbstractBoardManager abstractBoardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;


    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    protected GestureDetectGridView gridView;
    protected static int columnWidth, columnHeight;


    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    AbstractGameActivity(AbstractBoardManager abstractBoardManager) {
//        this.abstractBoardManager = abstractBoardManager;
//    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    protected void createTileButtons(Context context) {

        Board board = abstractBoardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getBoardSize(); row++) {
            for (int col = 0; col != board.getBoardSize(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    protected void updateTileButtons() {
        Board board = abstractBoardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getBoardSize();
            int col = nextPos % board.getBoardSize();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
        autoSave();
    }

    /**
     * Auto-save the game
     */
    abstract protected void autoSave();
//        GameHub gameHub = SaveAndLoad.loadGameHubTemp(this);
//        gameHub.setAbstractBoardManager();
//        SaveAndLoad.saveGameHubPermanent(abstractBoardManager, this);
//        SaveAndLoad.saveGameHubTemp(abstractBoardManager, this);
//    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        autoSave();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    public void setAbstractBoardManager(AbstractBoardManager abstractBoardManager) {
        this.abstractBoardManager = abstractBoardManager;
    }
}
