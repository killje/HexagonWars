/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.UpgradeAction;
import java.awt.Color;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WoodSaw extends Gatherer {

    public WoodSaw(Color playerColor){
        super(playerColor);
        addUI();
        type = ENTITY_BUILDING_GATHERER_SAWMILL;
    }

    private void addUI() {
        UpgradeAction action = new UpgradeAction();
        addUIElement("UPGRADE_SAWMILL", action);
    }
}
