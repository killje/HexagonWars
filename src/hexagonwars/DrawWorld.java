package hexagonwars;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class DrawWorld extends Component {

    private World world;
    private int worldWidth;
    private int worldHeight;
    private Tile[][] r;
    private Point selectedTileCoordinate;
    private int worldLocationX;
    private int worldLocationY;
    private String referenceName;
    private double zoomLevel = 1;
    private boolean CameraEnebled = true;
    private int cameraX = 0;
    private int cameraY = 0;

    public DrawWorld(World worldInput, int x, int y) {
        worldLocationX = x;
        worldLocationY = y;
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
        this.setPreferredSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * zoomLevel), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * zoomLevel)));
        this.setMaximumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * zoomLevel), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * zoomLevel)));
        this.setMinimumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * zoomLevel), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * zoomLevel)));

    }

    public void setReferenceName(String name) {
        referenceName = name;
    }

    public String getRefrenceName() {
        return referenceName;
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

    public int worldHeight() {
        return worldHeight;
    }

    public int worldWidth() {
        return worldWidth;
    }

    public void setWorld(World worldInput) {
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
    }

    public boolean inWorld(int x, int y) {
        if (x >= worldLocationX * zoomLevel
                && y >= worldLocationY * zoomLevel
                && x < (int) ((worldWidth * HexagonWars.WORLD_TILE_WIDTH + HexagonWars.WORLD_TILE_WIDTH / 2) * zoomLevel) + worldLocationX-cameraX
                && y < (int) ((worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MIN + HexagonWars.WORLD_TILE_UPPERHEIGHT) * zoomLevel) + worldLocationY-cameraY) {
            return true;
        }
        return false;
    }

    public int getXLocation() {
        return worldLocationX;
    }

    public int getYLocation() {
        return worldLocationY;
    }

    public boolean inWorld(Point point) {
        return inWorld(point.x, point.y);
    }

    public void changeZoomLevel(double zoomChange) {
        if (CameraEnebled) {
            zoomLevel -= zoomChange;
        }
    }

    public void resetZoomLevel() {
        if (CameraEnebled) {
            zoomLevel = 1;
        }
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public void setCameraEnabled(boolean b) {
        CameraEnebled = b;
    }
    
    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void changeCameraX(int amounth) {
        if (CameraEnebled) {
            cameraX += amounth;
        }
    }

    public void changeCameraY(int amounth) {
        if (CameraEnebled) {
            cameraY += amounth;
        }
    }
}
