package hexagonwars;

import hexagonwars.entities.Worker;
import java.awt.Color;
import java.awt.Graphics;
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
    private WorldModel newWorld;
    private WorldModel tileSelector;
    private Point selectedTileCoordinate;

    /**
     * opens a panel with functions to edit a x*x world
     */
    public MapEditorPanel() {
        init();
    }

    private void init() {
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
        save.addActionListener(new SaveWorld());
        save.setEnabled(false);

        add(inputWidthLabel);
        add(inputWidthText);
        add(inputHeightLabel);
        add(inputHeightText);
        add(go);
        add(save);
        tileChooser();
        repaint();
        revalidate();
    }

    /**
     * creates a world in where you can select a tile to later use on the board.
     */
    private void tileChooser() {
        WorldTiles world = new WorldTiles(7, 1);
        Tile[][] tiles = new Tile[7][1];
        tiles[0][0] = Tile.getTileFromType(WorldTiles.PLAIN);
        tiles[1][0] = Tile.getTileFromType(WorldTiles.MOUNTAIN);
        tiles[2][0] = Tile.getTileFromType(WorldTiles.WATER);
        tiles[3][0] = Tile.getTileFromType(WorldTiles.GOLD);
        tiles[4][0] = Tile.getTileFromType(WorldTiles.SHALLOWS);
        tiles[5][0] = Tile.getTileFromType(WorldTiles.FOREST);
        tiles[6][0] = Tile.getTileFromType(WorldTiles.PLAIN);
        tiles[6][0].addEntity(new Worker(new Color(55, 55, 55).getRGB()));
        world.setWorld(tiles);
        tileSelector = addWorld(world, 70, 40,new GameHandler());
        tileSelector.setCameraEnabled(false);
        tileSelector.setSaveable(false);
    }

    /**
     * creates a x*x witch you can edit and later save
     */
    private void newBoard() {
        removeWorld(newWorld);
        WorldTiles world = new WorldTiles(boardWidth, boardHeight);
        Tile[][] tiles = new Tile[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                tiles[i][j] = Tile.getTileFromType(WorldTiles.PLAIN);
            }
        }

        world.setWorld(tiles);

        newWorld = addWorld(world, 0, 180, new GameHandler());
        Player player1 = new Player();
        newWorld.getGameHandler().addPlayer(player1);
        player1.setName("Patrick");
        Player player2 = new Player();
        newWorld.getGameHandler().addPlayer(player2);
        player2.setName("Floris");
        Player player3 = new Player();
        newWorld.getGameHandler().addPlayer(player3);
        player3.setName("Timo");
        save.setEnabled(true);
        repaint();
        validate();
    }

    /**
     * if the mouse has clicked on tileSelector then the current selectedTile is
     * replaced with the clicked one, if the mouse has clicked on the newWorld
     * then that tile is replaced to the previous selected tile in tileSelector
     *
     * @param world the world that the mouse has clicked in
     * @param TileCoordinate the coordinate of the tile of the world
     */
    @Override
    protected void tileClick(WorldModel world, Point TileCoordinate) {
        if (world == newWorld) {
            Point worldTile = TileCoordinate;
            Tile tile = tileSelector.getTile(selectedTileCoordinate.x, selectedTileCoordinate.y);
            newWorld.setTile(worldTile.x, worldTile.y, tile);
            repaint();
            validate();
        } else {
            selectedTileCoordinate = TileCoordinate;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (selectedTileCoordinate != null) {
            drawHex(g, selectedTileCoordinate.x * HexagonWars.WORLD_TILE_WIDTH + tileSelector.getXLocation(), tileSelector.getYLocation(), new Color(255, 255, 0, 50), tileSelector);
        }
    }

    /**
     * class to see if someone clicks on the go button
     */
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
