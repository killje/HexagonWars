package hexagonwars;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldPanel extends JPanel implements Observer {

    HWFrame frame;
    Tile[][] tiles;
    File file;
    GameWorld worldMap;

    public WorldPanel(HWFrame hwframe) {
        frame = hwframe;
        file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        World world = new World(file);

        worldMap = new GameWorld(frame, world);

        this.setMinimumSize(new Dimension(800, 800));
        this.setPreferredSize(new Dimension(800, 800));
        add(addMenuBar());
        add(worldMap);
    }

    private JMenuBar addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = addMenu("File", KeyEvent.VK_F);
        menuFile.add(addMenuItem("Load", KeyEvent.VK_L, KeyEvent.VK_O, frame.getActionClass().new OpenWorld(this)));
        menuFile.add(addMenuItem("Save", KeyEvent.VK_S, KeyEvent.VK_S, frame.getActionClass().new SaveWorld(this)));
        menuFile.addSeparator();
        menuFile.add(addMenuItem("Quit", KeyEvent.VK_Q, frame.getActionClass().new QuitAction()));
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
    public void update(Observable o, Object o1) {
        if (o1 instanceof ActionClass.SaveWorld) {
        } else if (o1 instanceof ActionClass.OpenWorld) {
            String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
            World world = new World(new File(path));
            worldMap.setWorld(world);
            repaint();
            revalidate();
        }
    }
}
