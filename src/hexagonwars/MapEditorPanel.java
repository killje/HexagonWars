/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexagonwars;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class MapEditorPanel extends MapPanel {

    private JFormattedTextField inputWidthText;
    private JFormattedTextField inputHeightText;
    private JButton save = new JButton("Save");
    private int boardWidth, boardHeight;
    private DrawWorld newWorld;
    private DrawWorld tileSelector;
    private Point selectedTileCoordinate;

    public MapEditorPanel() {
        init();
    }
    
    private void init(){
        NumberFormat numberFormat;
        JLabel inputWidthLabel = new JLabel("Width");
        numberFormat = NumberFormat.getIntegerInstance();
        inputWidthText = new JFormattedTextField(numberFormat);
        inputWidthText.setColumns(20);
        inputWidthText.setValue(5);
        JLabel inputHeightLabel = new JLabel("Height");
        inputHeightText = new JFormattedTextField(numberFormat);
        inputHeightText.setColumns(20);
        inputHeightText.setValue(5);

        JButton go = new JButton("Go");
        go.addActionListener(new SetInputSize());

        save.addActionListener(new SaveWorld(newWorld));
        save.setEnabled(false);

        add(inputWidthLabel);
        add(inputWidthText);
        add(inputHeightLabel);
        add(inputHeightText);
        add(go);
        add(save);
        tileChoser();
        repaint();
        revalidate();
    }

    private void tileChoser() {
        World world = new World(4, 1);
        Tile[][] tiles = new Tile[4][1];
        tiles[0][0] = Tile.getType(HexagonWars.TILE_PLAIN);
        tiles[1][0] = Tile.getType(HexagonWars.TILE_MOUNTAIN);
        tiles[2][0] = Tile.getType(HexagonWars.TILE_WATER);
        tiles[3][0] = Tile.getType(HexagonWars.TILE_GOLD);
        world.setWorld(tiles);
        tileSelector = addWorld(world, 50, 30);
    }

    private void newBoard() {
        removeWorld(newWorld);
        World world = new World(boardWidth, boardHeight);
        Tile[][] tiles = new Tile[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                tiles[i][j] = Tile.getType(HexagonWars.TILE_PLAIN);
            }
        }

        world.setWorld(tiles);
        newWorld = addWorld(world, 0, 200);

        save.setEnabled(true);
        repaint();
        validate();
    }

    @Override
    protected void tileClick(DrawWorld world, Point TileCoordinate) {
        if (world == newWorld) {
            Point worldTile = TileCoordinate;
            Tile tile = tileSelector.getTile(selectedTileCoordinate.x, selectedTileCoordinate.y);
            newWorld.setTile(worldTile.x, worldTile.y, tile);
            repaint();
            validate();
        }else{
            selectedTileCoordinate = TileCoordinate;
        }
    }

    private class SetInputSize extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!inputWidthText.getText().equals("") && !inputHeightText.getText().equals("") && Integer.parseInt(inputWidthText.getText()) > 0 && Integer.parseInt(inputHeightText.getText()) > 0) {
                boardWidth = Integer.parseInt(inputWidthText.getText());
                boardHeight = Integer.parseInt(inputHeightText.getText());
                newBoard();
            }
        }
    }
}
