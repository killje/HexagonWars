/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import hexagonwars.entities.Building;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class UpgradeAction extends UIAction implements Serializable{

    public UpgradeAction(){
        // TODO code application logic here
    }
    
    public UpgradeAction(Building building) {
        building.upgrade();
    }

    public UpgradeAction(Building building, int upgrade) {
        building.upgrade(upgrade);
    }
}
