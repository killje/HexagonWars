package hexagonwars;

import hexagonwars.entities.Unit;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Tile {

    ArrayList<Unit> units;

    public Tile() {
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(int amount) {
        for (int i = 0; i < amount; i++) {
            if(units.isEmpty()) break;
            
            units.remove(1);
        }
    }
    
    public int isOccupied() {
        if(this.units.isEmpty()){
            return 0;
        }else{
            return units.get(0).getType();
        }
    }
}
