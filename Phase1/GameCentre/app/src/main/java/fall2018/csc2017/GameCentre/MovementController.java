package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.widget.Toast;


public class MovementController {

    private AbstractBoardManager abstractBoardManager = null;

    public MovementController() {
    }

    public void setAbstractBoardManager(AbstractBoardManager abstractBoardManager) {
        this.abstractBoardManager = abstractBoardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (abstractBoardManager.isValidTap(position)) {
            abstractBoardManager.touchMove(position);
            if (abstractBoardManager.puzzleSolved()) {
                double newScore = abstractBoardManager.getScore();
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "you scored=" + newScore, Toast.LENGTH_SHORT).show();
            }
        } else if (abstractBoardManager.isValidUndo(position)) {
            if (abstractBoardManager.moveIsEmpty()) {
                Toast.makeText(context, "No moves to Undo!", Toast.LENGTH_SHORT).show();
            } else if (abstractBoardManager.getGameSettings().getNumUndoes() > 0) {
                abstractBoardManager.tapUndo(position);
                Toast.makeText(context, "You have " +
                        abstractBoardManager.getGameSettings().getNumUndoes() +
                        " undoes left", Toast.LENGTH_SHORT).show();
            } else if (abstractBoardManager.getGameSettings().getNumUndoes() == -1) {
                abstractBoardManager.tapUndo(position);
            } else {
                Toast.makeText(context, "No more Undoes left", Toast.LENGTH_SHORT).show();
            }
        } else if (abstractBoardManager.isValidShoot(position)) {
            //TODO: implement
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
