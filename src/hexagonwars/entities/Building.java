package hexagonwars.entities;

import hexagonwars.Entity;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Building extends Entity {

    protected boolean enableSpawn;
    protected int buildState;
    protected Unit spawnUnit;
    protected int spawnAmount;
    protected int spawnInterval;
    
    public void disableSpawn() {
        this.enableSpawn = false;
    }
    
    public void enableSpawn() {
        this.enableSpawn = true;
    }
    
    public void startBuild() {
        this.buildState = 1;
    }
    
    public void upgradeBuild() {
        this.buildState++;
    }
    
    public void finishBuild() {
        this.buildState = -1;
        this.enableSpawn();
    }
    
    public boolean isBeingBuilt() {
        return this.buildState > 0;
    }
}
