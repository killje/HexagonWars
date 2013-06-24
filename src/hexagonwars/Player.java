package hexagonwars;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Player implements Serializable{

    private int resourceGold;
    private int resourceForest;
    private int color;
    private String name;
    private ArrayList<Entity> playerEntities = new ArrayList<>();
    
    /**
     * @return the amount of gold
     */
    public int getResourceGold() {
        return resourceGold;
    }

    /**
     * @param resourceGold the amount of gold to add
     */
    public void addResourceGold(int resourceGold) {
        this.resourceGold += resourceGold;
    }

    /**
     * @param resourceGold the amount of gold to remove
     */
    public void removeResourceGold(int resourceGold) {
        this.resourceGold -= resourceGold;
    }

    /**
     * @param resourceGold the amount of gold to set
     */
    public void setResourceGold(int resourceGold) {
        this.resourceGold = resourceGold;
    }

    /**
     * @return the amount of forest
     */
    public int getResourceForest() {
        return resourceForest;
    }

    /**
     * @param resourceForest the amount of forest to add
     */
    public void addResourceForest(int resourceForest) {
        this.resourceForest += resourceForest;
    }

    /**
     * @param resourceForest the amount of forest to remove
     */
    public void removeResourceForest(int resourceForest) {
        this.resourceForest -= resourceForest;
    }

    /**
     * @param resourceForest the amount of forest to set
     */
    public void setResourceForest(int resourceForest) {
        this.resourceForest = resourceForest;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @return the all the entities for a player
     */
    public ArrayList<Entity> getPlayerEntities() {
        return playerEntities;
    }

    /**
     * @return the all the entities for a player
     */
    public void addPlayerEntity(Entity entity) {
        playerEntities.add(entity);
    }

    /**
     * @return the all the entities for a player
     */
    public void removePlayerEntity(Entity entity) {
        playerEntities.remove(entity);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
