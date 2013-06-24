/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MoveAction extends UIAction {

    private int range;

    public MoveAction(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }
}
