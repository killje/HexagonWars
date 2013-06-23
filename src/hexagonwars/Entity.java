package hexagonwars;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Entity implements Serializable {

    //UNITS
    public static final int ENTITY_UNIT_WORKER = 1;
    public static final int ENTITY_UNIT_INFANTRY_SOLDIER = 2;
    public static final int ENTITY_UNIT_INFANTRY_ARCHER = 3;
    public static final int ENTITY_UNIT_INFANTRY_WIZARD = 4;
    public static final int ENTITY_UNIT_HORSEMAN = 5;
    public static final int ENTITY_UNIT_MECHANIC_CATAPULT = 6;
    public static final int ENTITY_UNIT_MECHANIC_BATTERINGRAM = 7;
    // BUILDINGS
    public static final int ENTITY_BUILDING_CASTLE = 101;
    public static final int ENTITY_BUILDING_GATHERER_SAWMILL = 121;
    public static final int ENTITY_BUILDING_GATHERER_GOLDMINE = 122;
    public static final int ENTITY_BUILDING_PRODUCER_BARRACKS = 151;
    public static final int ENTITY_BUILDING_PRODUCER_STABLE = 152;
    public static final int ENTITY_BUILDING_PRODUCER_WIZARDTOWER = 153;
    public static final int ENTITY_BUILDING_PRODUCER_WORKSHOP = 154;
    //VARIABLES
    protected int type = 0;
    protected int defenseStrength;
    protected int health;
    protected EntityUI ui;
    protected int playerColor;

    public Entity(int playerColor) {
        this.playerColor = playerColor;
        ui = new EntityUI(getClass().getSimpleName(), playerColor);
    }

    public void damage(int damage) {
        health -= damage;
        ui.setHealth(health);
    }

    public int getType() {
        return this.type;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDefenseStrength() {
        return defenseStrength;
    }

    protected void addUIElement(String actionName, UIAction action) {
        ui.addAction(actionName, action);
    }

    public EntityUI getEntityUI() {
        return ui;
    }

    public void setEntityUI(EntityUI ui) {
        this.ui = ui;
    }

    public Image getImage() {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(new Color(55, 171, 200).getRGB());
        return HWImage.getImage("Medium" + this.getClass().getSimpleName(), colors, playerColor);
    }
}
