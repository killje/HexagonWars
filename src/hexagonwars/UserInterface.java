/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class UserInterface {

    public static final int ICON_WIDTH = 50;
    public static final int ICON_HEIGHT = 50;
    private String name;
    private ArrayList<ImageWithLocation> icons = new ArrayList<>();

    public UserInterface(String name) {
        this.name = name;
    }

    public void addIcon(String iconName,Point p) {
        icons.add(new ImageWithLocation(HWImage.getImage(1, 1, ICON_WIDTH, ICON_HEIGHT, iconName),p));
    }

    public ArrayList<ImageWithLocation> getIcons() {
        return icons;
    }

    public String getName() {
        return name;
    }

    public class ImageWithLocation {

        Point point;
        Image image;

        private ImageWithLocation(Image image, Point p) {
            point = p;
            this.image = image;
        }
        
        public Image getImage(){
            return image;
        }
        
        public Point getLocation(){
            return point;
        }
            
    }
}
