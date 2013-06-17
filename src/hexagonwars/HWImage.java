package hexagonwars;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class HWImage {

    private Image image;

    public HWImage(int imX, int imY, String file) {
        image = getTile(imX, imY, file);
    }

    private Image getTile(int imX, int imY, String filename) {
        Image img;
        imX = (imX - 1) * HexagonWars.WORLD_TILE_WIDTH;
        imY = (imY - 1) * HexagonWars.WORLD_TILE_HEIGHT_MAX;


        BufferedImage image;
        try {
            File file = new File(Tile.class.getResource("images" + File.separator + filename + ".png").toURI());
            image = ImageIO.read(file);
            image = image.getSubimage(imX, imY, HexagonWars.WORLD_TILE_WIDTH, HexagonWars.WORLD_TILE_HEIGHT_MAX);
            img = makeColorTransparent(image, new Color(255, 0, 255), new Color(127, 0, 55));
        } catch (URISyntaxException e) {
            System.err.println("problems converting resources");
            System.err.println(e.getStackTrace());
            System.exit(1);
            return null;
        } catch (IOException ex) {
            System.err.println("The program has encountered a problem when opeing file");
            System.err.println(ex);
            System.exit(1);
            return null;
        }
        return img;
    }

    private Image makeColorTransparent(BufferedImage im, final Color color, final Color color2) {
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int transparentRGB = color.getRGB() | 0xFF000000;
            public int transparentRGB2 = color2.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == transparentRGB || ((rgb | 0xFF000000) == transparentRGB2)) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };
        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);

    }

    public Image getImage() {
        return image;
    }
}
