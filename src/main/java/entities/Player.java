package entities;

import enums.TileState;

public class Player {
    private String name;
    private TileState state;

    private Player(){

    }
    private Player(String name, TileState state){
        this.name = name;
        this.state = state;
    }

    /***
     * Creates a player Object and returns it
     * @param name The player name
     * @param state The players side (0,X), Get from Enum
     * @return Object Player | The player object
     */
    public static Player getPlayerObject(String name, TileState state) {
        return new Player(name,state);
    }

    /***
     * Returns the player name
     * @return String | The players name
     */
    public String getName() {
        return name;
    }

    /***
     * Returns the players side
     * @return Enum TileState | The players side
     */
    public TileState getState() {
        return state;
    }
}
