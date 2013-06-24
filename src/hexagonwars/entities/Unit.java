package hexagonwars.entities;

import hexagonwars.Entity;
import hexagonwars.MoveAction;
import hexagonwars.Tile;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Unit extends Entity implements Serializable {

    protected int attackDamage;
    protected int attackRatio;
    protected ArrayList<Tile> possibleTiles;

    public Unit(int playerColor) {
        super(playerColor);
        this.possibleTiles = new ArrayList<>();
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
