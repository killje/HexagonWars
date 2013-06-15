package hexagonwars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldEditor {

    int height = 0;
    int width = 0;
    Tile[][] world;

    public WorldEditor(File file) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        try {
            System.out.print("please specify the height and width: ");
            st = new StringTokenizer(br.readLine(), " ");
            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            world = new Tile[width][height];
            System.out.print("please specify the tiles seperated by \";\"\nthe given world is " + height + " by " + width + "\n");
            for (int y = 0; y < height; y++) {
                st = new StringTokenizer(br.readLine(), ";");
                for (int x = 0; x < width; x++) {
                    world[x][y] = Tile.getType(Integer.parseInt(st.nextToken()));
                }
            }
            store(file);

        } catch (IOException e) {
            System.out.println("could not read next byte" + e.getMessage() + e.getCause());
        } catch (NumberFormatException e) {
            System.out.println("the input is not a number");
        }
    }

    

    private void store(File file) {
        WorldFile saveWorld = createWorldFile();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(saveWorld);

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("The desired file was not found.");
        } catch (NotSerializableException e) {
            System.err.println(e.fillInStackTrace());
        } catch (IOException e) {
            System.err.println("An error with the I/O was reported, program closing.");
        }
    }

    private WorldFile createWorldFile() {
        WorldFile saveWorld = new WorldFile();
        saveWorld.setHeight(this.height);
        saveWorld.setWidth(this.width);
        saveWorld.setWorld(this.world);
        return saveWorld;
    }
}
