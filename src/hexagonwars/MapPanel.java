/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class MapPanel extends JPanel {

    private ArrayList<DrawWorld> worlds = new ArrayList<>();
    private DrawWorld selectedWorld;
    private Point selectedTileCoordinate;
    

    public MapPanel() {
        addMouseListener(new WorldPointer());
    }
    
    protected DrawWorld addWorld(World world,int x,int y){
        DrawWorld newWorld = new DrawWorld(world,x,y);
        worlds.add(newWorld);
        return newWorld;
    }
    
    protected void removeWorld(DrawWorld world){
        if (worlds.contains(world)) {
            worlds.remove(world);
        }
    }

    protected void saveWorld() {
        String path = JOptionPane.showInputDialog(null, "Path Name:", Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "mapname.hwm");
        File file = new File(path);

        store(file);
    }

    private void store(File file) {
//        try {
//            if (!file.exists()) {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            } else {
//                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to override " + file.getName() + "?") != 0) {
//                    return;
//                }
//            }
//            FileOutputStream fos = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            WorldFile saveWorld = new WorldFile();
//            saveWorld.setHeight(boardHeight);
//            saveWorld.setWidth(boardWidth);
//            saveWorld.setWorld(worlds.getWorld());
//            oos.writeObject(saveWorld);
//
//            oos.close();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            System.err.println("The desired file was not found.");
//        } catch (NotSerializableException e) {
//            System.err.println("The saved object is not serializable at: " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("An error with the I/O was reported, program closing.");
//            System.exit(-1);
//        }
    }

    protected class SaveWorld extends AbstractAction {

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
        for (int i = 0; i < worlds.size(); i++) {
            DrawWorld world = worlds.get(i);
            drawWorld(g, world, world.getXLocation(),world.getYLocation());
        }
    }

    private void drawWorld(Graphics g, DrawWorld world, int panelShiftX ,int panelShiftY)  {
        int x, y;
        for (y = 0; y < world.worldHeight(); y++) {
            for (x = 0; x < world.worldWidth(); x++) {
                g.drawImage(world.getWorld()[x][y].getImage(),
                        x * (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM) + y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * HexagonWars.PLACEHOLDER_ZOOM) - HexagonWars.PLACEHOLDER_CAMARA_X+panelShiftX,
                        y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * HexagonWars.PLACEHOLDER_ZOOM) - HexagonWars.PLACEHOLDER_CAMARA_X + panelShiftY,
                        (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM),
                        (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM),
                        null);
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
    
    protected void clicked(MouseEvent me) {
        for (DrawWorld world : worlds) {
            if (world.inWorld(me.getX(), me.getY())) {
                Point pointInWorld = new Point(me.getX() - world.getXLocation(),me.getY() - world.getYLocation());
                Point TileCoordinate=getTileCoordinate(pointInWorld);
                tileClick(world,TileCoordinate);
            }
        }
    }
    
    protected abstract void tileClick(DrawWorld world, Point TileCoordinate);

    private Point getTileCoordinate(Point p) {
        int x = (int) p.getX() + HexagonWars.PLACEHOLDER_CAMARA_X;
        int y = (int) p.getY() + HexagonWars.PLACEHOLDER_CAMARA_X;
        int tileX;
        int tileY;
        boolean uneven = false;
        final int zoomTileHeightMin = (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * HexagonWars.PLACEHOLDER_ZOOM);
        final int zoomTileWidth = (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM);
        final int zoomTileUpperHeight = (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * HexagonWars.PLACEHOLDER_ZOOM);


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
                if (inHex(x, y, true)) {
                    return new Point(tileX, tileY);
                } else {
                    if (uneven) {
                        return new Point(tileX, tileY - 1);
                    } else {
                        return new Point(tileX - 1, tileY - 1);
                    }
                }
            } else {
                if (inHex(x - zoomTileWidth / 2, y, false)) {
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

    private Boolean inHex(int x, int y, boolean up) {
        int x0, x1, y0, y1;
        x0 = 0;
        x1 = (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM) / 2;
        if (up) {
            y0 = (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * HexagonWars.PLACEHOLDER_ZOOM);
            y1 = 0;
        } else {
            y0 = 0;
            y1 = (int) (HexagonWars.WORLD_TILE_UPPERHEIGHT * HexagonWars.PLACEHOLDER_ZOOM);
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
    
    protected class OpenWorld extends AbstractAction {
        
        DrawWorld world;
        
        public OpenWorld(DrawWorld inputWorld){
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
}
