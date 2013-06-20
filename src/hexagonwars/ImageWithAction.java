/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Image;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ImageWithAction {

    private Image image;
    private UIAction action;

    public ImageWithAction(Image image, UIAction action) {
        this.image = image;
        this.action = action;
    }

    public Image getIcon() {
        return image;
    }

    public UIAction getAction() {
        return action;
    }
}
