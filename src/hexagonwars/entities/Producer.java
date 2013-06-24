package hexagonwars.entities;

import hexagonwars.MoveOutAction;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class Producer extends Building {

    protected MoveOutAction moveOut = new MoveOutAction(this, 3);
    protected ArrayList<Unit> units = new ArrayList<>();
    protected ArrayList<Unit> unitsNextTurn = new ArrayList<>();

    public Producer(int playerColor) {
        super(playerColor);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void upgrade(int upgrade) {
    }

    @Override
    protected void addUIAfterFinish() {
    }

    public void addUnitNextTurn(Unit unit) {
        units.add(unit);
    }

    public Unit getLastUnit() {
        return units.get(units.size() - 1);
    }

    public void removeLastUnit() {
        units.remove(units.size() - 1);
        if (units.isEmpty()) {
            ui.removeAction(moveOut);
        }
    }

    @Override
    public void nextTurn() {
        hasAction = true;
        units.addAll(unitsNextTurn);
        unitsNextTurn.clear();
        if (!units.isEmpty() && !ui.hasAction(moveOut)) {
            ui.addAction("Move", moveOut);
        }
    }
}
