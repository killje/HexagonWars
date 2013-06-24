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

    private static final int TRANSPARENTCOLOR = 0x00FFFFFF;
    private static ArrayList<ImageName> images = new ArrayList<>();
    private static ArrayList<Integer> defaultColors = new ArrayList<>();

    private static ImageName getTile(String filename, ArrayList<Integer> colorsFrom, int colorTo) {
        Image img;
        ImageName newImage;
        BufferedImage image;
        System.out.println(filename);
        try {
            File file = new File(Tile.class.getResource("images" + File.separator + filename + ".png").toURI());
            image = ImageIO.read(file);
            // the color we are looking for... Alpha bits are set to opaque
            img = makeColorTransparent(image, colorsFrom, colorTo);
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

        newImage = new ImageName(filename + colorTo, img);

        return newImage;
    }

    private static Image makeColorTransparent(BufferedImage im, final ArrayList<Integer> colorsFrom, final int colorTo) {
        ImageFilter filter = new RGBImageFilter() {
            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if (defaultColors.contains((rgb | 0xFF000000))) {
                    return TRANSPARENTCOLOR & rgb;
                } else if (colorsFrom.contains((rgb | 0xFF000000))) {
                    return colorTo & rgb; // make alpha bits zero - transparent
                } else {
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);

        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public static Image getImage(String imageName, ArrayList<Integer> colorsFrom, int colorTo) {
        if (defaultColors.isEmpty()) {
            int transparentRGB = new Color(127, 0, 55).getRGB() | 0xFF000000;
            int transparentRGB2 = new Color(255, 0, 255).getRGB() | 0xFF000000;
            defaultColors.add(transparentRGB);
            defaultColors.add(transparentRGB2);
        }

        for (ImageName image : images) {
            if (image.getName().equals(imageName + colorTo)) {
                return image.getImage();
            }
        }

        ImageName image = getTile(imageName, colorsFrom, colorTo);
        images.add(image);

        return image.getImage();
    }

    public static Image getImageWithDefaultTransparency(String imageName) {
        return getImage(imageName, defaultColors, TRANSPARENTCOLOR);
    }
}
