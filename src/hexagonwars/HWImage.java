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
import javax.imageio.ImageIO;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class HWImage {
    private Image image;
    public  HWImage(int imX, int imY, String file){
        image = getTile(imX,imY,file);
    }
    
    private Image getTile(int imX, int imY, String file) {
        Image img;
        imX = (imX - 1) * HexagonWars.WORLD_TILE_WIDTH;
        imY = (imY - 1) * HexagonWars.WORLD_TILE_HEIGHT_MAX;
        String filepath = Tile.class.getResource("images/" + file + ".png").toExternalForm();
        filepath = filepath.replace("file:/", "");
        filepath = filepath.replace("/", "\\");
        filepath = filepath.replace("%20", " ");
        File file1 = new File(filepath);
        BufferedImage image1;
        try {
            image1 = ImageIO.read(file1);
            image1 = image1.getSubimage(imX, imY, 70, 80);
            img = makeColorTransparent(image1, new Color(255, 0, 255), new Color(127, 0, 55));
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
            BufferedImage bi = new BufferedImage(70, 80, BufferedImage.TYPE_INT_RGB);
            bi.setRGB(255, 255, 255);
            img = bi.getSubimage(0, 0, 70, 80);
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
    
    public Image getImage(){
        return image;
    }
}
