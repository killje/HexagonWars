package hexagonwars;

import java.awt.Dimension;
import java.io.File;
import java.nio.file.Paths;
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

    public WorldPanel(HWFrame hwframe) {

        frame = hwframe;
        file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");
        World world = new World(file);
        
        DrawWorld worldMap = new DrawWorld(frame, world);
        this.setMinimumSize(new Dimension(800,800));
        this.setPreferredSize(new Dimension(800,800));
        worldMap.setMinimumSize(new Dimension(800,800));
        worldMap.setPreferredSize(new Dimension(800,800));
        add(worldMap);
        
    }
}
