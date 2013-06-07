/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hexagonwars;

import java.awt.event.ActionEvent;
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
            frame.removeAllPanels();
            frame.addEditorPanel();
        }
    }
    
    public class OpenMapAction extends AbstractAction{
       
        @Override
        public void actionPerformed(ActionEvent ae) {
            frame.removeAllPanels();
            frame.addMapEditorPanel();
        }
    }
}
