package hexagonwars;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class EntityUI implements Serializable {

    public static final int ICON_WIDTH = 50;
    public static final int ICON_HEIGHT = 50;
    private String name;
    private ArrayList<ImageWithAction> actionList = new ArrayList<>();
    private int health;
    private int playerColor;

    /**
     *
     * @param name the entity's name
     * @param playerColor the player color to be displayed
     */
    public EntityUI(String name, int playerColor) {
        this.name = name;
        this.playerColor = playerColor;
    }

    /**
     *
     * @return an ArrayList with images, connected to their respective actions
     */
    public ArrayList<ImageWithAction> getActions() {
        return actionList;
    }

    /**
     *
     * @return the entity's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param health health of the current entity
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @param action the action to be removed
     */
    public void removeAction(UIAction action) {
        for (int i = 0; i < actionList.size(); i++) {
            ImageWithAction actionImage = actionList.get(i);
            if (actionImage.getAction().equals(action)) {
                actionList.remove(i);
            }
        }
    }

    /**
     *
     * @param actionName the name for the action
     * @param action the action to add
     */
    public void addAction(String actionName, UIAction action) {
        actionList.add(new ImageWithAction(actionName, action, playerColor));
    }
}