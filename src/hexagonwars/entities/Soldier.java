package hexagonwars.entities;

import hexagonwars.Tile;
import hexagonwars.WorldTiles;
import java.awt.Color;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Soldier extends Infantry {

    public Soldier(int playerColor) {
        super(playerColor);
        this.startHealth = 80;
        this.health = this.startHealth;
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.PLAIN));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.FOREST));
        this.possibleTiles.add(Tile.getTileFromType(WorldTiles.SHALLOWS));
    }
}
