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

    public EntityUI(String name) {
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

    public void removeAction(UIAction action) {
        for (int i = 0; i < actionList.size(); i++) {
            ImageWithAction actionImage = actionList.get(i);
            if (actionImage.getAction().equals(action)) {
                actionList.remove(i);
            }
        }
    }

    public void addAction(String actionName, UIAction action) {
        actionList.add(new ImageWithAction(actionName, action));
    }
}