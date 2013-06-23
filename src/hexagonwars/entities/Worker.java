package hexagonwars.entities;

import hexagonwars.NewUIAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Worker extends Unit {

    public Worker(int playerColor) {
        super(playerColor);
        type = ENTITY_UNIT_WORKER;
    }
}
