package hexagonwars.entities;

import hexagonwars.UpgradeAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WoodSaw extends Gatherer {

    public WoodSaw(int playerColor) {
        super(playerColor);
        type = ENTITY_BUILDING_GATHERER_SAWMILL;
    }

    @Override
    protected void addUIAfterFinish() {
        UpgradeAction action = new UpgradeAction();
        addUIElement("UPGRADE_SAWMILL", action);
    }
}
