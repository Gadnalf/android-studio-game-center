package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class GameHub implements Serializable {
    SlidingTilesBoardManager slidingTilesBoardManager;
    SeaInvadersBoardManager seaInvadersBoardManager;
    User user;

    GameHub(SlidingTilesBoardManager slidingTilesBoardManager,
            SeaInvadersBoardManager seaInvadersBoardManager,
            User user) {
        this.slidingTilesBoardManager = slidingTilesBoardManager;
        this.seaInvadersBoardManager = seaInvadersBoardManager;
        this.user = user;
    }

    public SlidingTilesBoardManager getSlidingTilesBoardManager() {
        return slidingTilesBoardManager;
    }

    public void setSlidingTilesBoardManager(SlidingTilesBoardManager slidingTilesBoardManager) {
        this.slidingTilesBoardManager = slidingTilesBoardManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SeaInvadersBoardManager getSeaInvadersBoardManager() {
        return seaInvadersBoardManager;
    }

    public void setSeaInvadersBoardManager(SeaInvadersBoardManager seaInvadersBoardManager) {
        this.seaInvadersBoardManager = seaInvadersBoardManager;
    }
}
