package hexagonwars;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Tile implements Serializable {

    ArrayList<Entity> entities = new ArrayList<>();

    public Tile() {
    }

    public void addEntity(Entity Entity) {
        entities.add(Entity);
    }

    public void removeEntity(int amount) {
        for (int i = 0; i < amount; i++) {
            if (entities.isEmpty()) {
                break;
            }
            entities.remove(1);
        }
    }

    public int isOccupied() {
        if (this.entities.isEmpty()) {
            return 0;
        } else {
            return entities.get(0).getType();
        }
    }
    
    public Entity getFirstEntity(){
        return entities.get(0);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Image getImage() {
        return HWImage.getImage(1, 1, HexagonWars.WORLD_TILE_WIDTH, HexagonWars.WORLD_TILE_HEIGHT_MAX, this.getClass().getSimpleName());
    }

    public static Tile getType(int type) {
        Tile tile;
        switch (type) {
            case World.PLAIN:
                tile = new hexagonwars.tiles.Plain();
                break;
            case World.MOUNTAIN:
                tile = new hexagonwars.tiles.Mountain();
                break;
            case World.WATER:
                tile = new hexagonwars.tiles.Water();
                break;
            case World.GOLD:
                tile = new hexagonwars.tiles.Gold();
                break;
            default:
                tile = new hexagonwars.tiles.Plain();
                break;
        }
        return tile;
    }
}
