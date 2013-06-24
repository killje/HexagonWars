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
    private int upgrade = 0;
    private UpgradeAction soldierUpgrade = new UpgradeAction(this, UPGRADE_MORE_SOLDIERS);
    private UpgradeAction archerUpgrade = new UpgradeAction(this, UPGRADE_ARCHERS);

    public Barracks(int playerColor) {
        super(playerColor);
    }

    @Override
    protected void addUIAfterFinish() {
        ProduceAction produceSoldier = new ProduceAction(this, new Soldier(playerColor));
        addUIElement("SmallSoldier", produceSoldier);
        addUIElement("SoldierUpgrade", soldierUpgrade);
        addUIElement("ArcherUpgrade", archerUpgrade);
    }

    @Override
    public void upgrade(int upgrade) {
        ui.removeAction(soldierUpgrade);
        ui.removeAction(archerUpgrade);
        ProduceAction produceArchers = new ProduceAction(this, new Archer(playerColor));
        addUIElement("SmallArcher", produceArchers);
    }

    @Override
    public void startBuild() {
        this.buildState = 2;
    }

    @Override
    public void nextTurn() {
        System.out.println(units);
        hasAction = true;
        if (upgrade == 1) {
            units.addAll(unitsNextTurn);
        }
        units.addAll(unitsNextTurn);
        unitsNextTurn.clear();
        if (!units.isEmpty() && !ui.hasAction(moveOut)) {
            ui.addAction("Move", moveOut);
        }
    }
}
