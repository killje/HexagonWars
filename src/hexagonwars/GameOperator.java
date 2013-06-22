/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GameOperator {

    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private WorldPanel gameWorld;

    public GameOperator(WorldPanel gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void nextTurn() {
        int currentPlayerIndex = players.indexOf(currentPlayer);
        if (currentPlayerIndex == players.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }
        currentPlayer = players.get(currentPlayerIndex);
        gameWorld.nextTurn(currentPlayer);
    }
}
