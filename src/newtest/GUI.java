/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;

/**
 *
 * @author Patrick
 */
public class GUI {

    static int moneyPlayer1 = 0;
    static int moneyPlayer2 = 0;

    public static String GetMoneyCount(int player) {
        Integer money;
        if (player == 1) {
            money = moneyPlayer1;
            return money.toString();
        } else {
            money = moneyPlayer2;
            return money.toString();
        }
    }

    public static String GetUnitsCount(int player) {
        Integer money;
        if (player == 1) {
            money = moneyPlayer1;
            return money.toString();
        } else {
            money = moneyPlayer2;
            return money.toString();
        }
    }
}
