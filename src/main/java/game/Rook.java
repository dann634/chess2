package game;

import java.util.List;

public class Rook extends Piece{
    public Rook(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Rook");
    }

    @Override
    public void move(Piece piece, int targetRow, int targetColumn) {

    }

    @Override
    public List<int[]> getValidMoves() {
        return null;
    }
}
