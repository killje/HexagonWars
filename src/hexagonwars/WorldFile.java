package hexagonwars;

import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldFile implements Serializable {

    private int height;
    private int width;
    private Tile[][] tiles;
    private GameHandler gameHandler;

    public void WorldFile() {
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Tile[][] getWorld() {
        return this.tiles;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setWorld(Tile[][] world) {
        this.tiles = world;
    }
    
    public GameHandler getGameHandler() {
        return this.gameHandler;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
}
