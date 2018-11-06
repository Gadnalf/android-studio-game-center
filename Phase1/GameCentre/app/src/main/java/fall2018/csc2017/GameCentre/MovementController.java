package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.widget.Toast;


public class MovementController {

    private BoardManager boardManager = null;

    public MovementController() {
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                long newScore = boardManager.getScore();
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "you scored=" + newScore, Toast.LENGTH_SHORT).show();
            }
        } else if(boardManager.isValidUndo(position)) {
            if(boardManager.moveIsEmpty()) {
                Toast.makeText(context, "No moves to Undo!", Toast.LENGTH_SHORT).show();
            }else if(boardManager.getSlidingTileSettings().getNumUndoes() > 0){
                boardManager.tapUndo(position);
                Toast.makeText(context, "You have " +
                        boardManager.getSlidingTileSettings().getNumUndoes() +
                        " undoes left", Toast.LENGTH_SHORT).show();
            }else if (boardManager.getSlidingTileSettings().getNumUndoes() == -1) {
                boardManager.tapUndo(position);
            }else{
                Toast.makeText(context, "No more Undoes left", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
