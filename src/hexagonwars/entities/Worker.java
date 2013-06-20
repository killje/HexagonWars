/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.DummyAction;
import hexagonwars.NewUIAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Worker extends Unit{

    public Worker() {
        type = ENTITY_UNIT_WORKER;
        addUI();
    }

    private void addUI() {
        NewUIAction action = new NewUIAction(ui.getName());
        action.addIcon("test2",new DummyAction());
        addUIElement("test",action);
    }
}
