package entities;

import Exceptions.GameBoard.GameBoardTileAlreadySet;
import Exceptions.GameBoard.GameBoardTileOutOfBoard;
import enums.TileState;

import java.util.ArrayList;

public class GameBoard {
    private int height = 3;
    private int width = 3;
    private ArrayList<Tile> allGameboardTiles;

    public GameBoard(){
        createGameBoard();
    }

    //Fills an ArrayList with Tile objects of the state empty
    private void createGameBoard() {
        allGameboardTiles = new ArrayList<Tile>();
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= height; y++){
                Tile tile = new Tile(x,y);
                tile.setState(TileState.empty);
                allGameboardTiles.add(tile);
            }
        }
    }

    /***
     *
     * @param x The x coordinate of this tile
     * @param y The y coordinate of this tile
     * @param state Set the tiles state (Enum)
     * @throws GameBoardTileOutOfBoard
     * @throws GameBoardTileAlreadySet
     */
    public void setStateTile(int x, int y, TileState state) throws GameBoardTileOutOfBoard, GameBoardTileAlreadySet {
        if (x > width || y > height) {
            throw new GameBoardTileOutOfBoard("Tile-coordinates are out of Board range. Tried Coordinates: X: " + x + " | Y:"+ y);
        }
        for (Tile tile: allGameboardTiles) {
            if (tile.getCoordinate().x == x && tile.getCoordinate().y == y) {
                if (tile.getState() == TileState.empty){
                    tile.setState(state);
                } else {
                    throw new GameBoardTileAlreadySet("Tile is already set! (not empty)");
                }
            }
        }
    }

    /***
     * Get the state of the gameboard in an ArrayList of Tiles
     * @return ArrayList with Tiles of current state of gameboard
     */
    public ArrayList<Tile> getGameBoardState() {
        ArrayList<Tile> allGameboardTilesDeepCopied = new ArrayList<>();
        for (Tile tile : allGameboardTiles) {
            allGameboardTilesDeepCopied.add(copyTile(tile));
        }
        return allGameboardTilesDeepCopied;
    }
    //Deepcopy of Tile
    private Tile copyTile(Tile toCopy){
        return new Tile(toCopy.getCoordinate().x,toCopy.getCoordinate().y,toCopy.getState());
    }
}
