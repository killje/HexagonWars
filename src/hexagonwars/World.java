package hexagonwars;

import java.io.*;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class World {

    private int width;
    private int height;
    private Tile[][] tiles;

    public World(File file) {
        read(file);
        System.out.println(toString());
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
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
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        String returnString = "Height: " + getHeight() + "\nWidth: " + getWidth() + "\nTiles:\n";
        for (int i = 0; i < height; i++) {
            //for make up only ( starting y at 1
            returnString = returnString + tiles[i][0].toString();
            for (int j = 1; j < width; j++) {
                returnString = returnString + "\t" + tiles[i][j].toString();
            }
            returnString = returnString + "\n";
        }
        return returnString;
    }
}
