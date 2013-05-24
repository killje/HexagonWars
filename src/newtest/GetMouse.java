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
public class GetMouse {

    public static Point GetMouse(int x, int y) {
        y = y - 35;
        Point mousePointer = new Point();
        int xCoord, yCoord, xWaar, yWaar, extra = 0;
        boolean up = true;
        if (y % 60 > 20) {
            yCoord = y / 60;
            if ((y / 60) % 2 == 0) {
                xCoord = x / 70;
            } else {
                if (x - 35 < 0) {
                    xCoord = -1;
                } else {
                    xCoord = (x - 35) / 70;
                }
            }
        } else {
            yWaar = y % 60;
            if ((y / 60) % 2 == 0) {
                xWaar = x % 70;
            } else {
                xWaar = (x + 35) % 70;
            }
            if (xWaar > 35) {
                up = false;
                xWaar -= 35;
            }
            if ((y / 60) % 2 == 0) {
                if (checkLine(xWaar, yWaar, up) == 1 || up) {
                    extra = -1;
                }
                xCoord = x / 70 + checkLine(xWaar, yWaar, up) + extra;
            } else {
                if (!up && checkLine(xWaar, yWaar, up) == 0) {
                    extra = 1;
                }
                if (x - 35 < 0) {
                    xCoord = -1;
                } else {
                    xCoord = (x - 35) / 70 + extra;
                }
            }
            yCoord = y / 60 + checkLine(xWaar, yWaar, up) - 1;
        }
        mousePointer.setLocation(xCoord, yCoord);
        return mousePointer;
    }

    public static int checkLine(int x, int y, boolean up) {
        int x0, x1, y0, y1;
        if (up) {
            x0 = 0;
            y0 = 20;
            x1 = 34;
            y1 = 0;
        } else {
            x0 = 0;
            y0 = 0;
            x1 = 34;
            y1 = 20;
        }

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

        while (x0 <= x) {
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
        if (y <= y0) {
            return 0;
        } else {
            return 1;
        }

    }
}
