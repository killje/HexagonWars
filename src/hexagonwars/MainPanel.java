/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hexagonwars;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MainPanel extends JPanel {
    
    HWFrame frame;
    
    public MainPanel(HWFrame hwframe){
        frame = hwframe;
        JButton loadMap = new JButton("Open map");
        JButton openEditor = new JButton("Open editor");
        loadMap.addActionListener(new OpenWorldAction());
        openEditor.addActionListener(new OpenWorldeditorAction());
        add(loadMap);
        add(openEditor);
    }
    
    
    private class OpenWorldAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent ae) {
            frame.addWorldPanel();
        }
        
    }
    
    private class OpenWorldeditorAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent ae) {
            frame.addEditorPanel();
        }
    }
}
