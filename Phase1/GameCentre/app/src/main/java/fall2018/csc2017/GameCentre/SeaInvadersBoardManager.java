package fall2018.csc2017.GameCentre;

import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SeaInvadersBoardManager extends AbstractBoardManager implements Serializable {


    int lastOccupiedColumn = -1; //TODO: make sure he starts here
    int currentRound = 0;
    ArrayList<Integer> invaderPositions = new ArrayList<Integer>();
    boolean gameOver = false;

    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    SeaInvadersBoardManager(Board board, User user, SeaInvaderSettings seaInvaderSettings,
                            AppCompatActivity appCompatActivity) {
        super(board, user, seaInvaderSettings,
                appCompatActivity);
        this.lastOccupiedColumn = seaInvaderSettings.getBoardSize() - 1;
    }


    SeaInvadersBoardManager(User user, SeaInvaderSettings seaInvaderSettings) {
        super(user, seaInvaderSettings, new SeaInvadersTileFactory());
        this.lastOccupiedColumn = seaInvaderSettings.getBoardSize() - 1;
    }

    /**
     * Return whether currentRound == numRounds, and all enemies killed
     *
     * @return whether the tiles are in row-major order
     */
    @Override
    boolean puzzleSolved() {
        if (this.currentRound == ((SeaInvaderSettings) this.gameSettings).getNumRounds()
                //TODO: when you update the rounds between spawn and move to be indep we'll need to alter this too
                && getInvaderPositions().size() == 0
                && isGameOver() == false) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return whether tap is in 1st row, and col is a new col
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    @Override
    boolean isValidTap(int position) {
        if (this.lastOccupiedColumn == -1) {
            setLastOccupiedColumnToStart();
        }
        int row = position / getGameSettings().getBoardSize();
        int col = position % getGameSettings().getBoardSize();
        if (row == getGameSettings().getBoardSize() - 1 && col != this.lastOccupiedColumn) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    @Override
    void touchMove(int position) {

        int row = position / this.getGameSettings().getBoardSize();
        int col = position % this.getGameSettings().getBoardSize();
        int blankId = board.numTiles();
        if (isValidTap(position)) {
            board.swapTiles(
                    this.getGameSettings().getBoardSize() - 1,
                    col,
                    this.getGameSettings().getBoardSize() - 1,
                    this.lastOccupiedColumn);
            this.lastOccupiedColumn = col;
        }
    }

    /**
     * return true if there's an enemy to shoot at
     *
     * @param position
     * @return
     */
    @Override
    boolean isValidShoot(int position) {
        //TODO implement (1)
        return super.isValidShoot(position);
    }

    /**
     * fire at the enemy and update the grid
     * see MovementController processTapMovement for ideas on where to start
     * Should be somewhat similar to undo
     *
     * @param position
     */
    @Override
    public void fireAndUpdate(int position) {
        //TODO implement (1)
        super.fireAndUpdate(position);
    }


    /**
     * start the invaders moving towards the bottom of the board
     * - for every invader on the board we need to move them down 1 at a time
     * based on the time inverval specified in SeaInvaderSettings
     */
    public void spawnTheInvaders() {
//        while (this.currentRound < ((SeaInvaderSettings) this.gameSettings).getNumRounds()) {
//            this.currentRound += 1;
        ArrayList<Integer> newSpawnPositions = getInvaderSpawnPositions();
        for (int pos : newSpawnPositions) {
            board.updateTile(pos, new InvaderTile());
        }
//            try {
//                TimeUnit.SECONDS.sleep(
//                        (long)
//                                ((SeaInvaderSettings) this.gameSettings)
//                                        .getSecsBeforeSpawn());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

//    public void makeInvadersSwim() {
//        double scalingFactor = 100.0;
//        long startMillis = System.currentTimeMillis(); // https://stackoverflow.com/questions/36323569/how-to-count-the-seconds-in-java
//        while (!puzzleSolved() && !isGameOver() &&
//                this.currentRound < ((SeaInvaderSettings) this.gameSettings).getNumRounds()) {
//            long nowMillis = System.currentTimeMillis();
//            double secsFromStart = (double)(((nowMillis - startMillis)*scalingFactor) / (1000 * scalingFactor));
//            if (secsFromStart %
//                    (((SeaInvaderSettings) this.gameSettings).getSecsBeforeMove() * scalingFactor)
//                    == 0) {
//                //TODO: separate spawn and move rounds (2)
//                this.currentRound += 1;
//                ArrayList<Integer> invaderPositions = getInvaderPositions();
//                for (int pos : invaderPositions) {
//                    int row1 = pos / this.gameSettings.getBoardSize();
//                    int col1 = pos % this.gameSettings.getBoardSize();
//                    if (row1+1 < this.gameSettings.getBoardSize()) {
//                        board.swapTiles(row1, col1, row1 + 1, col1);
//                    } else {
//                        setGameOver(true);
//                    }
//                }
//            }
//        }
//    }

    public void swim() {
        if (!puzzleSolved() && !isGameOver() &&
                this.currentRound < ((SeaInvaderSettings) this.gameSettings).getNumRounds()) {
            //TODO: separate spawn and move rounds (2)
            this.currentRound += 1;
            ArrayList<Integer> invaderPositions = getInvaderPositions();
            for (int pos : invaderPositions) {
                int row1 = pos / this.gameSettings.getBoardSize();
                int col1 = pos % this.gameSettings.getBoardSize();
                if (row1 + 1 < this.gameSettings.getBoardSize()) {
                    board.swapTiles(row1, col1, row1 + 1, col1, false);
                } else {
                    setGameOver(true);
                }
            }
        }
    }

//    public void makeInvadersSwim() {
//        //https://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
//        new Timer().schedule(
//                new TimerTask() {
//                    @Override
//                    public void run() {
//                        swim();
//                        board.notifyObservers();
//                    }
//                },
//            (int) ((SeaInvaderSettings) this.gameSettings).getSecsBeforeMove()
//        );
//    }


//    public void makeInvadersSwim() {
//        //https://stackoverflow.com/questions/4597690/android-timer-how-to
//
//        final Handler timerHandler = new Handler();
//        Runnable timerRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                swim();
//                board.notifyObservers();
////                timerHandler.postDelayed(this, 5000);
//                timerHandler.postDelayed(this, 50);
//            }
//        };
//    }


    public void makeInvadersSwim() {
        //https://stackoverflow.com/questions/4597690/android-timer-how-to
//
//        final Handler timerHandler = new Handler();
//        Runnable timerRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                swim();
//                board.notifyObservers();
////                timerHandler.postDelayed(this, 5000);
//                timerHandler.postDelayed(this, 50);
//            }
//        };

//        ScheduledExecutorService service = Executors
//                .newSingleThreadScheduledExecutor();
//        service.scheduleAtFixedRate(timerRunnable, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * loop over the board and identify all the invaders
     * @return
     */
    public ArrayList getInvaderPositions() {
        //TODO: make more efficient (3)
        ArrayList<Integer> invaderPositions = new ArrayList<Integer>();
        Iterator boardIterator = board.iterator();
        for (int pos = 0;
             pos < this.gameSettings.getBoardSize() * this.gameSettings.getBoardSize();
             pos++) {
            int row1 = pos / this.gameSettings.getBoardSize();
            int col1 = pos % this.gameSettings.getBoardSize();
            if (board.getTile(row1, col1) instanceof InvaderTile) {
                invaderPositions.add(pos);
            }
        }
        Collections.sort(invaderPositions, Collections.<Integer>reverseOrder());
        // so when we swap tiles we swap from the bottom up
        return invaderPositions;
    }

    public ArrayList getInvaderSpawnPositions() {
        //TODO: improve this, want to be more random (2)
        ArrayList positions = new ArrayList();
        for (int i = 0; i < this.gameSettings.getBoardSize(); i++) {
            positions.add(i);
        }
        return positions;
    }

    @Override
    public void startGame() {
        makeInvadersSwim();
        spawnTheInvaders();
    }

    /**
     * @return
     */
    @Override
    public double getScore() {
        //TODO: implement (1)
        return 1.0;
    }


    public SeaInvaderSettings getSeaInvaderSettings() {
        return (SeaInvaderSettings) getGameSettings();
    }

    public void setLastOccupiedColumnToStart() {
        this.lastOccupiedColumn = getSeaInvaderSettings().getBoardSize() - 1;
    }

    public int getLastOccupiedColumn() {
        return lastOccupiedColumn;
    }

    public void setLastOccupiedColumn(int lastOccupiedColumn) {
        this.lastOccupiedColumn = lastOccupiedColumn;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void setInvaderPositions(ArrayList<Integer> invaderPositions) {
        this.invaderPositions = invaderPositions;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
