package hexagonwars.entities;

import hexagonwars.Entity;
import hexagonwars.Player;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Building extends Entity implements Serializable{

    protected Unit spawnUnit;
    protected int spawnAmount;
    protected int spawnInterval;
    
    public Building(Player team){
        super(team);
    }
    
    public abstract void upgrade();
    public abstract void upgrade(int upgrade);
    public abstract void turnUpdate(Player player);
}
