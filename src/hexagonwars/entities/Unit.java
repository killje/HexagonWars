package hexagonwars.entities;

import hexagonwars.Entity;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Unit extends Entity implements Serializable{

    protected int attackDamage;
    protected int attackRatio;

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackRatio() {
        return attackRatio;
    }
}
