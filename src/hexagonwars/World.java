package hexagonwars;

import java.io.*;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class World {
    
    public static final int PLAIN = 1;
    public static final int MOUNTAIN = 2;
    public static final int WATER = 3;
    public static final int GOLD = 4;
    
    private int width;
    private int height;
    private Tile[][] tiles;

    public World(int w, int h) {
        width = w;
        height = h;
    }

    public World(File file) {
        read(file);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
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

    private void read(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object object = ois.readObject();
            if (!(object instanceof WorldFile)) {
                throw new Exception("An illegal class type was found (" + object.getClass().getName() + ")");
            }

            WorldFile world = (WorldFile) object;
            this.height = world.getHeight();
            this.width = world.getWidth();
            this.tiles = world.getWorld();

            ois.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            System.err.println("The file could not be read.");
        } catch (FileNotFoundException e) {
            System.err.println("The desired file was not found.");
        } catch (IOException e) {
            System.err.println("An error with the I/O was reported, program closing.");
            System.err.println(e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
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
}
