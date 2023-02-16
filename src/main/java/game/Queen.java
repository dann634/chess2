package game;

import java.util.List;

public class Queen extends Piece{
    public Queen(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Queen");
    }

    @Override
    public void move(Piece piece, int targetRow, int targetColumn) {
    }

    @Override
    public List<int[]> getValidMoves() {
        return null;
    }
}
