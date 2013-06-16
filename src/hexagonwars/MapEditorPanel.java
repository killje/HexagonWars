/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Dimension;
import java.awt.Point;
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

    private HWFrame frame;
    private JFormattedTextField inputWidthText;
    private JFormattedTextField inputHeightText;
    private JButton save = new JButton("Save");
    private int boardWidth, boardHeight;
    private WorldEditorDrawWorld newWorld;
    private WorldEditorDrawWorld tilePanel;
    private Point selectedTileCoordinate;

    public MapEditorPanel(HWFrame hwframe) {
        frame = hwframe;
        NumberFormat numberFormat;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        JPanel buttons = new JPanel();
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
        go.addActionListener(frame.getActionClass().new SetInputSize(this));

        save.addActionListener(frame.getActionClass().new SaveWorld(this));
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
    }

    private void tileChoser() {
        World world = new World(4, 1);
        Tile[][] tiles = new Tile[4][1];
        tiles[0][0] = Tile.getType(HexagonWars.TILE_PLAIN);
        tiles[1][0] = Tile.getType(HexagonWars.TILE_MOUNTAIN);
        tiles[2][0] = Tile.getType(HexagonWars.TILE_WATER);
        tiles[3][0] = Tile.getType(HexagonWars.TILE_GOLD);
        world.setWorld(tiles);
        tilePanel = new WorldEditorDrawWorld(frame, world);
        tilePanel.setPreferredSize(new Dimension(HexagonWars.WORLD_TILE_WIDTH * 4, HexagonWars.WORLD_TILE_HEIGHT_MAX));
        tilePanel.setMaximumSize(new Dimension(HexagonWars.WORLD_TILE_WIDTH * 4, HexagonWars.WORLD_TILE_HEIGHT_MAX));
        tilePanel.addListner(this);
        add(tilePanel);
    }

    private void board() {
        if (newWorld != null) {
            remove(newWorld);
            newWorld.removeListner(this);
        }
        World world = new World(boardWidth, boardHeight);
        Tile[][] tiles = new Tile[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                tiles[i][j] = Tile.getType(HexagonWars.TILE_PLAIN);
            }
        }

        world.setWorld(tiles);
        newWorld = new WorldEditorDrawWorld(frame, world);
        newWorld.addListner(this);
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
        if (arg instanceof ActionClass.SaveWorld) {
            saveWorld();
        } else if (arg instanceof ActionClass.SetInputSize) {
            if (!inputWidthText.getText().equals("") && !inputHeightText.getText().equals("") && Integer.parseInt(inputWidthText.getText()) > 0 && Integer.parseInt(inputHeightText.getText()) > 0) {
                boardWidth = Integer.parseInt(inputWidthText.getText());
                boardHeight = Integer.parseInt(inputHeightText.getText());
                board();
            }
        } else if (arg == tilePanel) {
            selectedTileCoordinate = tilePanel.getSelectedTileCoordinate();
        } else if (arg == newWorld) {
            Point worldTile = newWorld.getSelectedTileCoordinate();
            newWorld.setTile(worldTile.x, worldTile.y, tilePanel.getTile(selectedTileCoordinate.x, selectedTileCoordinate.y));
            repaint();
            validate();
        }
    }
}
