package game;

import java.util.List;

public class King extends Piece{
    public King(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "King");
    }

    @Override
    public void move(Piece piece, int targetRow, int targetColumn) {

    }

    @Override
    public List<int[]> getValidMoves() {
        return null;
    }
}
