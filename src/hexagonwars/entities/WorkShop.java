package hexagonwars.entities;

import hexagonwars.ProduceAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorkShop extends Producer {

    public WorkShop(int playerColor) {
        super(playerColor);
        this.startHealth = 350;
        this.health = this.startHealth;
    }

    @Override
    public void addUIAfterFinish() {
        ProduceAction produceCatapult = new ProduceAction(this, new Catapult(playerColor));
        ProduceAction produceBatteringRam = new ProduceAction(this, new BatteringRam(playerColor));
        addUIElement("SmallCatapult", produceCatapult);
        addUIElement("SmallBatteringRam", produceBatteringRam);

    }
}
