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
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class HWImage {

    private static ArrayList<ImageName> images = new ArrayList<>();

    private static ImageName getTile(int imX, int imY, int width, int height, String filename) {
        Image img;
        ImageName newImage;
        imX = (imX - 1) * width;
        imY = (imY - 1) * height;

        BufferedImage image;
        try {
            File file = new File(Tile.class.getResource("images" + File.separator + filename + ".png").toURI());
            image = ImageIO.read(file);
            image = image.getSubimage(imX, imY, width, height);
            // the color we are looking for... Alpha bits are set to opaque
            int transparentRGB = new Color(127, 0, 55).getRGB() | 0xFF000000;
            int transparentRGB2 = new Color(255, 0, 255).getRGB() | 0xFF000000;
            ArrayList<Integer> colors = new ArrayList<>();
            colors.add(transparentRGB);
            colors.add(transparentRGB2);
            img = makeColorTransparent(image, colors);
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
        newImage = new ImageName(filename, img);
        return newImage;
    }

    private static Image makeColorTransparent(BufferedImage im, final ArrayList<Integer> colors) {
        ImageFilter filter = new RGBImageFilter() {
            @Override
            public final int filterRGB(int x, int y, int rgb) {

                if (colors.contains((rgb | 0xFF000000))) {
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

    public static Image getImage(int imX, int imY, int width, int height, String imageName) {
        for (ImageName image : images) {
            if (image.getName().equals(imageName)) {
                return image.getImage();
            }
        }
        ImageName image = getTile(imX, imY, width, height, imageName);
        images.add(image);
        return image.getImage();
    }
}
