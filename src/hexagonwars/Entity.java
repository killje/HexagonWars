package hexagonwars;

import java.awt.Graphics;
import java.awt.Image;
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
    
    void drawUI(Graphics g, Rectangle rect) {
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
        g.drawLine(rect.x + 200, rect.y, rect.x + 200, rect.y + rect.height);
        ArrayList<Image> list = ui.getIcons();
        int x = 0;
        int y = 0;
        for (int i = 0; i < list.size(); i++) {
            Image image = list.get(i);
            if (x < 6) {
                g.drawImage(image, rect.x+200+x*UserInterface.ICON_WIDTH, rect.y+y*UserInterface.ICON_HEIGHT, null);
                x++;
            }else{
                x=0;
                y++;
            }
        }
    }
}
