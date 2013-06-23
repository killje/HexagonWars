package hexagonwars;

import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class NewUIAction extends UIAction implements Serializable {

    protected EntityUI ui;

    public NewUIAction(String uiName, int playerColor) {
        ui = new EntityUI(uiName, playerColor);
    }

    public void addIcon(String iconName, UIAction action) {
        ui.addAction(iconName, action);
    }

    public EntityUI getUI() {
        return ui;
    }
}
