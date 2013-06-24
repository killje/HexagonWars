package hexagonwars;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class GameUI {
    
    private WorldPanel gameWorld;
    private WorldModel world;
    
    public GameUI(WorldPanel gameWorld, WorldModel world) {
        this.gameWorld = gameWorld;
        this.world = world;
    }
    
    public void clicked(Point p) {
        Rectangle nextTurn = new Rectangle(0, 0, 50, 50);
        Rectangle exitButton = new Rectangle(50, 0, 50, 50);
        Rectangle saveButton = new Rectangle(100, 0, 50, 50);
        Rectangle loadButton = new Rectangle(150, 0, 50, 50);
        
        if (exitButton.contains(p)) {
            System.exit(0);
        } else if (saveButton.contains(p)) {
            final JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter(".hwm", "hwm");
            chooser.removeChoosableFileFilter(chooser.getFileFilter());
            chooser.addChoosableFileFilter(filter);
            chooser.setFileFilter(filter);
            File defaultDocument = new File(new JFileChooser().getFileSystemView().getDefaultDirectory() + File.separator + "HexagonWars");
            if (!defaultDocument.exists()) {
                defaultDocument.mkdirs();
            }
            chooser.setCurrentDirectory(defaultDocument);
            
            int returnVal = chooser.showSaveDialog(null);
            
            if (returnVal != JFileChooser.APPROVE_OPTION) {
                return;
            }
            
            File file = new File(chooser.getSelectedFile().toString().replace(".hwm", "") + ".hwm");
            
            
            gameWorld.store(file, world);
        } else if (loadButton.contains(p)) {
            
            final JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter(".hwm", "hwm");
            chooser.addChoosableFileFilter(filter);
            chooser.setFileFilter(filter);
            File defaultDocument = new File(new JFileChooser().getFileSystemView().getDefaultDirectory() + File.separator + "HexagonWars");
            if (!defaultDocument.exists()) {
                defaultDocument.mkdirs();
            }
            chooser.setCurrentDirectory(defaultDocument);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File file = chooser.getSelectedFile();
            gameWorld.read(file, world);
        } else if (nextTurn.contains(p)) {
            gameWorld.getWorldModel().getGameHandler().nextTurn();
            gameWorld.repaint();
            gameWorld.validate();
        }
    }
}