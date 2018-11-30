package fall2018.csc2017.GameCentre;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlphabetTileFactoryTest {
    private AlphabetTileFactory alphabetTileFactory;

    @Test
    public void testBoardSizeFour() {
        alphabetTileFactory = new AlphabetTileFactory();
        List<Integer> EXPECTED_TILE_IDS;
        List<Integer> ACTUAL_TILE_IDS = new ArrayList<>();
        List<Tile> tiles;
        EXPECTED_TILE_IDS = new ArrayList<Integer>() {{add(0); add(1); add(1); add(0); add(0); add(0);
            add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0);}};
        tiles = alphabetTileFactory.getTiles(4);
        for (int i = 0; i != 16; i++) {
            ACTUAL_TILE_IDS.add(tiles.get(i).getId());
        }
        assertEquals(EXPECTED_TILE_IDS, ACTUAL_TILE_IDS);


    }

    @Test
    public void testBoardSizeFive() {
        alphabetTileFactory = new AlphabetTileFactory();
        List<Integer> EXPECTED_TILE_IDS;
        List<Integer> ACTUAL_TILE_IDS = new ArrayList<>();
        List<Tile> tiles;
        EXPECTED_TILE_IDS = new ArrayList<Integer>() {{add(0); add(1); add(1); add(0); add(0); add(0);
        add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0);
        add(0); add(0); add(0); add(0); add(0); add(0); add(0); }};
        tiles = alphabetTileFactory.getTiles(5);
        for (int i = 0; i != 25; i++) {
            ACTUAL_TILE_IDS.add(tiles.get(i).getId());
        }
        assertEquals(EXPECTED_TILE_IDS, ACTUAL_TILE_IDS);

    }

    @Test
    public void testBoardSizeSix() {
        alphabetTileFactory = new AlphabetTileFactory();
        List<Integer> EXPECTED_TILE_IDS;
        List<Integer> ACTUAL_TILE_IDS = new ArrayList<>();
        List<Tile> tiles;
        EXPECTED_TILE_IDS = new ArrayList<Integer>() {{add(0); add(1); add(1); add(0); add(0); add(0);
            add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0);
            add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0); add(0);
            add(0); add(0); add(0); add(0); add(0); add(0); add(0); }};
        tiles = alphabetTileFactory.getTiles(6);
        for (int i = 0; i != 36; i++) {
            ACTUAL_TILE_IDS.add(tiles.get(i).getId());
        }
        assertEquals(EXPECTED_TILE_IDS, ACTUAL_TILE_IDS);

    }
}
