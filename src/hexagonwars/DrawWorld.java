package hexagonwars;

import hexagonwars.ActionClass.WorldPointer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class DrawWorld extends JPanel implements Observer {

    private HWFrame frame;
    private World world;
    private int worldWidth;
    private int worldHeight;
    private Tile[][] r;
    private Tile selectedTile;
    private WorldPointer listner;
    private Notify notify = new Notify(); 

    public DrawWorld(HWFrame hwFrame, World worldInput) {
        frame = hwFrame;
        listner = frame.getActionClass().new WorldPointer(this);
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
        this.setMinimumSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
    }

    /**
     *
     * @param im as bufferdImage; this is the image you wand the transparent
     * color in
     * @param color as Color; this is the color you wand to make transparent
     * @return image with color replaced with transparency in im
     */
    @Override
    public void paint(Graphics g) {
        drawWorld(g);
        Color color1 = new Color(255, 255, 0);
        g.setColor(color1);
        this.addMouseListener(listner);
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void addListner(Observer o) {
        notify.addObserver(o);
    }

    private void drawWorld(Graphics g) {
        int x, y;
        for (y = 0; y < worldHeight; y++) {
            for (x = 0; x < worldWidth; x++) {
                g.drawImage(r[x][y].getImage(),
                        x * (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM) + y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * HexagonWars.PLACEHOLDER_ZOOM) - HexagonWars.PLACEHOLDER_CAMARA_X,
                        y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * HexagonWars.PLACEHOLDER_ZOOM) - HexagonWars.PLACEHOLDER_CAMARA_X,
                        (int) (HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM),
                        (int) (HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM),
                        null);
            }
        }
    }

    protected void clicked(MouseEvent me) {
        System.out.println(getTile(me.getPoint()));
        selectedTile = getTile(me.getPoint());
        notify.sendNotify(this);
    }

    private Tile getTile(Point p) {
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
                    return r[tileX][tileY];
                } else {
                    if (uneven) {
                        return r[tileX][tileY - 1];
                    } else {
                        return r[tileX - 1][tileY - 1];
                    }
                }
            } else {
                if (inHex(x - zoomTileWidth / 2, y, false)) {
                    return r[tileX][tileY];
                } else {
                    if (uneven) {
                        return r[tileX + 1][tileY - 1];
                    } else {
                        return r[tileX][tileY - 1];
                    }
                }
            }
        }
        return r[tileX][tileY];
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

    @Override
    public void update(Observable o, Object o1) {
        if (o1 instanceof MouseEvent) {
            clicked((MouseEvent) o1);
        }
    }
    
    public class Notify extends Observable {

        public void sendNotify(Object arg) {
            this.setChanged();
            this.notifyObservers(arg);
        }
    }
}
