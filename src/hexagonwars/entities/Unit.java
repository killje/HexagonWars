package hexagonwars.entities;

import hexagonwars.Entity;
import hexagonwars.MoveAction;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Unit extends Entity implements Serializable {

    protected int attackDamage;
    protected int attackRatio;

    public Unit(int playerColor) {
        super(playerColor);
        MoveAction move = new MoveAction(3);
        ui.addAction("Move", move);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackRatio() {
        return attackRatio;
    }
}
