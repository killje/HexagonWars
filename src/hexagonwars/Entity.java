package hexagonwars;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Entity implements Serializable{

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
    protected int health;
    protected EnityUI ui = new EnityUI(getClass().getSimpleName());
    protected Player team;

    public Entity(Player team){
        this.team = team;
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

    protected void addUIElement(String actionName, UIAction action) {
        ui.addAction(actionName, action);
    }

    public void drawUI(Graphics g, Rectangle rect) {
        int x = 0;
        int y = 0;
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.drawLine(rect.x + 199, rect.y, rect.x + 199, rect.y + rect.height);
        ArrayList<ImageWithAction> list = ui.getActions();
        for (int i = 0; i < list.size(); i++) {
            ImageWithAction imageWithAction = list.get(i);
            g.drawImage(imageWithAction.getIcon(), rect.x + 200 + x * EnityUI.ICON_WIDTH, rect.y + y * EnityUI.ICON_HEIGHT + 1, null);
            if (x < 5) {
                x++;
            } else {
                x = 0;
                y++;
            }
        }
    }

    public void clicked(Point p, Tile tile) {
        int x, y;
        Point actionPoint = new Point();
        if (p.x >= 200 && p.y >= 1) {
            actionPoint.x = p.x - 200;
            actionPoint.y = p.y - 1;
            x = actionPoint.x / EnityUI.ICON_WIDTH;
            y = actionPoint.y / EnityUI.ICON_HEIGHT;
            int elementIndex = y * 6 + x;
            if (ui.getActions().size() > elementIndex) {
                ArrayList<ImageWithAction> list = ui.getActions();
                UIAction action = list.get(elementIndex).getAction();
                if (action instanceof NewUIAction) {
                    NewUIAction newUIAction = (NewUIAction) action;
                    ui = newUIAction.getUI();
                } else if (action instanceof BuildAction) {
                    BuildAction buildAction = (BuildAction) action;
                    tile.removeAllEntities();
                    tile.addEntity(buildAction.getBuilding());
                } else if (action instanceof DummyAction) {
                    DummyAction dummyAction = (DummyAction) action;
                }
            }
        }
    }
}
