package hexagonwars.entities;

import hexagonwars.Tile;
import hexagonwars.WorldTiles;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Archer extends Infantry {

    public Archer(int playerColor) {
        super(playerColor);
        this.startHealth = 40;
        this.health = this.startHealth;
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.PLAIN));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.MOUNTAIN));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.FOREST));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.SHALLOWS));
    }
}
