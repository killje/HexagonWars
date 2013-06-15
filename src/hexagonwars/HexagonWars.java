package hexagonwars;

import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class HexagonWars {

    public static final int ENTITY_UNIT_INFANTRY = 1;
    public static final int ENTITY_UNIT_MECHANIC = 2;
    public static final int ENTITY_UNIT_MAGIC = 3;
    public static final int TILE_PLAIN = 1;
    public static final int TILE_MOUNTAIN = 2;
    public static final int TILE_WATER = 3;
    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 800;
    public static final int WORLD_TILE_WIDTH = 140;
    public static final int WORLD_TILE_HEIGHT_MIN = 98;
    public static final int WORLD_TILE_HEIGHT_MAX = 122;
    public static final int WORLD_TILE_UPPERHEIGHT = WORLD_TILE_HEIGHT_MAX - WORLD_TILE_HEIGHT_MIN;
    public static final int WORLD_TILE_SIDE = WORLD_TILE_HEIGHT_MIN - WORLD_TILE_UPPERHEIGHT;
    public static double PLACEHOLDER_ZOOM =1;
    public static int PLACEHOLDER_CAMARA_X=0;
    public static int PLACEHOLDER_CAMARA_Y=0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HWFrame frame = new HWFrame();
//        World newWorld = new World(file);
//        File file = new File("src\\hexagonwars\\maps\\firstmap.hwm");//for debug
//        WorldEditor editor = new WorldEditor(file);

    }
}
