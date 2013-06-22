package hexagonwars.entities;

import hexagonwars.Player;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Mechanic extends Unit {

    public Mechanic(Player team) {
        super(team);
        this.type = hexagonwars.HexagonWars.ENTITY_UNIT_MECHANIC;
    }
}
