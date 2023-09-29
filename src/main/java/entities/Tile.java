package entities;

import enums.TileState;

import java.awt.*;

public class Tile {
    private TileState state;
    private Point coordinate;

    public Tile(int x, int y){
        state = TileState.empty;
        coordinate = new Point(x,y);
    }
    public Tile(int x, int y, TileState tileState){
        state = tileState;
        coordinate = new Point(x,y);
    }

    /***
     * Sets the state of this with an enum
     * @param state - the state (Enum)
     */
    public void setState(TileState state) {
        this.state = state;
    }

    /***
     * Returns the TileState Enum of this tile
     * @return TileState - The TileState Enum
     */
    public TileState getState() {
        return state;
    }

    /***
     * Gets the location of this tile
     * @return Point - A Point Object with coordinates
     */
    public Point getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString(){
        return "[" + coordinate.x + "," + coordinate.y + "]" + " - " + state;
    }
}
