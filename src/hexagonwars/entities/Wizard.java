package hexagonwars.entities;

import java.awt.Color;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Wizard extends Infantry {

    public Wizard(Color playerColor) {
        super(playerColor);
        this.type = hexagonwars.HexagonWars.ENTITY_UNIT_MAGIC;
    }
}
