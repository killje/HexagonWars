/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.UpgradeAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GoldMine extends Gatherer {

    public GoldMine() {
        type = ENTITY_BUILDING_GATHERER_GOLDMINE;
        addUI();
    }

    private void addUI() {
        UpgradeAction action = new UpgradeAction();
        addUIElement("UPGRADE_GOLDMINE", action);
    }
}
