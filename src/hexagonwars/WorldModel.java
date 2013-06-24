package hexagonwars;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldModel extends Component {

    private WorldTiles world;
    private int worldWidth;
    private int worldHeight;
    private Tile[][] r;
    private Point selectedTileCoordinate;
    private int worldLocationX;
    private int worldLocationY;
    private double zoomLevel = 1;
    private boolean cameraEnabled = true;
    private boolean saveable = true;
    private int cameraX = 0;
    private int cameraY = 0;
    private GameHandler gameHandler;

    public WorldModel(WorldTiles worldInput, int x, int y, GameHandler gameHandler) {
        worldLocationX = x;
        worldLocationY = y;
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
        this.gameHandler = gameHandler;
        this.setPreferredSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * zoomLevel), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * zoomLevel)));
        this.setMaximumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * zoomLevel), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * zoomLevel)));
        this.setMinimumSize(new Dimension((int) (worldWidth * HexagonWars.WORLD_TILE_WIDTH * zoomLevel), (int) (worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MAX * zoomLevel)));

    }

    public WorldModel() {
    }

    public Point getSelectedTileCoordinate() {
        return selectedTileCoordinate;
    }

    public void setTile(int x, int y, Tile tile) {
        r[x][y] = Tile.getTileFromType(tile.getType());
        if (tile.isOccupied()) {
            r[x][y].addEntity(tile.getEntity());

        }
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

    public void setWorld(WorldTiles worldInput) {
        world = worldInput;
        r = world.getTiles();
        worldHeight = world.getHeight();
        worldWidth = world.getWidth();
    }

    public boolean inWorld(int x, int y) {
        if (x >= worldLocationX * zoomLevel
                && y >= worldLocationY * zoomLevel
                && x < (int) ((worldWidth * HexagonWars.WORLD_TILE_WIDTH + HexagonWars.WORLD_TILE_WIDTH / 2) * zoomLevel) + worldLocationX - cameraX
                && y < (int) ((worldHeight * HexagonWars.WORLD_TILE_HEIGHT_MIN + HexagonWars.WORLD_TILE_UPPERHEIGHT) * zoomLevel) + worldLocationY - cameraY) {
            return true;
        }
        return false;
    }

    public void setXLocation(int x) {
        this.worldLocationX = x;
    }

    public void setYLocation(int y) {
        this.worldLocationY = y;
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
        if (cameraEnabled) {
            zoomLevel -= zoomChange;
        }
    }

    public void resetZoomLevel() {
        if (cameraEnabled) {
            zoomLevel = 1;
        }
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public void setCameraEnabled(boolean b) {
        cameraEnabled = b;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void changeCameraX(int amount) {
        if (cameraEnabled) {
            cameraX += amount;
        }
    }

    public void changeCameraY(int amount) {
        if (cameraEnabled) {
            cameraY += amount;
        }
    }

    public void setSaveable(boolean b) {
        saveable = b;
    }

    public boolean isSavable() {
        return saveable;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public ArrayList<Tile> getMoves(Tile[] possibleTiles, Point p, int moves) {
        ArrayList<Tile> tilesToMoveOn = new ArrayList<>();

        if (p.x < 0 || p.x >= world.getWidth() || p.y < 0 || p.y >= world.getHeight()) {
            return tilesToMoveOn;
        }

        if (moves == 0) {
            return tilesToMoveOn;
        } else {
            tilesToMoveOn.add(world.getTile(p));
            getTilePosition(world.getTile(p));
        }

        Point[] points = new Point[6];

        points[0] = new Point(p.x, p.y - 1);
        points[1] = new Point(p.x, p.y + 1);
        points[2] = new Point(p.x - 1, p.y);
        points[3] = new Point(p.x + 1, p.y);

        if (p.y % 2 == 0) {
            points[4] = new Point(p.x - 1, p.y - 1);
            points[5] = new Point(p.x - 1, p.y + 1);
        } else {
            points[4] = new Point(p.x + 1, p.y - 1);
            points[5] = new Point(p.x + 1, p.y + 1);
        }

        for (int i = 0; i < 6; i++) {
            ArrayList<Tile> recursiveTiles = getMoves(possibleTiles, points[i], moves - 1);
            for (Tile tile : recursiveTiles) {
                if (!tilesToMoveOn.contains(tile)) {
                    tilesToMoveOn.add(tile);
                }
            }
        }

        return tilesToMoveOn;
    }

    public Point getTilePosition(Tile tile) {
        for (int x = 0; x < r.length; x++) {
            Tile[] tileRow = r[x];
            for (int y = 0; y < tileRow.length; y++) {
                if (r[x][y] == tile) {
                    return new Point(x, y);
                }
            }
        }

        return null;
    }
}
