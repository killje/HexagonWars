/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;
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
        this.getRootPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
                currentPanel.setMinimumSize(new Dimension(getWidth(), getHeight()));
                currentPanel.repaint();
                currentPanel.revalidate();
            }
        });
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
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        mainPanel.setMinimumSize(new Dimension(getWidth(), getHeight()));
        currentPanel = mainPanel;
        add(mainPanel);
        validate();
    }

    public void addEditorPanel() {
        removeAllPanels();
        mapPanel = new MapEditorPanel();
        mapPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        mapPanel.setMinimumSize(new Dimension(getWidth(), getHeight()));
        currentPanel = mapPanel;
        add(mapPanel);
        validate();
    }

    public void addWorldPanel() {
        removeAllPanels();
        worldPanel = new WorldPanel();
        worldPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        worldPanel.setMinimumSize(new Dimension(getWidth(), getHeight()));
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
