package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SaveAndLoad extends AppCompatActivity {


    //-------------basic savers and loaders
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

//    /**
//     * same as above, but doesnt require the appCompatActivity
//     * https://stackoverflow.com/questions/2808277/can-we-write-a-hashtable-to-a-file
//     * @param object
//     * @param filePath
//     */
//    public static void saveObjectToFileNoApp(Object object, String filePath) {
//        try {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//            oos.writeObject(object);
//            oos.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    /**
//     * http://tutorials.jenkov.com/java-io/objectinputstream.html
//     * @param fileName
//     * @return
//     */
//    public static Object loadObjectFromFileNoApp(String fileName) {
//        try {
//            ObjectInputStream objectInputStream =
//                    new ObjectInputStream(new FileInputStream(fileName));
//            Object fileContents = objectInputStream.readObject();
//            objectInputStream.close();
//            return fileContents;
//        } catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//            throw new RuntimeException("file not found");
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//            throw new RuntimeException("can't read file");
//        } catch (ClassNotFoundException e) {
//            Log.e("login activity", "File contained unexpected data type: " + e.toString());
////            throw new Exception("file contained unexpected data type");
//        }
//        return null;
//    }

    //---------------------board manager savers and loaders

    /**
     * @param userName
     * @param appCompatActivity
     * @return
     */
    public static GameHub loadGameHubPermanent(
            String userName,
            AppCompatActivity appCompatActivity) {

        GameHub gameHub;

        //if permanent file not there load the temp
        try{
            String fileName = userName + "_" + SlidingTilesStartingActivity.saveFilename;
            gameHub = (GameHub) loadFromFile(
                    fileName, appCompatActivity);
        } catch (RuntimeException e) {
            gameHub = loadGameHubTemp(appCompatActivity);
        }
        return gameHub;
    }

    /**
     * @param appCompatActivity
     * @return
     */
    public static GameHub loadGameHubTemp(
            AppCompatActivity appCompatActivity) {
        String fileName = SlidingTilesStartingActivity.tempSaveFilename;
        GameHub gameHub = (GameHub) loadFromFile(
                fileName, appCompatActivity);
        return gameHub;
    }



    /**
     * @param gameHub
     * @param appCompatActivity
     */
    public static void saveGameHubTemp(GameHub gameHub,
                                       AppCompatActivity appCompatActivity) {
        String fileName = SlidingTilesStartingActivity.tempSaveFilename;
        saveToFile(gameHub, fileName, appCompatActivity);
    }

    /**
     * For temporary saves, save without user_id, for permanant ones use user id
     * @param gameHub
     * @param appCompatActivity
     */
    public static void saveGameHubPermanent(GameHub gameHub,
                                            AppCompatActivity appCompatActivity) {
        String fileName = gameHub.getUser().getUserName() + "_" + SlidingTilesStartingActivity.saveFilename;
        saveToFile(gameHub,
                fileName,
                appCompatActivity);
    }



    //------------------------scoreboard loaders

    public static HashMap<String, GameSettings> loadPermGameScoreboard(AppCompatActivity appCompatActivity) {
        HashMap<String, GameSettings> gameScoreBoard;//if permanent file not there load the temp
        try{
            gameScoreBoard = (HashMap<String, GameSettings>) loadFromFile(
                    getGameScoreBoardFileName(),
                    appCompatActivity);
        } catch (RuntimeException e) {
            gameScoreBoard = loadTempGameScoreboard(appCompatActivity);
        }
        return gameScoreBoard;
    }

    public static HashMap<String, GameSettings> loadPermUserScoreboard(AppCompatActivity appCompatActivity,
                                                                        String userName) {
        HashMap<String, GameSettings> userScoreBoard;//if permanent file not there load the temp
        try{
            String fileName = getUserScoreBoardFilePath(userName);
            userScoreBoard = (HashMap<String, GameSettings>) loadFromFile(
                    fileName,
                    appCompatActivity);
        } catch (RuntimeException e) {
            userScoreBoard = loadTempUserScoreboard(appCompatActivity);
        }
        return userScoreBoard;
    }

    public static HashMap<String, GameSettings> loadTempGameScoreboard(AppCompatActivity appCompatActivity) {
        HashMap<String, GameSettings> gameScoreBoard;
        String fileName = getTempGameScoreboardFileName();
        gameScoreBoard = (HashMap<String, GameSettings>) loadFromFile(
                fileName,
                appCompatActivity);
        return gameScoreBoard;
    }

    public static HashMap<String, GameSettings> loadTempUserScoreboard(AppCompatActivity appCompatActivity) {
        HashMap<String, GameSettings> userScoreBoard;
        userScoreBoard = (HashMap<String, GameSettings>) loadFromFile(
                getTempUserScoreboardFileName(),
                appCompatActivity);
        return userScoreBoard;
    }



    //------------------scoreboard savers
    public static void savePermScoreboard(AppCompatActivity appCompatActivity,
                                                                   String userName,
                                                                   ScoreBoard scoreBoard) {
        saveToFile(scoreBoard.getPerUserScoreBoard(),
                getUserScoreBoardFilePath(userName),
                appCompatActivity);

        saveToFile(scoreBoard.getPerGameScoreBoard(),
                getGameScoreBoardFileName(),
                appCompatActivity);
    }

    /**
     * Note for scoreboards unlike board manager we dont care what the tmps are
     * bc were always loading the perms if they exists
     * so we just specify these as empty
     * @param appCompatActivity
     */
    public static void saveTempScoreboard(AppCompatActivity appCompatActivity) {

        saveToFile(new HashMap<String, GameSettings>(),
                getTempUserScoreboardFileName(),
                appCompatActivity);

        String fileName = getTempGameScoreboardFileName();
        saveToFile(new HashMap<String, GameSettings>(),
                fileName,
                appCompatActivity);
    }


    //-------------------helpers

    @NonNull
    private static String getGameScoreBoardFileName() {
        return SlidingTilesStartingActivity.GAME_SCORE_BOARD_FILEPREFIX + SlidingTilesStartingActivity.saveFilename;
    }

    @NonNull
    private static String getTempUserScoreboardFileName() {
        return SlidingTilesStartingActivity.USER_SCORE_BOARD_FILEPREFIX + SlidingTilesStartingActivity.tempSaveFilename;
    }

    @NonNull
    private static String getTempGameScoreboardFileName() {
        return SlidingTilesStartingActivity.GAME_SCORE_BOARD_FILEPREFIX + SlidingTilesStartingActivity.tempSaveFilename;
    }

    @NonNull
    private static String getUserScoreBoardFilePath(String userName) {
        return userName + "_" + SlidingTilesStartingActivity.USER_SCORE_BOARD_FILEPREFIX + SlidingTilesStartingActivity.saveFilename;
    }


    //----------------------------groupings of saves / loads

    public static void saveAllTemp(GameHub gameHub, AppCompatActivity appCompatActivity) {
        saveTempScoreboard(appCompatActivity);
        saveGameHubTemp(gameHub, appCompatActivity);
    }

}
