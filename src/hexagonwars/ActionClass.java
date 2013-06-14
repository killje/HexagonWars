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
    HWFrame frame;
    public ActionClass(HWFrame hwframe){
        frame = hwframe;
    }
    
    public class OpenWorldeditorAction extends AbstractAction{
       
        @Override
        public void actionPerformed(ActionEvent ae) {
            frame.addEditorPanel();
        }
    }
    
    public class OpenWorldAction extends AbstractAction{
       
        @Override
        public void actionPerformed(ActionEvent ae) {
            frame.addWorldPanel();
        }
    }
    
    public class SetInputSize extends AbstractAction{
       
        Notify notify;
        
        public SetInputSize(Observer o){
            notify = new Notify(o);
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            notify.sendNotify();
        }
        
        public class Notify extends Observable {
            
            public Notify(Observer o){
                this.addObserver(o);
            }
            
            public void sendNotify(){
                this.setChanged();
                this.notifyObservers();
            }
        }
    }
    
    public class SaveWorld extends AbstractAction{
       
        MapEditorPanel mapEditor;
        
        public SaveWorld(MapEditorPanel c){
            mapEditor = c;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            mapEditor.saveWorld();
        }
    }
    
    public class WorldPointer implements MouseListener{
       
        DrawWorld drawWorld;
        
        public WorldPointer(DrawWorld dw){
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
}
