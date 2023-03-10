package com.jackson.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;


public abstract class Piece {

    private boolean isWhite;
    private int row;
    private int column;
    private Image image;
    private ImageView imageView;

    public Piece(boolean isWhite, int row, int column, String name) {
        this.isWhite = isWhite;
        this.row = row;
        this.column = column;

        this.image = new Image(getImageDir(name));
        this.imageView = new ImageView(this.image);
        this.imageView.setFitHeight(75);
        this.imageView.setFitWidth(75);
        this.imageView.setMouseTransparent(true);
    }

    protected String getImageDir(String name) {
        String colour = this.isWhite ? "white" : "black";
        return "images/" + colour + name + ".png";
    }

    protected boolean isTargetWhite(int row, int column) {
        if(Game.getPiece(row, column).isWhite) {
            return true;
        }
        return false;

    }
    protected List<int[]> getCheckMoves() {
        return null;
    }

    public boolean isCellOccupied(int targetRow, int targetColumn) {

        if(Game.getPiece(targetRow, targetColumn) != null) {
            return true;
        }

        return false;

    }

    public abstract List<int[]> getValidMoves();

    protected abstract List<int[]> getAllMoves();

    public ImageView getImageView() {
        return imageView;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int row) {
        if (row >= 0 && row <= 7) {
            this.row = row;
        }
    }

    public void setColumn(int column) {
        if (column >= 0 && column <= 7) {
            this.column = column;
        }
    }

    public boolean isWhite() {
        return this.isWhite;
    }

}


