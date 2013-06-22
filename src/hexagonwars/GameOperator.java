/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GameOperator extends Observable {

    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public GameOperator(Player player) {
        players.add(player);
        currentPlayer = player;
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
        this.setChanged();
        this.notifyObservers();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
