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

    /**
     * empty constructor
     */
    public Tile() {
    }

    /**
     * adds an entity
     * @param Entity adds an entity to the tile instance when it was previously
     * empty. In case there were already entities on the tile, it just raises
     * the entity counter.
     */
    public void addEntity(Entity Entity) {
        if (entity == null) {
            entity = Entity;
            entitiesAmount = 1;
        } else {
            entitiesAmount++;
        }
    }

    /**
     * removes a certain amount of entities.
     * @param amount the amount of entities to be removed.
     */
    public void removeEntity(int amount) {
        entitiesAmount -= amount;

        if (entitiesAmount <= 0) {
            entity = null;
            amount = 0;
        }
    }

    /**
     * removes all entities
     */
    public void removeAllEntities() {
        entity = null;
        entitiesAmount = 0;
    }

    /**
     *
     * @return whether the tile is occupied
     */
    public boolean isOccupied() {
        if (entitiesAmount > 0) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     *
     * @return
     */
    public Image getImage() {
        return HWImage.getImageWithDefaultTransparency(this.getClass().getSimpleName());
    }

    /**
     *
     * @return the current entity type on the tile
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     *
     * @return the amount of entities on the tile
     */
    public int getEntityAmount() {
        return entitiesAmount;
    }

    /**
     *
     * @param type the type to be used for the new instance
     * @return a new instance of type type
     */
    public static Tile getType(int type) {
        Tile tile;
        switch (type) {
            case WorldTiles.PLAIN:
                tile = new hexagonwars.tiles.Plain();
                break;
            case WorldTiles.MOUNTAIN:
                tile = new hexagonwars.tiles.Mountain();
                break;
            case WorldTiles.WATER:
                tile = new hexagonwars.tiles.Water();
                break;
            case WorldTiles.GOLD:
                tile = new hexagonwars.tiles.Gold();
                break;
            case WorldTiles.SHALLOWS:
                tile = new hexagonwars.tiles.Shallows();
                break;
            case WorldTiles.FOREST:
                tile = new hexagonwars.tiles.Forest();
                break;
            default:
                tile = new hexagonwars.tiles.Plain();
                break;
        }
        return tile;
    }
}
