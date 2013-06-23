package hexagonwars;

import hexagonwars.entities.Building;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BuildAction extends UIAction implements Serializable {

    private Building building;

    public BuildAction(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }
}
