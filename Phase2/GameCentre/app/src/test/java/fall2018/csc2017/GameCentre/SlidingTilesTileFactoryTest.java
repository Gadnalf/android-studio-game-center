package fall2018.csc2017.GameCentre;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SlidingTilesTileFactoryTest {

    private SlidingTilesTileFactory slidingTilesTileFactory;

    @Test
    public void testBoardSizeThree() {
        slidingTilesTileFactory = new SlidingTilesTileFactory();
        List<Integer> EXPECTED_TILE_IDS;
        List<Integer> ACTUAL_TILE_IDS = new ArrayList<>();
        List<Tile> tiles;
        EXPECTED_TILE_IDS = new ArrayList<Integer>() {{add(1); add(2); add(3); add(4); add(5); add(6);
        add(7); add(8); add(25);}};
        tiles = slidingTilesTileFactory.getTiles(3);
        for (int i = 0; i != 9; i++) {
            ACTUAL_TILE_IDS.add(tiles.get(i).getId());
        }
        assertEquals(EXPECTED_TILE_IDS, ACTUAL_TILE_IDS);

    }

    @Test
    public void testBoardSizeFour() {
        slidingTilesTileFactory = new SlidingTilesTileFactory();
        List<Integer> EXPECTED_TILE_IDS;
        List<Integer> ACTUAL_TILE_IDS = new ArrayList<>();
        List<Tile> tiles;
        EXPECTED_TILE_IDS = new ArrayList<Integer>() {{add(1); add(2); add(3); add(4); add(5); add(6);
            add(7); add(8); add(9); add(10); add(11); add(12); add(13); add(14); add(15); add(25);}};
        tiles = slidingTilesTileFactory.getTiles(4);
        for (int i = 0; i != 16; i++) {
            ACTUAL_TILE_IDS.add(tiles.get(i).getId());
        }
        assertEquals(EXPECTED_TILE_IDS, ACTUAL_TILE_IDS);

    }

    @Test
    public void testBoardSizeFive() {
        slidingTilesTileFactory = new SlidingTilesTileFactory();
        List<Integer> EXPECTED_TILE_IDS;
        List<Integer> ACTUAL_TILE_IDS = new ArrayList<>();
        List<Tile> tiles;
        EXPECTED_TILE_IDS = new ArrayList<Integer>() {{add(1); add(2); add(3); add(4); add(5); add(6);
            add(7); add(8); add(9); add(10); add(11); add(12); add(13); add(14); add(15); add(16);
            add(17); add(18); add(19); add(20); add(21); add(22); add(23); add(24); add(25);}};
        tiles = slidingTilesTileFactory.getTiles(5);
        for (int i = 0; i != 25; i++) {
            ACTUAL_TILE_IDS.add(tiles.get(i).getId());
        }
        assertEquals(EXPECTED_TILE_IDS, ACTUAL_TILE_IDS);

    }
}
