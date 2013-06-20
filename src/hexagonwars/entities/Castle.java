/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.ProduceAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Castle extends Building {

    public Castle() {
        type = ENTITY_BUILDING_CASTLE;
        addUI();
    }

    private void addUI() {
        ProduceAction action = new ProduceAction(new Worker());
        addUIElement("PRODUCE_WORKER", action);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void upgrade(int upgrade) {
    }
}
