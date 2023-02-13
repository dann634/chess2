package game;

import javafx.application.Platform;
import ui.Board;

public class Game {

    public void start(Board board) {
        Thread gameThread = new Thread(() -> {
            Player player1 = new Player(true);
            Player player2 = new Player(false);

            player1.initializePieces();
            player2.initializePieces();

            while(true) { //Main game loop

                //Draw Pieces to board (only updates after player has moved)
                if(player1.hasPlayerMoved() || player2.hasPlayerMoved()) {
                    Platform.runLater(() -> board.drawBoard(player1.getPieces(), player2.getPieces()));
                    player1.setHasPlayerMoved(false);
                    player2.setHasPlayerMoved(false);
                }

                //Round based system
                if(player1.getTurn()) {
                    player1.enablePieces();
                    player2.disablePieces();
                } else {
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


}
