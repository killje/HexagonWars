/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class HWFrame extends JFrame {

    private MainPanel mainPanel;
    private MapEditorPanel mapPanel;
    private WorldPanel worldPanel;
    private JPanel currentPanel;

    public HWFrame() {
        createFrame();
        addMainPanel();
    }

    private void createFrame() {
        setTitle("HexagonWars");
        setSize(HexagonWars.FRAME_WIDTH, HexagonWars.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);
        setSize(900, 900);
    }

    public void addMainPanel() {
        removeAllPanels();
        mainPanel = new MainPanel(this);
        currentPanel = mainPanel;
        add(mainPanel);
        validate();
    }

    public void addEditorPanel() {
        removeAllPanels();
        mapPanel = new MapEditorPanel();
        currentPanel = mapPanel;
        add(mapPanel);
        validate();
    }

    public void addWorldPanel() {
        removeAllPanels();
        worldPanel = new WorldPanel();
        currentPanel = worldPanel;
        add(worldPanel);
        validate();
    }

    private void removeAllPanels() {
        for (int i = 0; i < this.getContentPane().getComponents().length; i++) {
            if (this.getContentPane().getComponents()[i] == mainPanel) {
                remove(mainPanel);
            } else if (this.getContentPane().getComponents()[i] == mapPanel) {
                remove(mapPanel);
            } else if (this.getContentPane().getComponents()[i] == worldPanel) {
                remove(worldPanel);
            }
        }
        repaint();
    }
}
