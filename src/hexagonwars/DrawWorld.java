package hexagonwars;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class DrawWorld extends JPanel {

    private HWFrame frame;
    private World world;

    public DrawWorld(HWFrame hwFrame, World worldInput) {
        frame = hwFrame;
        world = worldInput;
    }

    /**
     *
     * @param im as bufferdImage; this is the image you wand the transparent
     * color in
     * @param color as Color; this is the color you wand to make transparent
     * @return image with color replaced with transparency in im
     */
    @Override
    public void paint(Graphics g) {
        drawWorld(g);
        Color color1 = new Color(255, 255, 0);
        g.setColor(color1);
    }

    private void drawWorld(Graphics g) {
        int i, j, xOut, yOut;
        xOut = world.getWidth();
        yOut = world.getHeight();
        Tile[][] r = world.getTiles();
        for (j = 0; j < yOut; j++) {
            for (i = 0; i < xOut; i++) {
                g.drawImage(r[i][j].getImage(),
                        i * HexagonWars.WORLD_TILE_WIDTH + j % 2 * (HexagonWars.WORLD_TILE_WIDTH / 2),
                        j * HexagonWars.WORLD_TILE_HEIGHT_MIN,
                        this);
            }
        }
        
    }
}
