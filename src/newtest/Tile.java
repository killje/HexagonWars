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
public class Tile {

    int blockID;
    Point[] normalConnections = new Point[7];
    Point[] dificultConnections = new Point[7];
    boolean highLighted = false;
    int highLightColor=0;
    public Tile(int ID) {
        blockID = ID;
    }

    public void setID(int ID) {
        blockID = ID;
    }
    
    public int getID() {
        return blockID;
    }

    public void setNormalConnections(int connectionNumber, Point xy) {
        normalConnections[connectionNumber] = xy;
    }

    public Point[] getNormalConnections() {
        return normalConnections;
    }

    public void setDificultConnections(int connectionNumber, Point xy) {
        dificultConnections[connectionNumber] = xy;
    }

    public Point[] getDificultConnections() {
        return dificultConnections;
    }
    public void highLight(int color){
        highLighted=true;
        highLightColor=color;
    }
    public void deHighLight(){
        highLighted=false;
    }
    public boolean isHighLighted(){
        return highLighted;
    }
}
