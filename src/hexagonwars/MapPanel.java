/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class MapPanel extends JPanel {

    private ArrayList<DrawWorld> worlds = new ArrayList<>();
    private Tile selectedTile = null;

    public MapPanel() {
        addMouseListener(new WorldPointer());
        addMouseWheelListener(new ZoomListner());
        setLayout(new FlowLayout(FlowLayout.LEFT));
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke, "up");
        getActionMap().put("up", new ArrowAction(ArrowAction.ARROW_UP));
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        inputMap.put(keyStroke, "down");
        getActionMap().put("down", new ArrowAction(ArrowAction.ARROW_DOWN));
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        inputMap.put(keyStroke, "left");
        getActionMap().put("left", new ArrowAction(ArrowAction.ARROW_LEFT));
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        inputMap.put(keyStroke, "right");
        getActionMap().put("right", new ArrowAction(ArrowAction.ARROW_RIGHT));
    }

    protected abstract void tileClick(DrawWorld world, Point TileCoordinate);

    protected DrawWorld addWorld(World world, int x, int y) {
        DrawWorld newWorld = new DrawWorld(world, x, y);
        worlds.add(newWorld);
        return newWorld;
    }

    protected void removeWorld(DrawWorld world) {
        if (worlds.contains(world)) {
            worlds.remove(world);
        }
    }

    protected void saveWorld(DrawWorld world) {
        String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
        File file = new File(path);

        store(file, world);
    }

    protected void clicked(MouseEvent me) {
        boolean hasFoundTile = false;
        if (selectedTile != null) {
            if (selectedTile.isOccupied() != 0) {
                Rectangle rect = new Rectangle(getSize().width - 506, getSize().height - 207, 500, 201);
                if (rect.contains(me.getPoint())) {
                    Point p = new Point(me.getPoint().x - getSize().width + 506, me.getPoint().y - getSize().height + 207);
                    selectedTile.getEntity().clicked(p, selectedTile);
                    repaint();
                    validate();
                    return;
                }
            }
        }
        for (DrawWorld world : worlds) {
            if (world.inWorld(me.getX(), me.getY())) {
                Point pointInWorld = new Point(me.getX() - world.getXLocation(), me.getY() - world.getYLocation());
                Point TileCoordinate = getTileCoordinate(pointInWorld, world);
                tileClick(world, TileCoordinate);
                hasFoundTile = true;
                selectedTile = world.getTile(TileCoordinate.x, TileCoordinate.y);
                repaint();
                validate();
            }
        }
        if (!hasFoundTile) {
            selectedTile = null;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < worlds.size(); i++) {
            DrawWorld world = worlds.get(i);
            drawWorld(g, world, (int) (world.getXLocation()), (int) (world.getYLocation()));
        }
        if (selectedTile != null) {
            if (selectedTile.isOccupied() != 0) {
                g.setColor(Color.BLACK);
                Rectangle rect = new Rectangle(getSize().width - 506, getSize().height - 207, 500, 201);
                selectedTile.getEntity().drawUI(g, rect);
            }
        }
    }

    private void drawWorld(Graphics g, DrawWorld world, int panelShiftX, int panelShiftY) {
        for (int y = 0; y < world.worldHeight(); y++) {
            for (int x = 0; x < world.worldWidth(); x++) {
                g.drawImage(world.getWorld()[x][y].getImage(),
                        x * (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()) + y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * world.getZoomLevel()) - world.getCameraX() + panelShiftX,
                        y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * world.getZoomLevel()) - world.getCameraY() + panelShiftY,
                        (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()),
                        (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * world.getZoomLevel()),
                        null);
            }
        }
    }

    private void store(File file, DrawWorld world) {
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
            saveWorld.setHeight(world.worldHeight());
            saveWorld.setWidth(world.worldWidth());
            saveWorld.setWorld(world.getWorld());
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

    private Point getTileCoordinate(Point p, DrawWorld world) {
        int x = (int) p.getX() + world.getCameraX();
        int y = (int) p.getY() + world.getCameraY();
        int tileX;
        int tileY;
        boolean uneven = false;
        final int zoomTileHeightMin = (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * world.getZoomLevel());
        final int zoomTileWidth = (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel());
        final int zoomTileUpperHeight = (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * world.getZoomLevel());


        tileY = y / zoomTileHeightMin;
        y = y % zoomTileHeightMin;
        if (tileY % 2 == 1) {
            uneven = true;
        }
        // check if there is a offset because of its row
        if (uneven) {
            tileX = (x - (zoomTileWidth / 2)) / zoomTileWidth;
            x = (x - (zoomTileWidth / 2)) % zoomTileWidth;
        } else {
            tileX = x / zoomTileWidth;
            x = x % zoomTileWidth;
        }
        //check if it is in a area with a sloped side
        if (y <= zoomTileUpperHeight) {
            if (x <= zoomTileWidth / 2) {
                if (inHex(x, y, true, world)) {
                    return new Point(tileX, tileY);
                } else {
                    if (uneven) {
                        return new Point(tileX, tileY - 1);
                    } else {
                        return new Point(tileX - 1, tileY - 1);
                    }
                }
            } else {
                if (inHex(x - zoomTileWidth / 2, y, false, world)) {
                    return new Point(tileX, tileY);
                } else {
                    if (uneven) {
                        return new Point(tileX + 1, tileY - 1);
                    } else {
                        return new Point(tileX, tileY - 1);
                    }
                }
            }
        }
        return new Point(tileX, tileY);
    }

    private Boolean inHex(int x, int y, boolean up, DrawWorld world) {
        int x0, x1, y0, y1;
        x0 = 0;
        x1 = (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()) / 2;
        if (up) {
            y0 = (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * world.getZoomLevel());
            y1 = 0;
        } else {
            y0 = 0;
            y1 = (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * world.getZoomLevel());
        }

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx, sy;
        if (x0 < x1) {
            sx = 1;
        } else {
            sx = -1;
        }
        if (y0 < y1) {
            sy = 1;
        } else {
            sy = -1;
        }
        int err = dx - dy;

        while (x0 <= x) {
            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
        }
        if (y <= y0) {
            return false;
        } else {
            return true;
        }
    }

    protected class SaveWorld extends AbstractAction {

        String referenceName;

        protected SaveWorld(String referenceName) {
            this.referenceName = referenceName;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (DrawWorld world : worlds) {
                if (world.getRefrenceName() != null && world.getRefrenceName().equals(referenceName)) {
                    saveWorld(world);
                }
            }
        }
    }

    private class WorldPointer implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            clicked(me);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            // not implemented
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            // not implemented
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            // not implemented
        }

        @Override
        public void mouseExited(MouseEvent me) {
            // not implemented
        }
    }

    private class ZoomListner implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent mwe) {
            for (DrawWorld world : worlds) {
                if (world.inWorld(mwe.getPoint())) {
                    if (world.getZoomLevel() - (mwe.getPreciseWheelRotation() * 0.05) >= 0.1 && world.getZoomLevel() - (mwe.getPreciseWheelRotation() * 0.05) <= 1.9) {
                        world.changeZoomLevel(mwe.getPreciseWheelRotation() * 0.05);
                        repaint();
                        validate();
                    }
                }
            }
        }
    }

    protected class OpenWorld extends AbstractAction {

        DrawWorld world;

        public OpenWorld(DrawWorld inputWorld) {
            world = inputWorld;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
            World newWorld = new World(new File(path));
            world.setWorld(newWorld);
            repaint();
            revalidate();
        }
    }

    protected class QuitAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

    protected class ArrowAction extends AbstractAction {

        public static final int ARROW_UP = 0;
        public static final int ARROW_DOWN = 1;
        public static final int ARROW_LEFT = 2;
        public static final int ARROW_RIGHT = 3;
        private int direction;

        private ArrowAction(int direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            switch (direction) {
                case ARROW_UP:
                    for (DrawWorld world : worlds) {
                        world.changeCameraY(-30);
                    }
                    repaint();
                    validate();
                    break;
                case ARROW_DOWN:
                    for (DrawWorld world : worlds) {
                        world.changeCameraY(30);
                    }
                    repaint();
                    validate();
                    break;
                case ARROW_LEFT:
                    for (DrawWorld world : worlds) {
                        world.changeCameraX(-30);
                    }
                    repaint();
                    validate();
                    break;
                case ARROW_RIGHT:
                    for (DrawWorld world : worlds) {
                        world.changeCameraX(30);
                    }
                    repaint();
                    validate();
                    break;
            }
        }
    }
}
