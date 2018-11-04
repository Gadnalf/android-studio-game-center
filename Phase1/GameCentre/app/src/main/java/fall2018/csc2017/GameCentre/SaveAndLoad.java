package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveAndLoad extends AppCompatActivity {

    public static Object loadFromFile(String fileName, AppCompatActivity appCompatActivity) {
        try {
            InputStream inputStream = appCompatActivity.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                Object fileContents =  input.readObject();
                inputStream.close();
                return fileContents;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            throw new RuntimeException("file not found");
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            throw new RuntimeException("can't read file");
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
//            throw new Exception("file contained unexpected data type");
        }
        return null;
    }


    /**
     * @param userName
     * @param appCompatActivity
     * @return
     */
    public static BoardManager loadBoardManagerPermanent(
            String userName,
            AppCompatActivity appCompatActivity) {

        BoardManager boardManager;
        ArrayList<Game> gameScoreBoard;

        //if permanent file not there load the temp
        try{
            String fileName = userName + "_" + StartingActivity.SAVE_FILENAME;
            boardManager = (BoardManager) loadFromFile(
                    fileName, appCompatActivity);
        } catch (RuntimeException e) {
            boardManager = loadBoardManagerTemp(appCompatActivity);
        }

        //if permanent file not there load the temp
        try{
            gameScoreBoard = (ArrayList<Game>) loadFromFile(
                    StartingActivity.GAME_SCORE_BOARD_FILEPREFIX + StartingActivity.SAVE_FILENAME,
                    appCompatActivity);
        } catch (RuntimeException e) {
            gameScoreBoard = (ArrayList<Game>) loadFromFile(
                    StartingActivity.GAME_SCORE_BOARD_FILEPREFIX + StartingActivity.TEMP_SAVE_FILENAME,
                    appCompatActivity);
        }

        boardManager.scoreBoard.setPerGameScoreBoard(gameScoreBoard);
        return boardManager;
    }

    /**
     * @param appCompatActivity
     * @return
     */
    public static BoardManager loadBoardManagerTemp(
            AppCompatActivity appCompatActivity) {
        String fileName = StartingActivity.TEMP_SAVE_FILENAME;
        BoardManager boardManager = (BoardManager) loadFromFile(
                fileName, appCompatActivity);
        ArrayList<Game> gameScoreBoard = (ArrayList<Game>) loadFromFile(
                StartingActivity.GAME_SCORE_BOARD_FILEPREFIX + StartingActivity.TEMP_SAVE_FILENAME,
                appCompatActivity);
        boardManager.scoreBoard.setPerGameScoreBoard(gameScoreBoard);
        return boardManager;
    }


    public static void saveToFile(Object inputObject, String fileName,
                           AppCompatActivity appCompatActivity) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    appCompatActivity.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(inputObject);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * @param boardManager
     * @param appCompatActivity
     */
    public static void saveBoardManagerTemp(BoardManager boardManager,
                                            AppCompatActivity appCompatActivity) {
        String fileName = StartingActivity.TEMP_SAVE_FILENAME;
        saveToFile(boardManager, fileName, appCompatActivity);
        saveToFile(boardManager.getScoreBoard().getPerGameScoreBoard(),
                StartingActivity.GAME_SCORE_BOARD_FILEPREFIX + StartingActivity.TEMP_SAVE_FILENAME,
                appCompatActivity);
    }

    /**
     * For temporary saves, save without user_id, for permanant ones use user id
     * @param boardManager
     * @param appCompatActivity
     */
    public static void saveBoardManagerPermanent(BoardManager boardManager,
                                        AppCompatActivity appCompatActivity) {
        String fileName = boardManager.getUser().getUserName() + "_" + StartingActivity.SAVE_FILENAME;
        saveToFile(boardManager,
                fileName,
                appCompatActivity);
        saveToFile(boardManager.getScoreBoard().getPerGameScoreBoard(),
                StartingActivity.GAME_SCORE_BOARD_FILEPREFIX + StartingActivity.SAVE_FILENAME,
                appCompatActivity);
    }
}
