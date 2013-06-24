package hexagonwars.entities;

import hexagonwars.Entity;
import hexagonwars.HWImage;
import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Building extends Entity implements Serializable {

    protected boolean enableSpawn;
    protected int buildState;
    protected Unit spawnUnit;
    protected int spawnAmount;
    protected int spawnInterval;

    public Building(int playerColor) {
        super(playerColor);
    }

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
        this.buildState--;
    }

    public void finishBuild() {
        this.buildState = -1;
        this.enableSpawn();
        this.addUIAfterFinish();
    }
    
    protected abstract void addUIAfterFinish();
    
    public boolean isBeingBuilt() {
        return this.buildState > 0;
    }
    
    public abstract void upgrade();

    public abstract void upgrade(int upgrade);
    
    @Override
    public Image getImage() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(new Color(55, 171, 200).getRGB());
        if (enableSpawn) {
            return HWImage.getImage("Medium" + this.getClass().getSimpleName(), colors, playerColor);
        }else{
            return HWImage.getImage("Construction", colors, playerColor);
        }
        
    }
}
