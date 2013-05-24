/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;

import java.awt.Point;
/**
 *
 * @author Patrick
 */

public class Units {
    public int health;
    public int deafaultDamage;
    public int bigDamage;
    public int smallDamage;
    public int posX,posY;
    public int ID;
    
    public Units() {
    }
    public void setUnits(int x,int y){
        posX=x;
        posY=y;
    }

    public void setHealt(int newHealth) {
        health = newHealth;
    }

    public int getHealt() {
        return health;
    }

    public int getNormalDamage() {
        return deafaultDamage;
    }

    public int getBigDamage() {
        return bigDamage;
    }

    public int getSmallDamage() {
        return smallDamage;
    }
    public Point getXY(){
        Point point = new Point(posX,posY);
        return point;
    }
    public int getID(){
        return ID;
    }
}
