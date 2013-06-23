package hexagonwars;

import java.awt.Image;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ImageName {

    private String name;
    private Image image;

    /**
     *
     * @param name the name to be connected to the image
     * @param image the image to be used
     */
    public ImageName(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    /**
     *
     * @return the name for the instance
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the image for the instance
     */
    public Image getImage() {
        return image;
    }
}
