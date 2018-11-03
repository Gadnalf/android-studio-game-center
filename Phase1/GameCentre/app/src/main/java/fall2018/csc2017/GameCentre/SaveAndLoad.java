package fall2018.csc2017.GameCentre;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return null;
    }

    public static void saveToFile(BoardManager boardManager, String fileName,
                           AppCompatActivity appCompatActivity) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    appCompatActivity.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
