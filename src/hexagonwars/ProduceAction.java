/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import hexagonwars.entities.Unit;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ProduceAction extends UIAction{

    Unit unit;
    
    public ProduceAction(Unit unit){
        this.unit = unit;
    }
}
