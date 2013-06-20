/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Construction extends Building{

    Building building;
    int turnsLeft;
    int turnsStart;
    
    public Construction(Building building, int turns){
        type = ENTITY_BUILDING_CONSTRUCTION;
        this.building = building;
        turnsLeft = turns;
        turnsStart = turns;
    }
}
