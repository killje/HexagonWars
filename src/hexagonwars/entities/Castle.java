/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.Player;
import hexagonwars.ProduceAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Castle extends Building {

    public Castle(Player team) {
        super(team);
        type = ENTITY_BUILDING_CASTLE;
        addUI();
    }

    private void addUI() {
        ProduceAction action = new ProduceAction(new Worker(team));
        addUIElement("PRODUCE_WORKER", action);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void upgrade(int upgrade) {
    }

    @Override
    public void turnUpdate(Player player) {
        throw new UnsupportedOperationException("Not supported yet. at: hexagonwars.entities.Castle:turnUpdate();");
    }
}
