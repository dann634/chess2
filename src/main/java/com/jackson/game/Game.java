package com.jackson.game;

import javafx.application.Platform;
import com.jackson.ui.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

            //Add Pieces to start com.jackson.game
            Platform.runLater(() -> board.drawBoard(player1.getPieces(), player2.getPieces()));

            while(true) { //Main com.jackson.game loop


                //Update Turns
                if(player1.isHasMoved()) {
                    player1.setHasMoved(false);
                    player1.setTurn(false);
                    player2.setTurn(true);
                    player2.isChecked();
                } else if(player2.isHasMoved()){
                    player2.setHasMoved(true);
                    player2.setHasMoved(false);
                    player1.setTurn(true);
                    player1.isChecked();
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

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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

    public static void destroyPiece(Piece piece) {
        try {
            player1.getPieces().remove(piece);
            player2.getPieces().remove(piece);
        } catch (NullPointerException ignored) {}
    }

    public static Piece getPiece(int row, int column) {
        for(Piece piece : getAllPieces()) {
            if(piece.getRow() == row && piece.getColumn() == column) {
                return piece;
            }
        }
        return null;
    }

    public static boolean contains(Set<int[]> list, int[] o) { //For all the lists
        for(int[] x : list) {
            if(x[0] == o[0] && x[1] == o[1]) {
                return true;
            }
        }
        return false;
    }


}
