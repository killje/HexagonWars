/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.FlowLayout;
import java.util.Observable;
import javax.swing.JFrame;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class HWFrame extends JFrame{

    private ActionClass actions = new ActionClass(this);
    private MainPanel mainPanel;
    private MapEditorPanel mapPanel;
    
    public HWFrame() {
        createFrame();
        addMainPanel();
    }

    private void createFrame() {
        setTitle("Graph editor");
        setSize(HexagonWars.FRAME_WIDTH, HexagonWars.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public void addMainPanel() {
        mainPanel = new MainPanel(this);
        add(mainPanel);
        validate();
    }
    
    public void addEditorPanel() {
        mapPanel = new MapEditorPanel(this);
        add(mapPanel);
        validate();
    }
    
    public void addMapEditorPanel() {
        mapPanel = new MapEditorPanel(this);
        add(mapPanel);
        validate();
    }
    
    public void removeAllPanels(){
        remove(mainPanel);
        repaint();
    }
    
    public ActionClass getActionClass(){
        return actions;
    }
}
