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

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MapEditorPanel extends JPanel implements Observer {

    HWFrame frame;
    JFormattedTextField inputWidthText;
    JFormattedTextField inputHeightText;
    JPanel panel = new JPanel();

    public MapEditorPanel(HWFrame hwframe) {
        frame = hwframe;
        NumberFormat numberFormat;

        setLayout(new FlowLayout());
        JLabel inputWidthLabel = new JLabel("Width");
        numberFormat = NumberFormat.getIntegerInstance();
        inputWidthText = new JFormattedTextField(numberFormat);
        inputWidthText.setColumns(20);

        JLabel inputHeightLabel = new JLabel("Height");
        inputHeightText = new JFormattedTextField(numberFormat);
        inputHeightText.setColumns(20);
        JButton go = new JButton("Go");
        go.addActionListener(frame.getActionClass().new SetInputSize(this));
        add(inputWidthLabel);
        add(inputWidthText);
        add(inputHeightLabel);
        add(inputHeightText);
        add(go);
        add(panel);
    }

    private void board(int width, int height) {
        panel.removeAll();
        panel.setLayout(new GridLayout(width, height));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                panel.add(new JLabel(i + ":" + j));
            }
        }
        repaint();
        revalidate();
    }

    @Override
    public void update(Observable o, Object o1) {
        if (!inputWidthText.getText().equals("") && !inputHeightText.getText().equals("")) {
            board(Integer.parseInt(inputWidthText.getText()), Integer.parseInt(inputHeightText.getText()));
        }
    }
}
