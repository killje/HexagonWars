/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import hexagonwars.entities.Building;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BuildAction extends UIAction{

    Building building;
    
    public BuildAction(Building building){
        this.building = building;
    }
}
