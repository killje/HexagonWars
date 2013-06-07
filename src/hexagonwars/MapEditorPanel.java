/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hexagonwars;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MapEditorPanel extends JPanel{
    HWFrame frame;
    
    public MapEditorPanel(HWFrame hwframe){
        frame = hwframe;
        
        JLabel inputWidthLabel = new JLabel("Width");
        JTextField inputWidthText = new JTextField();
        JLabel inputHeightLabel = new JLabel("Height");
        JTextField inputHeightText = new JTextField();
        add(inputWidthLabel);
        add(inputWidthText);
        add(inputHeightLabel);
        add(inputHeightText);
    }
}
