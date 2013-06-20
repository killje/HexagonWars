/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

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
    private ArrayList<ImageWithAction> actionList = new ArrayList<>();
    private int health;

    public UserInterface(String name) {
        this.name = name;
    }

    public ArrayList<ImageWithAction> getActions() {
        return actionList;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addAction(String actionName, UIAction action) {
        actionList.add(new ImageWithAction(HWImage.getImage(1, 1, ICON_WIDTH, ICON_HEIGHT, actionName),action));
    }
}
