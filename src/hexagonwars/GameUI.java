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

    public void drawUI(Graphics g, Rectangle uiRect) {
        g.drawRect(uiRect.x, uiRect.y, uiRect.width, uiRect.height);
        g.drawLine(uiRect.x +51, uiRect.y, uiRect.x +51, uiRect.width+uiRect.y);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("nextTurn"), uiRect.x+1, uiRect.y+1, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("exitButton"), uiRect.x+1, uiRect.y+51, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("saveButton"), uiRect.x+1, uiRect.y+101, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("loadButton"), uiRect.x+1, uiRect.y+151, null);
    }

    public void clicked(Point p) {
        Rectangle nextTurn = new Rectangle(1,1,50,50);
        Rectangle exitButton = new Rectangle(51,51,100,100);
        Rectangle saveButton = new Rectangle(101,101,150,150);
        Rectangle loadButton = new Rectangle(151,151,200,200);
        if (exitButton.contains(p)) {
            System.exit(0);
        }else if(saveButton.contains(p)){
            
        }else if(loadButton.contains(p)){
            
        }else if(nextTurn.contains(p)){
            gameWorld.getWorldModel().getGameHandler().nextTurn();
        }
        System.out.println(p);
    }
}