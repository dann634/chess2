package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Piece {

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
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isWhite() {
        return isWhite;
    }
}


