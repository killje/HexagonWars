package hexagonwars;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Entity {
    
    protected int type = 0;
    protected int health;
    protected UserInterface ui = new UserInterface(getClass().getSimpleName());
    
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
    
    void drawUI(Graphics g, Rectangle rect) {
        int x = 0;
        int y = 0;
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.drawLine(rect.x + 199, rect.y, rect.x + 199, rect.y + rect.height);
        ArrayList<ImageWithAction> list = ui.getActions();
        for (int i = 0; i < list.size(); i++) {
            ImageWithAction imageWithAction = list.get(i);
            g.drawImage(imageWithAction.getIcon(), rect.x + 200 + x * UserInterface.ICON_WIDTH, rect.y + y * UserInterface.ICON_HEIGHT + 1, null);
            if (x < 5) {
                x++;
            } else {
                x = 0;
                y++;
            }
        }
    }
    
    void clicked(Point p) {
        int x, y;
        Point actionPoint = new Point();
        if (p.x >= 200 && p.y >= 1) {
            actionPoint.x = p.x - 200;
            actionPoint.y = p.y - 1;
            x = actionPoint.x / UserInterface.ICON_WIDTH;
            y = actionPoint.y / UserInterface.ICON_HEIGHT;
            int elementIndex = y * 6 + x;
                System.out.println(ui.getActions().size());
            if (ui.getActions().size() > elementIndex) {
                ArrayList<ImageWithAction> list = ui.getActions();
                UIAction action = list.get(elementIndex).getAction();
                if (action instanceof NewUIAction) {
                    NewUIAction newUIAction = (NewUIAction) action;
                    ui = newUIAction.getUI();
                    System.out.println("new ui");
                }else if (action instanceof BuildAction) {
                    BuildAction buildAction = (BuildAction) action;
                }else if (action instanceof DummyAction) {
                    DummyAction dummyAction = (DummyAction) action;
                }
            }
        }
    }
}
