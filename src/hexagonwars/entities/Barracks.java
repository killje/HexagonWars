/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.Player;
import hexagonwars.ProduceAction;
import hexagonwars.UpgradeAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Barracks extends Producer {

    private static final int UPGRADE_MORE_SOLDIERS = 1;
    private static final int UPGRADE_ARCHERS = 2;
    UpgradeAction soldierUpgrade = new UpgradeAction(this, UPGRADE_MORE_SOLDIERS);
    UpgradeAction archerUpgrade = new UpgradeAction(this, UPGRADE_ARCHERS);

    public Barracks(Player team) {
        super(team);
        addUI();
    }

    private void addUI() {
        ProduceAction produceSoldier = new ProduceAction(new Soldier(team));
        addUIElement("PRODUCE_SOLDIER", produceSoldier);
        addUIElement("UPGRADE_MORE_SOLDIER", soldierUpgrade);
        addUIElement("UPGRADE_MAKE_ARCHER", archerUpgrade);
    }

    @Override
    public void upgrade(int upgrade) {
        ui.removeAction(soldierUpgrade);
        ui.removeAction(archerUpgrade);
        ProduceAction produceArchers = new ProduceAction(new Archer(team));
        addUIElement("PRODUCE_ARCHER", produceArchers);
    }
}