package hexagonwars;

import hexagonwars.entities.Producer;
import hexagonwars.entities.Unit;
import java.io.Serializable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ProduceAction extends UIAction implements Serializable {

    private Unit unit;
    private Producer building;

    public ProduceAction(Producer building, Unit unit) {
        this.unit = unit;
        this.building = building;
    }

    public Unit getUnit() {
        return unit;
    }

    public Producer getProducer() {
        return building;
    }
}
