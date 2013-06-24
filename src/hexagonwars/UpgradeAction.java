package hexagonwars;

import hexagonwars.entities.Building;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class UpgradeAction extends UIAction implements Serializable {

    public UpgradeAction() {
        // TODO code application logic here
    }
    private Building building;
    private int upgrade = -1;

    public UpgradeAction(Building building) {
        this.building = building;
    }

    public UpgradeAction(Building building, int upgrade) {
        this.building = building;
        this.upgrade = upgrade;
    }

    public Building upgradedBuilding() {
        return building;
    }

    public int upgradeID() {
        return upgrade;
    }
}
