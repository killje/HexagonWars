package hexagonwars;

import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Tile implements Serializable {

    private Entity entity;
    private int entitiesAmount = 0;

    public Tile() {
    }

    public void addEntity(Entity Entity) {
        if (entity == null) {
            entity = Entity;
            entitiesAmount = 1;
        } else {
            entitiesAmount++;
        }
    }

    public void removeEntity(int amount) {
        entitiesAmount -= amount;
        
        if (entitiesAmount <= 0) {
            entity = null;
            amount = 0;
        }
    }

    public void removeAllEntities() {
        entity = null;
        entitiesAmount = 0;
    }

    public int isOccupied() {
        if (entitiesAmount <= 0) {
            return 0;
        } else {
            return entity.getType();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public Image getImage() {
        return HWImage.getImage(1, 1, HexagonWars.WORLD_TILE_WIDTH, HexagonWars.WORLD_TILE_HEIGHT_MAX, this.getClass().getSimpleName());
    }

    public Entity getEntity() {
        return entity;
    }

    public int getEntityAmount() {
        return entitiesAmount;
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
