package game;

import javafx.application.Platform;
import ui.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static Player player1;
    public static Player player2;

    public static Board board;

    public void start(Board localboard) {
        Thread gameThread = new Thread(() -> {
            board = localboard;
            player1 = new Player(true);
            player2 = new Player(false);

            player1.initializePieces();
            player2.initializePieces();

            //Add Pieces to start game
            Platform.runLater(() -> board.drawBoard(player1.getPieces(), player2.getPieces()));

            while(true) { //Main game loop

                //Draw Pieces to board (only updates after player has moved)
                if(player1.hasPlayerMoved() || player2.hasPlayerMoved()) {
                    Platform.runLater(() -> board.drawBoard(player1.getPieces(), player2.getPieces()));
                    player1.setHasPlayerMoved(false);
                    player2.setHasPlayerMoved(false);
                }

                //Round based system
                if(player1.getTurn() && !player1.arePiecesEnabled()) {
                    player1.enablePieces();
                    player2.disablePieces();

                } else if(player2.getTurn() && !player2.arePiecesEnabled()){
                    player2.enablePieces();
                    player1.disablePieces();
                }

                //Win Conditions
                if(player1.hasWon()) {
                    System.out.println("Player 1 has won");
                } else if(player2.hasWon()) {
                    System.out.println("Player 2 has won");
                }

            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public static List<Piece> getAllPieces() {
        List<Piece> allPieces = new ArrayList<>();
        allPieces.addAll(player1.getPieces());
        allPieces.addAll(player2.getPieces());
        return allPieces;
    }

    public static boolean isWhiteTurn() {
        return player1.getTurn();
    }


}
