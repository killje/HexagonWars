package hexagonwars;

import java.io.File;


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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("src\\hexagonwars\\maps\\firstmap.hwm");
        /*debug*/System.out.println(file.getAbsolutePath());
        HWFrame frame = new HWFrame();
//        World newWorld = new World(file);
//        WorldEditor editor = new WorldEditor(file);
        
    }
    
}