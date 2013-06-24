package hexagonwars.entities;

import hexagonwars.Tile;
import hexagonwars.WorldTiles;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Catapult extends Mechanic {

    public Catapult(int playerColor) {
        super(playerColor);
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.PLAIN));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.SHALLOWS));
    }
}
