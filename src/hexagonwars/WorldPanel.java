package hexagonwars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class WorldPanel extends MapPanel {

    private WorldModel worldMap = new WorldModel();
    private GameUI gameUI = new GameUI(this);
    private final Tile[] possibleTiles;
    private ArrayList<Tile> tiles;
    private boolean drawMoves = false;

    public WorldPanel() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "src" + File.separator + "hexagonwars" + File.separator + "maps" + File.separator + "firstmap.hwm");//debug
        WorldTiles world = read(file, worldMap);

        worldMap = addWorld(world, 50, 30, worldMap.getGameHandler());
        possibleTiles = new Tile[6];
        possibleTiles[0] = Tile.getTileFromType(WorldTiles.PLAIN);
        possibleTiles[1] = Tile.getTileFromType(WorldTiles.MOUNTAIN);
        possibleTiles[2] = Tile.getTileFromType(WorldTiles.WATER);
        possibleTiles[3] = Tile.getTileFromType(WorldTiles.GOLD);
        possibleTiles[4] = Tile.getTileFromType(WorldTiles.SHALLOWS);
        possibleTiles[5] = Tile.getTileFromType(WorldTiles.FOREST);


    }

    @Override
    protected void tileClick(WorldModel world, Point TileCoordinate) {
        selectedTile = world.getTile(TileCoordinate.x, TileCoordinate.y);
        drawMoves = true;
        if (drawMoves) {
            tiles = worldMap.getMoves(possibleTiles, worldMap.getTilePosition(selectedTile), 1);
        }
    }

    @Override
    protected void clicked(MouseEvent me) {
        if (selectedTile != null) {
            if (selectedTile.isOccupied()) {
                Rectangle rect = new Rectangle(getSize().width - 506, getSize().height - 207, 500, 201);
                if (rect.contains(me.getPoint())) {
                    Point p = new Point(me.getPoint().x - getSize().width + 506, me.getPoint().y - getSize().height + 207);
                    //from here edit
                    int x, y;
                    Point actionPoint = new Point();
                    if (p.x >= 200 && p.y >= 1) {
                        actionPoint.x = p.x - 200;
                        actionPoint.y = p.y - 1;
                        x = actionPoint.x / EntityUI.ICON_WIDTH;
                        y = actionPoint.y / EntityUI.ICON_HEIGHT;
                        int elementIndex = y * 6 + x;
                        if (selectedTile.getEntity().getEntityUI().getActions().size() > elementIndex) {
                            ArrayList<ImageWithAction> list = selectedTile.getEntity().getEntityUI().getActions();
                            UIAction action = list.get(elementIndex).getAction();
                            if (action instanceof NewUIAction) {
                                NewUIAction newUIAction = (NewUIAction) action;
                                selectedTile.getEntity().setEntityUI(newUIAction.getUI());
                            } else if (action instanceof BuildAction) {
                                BuildAction buildAction = (BuildAction) action;
                                selectedTile.removeAllEntities();
                                worldMap.getGameHandler().build(buildAction.getBuilding());
                                selectedTile.addEntity(buildAction.getBuilding());
                            } else if (action instanceof UpgradeAction) {
                                UpgradeAction upgradeAction = (UpgradeAction) action;
                                if (upgradeAction.upgradeID() == -1) {
                                    upgradeAction.upgradedBuilding().upgrade();
                                } else {
                                    upgradeAction.upgradedBuilding().upgrade(upgradeAction.upgradeID());
                                }
                            }
                        }
                    }
                    //to here
                    repaint();
                    validate();

                    return;
                }
            }
        }
        super.clicked(me);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle uiRect = new Rectangle(0, this.getSize().height - 55, 50, 200);
        g.drawImage(HWImage.getImageWithDefaultTransparency("nextTurn"), uiRect.x, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparency("exitButton"), uiRect.x + 50, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparency("saveButton"), uiRect.x + 100, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparency("loadButton"), uiRect.x + 150, uiRect.y, null);
        if (drawMoves) {
            for (Tile tile : tiles) {
                drawHex(g, worldMap.getTilePosition(tile).x * (int) (HexagonWars.WORLD_TILE_WIDTH * worldMap.getZoomLevel()) + worldMap.getTilePosition(tile).y % 2 * (int) (HexagonWars.WORLD_TILE_WIDTH / 2 * worldMap.getZoomLevel()) + worldMap.getXLocation(), worldMap.getTilePosition(tile).y * (int) (HexagonWars.WORLD_TILE_HEIGHT_MIN * worldMap.getZoomLevel()) + worldMap.getYLocation(), new Color(0, 0, 255, 80), worldMap);
            }
        }
    }

    public WorldModel getWorldModel() {
        return this.worldMap;
    }
}
