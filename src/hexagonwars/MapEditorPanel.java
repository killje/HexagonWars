/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MapEditorPanel extends JPanel implements Observer {

    HWFrame frame;
    JFormattedTextField inputWidthText;
    JFormattedTextField inputHeightText;

    public MapEditorPanel(HWFrame hwframe) {
        frame = hwframe;
        NumberFormat numberFormat;

        setLayout(new FlowLayout());
        JLabel inputWidthLabel = new JLabel("Width");
        numberFormat = NumberFormat.getIntegerInstance();
        inputWidthText = new JFormattedTextField(numberFormat);
        inputWidthText.setSize(200, 20);
        JLabel inputHeightLabel = new JLabel("Height");
        inputHeightText = new JFormattedTextField(numberFormat);
        inputHeightText.setSize(200, 20);
        JButton go = new JButton("Go");
        go.addActionListener(frame.getActionClass().new SetInputSize());
        add(inputWidthLabel);
        add(inputWidthText);
        add(inputHeightLabel);
        add(inputHeightText);
        add(go);
    }

    private void board(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(width, height));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                panel.add(new JLabel(i + ":" + j));
            }
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        board(Integer.parseInt(inputWidthText.getText()),Integer.parseInt(inputHeightText.getText()));
        throw new UnsupportedOperationException("Not supported yet. at: hexagonwars.MapEditorPanel:update();");
    }
}
