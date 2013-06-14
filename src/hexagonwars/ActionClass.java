/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class ActionClass {

    public ActionClass() {
    }

    public class OpenWorldeditorAction extends AbstractAction {

        Notify notify;

        public OpenWorldeditorAction(Observer o) {
            notify = new Notify(o);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            notify.sendNotify(this);
        }
    }

    public class OpenWorldAction extends AbstractAction {

        Notify notify;

        public OpenWorldAction(Observer o) {
            notify = new Notify(o);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            notify.sendNotify(this);
        }
    }

    public class SetInputSize extends AbstractAction {

        Notify notify;

        public SetInputSize(Observer o) {
            notify = new Notify(o);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            notify.sendNotify(this);
        }
    }

    public class SaveWorld extends AbstractAction {

        Notify notify;

        public SaveWorld(Observer o) {
            notify = new Notify(o);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            notify.sendNotify(this);
        }
    }

    public class WorldPointer implements MouseListener {

        DrawWorld drawWorld;

        public WorldPointer(DrawWorld dw) {
            drawWorld = dw;
        }
        

        @Override
        public void mouseClicked(MouseEvent me) {
            drawWorld.clicked(me);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            //not supported yet
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            //not supported yet
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            // not supported
        }

        @Override
        public void mouseExited(MouseEvent me) {
            // not supported
        }
    }

    public class Notify extends Observable {

        public Notify(Observer o) {
            this.addObserver(o);
        }

        public void sendNotify(Object arg) {
            this.setChanged();
            this.notifyObservers(arg);
        }
    }
}
