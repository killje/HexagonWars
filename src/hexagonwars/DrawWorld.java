package hexagonwars;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class DrawWorld extends Component{

    private World world;
    private int worldWidth;
    private int worldHeight;
    private Tile[][] r;
    private Point selectedTileCoordinate;
    private Notify notify = new Notify();
    private String action = null;

    public DrawWorld(World world, String select) {
        this(world);
        action = select;
    }
    
    public DrawWorld(World worldInput) {
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
        this.addMouseListener(new WorldPointer());
        this.setPreferredSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM),(int)(worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)));
        this.setMaximumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM),(int)(worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)));
        this.setMinimumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM),(int)(worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)));
    }

    

    /**
     *
     * @param im as bufferdImage; this is the image you want the transparent
     * color in
     * @param color as Color; this is the color you wand to make transparent
     * @return image with color replaced with transparency in im
     */
    

    public Point getSelectedTileCoordinate() {
        return selectedTileCoordinate;
    }

    public void setTile(int x, int y, Tile tile) {
        r[x][y] = tile;
    }

    public Tile getTile(int x, int y) {
        return r[x][y];
    }

    public Tile[][] getWorld() {
        return r;
    }
    
    public int worldHeight(){
        return worldHeight;
    }
    
    public int worldWidth(){
        return worldWidth;
    }

    protected void clicked(MouseEvent me) {
        selectedTileCoordinate = getTileCoordinate(me.getPoint());
        notify.sendNotify(action);
    }

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

    public void setWorld(World worldInput){
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
    }
    
    private class WorldPointer implements MouseListener{

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
    
    private class Notify extends Observable{
        
        private void sendNotify(Object arg){
            this.setChanged();
            this.notifyObservers(arg);
        }
    }
    
    public void addObserver(Observer o){
        notify.addObserver(o);
    }
    
    public void deleteObserver(Observer o){
        notify.deleteObserver(o);
    }
}
