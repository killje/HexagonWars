package hexagonwars.entities;

import hexagonwars.ProduceAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Castle extends Building {

    public Castle(int playerColor) {
        super(playerColor);
        type = ENTITY_BUILDING_CASTLE;
    }

    @Override
    protected void addUIAfterFinish() {
        ProduceAction action = new ProduceAction(new Worker(playerColor));
        addUIElement("PRODUCE_WORKER", action);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void upgrade(int upgrade) {
    }
}
