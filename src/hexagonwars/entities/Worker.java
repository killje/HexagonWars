package hexagonwars.entities;

import hexagonwars.BuildAction;
import hexagonwars.Tile;
import hexagonwars.WorldTiles;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Worker extends Unit {

    public Worker(int playerColor) {
        super(playerColor);
        type = ENTITY_UNIT_WORKER;
        this.startHealth = 50;
        this.health = this.startHealth;
        addUI();
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.PLAIN));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.MOUNTAIN));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.FOREST));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.SHALLOWS));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.GOLD));
    }

    private void addUI() {
        BuildAction buildBarracks = new BuildAction(new Barracks(playerColor));
        BuildAction goldMine = new BuildAction(new GoldMine(playerColor));
        BuildAction woodSaw = new BuildAction(new WoodSaw(playerColor));
        BuildAction wizardTower = new BuildAction(new WizardTower(playerColor));
        BuildAction workShop = new BuildAction(new WorkShop(playerColor));
        ui.addAction("SmallBarracks", buildBarracks);
        ui.addAction("SmallGoldMine", goldMine);
        ui.addAction("SmallWoodSaw", woodSaw);
        ui.addAction("SmallWizardTower", wizardTower);
        ui.addAction("SmallWorkShop", workShop);
    }
}
