package fall2018.csc2017.GameCentre;

import java.io.Serializable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


public class HandlerSerializable extends Handler implements Serializable {
    public HandlerSerializable() {
        super();
    }

    public HandlerSerializable(Callback callback) {
        super(callback);
    }

    public HandlerSerializable(Looper looper) {
        super(looper);
    }

    public HandlerSerializable(Looper looper, Callback callback) {
        super(looper, callback);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
    }

    @Override
    public String getMessageName(Message message) {
        return super.getMessageName(message);
    }

    @Override
    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        return super.sendMessageAtTime(msg, uptimeMillis);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}