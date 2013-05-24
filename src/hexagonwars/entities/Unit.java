package hexagonwars.entities;

import hexagonwars.Entity;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Unit extends Entity {
    
    public int getType() {
        if (this instanceof Infantry) {
            return hexagonwars.HexagonWars.ENTITY_UNIT_INFANTRY;
        } else if (this instanceof Mechanic) {
            return hexagonwars.HexagonWars.ENTITY_UNIT_MECHANIC;
        } else if (this instanceof Magic) {
            return hexagonwars.HexagonWars.ENTITY_UNIT_MAGIC;
        }
        
        return 0;
    }
}
