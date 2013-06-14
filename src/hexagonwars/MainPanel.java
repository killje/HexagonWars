/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hexagonwars;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MainPanel extends JPanel implements Observer{
    
    HWFrame frame;
    
    public MainPanel(HWFrame hwframe){
        frame = hwframe;
        JButton loadMap = new JButton("Open map");
        JButton openEditor = new JButton("Open editor");
        loadMap.addActionListener(frame.getActionClass().new OpenWorldAction(this));
        openEditor.addActionListener(frame.getActionClass().new OpenWorldeditorAction(this));
        add(loadMap);
        add(openEditor);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ActionClass.OpenWorldAction) {
            frame.addWorldPanel();
        }else if(arg instanceof ActionClass.OpenWorldeditorAction){
            frame.addEditorPanel();
        }
    }
}
