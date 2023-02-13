package ui;

import game.Piece;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class Board {

    private GridPane gridPane;

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
            gridPane.add(piece.getImageView(), piece.getRow(), piece.getColumn());
        }
        for(Piece piece : player2Pieces) {
            gridPane.add(piece.getImageView(), piece.getRow(), piece.getColumn());
        }
    }


    private void setUpBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gridPane.add(new Cell(i, j).getCell(), i, j);
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

            boolean isLight = (row + column) % 2 != 0;
            this.vBox = new VBox();
            this.vBox.getStyleClass().add("cell");
            if(isLight) {
                this.vBox.setId("lightCell");
            } else {
                this.vBox.setId("darkCell");
            }

            vBox.setOnMouseClicked(mouseEvent -> {
                // TODO: 12/02/2023 Get Available moves if piece in cell and right players turn
            });

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