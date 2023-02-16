package game;

import java.util.List;

public class Bishop extends Piece{
    public Bishop(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Bishop");
    }

    @Override
    public void move(Piece piece, int targetRow, int targetColumn) {

    }

    @Override
    public List<int[]> getValidMoves() {
        return null;
    }
}
