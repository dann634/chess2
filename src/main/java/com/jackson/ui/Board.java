package com.jackson.ui;

import com.jackson.game.Game;
import com.jackson.game.Piece;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;

import static com.jackson.game.Game.player1;
import static com.jackson.game.Game.player2;

public class Board {

    private GridPane gridPane;
    private Piece selectedPiece;

    public Scene getBoard() {
        gridPane = new GridPane();
        gridPane.setId("boardGridPane");
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("stylesheets/board.css");

        setUpBoard();

        return scene;
    }

    public void drawBoard(List<Piece> player1Pieces, List<Piece> player2Pieces) {

        gridPane.getChildren().clear();
        setUpBoard();

        for(Piece piece : player1Pieces) {
            gridPane.add(piece.getImageView(), piece.getColumn(), piece.getRow());
        }
        for(Piece piece : player2Pieces) {
            gridPane.add(piece.getImageView(), piece.getColumn(), piece.getRow());
        }
    }


    private void setUpBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gridPane.add(new Cell(i, j).getCell(), j, i);
            }
        }
    }

    public Node getNodeFromGridPane(int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void addMovementIndicators(List<int[]> validMoves) {

        //remove all indicators
        drawBoard(player1.getPieces(), player2.getPieces()); //Removes all old movement indicators (lazy fix + inefficient)
        //Removing just circles ran into problems

        //add new indicators
        for(int[] move : validMoves) {
            if(this.selectedPiece.isWhite() && Game.isWhiteTurn()) {
                createMovementIndicators(move);
            } else if(!this.selectedPiece.isWhite() && !Game.isWhiteTurn()) {
                createMovementIndicators(move);
            }
        }
    }

    private void createMovementIndicators(int[] move) {
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setTranslateX(17);
        circle.setId("movementIndicator");

        circle.setOnMouseClicked(mouseEvent -> {
            //selected piece moves to square

            if(this.selectedPiece.isWhite() && Game.isWhiteTurn()) { //Checking piece matching the turn
                player1.move(move[1], move[0], this.selectedPiece);

            } else if(!this.selectedPiece.isWhite() && !Game.isWhiteTurn()) { //Checking piece matching the turn
                player2.move(move[1], move[0], this.selectedPiece);
            }

            //Update Board
            drawBoard(player1.getPieces(), player2.getPieces());

        });

        gridPane.add(circle, move[0], move[1]);
    }

    class Cell {
        private Piece pieceInCell;
        private boolean isOccupied;
        private int row;
        private int column;
        private VBox vBox;

        public Cell(int row, int column) {

            this.row = row;
            this.column = column;
            this.isOccupied = false;
            this.pieceInCell = null;

            boolean isLight = (row + column) % 2 == 0;
            this.vBox = new VBox();
            this.vBox.getStyleClass().add("cell");
            if(isLight) {
                this.vBox.setId("lightCell");
            } else {
                this.vBox.setId("darkCell");
            }

            vBox.setOnMouseClicked(mouseEvent -> {
                this.pieceInCell = getPieceInCell();
                if(this.pieceInCell != null) {
                    selectedPiece = this.pieceInCell;
                    addMovementIndicators(selectedPiece.getValidMoves());

                }
            });

        }





        private Piece getPieceInCell() {
            List<Piece> allPieces = Game.getAllPieces();
            for(Piece piece : allPieces) {
                if(piece.getRow() == this.row && piece.getColumn() == this.column) {
                    return piece;
                }
            }
            return null;
        }

        public void occupyCell(Piece piece) {
            this.isOccupied = true;
            this.pieceInCell = piece;
        }


        public VBox getCell() {
            return this.vBox;
        }
    }
}
