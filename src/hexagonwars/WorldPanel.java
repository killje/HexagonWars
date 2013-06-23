package hexagonwars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldPanel extends MapPanel {

    private WorldModel worldMap;
    private GameUI gameUI = new GameUI(this);
    private final Tile[] posibleTiles;
    private ArrayList<Tile> tiles;
    private boolean drawMoves = false;

    public WorldPanel() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        WorldTiles world = new WorldTiles(file);

        worldMap = addWorld(world, 50, 30);
        posibleTiles = new Tile[6];
        posibleTiles[0] = Tile.getTileFromType(WorldTiles.PLAIN);
        posibleTiles[1] = Tile.getTileFromType(WorldTiles.MOUNTAIN);
        posibleTiles[2] = Tile.getTileFromType(WorldTiles.WATER);
        posibleTiles[3] = Tile.getTileFromType(WorldTiles.GOLD);
        posibleTiles[4] = Tile.getTileFromType(WorldTiles.SHALLOWS);
        posibleTiles[5] = Tile.getTileFromType(WorldTiles.FOREST);

    }

    @Override
    protected void tileClick(WorldModel world, Point TileCoordinate) {
        selectedTile = world.getTile(TileCoordinate.x, TileCoordinate.y);
        drawMoves = true;
        if (drawMoves) {
            tiles = worldMap.getMoves(posibleTiles, worldMap.getTilePosition(selectedTile), 3);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle uiRect = new Rectangle(0, this.getSize().height - 55, 50, 200);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("nextTurn"), uiRect.x, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("exitButton"), uiRect.x + 50, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("saveButton"), uiRect.x + 100, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("loadButton"), uiRect.x + 150, uiRect.y, null);
        if (drawMoves) {
            for (Tile tile : tiles) {
                System.out.println(worldMap.getTilePosition(tile));
                drawHex(g, worldMap.getTilePosition(tile).x * (int) (HexagonWars.WORLD_TILE_WIDTH * worldMap.getZoomLevel()) + worldMap.getTilePosition(tile).y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * worldMap.getZoomLevel())+worldMap.getXLocation(), worldMap.getTilePosition(tile).y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * worldMap.getZoomLevel())+worldMap.getYLocation(), new Color(0, 0, 255, 80), worldMap);
            }
        }
    }

    public WorldModel getWorldModel() {
        return this.worldMap;
    }
}
