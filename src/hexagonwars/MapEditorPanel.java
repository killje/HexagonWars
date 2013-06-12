/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MapEditorPanel extends JPanel implements Observer {

    private HWFrame frame;
    private JFormattedTextField inputWidthText;
    private JFormattedTextField inputHeightText;
    private JPanel board = new JPanel();
    private JButton save = new JButton("Save");
    private int boardWidth, boardHeight;

    public MapEditorPanel(HWFrame hwframe) {
        frame = hwframe;
        NumberFormat numberFormat;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setSize(300, 30);
        JLabel inputWidthLabel = new JLabel("Width");
        numberFormat = NumberFormat.getIntegerInstance();
        inputWidthText = new JFormattedTextField(numberFormat);
        inputWidthText.setColumns(20);

        JLabel inputHeightLabel = new JLabel("Height");
        inputHeightText = new JFormattedTextField(numberFormat);
        inputHeightText.setColumns(20);
        JButton go = new JButton("Go");
        go.addActionListener(frame.getActionClass().new SetInputSize(this));

        save.addActionListener(frame.getActionClass().new SaveWorld(this));
        save.setEnabled(false);

        buttons.add(inputWidthLabel);
        buttons.add(inputWidthText);
        buttons.add(inputHeightLabel);
        buttons.add(inputHeightText);
        buttons.add(go);
        add(buttons);
        add(board);
        add(save);
    }

    private void board() {
        board.removeAll();
        board.setLayout(new GridLayout(boardHeight, boardWidth));
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                JFormattedTextField textField = new JFormattedTextField(numberFormat);
                textField.setColumns(2);
                board.add(textField);
            }
        }
        save.setEnabled(true);
        repaint();
        revalidate();
    }

    @Override
    public void update(Observable o, Object o1) {
        if (!inputWidthText.getText().equals("") && !inputHeightText.getText().equals("") && Integer.parseInt(inputWidthText.getText()) > 0 && Integer.parseInt(inputHeightText.getText()) > 0) {
            boardWidth = Integer.parseInt(inputWidthText.getText());
            boardHeight = Integer.parseInt(inputHeightText.getText());
            board();
        }
    }

    public void saveWorld() {
        System.out.println("it works, muhahahaha");

        String path = JOptionPane.showInputDialog(null, "Path Name:", "src/graph/saveFiles/test.txt");
        path = path.replace("/", "//");
        File file = new File(path);
        
        WorldFile saveWorld = new WorldFile();
        saveWorld.setHeight(boardHeight);
        saveWorld.setWidth(boardWidth);
        
        store(file);
    }
    
    private void store(File file){
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to override " + file.getName() + "?") != 0) {
                    return;
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(null);

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("The desired file was not found.");
        } catch (NotSerializableException e) {
            System.err.println("The saved object is not serializable at: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error with the I/O was reported, program closing.");
            System.exit(-1);
        }
    }
}
