package hexagonwars;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Tile implements Serializable{

    ArrayList<Entity> entities;

    public Tile() {
    }

    public void addEntity(Entity Entity) {
        entities.add(Entity);
    }

    public void removeEntity(int amount) {
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
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
