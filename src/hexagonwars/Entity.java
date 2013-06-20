package hexagonwars;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public abstract class Entity {

    protected int type = 0;
    protected int health;
    protected UserInterface ui = new UserInterface(getClass().getSimpleName());
    
    public void damage(int damage) {
        health -= damage;
        ui.setHealth(health);
    }

    public int getType() {
        return this.type;
    }

    public int getHealth() {
        return this.health;
    }
}
