/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GameUI {

    private WorldPanel gameWorld;
    
    public GameUI(WorldPanel gameWorld) {
        this.gameWorld = gameWorld;
    }

    void drawUI(Graphics g, Rectangle uiRect) {
        g.drawRect(uiRect.x, uiRect.y, uiRect.width, uiRect.height);
        g.drawLine(uiRect.x +51, uiRect.y, uiRect.x +51, uiRect.width+uiRect.y);
        g.drawImage(HWImage.getImage(1, 1, 50, 50, "nextTurn"), uiRect.x+1, uiRect.y+1, null);
        g.drawImage(HWImage.getImage(1, 1, 50, 50, "exitButton"), uiRect.x+1, uiRect.y+51, null);
        g.drawImage(HWImage.getImage(1, 1, 50, 50, "saveButton"), uiRect.x+1, uiRect.y+101, null);
        g.drawImage(HWImage.getImage(1, 1, 50, 50, "loadButton"), uiRect.x+1, uiRect.y+151, null);
    }

    void clicked(Point p) {
        Rectangle nextTurn = new Rectangle(1,1,50,50);
        Rectangle exitButton = new Rectangle(51,51,100,100);
        Rectangle saveButton = new Rectangle(101,101,150,150);
        Rectangle loadButton = new Rectangle(151,151,200,200);
        if (exitButton.contains(p)) {
            System.exit(0);
        }else if(saveButton.contains(p)){
            
        }else if(loadButton.contains(p)){
            
        }else if(nextTurn.contains(p)){
            gameWorld.getGameOperator().nextTurn();
        }
        System.out.println(p);
    }
}
