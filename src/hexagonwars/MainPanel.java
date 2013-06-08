/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hexagonwars;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MainPanel extends JPanel{
    
    HWFrame frame;
    
    public MainPanel(HWFrame hwframe){
        frame = hwframe;
        JButton loadMap = new JButton("Open map");
        JButton openEditor = new JButton("Open editor");
        loadMap.addActionListener(frame.getActionClass().new OpenMapAction());
        openEditor.addActionListener(frame.getActionClass().new OpenWorldeditorAction());
        add(loadMap);
        add(openEditor);
    }
}
