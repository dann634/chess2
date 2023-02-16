package ui;

import game.Game;
import game.Piece;
import game.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;

import static game.Game.player1;
import static game.Game.player2;

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
        gridPane.getChildren().removeIf(Circle.class::isInstance);

        //add new indicators
        for(int[] move : validMoves) {
            Circle movementIndicator = new Circle();
            movementIndicator.setRadius(20);
            movementIndicator.setTranslateX(17);
            movementIndicator.toFront();
            if(!selectedPiece.isCellOccupied(move[1], move[0])) {
                movementIndicator.setId("movementIndicator");
            } else {
                movementIndicator.setId("movementIndicatorTake");
            }
            gridPane.add(movementIndicator, move[0], move[1]);
        }
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
                // TODO: 12/02/2023 Get Available moves if piece in cell and right players turn
                this.pieceInCell = getPieceInCell();
                if(this.pieceInCell != null) {
                    selectedPiece = this.pieceInCell;
                    addMovementIndicators(selectedPiece.getValidMoves());
                    if(this.pieceInCell.isWhite() && Game.isWhiteTurn()) {
                        player1.move(row, column);
                    } else if(!this.pieceInCell.isWhite() && !Game.isWhiteTurn()) {
                        player2.move(row, column);
                    }
                } else {
                    System.out.println("Empty Square");
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
            // TODO: 12/02/2023 Update Board UI
        }


        public VBox getCell() {
            return this.vBox;
        }
    }
}
