package hexagonwars;

import hexagonwars.entities.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GameHandler implements Serializable {

    private int turn;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Building> buildingConstructions = new ArrayList<>();

    /**
     * Initialization of a Game instance.
     */
    public GameHandler() {
        turn = 0; /* player one */
    }

    /**
     * Adds a player to the game.
     * @param player the Player instance to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns the player for the current turn.
     * @return the current player on turn
     */
    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    /**
     * Returns the name for a specific player.
     * @param player the player instance
     * @return the name
     */
    public String getPlayerName(Player player) {
        return player.getName();
    }

    /**
     * Raises the turn counter by one (or goes back to zero), handles the building
     * processes and updates all entities - allowing them to do finish their tasks.
     */
    public void nextTurn() {
        turn++;
        if (turn >= players.size()) {
            turn = 0;
        }
        ArrayList<Building> buildings = new ArrayList(buildingConstructions);
        for (Building building : buildings) {
            if (getCurrentPlayer().getPlayerEntities().contains(building)) {
                if (building.isBeingBuilt()) {
                    building.upgradeBuild();
                } else {
                    building.finishBuild();
                    System.out.println("test");
                    buildingConstructions.remove(building);
                }
            }
        }
        for (Entity entity : getCurrentPlayer().getPlayerEntities()) {
            entity.nextTurn();
        }
    }

    /**
     * Moves a unit from one tile to another.
     * @param entity the entity to be moved.
     * @param currentTile the current tile.
     * @param newTile the new tile.
     */
    public void moveUnit(Entity entity, Tile currentTile, Tile newTile) {
        currentTile.removeAllEntities();
        newTile.addEntity(entity);
    }

    /**
     * Gets a newly spawned entity out of a building.
     * @param entity the entity to be moved.
     * @param currentTile the current tile.
     * @param newTile the new tile.
     */
    public void moveFromBuilding(Entity entity, Tile currentTile, Tile newTile) {
        Producer producer = (Producer) entity;
        Unit unit = producer.getLastUnit();
        getCurrentPlayer().addPlayerEntity(unit);
        newTile.addEntity(unit);
        producer.removeLastUnit();
    }

    /**
     * Starts the building process of a newly created building.
     * @param building the building to be built.
     */
    public void build(Building building) {
        buildingConstructions.add(building);
        getCurrentPlayer().addPlayerEntity(building);

        building.startBuild();
    }

    /**
     * A possible implementation of an attack method.
     * @param tileAttacker the tile where the attacker stood.
     * @param tileDefender the tile where the defender stands.
     * @throws Exception if the entity types did not meet any requirements.
     */
    public void attack(Tile tileAttacker, Tile tileDefender) throws Exception {
        if (!(tileAttacker.getEntity() instanceof Unit)) {
            throw new Exception("The provided attacking entity was not of type Unit");
        } else if (!(tileDefender.getEntity() instanceof Building || (tileDefender.getEntity() instanceof Unit))) {
            throw new Exception("The provided defending entity was not of type Building or Unit");
        }

        Unit unitAttacker = (Unit) tileAttacker.getEntity();
        Entity entityDefender = tileDefender.getEntity();

        int defense = new Random().nextInt(entityDefender.getDefenseStrength());

        if (defense > (unitAttacker.getAttackDamage() / 2)) {
            // attack was successfully defended
        } else {
            if (entityDefender instanceof Building) {
                Building building = (Building) entityDefender;
                building.damage(unitAttacker.getAttackDamage());
            } else if (entityDefender instanceof Unit) {
                int remove = tileAttacker.getEntityAmount() * unitAttacker.getAttackDamage() - tileDefender.getEntityAmount() * entityDefender.getDefenseStrength();
                tileDefender.removeEntity(remove);
            }
        }
    }
}
