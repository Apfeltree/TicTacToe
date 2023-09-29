package ui;

import Exceptions.GameBoard.GameBoardTileAlreadySet;
import Exceptions.GameBoard.GameBoardTileOutOfBoard;
import entities.Player;
import entities.Tile;
import enums.TileState;
import logic.Worker;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalUI {

    private Worker gameWorker;
    private Player aktivePlayer;
    private Scanner scan = new Scanner(System.in);
    private String playerName1;
    private String playerName2;

    public TerminalUI(){
        startGameTicTacToe();
    }
    //Starts the game and creates the gameworker
    private void startGameTicTacToe(){

        initializeGame();
        do {
            gameWorker = new Worker(playerName1,playerName2);
            do {
                aktivePlayer = gameWorker.getActivePlayer();
                System.out.print("Spieler " + aktivePlayer.getName() + " ist an der Reihe. Zeichen: ");
                if (aktivePlayer.getState() == TileState.cross){
                    System.out.print("X\n\n");
                } else {
                    System.out.print("O\n\n");
                }
                drawBoardToTerminal(gameWorker.getGameBoardState());
                System.out.println("\nBitte Koordinaten (X-Achse: 1-3,Y-Achse: 1-3)eingeben. \n" +
                        "z.B [1,3]: ");
                String input = scan.nextLine();
                if (parseInputToPoint(input) == null){
                    System.out.println("Eingabe ungültig!");
                } else {
                    Point point = parseInputToPoint(input);
                    try {
                        gameWorker.setActivePlayerTile(point.x,point.y,aktivePlayer.getState());
                        if (gameWorker.checkIfWinner()) break;
                        gameWorker.nextRound();
                        if (gameWorker.checkDraw()) break;
                    } catch (GameBoardTileAlreadySet e) {
                        System.out.println("Feld schon vergeben");
                    } catch (GameBoardTileOutOfBoard e) {
                        System.out.println("Feld nicht vorhanden!");
                    }
                }
            } while (true);
            System.out.println("\n");
            if (gameWorker.checkIfWinner()) {
                String winnerName = gameWorker.getWinnerName();
                System.out.println("Spieler " + winnerName + " hat gewonnen!");
            }else {
                System.out.println("Unentschieden!");
            }
            drawBoardToTerminal(gameWorker.getGameBoardState());
            //Gameend
            String anotherRound;
            do {
                System.out.print("Noch eine Runde? [J/N]: ");
                anotherRound = scan.next();
                //consume nextline token
                scan.nextLine();
            } while (!(anotherRound.toLowerCase().equals("j") || anotherRound.toLowerCase().equals("n")));
            if (!anotherRound.toLowerCase().equals("j")){
                break;
            }
        } while (true);
    }
    //Asks the user to input the usernames
    private void initializeGame() {
        System.out.println("(: Herzlich Willkommen zu TicTacToe :)");
        System.out.print("Name Spieler1: ");
        playerName1 = scan.nextLine();
        System.out.print("Name Spieler2: ");
        playerName2 = scan.nextLine();
    }

    //Prints the current state of the gameboard into the terminal
    private void drawBoardToTerminal(ArrayList<Tile> gameBoardState){
        System.out.print(printTile(gameBoardState.get(0))+
                "|"+printTile(gameBoardState.get(1))+
                "|"+printTile(gameBoardState.get(2))+
                "\n");
        printSeperator();
        System.out.print(printTile(gameBoardState.get(3))+
                "|"+printTile(gameBoardState.get(4))+
                "|"+printTile(gameBoardState.get(5))+
                "\n");
        printSeperator();
        System.out.print(printTile(gameBoardState.get(6))+
                "|"+printTile(gameBoardState.get(7))+
                "|"+printTile(gameBoardState.get(8))+
                "\n");
    }
    //Prints the current state of the tile as a char to the terminal
    private char printTile(Tile tile) {
        switch (tile.getState()){
            case cross -> {
                return 'X';
            }
            case circle -> {
                return 'O';
            }
        }
        return ' ';
    }
    //Prints a separator to the terminal
    private void printSeperator() {
        System.out.println("-+-+-");
    }
    //Checks if the given String is parsable to an int and returns boolean if possible
    private boolean checkIfInputStringIsParsableToInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    //Parse the given input String to a point if the format is given correctly [1,2]
    private Point parseInputToPoint(String inputString){
        String [] inputSplit = inputString.split(",");
        if (inputSplit.length != 2) return null;
        if (checkIfInputStringIsParsableToInt(inputSplit[0])
                && checkIfInputStringIsParsableToInt(inputSplit[1])){
            return new Point(Integer.parseInt(inputSplit[0]),Integer.parseInt(inputSplit[1]));
        }
        return null;
    }

}
