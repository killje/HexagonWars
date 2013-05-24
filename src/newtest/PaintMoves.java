package newtest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

public class PaintMoves extends JPanel {
    int PaintX,PaintY;
    public PaintMoves(Point point) {
        PaintX =point.x;
        PaintY=point.y;
    }
    boolean showMoves = false;
   
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point point = new Point(PaintX, PaintY);
        drawMoves(g2d, true, 2, point);
//        if (showMoves){
//            drawMoves(g2d);
//        }
//        drawUnits(g2d);
    }

//    private void drawUnits(Graphics2D g2d){
//     Player playerWithUnits = GenerateWorld.getPlayer();
//     Units[] unitsToDraw = playerWithUnits.unitList();
//     int arrayLenght = unitsToDraw.length;
//     int i;
//     for(i=0;i<arrayLenght;i++){
//     Units unit = unitsToDraw[i];
//     System.out.println(unit.getBigDamage());
//     g2d.drawImage(TransformIdToImage(unit.getID()), unit.getXY().x * 70 + unit.getXY().y % 2 * 35, unit.getXY().y* 60 + 35, null);
//     }
//     }

    private void drawMoves(Graphics2D g2d, boolean easy, int moves, Point xy) {
        Tile[][] r = GenerateWorld.GetWorld();
        Tile thisTile = r[xy.x][xy.y];
        highLightTiles(easy, thisTile, moves);
        int i, j;
        for (i = 0; i < GenerateWorld.GetWorldWidth(); i++) {
            for (j = 0; j < GenerateWorld.GetWorldHeight(); j++) {
                if (r[i][j].isHighLighted()) {
                    drawHexagon(g2d, i, j);
                }
            }
        }
    }

    private void highLightTiles(boolean easy, Tile thisTile, int moves) {
        thisTile.highLight(1);
        if (moves > 0) {
            Point[] otherTiles;
            if (easy) {
                otherTiles = thisTile.getNormalConnections();
            } else {
                otherTiles = thisTile.getDificultConnections();
            }
            int i;
            for (i = 0; i < 6; i++) {
                if (otherTiles[i].x >= 0
                        && otherTiles[i].x < GenerateWorld.GetWorldWidth()
                        && otherTiles[i].y >= 0
                        && otherTiles[i].y < GenerateWorld.GetWorldHeight()) {
                    Tile[][] r = GenerateWorld.GetWorld();
                    Tile newTile = r[otherTiles[i].x][otherTiles[i].y];
                    highLightTiles(easy, newTile, moves - 1);
                }
            }
        }
    }

    private void drawHexagon(Graphics2D g2d, int x, int y) {
        int xPoint = x * 70 + 35 * (y % 2);
        int yPoint = y * 60;
        drawLine(xPoint, yPoint + 55, xPoint + 34, yPoint + 35, g2d, 1);
        drawLine(xPoint + 35, yPoint + 35, xPoint + 69, yPoint + 55, g2d, 2);
        drawLine(xPoint, yPoint + 94, xPoint + 34, yPoint + 114, g2d, 3);
        drawLine(xPoint + 35, yPoint + 114, xPoint + 69, yPoint + 94, g2d, 4);
    }

    private void drawLine(int x0, int y0, int x1, int y1, Graphics2D g2d, int corner) {
        Color color = new Color(0, 255, 0, 100);
        g2d.setColor(color);
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx, sy;
        if (x0 < x1) {
            sx = 1;
        } else {
            sx = -1;
        }
        if (y0 < y1) {
            sy = 1;
        } else {
            sy = -1;
        }
        int err = dx - dy;

        while (x0 <= x1) {
            switch (corner) {
                case 1:
                    g2d.drawLine(x0, y1 + 58, x0, y0);
                    break;
                case 2:
                    g2d.drawLine(x0, y1 + 38, x0, y0);
                    break;
                case 3:
                    g2d.drawLine(x0, y1 - 20, x0, y0);
                    break;
                case 4:
                    g2d.drawLine(x0, y1, x0, y0);
                    break;
                default:
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
        }
    }
}