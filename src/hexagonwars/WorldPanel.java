package hexagonwars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldPanel extends MapPanel {

    private WorldModel worldMap;
    private GameUI gameUI = new GameUI(this);

    public WorldPanel() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        WorldTiles world = new WorldTiles(file);

        worldMap = addWorld(world, 50, 30);
    }

    @Override
    protected void tileClick(WorldModel world, Point TileCoordinate) {
        throw new UnsupportedOperationException("Not supported yet. at: hexagonwars.WorldPanel:tileClick();");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle uiRect = new Rectangle(0, this.getSize().height - 55, 50, 200);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("nextTurn"), uiRect.x, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("exitButton"), uiRect.x+50, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("saveButton"), uiRect.x+100, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("loadButton"), uiRect.x+150, uiRect.y, null);
    }

    public WorldModel getWorldModel() {
        return this.worldMap;
    }
}
