/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars.entities;

import hexagonwars.Player;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Producer extends Building{

    public Producer(Player team){
        super(team);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void upgrade(int upgrade) {
    }

    @Override
    public void turnUpdate(Player player) {
        throw new UnsupportedOperationException("Not supported yet. at: hexagonwars.entities.Producer:turnUpdate();");
    }
}
