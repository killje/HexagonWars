package hexagonwars;

import java.awt.GridLayout;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldPanel extends JPanel {

    HWFrame frame;
    Tile[][] tiles;
    File file;
    World world;
    
    public WorldPanel(HWFrame hwframe) {
        
        frame = hwframe;
        file = new File("src\\hexagonwars\\maps\\firstmap.hwm");
        World world = new World(file);
        tiles = world.getTiles();
        int height = world.getHeight();
        int width = world.getWidth();
        setLayout(new GridLayout(width,height));
        for (int i = 0; i < height; i++) {
            for (int j = 1; j < width; j++) {
                add(new JLabel(tiles[i][j].getClass().toString()));
            }
        }
    }
}
