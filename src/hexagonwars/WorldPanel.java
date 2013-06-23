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

    public WorldPanel() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        WorldTiles world = new WorldTiles(file);

        worldMap = addWorld(world, 50, 30);
        posibleTiles  = new Tile[6];
        posibleTiles[0] = Tile.getType(WorldTiles.PLAIN);
        posibleTiles[1] = Tile.getType(WorldTiles.MOUNTAIN);
        posibleTiles[2] = Tile.getType(WorldTiles.WATER);
        posibleTiles[3] = Tile.getType(WorldTiles.GOLD);
        posibleTiles[4] = Tile.getType(WorldTiles.SHALLOWS);
        posibleTiles[5] = Tile.getType(WorldTiles.FOREST);
        tiles=worldMap.getMoves(posibleTiles, new Point(3,3), 0, 4, 0);
    }

    @Override
    protected void tileClick(WorldModel world, Point TileCoordinate) {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle uiRect = new Rectangle(0, this.getSize().height - 55, 50, 200);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("nextTurn"), uiRect.x, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("exitButton"), uiRect.x+50, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("saveButton"), uiRect.x+100, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparensy("loadButton"), uiRect.x+150, uiRect.y, null);
        for (Tile tile : tiles) {
            System.out.println(worldMap.getTilePosition(tile));
            drawHex(g, worldMap.getTilePosition(tile).x*HexagonWars.WORLD_TILE_WIDTH, worldMap.getTilePosition(tile).y*HexagonWars.WORLD_TILE_HEIGHT_MAX, new Color(0,0,255,80), worldMap);
        }
        
    }

    public WorldModel getWorldModel() {
        return this.worldMap;
    }
    
    
}
