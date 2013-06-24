package hexagonwars;

import hexagonwars.entities.Building;
import hexagonwars.entities.Unit;
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
    private GameUI gameUI = new GameUI(this, worldMap);
    private final Tile[] possibleTiles;
    private ArrayList<Tile> tiles;
    private boolean drawMoves = false;
    private Entity currentEntity;
    private Tile currentPosition;

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
        if (drawMoves) {
            if (currentEntity instanceof Unit) {
                worldMap.getGameHandler().moveUnit(currentEntity, currentPosition, world.getTile(TileCoordinate.x, TileCoordinate.y));
            } else {
                worldMap.getGameHandler().moveFromBuilding(currentEntity, currentPosition, world.getTile(TileCoordinate.x, TileCoordinate.y));
            }
            drawMoves = false;
        }
        selectedTile = world.getTile(TileCoordinate.x, TileCoordinate.y);

    }

    @Override
    protected void clicked(MouseEvent me) {
        Rectangle uiRect = new Rectangle(0, this.getSize().height - 55, 200, 50);
        if (uiRect.contains(me.getPoint())) {
            Point pointInUI = new Point(me.getPoint().x, me.getPoint().y - this.getSize().height + 55);
            gameUI.clicked(pointInUI);
        }
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
                            if (action instanceof BuildAction) {
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
                            } else if (action instanceof MoveAction) {
                                MoveAction moveAction = (MoveAction) action;
                                currentEntity = selectedTile.getEntity();
                                Unit unit = (Unit) currentEntity;
                                drawMoves = true;
                                currentPosition = selectedTile;
                                tiles = worldMap.getMoves(unit.getMoves(), worldMap.getTilePosition(selectedTile), moveAction.getRange());
                            } else if (action instanceof ProduceAction) {
                                ProduceAction produceAction = (ProduceAction) action;
                                produceAction.getProducer().addUnitNextTurn(produceAction.getUnit());
                            } else if (action instanceof MoveOutAction) {
                                MoveOutAction moveOutAction = (MoveOutAction) action;
                                currentEntity = selectedTile.getEntity();
                                drawMoves = true;
                                currentPosition = selectedTile;
                                tiles = worldMap.getMoves(moveOutAction.getProducer().getLastUnit().getMoves(), worldMap.getTilePosition(selectedTile), moveOutAction.getRange());
                            }
                        }
                    }
                }
                //to here
                repaint();
                validate();

            }

        }

        super.clicked(me);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (selectedTile != null) {
            if (selectedTile.isOccupied()) {
                g.setColor(Color.BLACK);
                Rectangle rect = new Rectangle(getSize().width - 506, getSize().height - 207, 500, 201);
                int x = 0;
                int y = 0;

                g.drawRect(rect.x, rect.y, rect.width, rect.height);
                g.drawString(selectedTile.getEntity().getClass().getSimpleName(), rect.x + 50, rect.y + 20);
                g.drawString(Integer.toString(selectedTile.getEntity().getHealth()) + "/" + Integer.toString(selectedTile.getEntity().getStartHealth()), rect.x + 50, rect.y + 170);
                g.drawImage(selectedTile.getEntity().getImage(), rect.x + 30, rect.y + 30, null);
                g.drawLine(rect.x + 199, rect.y, rect.x + 199, rect.y + rect.height);

                if (worldMap.getGameHandler().getCurrentPlayer().getPlayerEntities().contains(selectedTile.getEntity())) {
                    ArrayList<ImageWithAction> list = selectedTile.getEntity().getEntityUI().getActions();
                    for (int i = 0; i < list.size(); i++) {
                        ImageWithAction imageWithAction = list.get(i);
                        g.drawImage(imageWithAction.getIcon(), rect.x + 200 + x * EntityUI.ICON_WIDTH, rect.y + y * EntityUI.ICON_HEIGHT + 1, null);
                        if (x < 5) {
                            x++;
                        } else {
                            x = 0;
                            y++;
                        }
                    }
                }
            }
        }
        Rectangle uiRect = new Rectangle(0, this.getSize().height - 55, 200, 50);
        g.drawImage(HWImage.getImageWithDefaultTransparency("nextTurn"), uiRect.x, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparency("exitButton"), uiRect.x + 50, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparency("saveButton"), uiRect.x + 100, uiRect.y, null);
        g.drawImage(HWImage.getImageWithDefaultTransparency("loadButton"), uiRect.x + 150, uiRect.y, null);
        g.drawString(worldMap.getGameHandler().getPlayerName(worldMap.getGameHandler().getCurrentPlayer()), uiRect.x + 210, uiRect.y + 12);
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
