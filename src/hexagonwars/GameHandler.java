package hexagonwars;

import hexagonwars.entities.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GameHandler {

    private int turn;
    private ArrayList<Player> players;
    private ArrayList<Building> buildingConstructions;

    public GameHandler() {
        turn = 0; /* player one */
    }

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    public void nextTurn() {
        turn++;
        if (turn >= players.size()) {
            turn = 0;
        }

        for (Building building : buildingConstructions) {
            if (building.isBeingBuilt()) {
                building.upgradeBuild();
            } else {
                building.finishBuild();
                buildingConstructions.remove(building);
            }
        }
    }

    public void move(Unit unit, int amount, Tile currentTile, Tile newTile) {
        currentTile.removeEntity(amount);
        for (int i = 0; i < amount; i++) {
            newTile.addEntity(unit);
        }
    }

    public void build(Building building) {
        getCurrentPlayer().addPlayerEntity(building);

        building.startBuild();
    }

    public void attack(Tile tileAttacker, Tile tileDefender) throws Exception {
        if (!(tileAttacker.getEntity() instanceof Unit)) {
            throw new Exception("The provided attacking entity was not of type Unit");
        } else if (!(tileDefender.getEntity() instanceof Building || (tileDefender.getEntity() instanceof Unit))) {
            throw new Exception("The provided defending entity was not of type Building or Unit");
        }

        Unit unitAttacker = (Unit) tileAttacker.getEntity();
        Entity entityDefender = tileDefender.getEntity();

        int defense = new Random().nextInt(entityDefender.getDefenseStrength());

        if (defense > (unitAttacker.getAttackDamage() / 2)) {
            // attack was successfully defended
        } else {
            if (entityDefender instanceof Building) {
                Building building = (Building) entityDefender;
                building.damage(unitAttacker.getAttackDamage());
            } else if (entityDefender instanceof Unit) {
                int remove = tileAttacker.getEntityAmount() * unitAttacker.getAttackDamage() - tileDefender.getEntityAmount() * entityDefender.getDefenseStrength();
                tileDefender.removeEntity(remove);
            }
        }
    }
}
