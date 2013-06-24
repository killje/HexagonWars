package hexagonwars;

import java.awt.Point;
import java.io.*;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldTiles {

    public static final int PLAIN = 1;
    public static final int MOUNTAIN = 2;
    public static final int WATER = 3;
    public static final int GOLD = 4;
    public static final int SHALLOWS = 5;
    public static final int FOREST = 6;
    private int width;
    private int height;
    private Tile[][] tiles;

    public WorldTiles(int w, int h) {
        width = w;
        height = h;
    }

    public WorldTiles() {
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setWorld(Tile[][] tiles) {
        this.tiles = tiles;
    }

    @Override
    public String toString() {
        String returnString = "Height: " + getHeight() + "\nWidth: " + getWidth() + "\nTiles:\n";
        for (int y = 0; y < height; y++) {
            //for make up only ( starting y at 1
            returnString = returnString + tiles[y][0].toString();
            for (int x = 1; x < width; x++) {
                returnString = returnString + "\t" + tiles[x][y].toString();
            }
            returnString = returnString + "\n";
        }
        return returnString;
    }

    public Tile getTile(Point p) {
        return this.getTile(p.x, p.y);
    }
}
