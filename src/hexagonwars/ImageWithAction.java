package hexagonwars;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ImageWithAction implements Serializable {

    private String imageName;
    private UIAction action;
    private int playerColor;

    public ImageWithAction(String imageName, UIAction action, int playerColor) {
        this.imageName = imageName;
        this.action = action;
        this.playerColor = playerColor;
    }

    public Image getIcon() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(new Color(55, 171, 200).getRGB());
        return HWImage.getImage(imageName, colors, playerColor);
    }

    public UIAction getAction() {
        return action;
    }
}
