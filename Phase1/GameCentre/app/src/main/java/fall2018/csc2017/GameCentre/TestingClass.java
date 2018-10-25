package fall2018.csc2017.GameCentre;

public class TestingClass {

    public static void main(String[] args) {
        testBoardManager();
    }

    public static void testBoardManager() {
        BoardManager bm = new BoardManager();
        bm.touchMove(4);
    }
}
