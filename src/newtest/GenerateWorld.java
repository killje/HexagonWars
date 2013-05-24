/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;

import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Patrick
 */
public class GenerateWorld {

    JFrame frame = new JFrame("GridLayout Test");
    static Tile[][] world;
    static int width, height;
    static Player[] players=new Player[2];
    public void GenerateWorld(int localWidth, int localHeight) {
        int i, j;
        world = new Tile[5][5];
        width = localWidth;
        height = localHeight;
        for (j = 0; j < height; j++) {
            for (i = 0; i < width; i++) {
                Random rand = new Random();
                world[i][j] = new Tile(rand.nextInt(4));
            }
        }
        setWorldConnections();
        players[0] = new Player();
        players[1] = new Player();
    }

    private void setWorldConnections() {
        int i, j;
        for (j = 0; j < height; j++) {
            for (i = 0; i < width; i++) {
                setPoint(i, j, i, j - 1, 0);
                setPoint(i, j, i, j + 1, 1);
                setPoint(i, j, i - 1, j, 2);
                setPoint(i, j, i + 1, j, 3);
                if (j % 2 == 0) {
                    setPoint(i, j, i - 1, j - 1, 4);
                    setPoint(i, j, i - 1, j + 1, 5);
                } else {
                    setPoint(i, j, i + 1, j - 1, 4);
                    setPoint(i, j, i + 1, j + 1, 5);
                }
            }
        }
    }

    private void setPoint(int x, int y, int i, int j, int ID) {
        Point point = new Point(i, j);
        Point pointNegative = new Point(-1, -1);
        if (i >= 0 && j >= 0 && i < width && j < height) {
            if(world[i][j].getID()==3){
                world[x][y].setNormalConnections(ID, pointNegative);
            }else{
                world[x][y].setNormalConnections(ID, point);
            }
            world[x][y].setDificultConnections(ID, point);
        }else{
            world[x][y].setNormalConnections(ID, pointNegative);
            world[x][y].setDificultConnections(ID, pointNegative);
        }
    }

    public static Tile[][] GetWorld() {
        return world;
    }

    public static int GetWorldWidth() {
        return width;
    }

    public static int GetWorldHeight() {
        return height;
    }
    public static Player[] getPlayers(){
        return players;
    }
}
