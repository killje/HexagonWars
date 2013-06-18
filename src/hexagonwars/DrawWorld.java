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

    public DrawWorld(World worldInput, int x, int y) {
        worldLocationX = x;
        worldLocationY = y;
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
        this.setPreferredSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)));
        this.setMaximumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)));
        this.setMinimumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)));
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
        if (x >= worldLocationX
                && y >= worldLocationY
                && x < (int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * HexagonWars.PLACEHOLDER_ZOOM)
                && y < (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * HexagonWars.PLACEHOLDER_ZOOM)) {
            return true;
        }
        return false;
    }
    
    public int getXLocation(){
        return worldLocationX;
    }
    
    public int getYLocation(){
        return worldLocationY;
    }
}
