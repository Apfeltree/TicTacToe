package logic;
import Exceptions.GameBoard.GameBoardTileAlreadySet;
import Exceptions.GameBoard.GameBoardTileOutOfBoard;
import entities.*;
import enums.TileState;

import java.util.ArrayList;
import java.util.Random;

public class Worker {
    private Player player1;
    private Player player2;
    private Player aktivePlayer;
    private GameBoard gameBoard;
    private int playCounter;

    public Worker(String namePlayer1, String namePlayer2) {
        createPlayer(namePlayer1, namePlayer2);
        gameBoard = new GameBoard();
        aktivePlayer = player1;
        playCounter = 1;
    }

    /***
     * Creates the players in the worker
     * @param namePlayer1 String The name of Player 1
     * @param namePlayer2 String The name of Player 2
     */
    private void createPlayer(String namePlayer1, String namePlayer2){
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        if (randomNumber <= 4){
            player1 = Player.getPlayerObject(namePlayer1 , TileState.cross);
            player2 = Player.getPlayerObject(namePlayer2, TileState.circle);
        } else {
            player1 = Player.getPlayerObject(namePlayer2 , TileState.cross);
            player2 = Player.getPlayerObject(namePlayer1, TileState.circle);
        }
    }

    /***
     * Checks if there is a winner
     * @return boolean if there is a winner
     */
    public boolean checkIfWinner() {
        //Check horizontal
        int winnnerCount = 0;
        int cyclus = 1;
        for (Tile tile:gameBoard.getGameBoardState()) {
            if (tile.getState() == aktivePlayer.getState()) {
                winnnerCount++;
            }
            if (cyclus == 3) {
                cyclus = 1;
                if (winnnerCount == 3)  {
                    return true;
                }else
                    winnnerCount = 0;
            }
            cyclus++;
        }
        //Check vertikal
        int column1 = 0;
        int column2 = 0;
        int column3 = 0;
        cyclus = 1;
        for (Tile tile:gameBoard.getGameBoardState()) {
            if (tile.getState() == aktivePlayer.getState() && (cyclus == 1 || cyclus == 4 || cyclus == 7)) {
                column1++;
            }
            if (tile.getState() == aktivePlayer.getState() && (cyclus == 2 || cyclus == 5 || cyclus == 8)) {
                column2++;
            }
            if (tile.getState() == aktivePlayer.getState() && (cyclus == 3 || cyclus == 6 || cyclus == 9)) {
                column3++;
            }
            if (column1 == 3 || column2 == 3 || column3 == 3) {
                return true;
            }
            cyclus++;
        }
        // Check diagonals
        int diagonal1 = 0;
        int diagonal2 = 0;
        cyclus = 1;
        for (Tile tile:gameBoard.getGameBoardState()) {
            if (tile.getState() == aktivePlayer.getState() && (cyclus == 1 || cyclus == 5 || cyclus == 9)) {
                diagonal1++;
            }
            if (tile.getState() == aktivePlayer.getState() && (cyclus == 3 || cyclus == 5 || cyclus == 7)) {
                diagonal2++;
            }
            if (diagonal1 == 3 || diagonal2 == 3) {
                return true;
            }
            cyclus++;
        }
        return false;
    }

    /***
     * Checks if the game is a draw
     * @return boolean if there is a draw
     */
    public boolean checkDraw(){
        return playCounter >= 10;
    }

    /***
     * Gets the name of the winner
     * @return String the name of the winner
     */
    public String getWinnerName() {
        return aktivePlayer.getName();
    }

    /***
     * Gets the object of the Player whose turn it is
     * @return Player - The Player object
     */
    public Player getActivePlayer(){
        return aktivePlayer;
    }

    /***
     * Sets the next round - Changes the active player
     */
    public void nextRound() {
        playCounter++;
        if (aktivePlayer == player1) {
            aktivePlayer = player2;
        }else {
            aktivePlayer = player1;
        }
    }

    /***
     * Sets a tile for the active player
     * @param x - The x coordinate of the tile
     * @param y - The y coordinate of the tile
     * @param tileState - The state that the tile should be set to
     * @throws GameBoardTileAlreadySet - if the tile has another state than empty
     * @throws GameBoardTileOutOfBoard - if the coordinates are not on the board
     */
    public void setActivePlayerTile(int x, int y,TileState tileState) throws GameBoardTileAlreadySet, GameBoardTileOutOfBoard {
        try {
            gameBoard.setStateTile(x,y,tileState);
        } catch (GameBoardTileAlreadySet | GameBoardTileOutOfBoard e) {
            throw e;
        }
    }

    /***
     * Get the state of the gameboard in an ArrayList of Tiles
     * @return ArrayList with Tiles of current state of gameboard
     */
    public ArrayList<Tile> getGameBoardState(){
        return gameBoard.getGameBoardState();
    }
}
