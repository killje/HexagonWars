/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import hexagonwars.entities.Producer;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MoveOutAction extends UIAction {

    private Producer producer;
    private int range;

    public MoveOutAction(Producer producer, int range) {
        this.range = range;
        this.producer = producer;
    }

    public Producer getProducer() {
        return producer;
    }

    public int getRange() {
        return range;
    }
}
