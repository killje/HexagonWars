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

    private ArrayList<WorldModel> worlds = new ArrayList<>();
    protected Tile selectedTile = null;

    /**
     * mapPanel is both the view and the controller for the most part
     */
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

    /**
     *
     * @param world sends the worldModel that has been clicked
     * @param TileCoordinate sends on witch tile there has been clicked on
     */
    protected abstract void tileClick(WorldModel world, Point TileCoordinate);

    /**
     *
     * @param world the WorldTiles of the new WorldModel
     * @param xShift the position on the x-axel in the panel
     * @param yShift the position on the y-axel in the panel
     * @return returns the created WorldModel
     */
    protected WorldModel addWorld(WorldTiles world, int xShift, int yShift) {
        WorldModel newWorld = new WorldModel(world, xShift, yShift);
        worlds.add(newWorld);
        return newWorld;
    }

    /**
     *
     * @param world removes the given world from the array
     */
    protected void removeWorld(WorldModel world) {
        if (worlds.contains(world)) {
            worlds.remove(world);
        }
    }

    /**
     *
     * @param world saves the given world
     */
    protected void saveWorld(WorldModel world) {
        String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");
        File file = new File(path);

        store(file, world);
    }

    /**
     * First looks if the mouse has clicked inside a UI then if not, looks where
     * you have clicked in the world and acts on that
     *
     * @param me MouseEvent that comes from the MouseListner
     */
    //!!!!!!!!!!!!!!!!---------------------------------edit when game.java is done----------------------------!!!!!!!!!!!!!!!!!!!!!!!!
    protected void clicked(MouseEvent me) {
        boolean hasFoundTile = false;
        for (WorldModel world : worlds) {
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
            WorldModel world = worlds.get(i);
            drawWorld(g, world, (int) (world.getXLocation()), (int) (world.getYLocation()));
        }
        if (selectedTile != null) {
            if (selectedTile.isOccupied()) {
                g.setColor(Color.BLACK);
                Rectangle rect = new Rectangle(getSize().width - 506, getSize().height - 207, 500, 201);
                int x = 0;
                int y = 0;

                g.drawRect(rect.x, rect.y, rect.width, rect.height);
                g.drawLine(rect.x + 199, rect.y, rect.x + 199, rect.y + rect.height);
                ArrayList<ImageWithAction> list = selectedTile.getEntity().getEntityUI().getActions();

                for (int i = 0; i < list.size(); i++) {
                    ImageWithAction imageWithAction = list.get(i);
                    g.drawImage(imageWithAction.getIcon(), rect.x + 200 + x * EntityUI.ICON_WIDTH, rect.y + y * EntityUI.ICON_HEIGHT + 1, null);
                    if (x < 5) {
                        x++;
                    } else {
                        x = 0;
                        y++;
                    }
                }
            }
        }
    }

    /**
     * draws the given world in the panel at the given x and y
     *
     * @param g the paint graphics
     * @param world the world you wand to draw
     * @param panelShiftX where on the x-axel to start to draw
     * @param panelShiftY where on the y-axel to start to draw
     */
    private void drawWorld(Graphics g, WorldModel world, int panelShiftX, int panelShiftY) {
        for (int y = 0; y < world.worldHeight(); y++) {
            for (int x = 0; x < world.worldWidth(); x++) {
                g.drawImage(world.getWorld()[x][y].getImage(),
                        x * (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()) + y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * world.getZoomLevel()) - world.getCameraX() + panelShiftX,
                        y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * world.getZoomLevel()) - world.getCameraY() + panelShiftY,
                        (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()),
                        (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * world.getZoomLevel()),
                        null);
                if (world.getWorld()[x][y].isOccupied()) {
                    g.drawImage(world.getWorld()[x][y].getEntity().getImage(),
                            x * (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()) + y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * world.getZoomLevel()) - world.getCameraX() + panelShiftX,
                            y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * world.getZoomLevel()) - world.getCameraY() + panelShiftY,
                            (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel()),
                            (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * world.getZoomLevel()),
                            null);
                }
            }
        }
    }

    protected void drawHex(Graphics g, int x, int y, Color color, WorldModel world) {
        Color oldColor = g.getColor();
        g.setColor(color);
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        xPoints[0] = x + (int) ((HexagonWars.WORLD_TILE_WIDTH / 2) * world.getZoomLevel());
        yPoints[0] = y;
        xPoints[1] = x;
        yPoints[1] = y + (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * world.getZoomLevel());
        xPoints[5] = x + (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel());
        yPoints[5] = y + (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * world.getZoomLevel());
        xPoints[2] = x;
        yPoints[2] = y + (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * world.getZoomLevel());
        xPoints[4] = x + (int) (HexagonWars.WORLD_TILE_WIDTH * world.getZoomLevel());
        yPoints[4] = y + (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * world.getZoomLevel());
        xPoints[3] = x + (int) ((HexagonWars.WORLD_TILE_WIDTH / 2) * world.getZoomLevel());
        yPoints[3] = y + (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * world.getZoomLevel());
        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(oldColor);
    }

    /**
     * function to store the world
     *
     * @param file stores the given world at this location
     * @param world the world to store
     */
    private void store(File file, WorldModel world) {
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

    /**
     * converts the given mouse point into a usable tile coordinate
     *
     * @param p the mouse position
     * @param world the world where is clicked on
     */
    private Point getTileCoordinate(Point p, WorldModel world) {
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

    /**
     * this function returns true if if the mouse is under the lie other wise
     * false.
     *
     * @param x the x of the mouse in the corner
     * @param y the y of the mouse in the corner
     * @param if the line of the hexagon moves up or down
     * @param world the world where is clicked on
     */
    private Boolean inHex(int x, int y, boolean up, WorldModel world) {
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

    /**
     * lister for if the save button is hit
     */
    protected class SaveWorld extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (WorldModel world : worlds) {
                if (world.isSavable()) {
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
            clicked(me);
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
            for (WorldModel world : worlds) {
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

    /**
     * opens a new map
     */
    protected class OpenWorld extends AbstractAction {

        WorldModel world;

        /**
         *
         * @param inputWorld
         */
        public OpenWorld(WorldModel inputWorld) {
            world = inputWorld;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
            WorldTiles newWorld = new WorldTiles(new File(path));
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

    /**
     * listens to the keyboard if an arrow has been hit and shifts the map
     */
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
                    for (WorldModel world : worlds) {
                        world.changeCameraY(-30);
                    }
                    repaint();
                    validate();
                    break;
                case ARROW_DOWN:
                    for (WorldModel world : worlds) {
                        world.changeCameraY(30);
                    }
                    repaint();
                    validate();
                    break;
                case ARROW_LEFT:
                    for (WorldModel world : worlds) {
                        world.changeCameraX(-30);
                    }
                    repaint();
                    validate();
                    break;
                case ARROW_RIGHT:
                    for (WorldModel world : worlds) {
                        world.changeCameraX(30);
                    }
                    repaint();
                    validate();
                    break;
            }
        }
    }
}
