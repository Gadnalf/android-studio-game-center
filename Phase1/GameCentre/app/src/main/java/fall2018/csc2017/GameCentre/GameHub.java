package fall2018.csc2017.GameCentre;

import java.io.Serializable;

public class GameHub implements Serializable {
    SlidingTilesBoardManager slidingTilesBoardManager;
    SeaInvadersBoardManager seaInvadersBoardManager;
    AlphabetTilesBoardManager alphabetTilesBoardManager;
    String user;
    GameHub(SlidingTilesBoardManager slidingTilesBoardManager,
            SeaInvadersBoardManager seaInvadersBoardManager,
            AlphabetTilesBoardManager alphabetTilesBoardManager,
            String user) {
        this.slidingTilesBoardManager = slidingTilesBoardManager;
        this.seaInvadersBoardManager = seaInvadersBoardManager;
        this.alphabetTilesBoardManager = alphabetTilesBoardManager;
        this.user = user;
    }

    public SlidingTilesBoardManager getSlidingTilesBoardManager() {
        return slidingTilesBoardManager;
    }


    public void setSlidingTilesBoardManager(SlidingTilesBoardManager slidingTilesBoardManager) {
        this.slidingTilesBoardManager = slidingTilesBoardManager;
    }

    public void setZTileBoardManager(AlphabetTilesBoardManager alphabetTilesBoardManager) {
        this.alphabetTilesBoardManager = alphabetTilesBoardManager;
    }

    public AlphabetTilesBoardManager getZTileBoardManager() {
        return alphabetTilesBoardManager;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public SeaInvadersBoardManager getSeaInvadersBoardManager() {
        return seaInvadersBoardManager;
    }

    public void setSeaInvadersBoardManager(SeaInvadersBoardManager seaInvadersBoardManager) {
        this.seaInvadersBoardManager = seaInvadersBoardManager;
    }

}
