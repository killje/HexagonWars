package hexagonwars.entities;

import hexagonwars.UpgradeAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GoldMine extends Gatherer {

    public GoldMine(int playerColor) {
        super(playerColor);
        this.startHealth = 350;
        this.health = this.startHealth;
        type = ENTITY_BUILDING_GATHERER_GOLDMINE;
    }

    @Override
    protected void addUIAfterFinish() {
        UpgradeAction action = new UpgradeAction();
        addUIElement("UPGRADE_GOLDMINE", action);
    }
}
