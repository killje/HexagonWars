package hexagonwars;

import hexagonwars.entities.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Game {

    private int turn;
    private ArrayList<Building> buildingConstructions;
    
    public Game() {
        turn = 1; /* player one */
    }

    private int whosTurn() {
        return turn % 2;
    }

    private void switchSide() {
        turn++;

        for (Building building : buildingConstructions) {
            if (building.isBeingBuilt()) {
                building.upgradeBuild();
            } else {
                buildingConstructions.remove(building);
            }
        }
    }

    private void build(Building building) {
        Timer timer = new Timer();

        building.startBuild();

        switchSide();
    }

    private void attack(Tile tileAttacker, Tile tileDefender) throws Exception {
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
            } else if(entityDefender instanceof Unit) {
                int remove = tileAttacker.getEntityAmount() * unitAttacker.getAttackDamage() - tileDefender.getEntityAmount() * entityDefender.getDefenseStrength();
                tileDefender.removeEntity(remove);
            }
        }

        switchSide();
    }
}
