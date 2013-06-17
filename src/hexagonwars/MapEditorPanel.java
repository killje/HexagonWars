/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MapEditorPanel extends JPanel implements Observer {

    private JFormattedTextField inputWidthText;
    private JFormattedTextField inputHeightText;
    private JButton save = new JButton("Save");
    private int boardWidth, boardHeight;
    private DrawWorld newWorld;
    private DrawWorld tileSelector;
    private Point selectedTileCoordinate;

    public MapEditorPanel() {
        JPanel buttons = new JPanel();
        NumberFormat numberFormat;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        JLabel inputWidthLabel = new JLabel("Width");
        numberFormat = NumberFormat.getIntegerInstance();
        inputWidthText = new JFormattedTextField(numberFormat);
        inputWidthText.setColumns(20);
        inputWidthText.setValue(5);
        JLabel inputHeightLabel = new JLabel("Height");
        inputHeightText = new JFormattedTextField(numberFormat);
        inputHeightText.setColumns(20);
        inputHeightText.setValue(5);

        JButton go = new JButton("Go");
        go.addActionListener(new SetInputSize());

        save.addActionListener(new SaveWorld());
        save.setEnabled(false);

        buttons.add(inputWidthLabel);
        buttons.add(inputWidthText);
        buttons.add(inputHeightLabel);
        buttons.add(inputHeightText);
        buttons.add(go);
        buttons.add(save);
        buttons.setMaximumSize(new Dimension(800, 26));
        add(buttons);
        tileChoser();
        repaint();
        revalidate();
    }

    private void tileChoser() {
        World world = new World(4, 1);
        Tile[][] tiles = new Tile[4][1];
        tiles[0][0] = Tile.getType(HexagonWars.TILE_PLAIN);
        tiles[1][0] = Tile.getType(HexagonWars.TILE_MOUNTAIN);
        tiles[2][0] = Tile.getType(HexagonWars.TILE_WATER);
        tiles[3][0] = Tile.getType(HexagonWars.TILE_GOLD);
        world.setWorld(tiles);
        tileSelector = new DrawWorld(world, "select");
        tileSelector.addObserver(this);
        add(tileSelector);
    }

    private void board() {
        if (newWorld != null) {
            remove(newWorld);
            newWorld.deleteObserver(this);
        }
        World world = new World(boardWidth, boardHeight);
        Tile[][] tiles = new Tile[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                tiles[i][j] = Tile.getType(HexagonWars.TILE_PLAIN);
            }
        }

        world.setWorld(tiles);
        newWorld = new DrawWorld(world, "place");
        newWorld.addObserver(this);
        add(newWorld);

        save.setEnabled(true);
        repaint();
        validate();
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
            saveWorld.setHeight(boardHeight);
            saveWorld.setWidth(boardWidth);
            saveWorld.setWorld(newWorld.getWorld());
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
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String doAction = (String) arg;
            System.out.println("doAction = " + doAction);
            if (doAction.equals("select")) {
                selectedTileCoordinate = tileSelector.getSelectedTileCoordinate();
            } else if (doAction.equals("place")) {
                Point worldTile = newWorld.getSelectedTileCoordinate();
                newWorld.setTile(worldTile.x, worldTile.y, tileSelector.getTile(selectedTileCoordinate.x, selectedTileCoordinate.y));
                repaint();
                validate();
            }
        }
    }

    private class SetInputSize extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!inputWidthText.getText().equals("") && !inputHeightText.getText().equals("") && Integer.parseInt(inputWidthText.getText()) > 0 && Integer.parseInt(inputHeightText.getText()) > 0) {
                boardWidth = Integer.parseInt(inputWidthText.getText());
                boardHeight = Integer.parseInt(inputHeightText.getText());
                board();
            }
        }
    }

    private class SaveWorld extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            saveWorld();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color color1 = new Color(255, 255, 0);
        g.setColor(color1);
        if (tileSelector != null) {
            drawWorld(g, tileSelector, 30);
        }
        if (newWorld != null) {
            drawWorld(g, newWorld, 200);
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
