package hexagonwars.entities;

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

    public Barracks(int playerColor) {
        super(playerColor);
    }

    @Override
    protected void addUIAfterFinish() {
        ProduceAction produceSoldier = new ProduceAction(new Soldier(playerColor));
        addUIElement("SmallSoldier", produceSoldier);
        addUIElement("SoldierUpgrade", soldierUpgrade);
        addUIElement("ArcherUpgrade", archerUpgrade);
    }

    @Override
    public void upgrade(int upgrade) {
        ui.removeAction(soldierUpgrade);
        ui.removeAction(archerUpgrade);
        ProduceAction produceArchers = new ProduceAction(new Archer(playerColor));
        addUIElement("SmallArcher", produceArchers);
    }
    
    @Override
    public void startBuild() {
        this.buildState = 2;
    }
}
