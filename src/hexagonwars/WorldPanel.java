package hexagonwars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
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
public class WorldPanel extends JPanel implements Observer{

    Tile[][] tiles;
    DrawWorld worldMap;

    public WorldPanel() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        World world = new World(file);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        worldMap = new DrawWorld(world);
        worldMap.addObserver(this);
        add(addMenuBar());
        add(worldMap);
    }

    private JMenuBar addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(50, 25));
        JMenu menuFile = addMenu("File", KeyEvent.VK_F);
        menuFile.add(addMenuItem("Load", KeyEvent.VK_L, KeyEvent.VK_O, new OpenWorld()));
        menuFile.add(addMenuItem("Save", KeyEvent.VK_S, KeyEvent.VK_S, new SaveWorld()));
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

    public void saveWorld() {
        String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
        File file = new File(path);

        store(file);
    }

    private void store(File file) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to override " + file.getName() + "?") != 0) {
                    return;
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            WorldFile saveWorld = new WorldFile();
            saveWorld.setHeight(worldMap.worldHeight());
            saveWorld.setWidth(worldMap.worldWidth());
            saveWorld.setWorld(worldMap.getWorld());
            oos.writeObject(saveWorld);

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("The desired file was not found.");
        } catch (NotSerializableException e) {
            System.err.println("The saved object is not serializable at: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error with the I/O was reported, program closing.");
            System.exit(-1);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("click!");
    }

    private class OpenWorld extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
            World world = new World(new File(path));
            worldMap.setWorld(world);
            repaint();
            revalidate();
        }
    }

    private class SaveWorld extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            saveWorld();
        }
    }

    private class QuitAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color color1 = new Color(255, 255, 0);
        g.setColor(color1);
        if (worldMap != null) {
            drawWorld(g, worldMap, 100);
        }

    }

    private void drawWorld(Graphics g, DrawWorld world, int panelShift) {
        int x, y;
        for (y = 0; y < world.worldHeight(); y++) {
            for (x = 0; x < world.worldWidth(); x++) {
                g.drawImage(world.getWorld()[x][y].getImage(),
                        x * (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM) + y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * HexagonWars.PLACEHOLDER_ZOOM) - HexagonWars.PLACEHOLDER_CAMARA_X,
                        y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * HexagonWars.PLACEHOLDER_ZOOM) - HexagonWars.PLACEHOLDER_CAMARA_X + panelShift,
                        (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM),
                        (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM),
                        null);
            }
        }
    }
}
