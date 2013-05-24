/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newtest;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Patrick
 */
public class StartUp {

    static JFrame frame = new JFrame("Test");
    int pWidth;
    int pHeight;

    public void StartUp(int width, int height) {
        pWidth = width;
        pHeight = height;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(51 + width * 70, 93 + height * 60);
        frame.setLocationRelativeTo(null);
        new GenerateWorld().GenerateWorld(width, height);
        frame.add(new Paint());
        frame.setVisible(true);
        frame.addMouseListener(new StartUp.TimingMouseAdapter());
    }
    boolean toggleMoves = true;
    JPanel paintMove;
    private class TimingMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e){
            int frameX = frame.getX();
            int frameY = frame.getY();
            int mouseLocationX = MouseInfo.getPointerInfo().getLocation().x - frameX - 8;
            int mouseLocationY = MouseInfo.getPointerInfo().getLocation().y - frameY - 30;
            System.out.println(GetMouse.GetMouse(mouseLocationX, mouseLocationY));
            paintMove=new PaintMoves(GetMouse.GetMouse(mouseLocationX, mouseLocationY));
            if (toggleMoves){
                frame.add(paintMove);
                paintMove.repaint();
                toggleMoves = false;
            }else{
                frame.remove(paintMove);
                paintMove.repaint();
                toggleMoves = true;
            }
            /*int x = GetMouse.GetMouse(mouseLocationX, mouseLocationY).x;
            int y = GetMouse.GetMouse(mouseLocationX, mouseLocationY).y;
            Tile[][] world = GenerateWorld.GetWorld();
            Point[] point;
            int i;
            point = world[x][y].getNormalConnections();
            for (i = 0; i < 6; i++) {
                System.out.println(point[i].x + ":" + point[i].y);
            }*/
        }
    }

    public static int getFrameWidth() {
        return frame.getWidth();
    }

    public static int getFrameHeigth() {
        return frame.getHeight();
    }
}
