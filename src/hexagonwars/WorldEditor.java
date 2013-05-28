package hexagonwars;

import java.io.*;
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
            world = new Tile[height][width];
            System.out.print("please specify the tiles seperated by \";\"\nthe given world is " + height + " by " + width + "\n");
            for (int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine(), ";");
                for (int j = 0; j < width; j++) {
                    world[i][j] = getType(Integer.parseInt(st.nextToken()));
                }
            }
            store(file);

        } catch (IOException e) {
            System.out.println("could not read next byte" + e.getMessage() + e.getCause());
        } catch (NumberFormatException e) {
            System.out.println("the input is not a number");
        }

    }

    private Tile getType(int type) {
        Tile tile;
        switch (type) {
            case HexagonWars.TILE_PLAIN:
                tile = new hexagonwars.tiles.Plain();
                break;
            case HexagonWars.TILE_MOUNTAIN:
                tile = new hexagonwars.tiles.Mountain();
                break;
            default:
                tile = new hexagonwars.tiles.Plain();
                break;
        }
        return tile;

    }

    private void store(File file) {
        WorldFile saveWorld = new WorldFile();
        saveWorld.setHeight(this.height);
        saveWorld.setWidth(this.width);
        saveWorld.setWorld(this.world);
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
}
