/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Color;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Player {
    
    private int recourceWood;
    private int recourceGold;
    private Color playerColor;
    

    public Player(){
        this(new Color(255,255,255));
    }
    
    public Player(Color color){
        playerColor = color;
    }
}
