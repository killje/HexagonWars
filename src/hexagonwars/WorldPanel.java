package hexagonwars;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
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
public class WorldPanel extends MapPanel{

    Tile[][] tiles;
    DrawWorld worldMap;

    public WorldPanel() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        World world = new World(file);

        worldMap = addWorld(world, 50, 30);
        worldMap.setReferenceName("worldMap");
        add(addMenuBar());
    }

    private JMenuBar addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(50, 25));
        JMenu menuFile = addMenu("File", KeyEvent.VK_F);
        menuFile.add(addMenuItem("Load", KeyEvent.VK_L, KeyEvent.VK_O, new OpenWorld(worldMap)));
        menuFile.add(addMenuItem("Save", KeyEvent.VK_S, KeyEvent.VK_S, new SaveWorld("worldMap")));
        menuFile.addSeparator();
        menuFile.add(addMenuItem("Quit", KeyEvent.VK_Q, new QuitAction()));
        menuBar.add(menuFile);

        return menuBar;
    }

    private JMenu addMenu(String name, int shortKey) {
        JMenu menu = new JMenu(name);
        menu.setMnemonic(shortKey);

        return menu;
    }

    private JMenuItem addMenuItem(String name, int shortKey, AbstractAction actionListener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(actionListener);
        menuItem.setMnemonic(shortKey);

        return menuItem;
    }

    private JMenuItem addMenuItem(String name, int shortKey, int keyBindings, AbstractAction actionListener) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyBindings, Event.CTRL_MASK);
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke, name);
        this.getActionMap().put(name, actionListener);

        return addMenuItem(name, shortKey, actionListener);
    }

    @Override
    protected void tileClick(DrawWorld world, Point TileCoordinate) {
        throw new UnsupportedOperationException("Not supported yet. at: hexagonwars.WorldPanel:tileClick();");
    }
}
