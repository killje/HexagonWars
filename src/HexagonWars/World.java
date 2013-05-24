package HexagonWars;
package HexagonWars.Tiles;

import java.io.*;
import java.util.StringTokenizer;

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
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);

            String inLine = bf.readLine();
            initializeWorld(inLine);

            inLine = bf.readLine();
            for (int x = 0; x < getWidth(); x++) {
                processWorldLine(inLine, x);
                inLine = bf.readLine();
            }

            bf.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.err.println("The desired file was not found.");
        } catch (IOException e) {
            System.err.println("An error with the I/O was reported, program closing.");
        } catch (NumberFormatException e) {
            System.err.println("The world file did not contain a valid width and/or height.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void initializeWorld(String line) throws NumberFormatException {
        StringTokenizer st = new StringTokenizer(line, ";");

        this.width = Integer.parseInt(st.nextToken());
        this.height = Integer.parseInt(st.nextToken());
        
        tiles = new Tile[getWidth()][getHeight()];
    }

    private void processWorldLine(String line, int x) throws NumberFormatException {
        StringTokenizer st = new StringTokenizer(line, ";");
        
        for (int y = 0; y < getHeight(); y++) {
            /*
             * 0: PLAIN
             * 1: MOUNTAIN
             */
            tiles[x][y] = new HexagonWars.Tiles.Mountain(Integer.parseInt(st.nextToken()));
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
