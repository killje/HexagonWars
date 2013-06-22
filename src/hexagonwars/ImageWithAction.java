/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ImageWithAction implements Serializable{

    private String imageName;
    private UIAction action;

    public ImageWithAction(String imageName, UIAction action) {
        this.imageName = imageName;
        this.action = action;
    }

    public Image getIcon() {
        return HWImage.getImage(1, 1, EnityUI.ICON_WIDTH, EnityUI.ICON_HEIGHT, imageName);
    }

    public UIAction getAction() {
        return action;
    }
}
