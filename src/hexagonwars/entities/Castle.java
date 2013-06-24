package hexagonwars.entities;

import hexagonwars.ProduceAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Castle extends Producer {

    public Castle(int playerColor) {
        super(playerColor);
        this.startHealth = 1000;
        this.health = this.startHealth;
        type = ENTITY_BUILDING_CASTLE;
    }

    @Override
    protected void addUIAfterFinish() {
        ProduceAction action = new ProduceAction(this, new Worker(playerColor));
        addUIElement("SmallWorker", action);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void upgrade(int upgrade) {
    }
}
