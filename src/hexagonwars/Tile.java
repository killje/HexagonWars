package hexagonwars;

import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Tile {

    ArrayList<Entity> entities;

    public Tile() {
    }

    public void addEntity(Entity Entity) {
        entities.add(Entity);
    }

    public void removeUnit(int amount) {
        for (int i = 0; i < amount; i++) {
            if(entities.isEmpty()) break;
            
            entities.remove(1);
        }
    }
    
    public int isOccupied() {
        if(this.entities.isEmpty()){
            return 0;
        }else{
            return entities.get(0).getType();
        }
    }
}
