package game;

import java.util.List;

public class Knight extends Piece{
    public Knight(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Knight");
    }

    @Override
    public void move(Piece piece, int targetRow, int targetColumn) {

    }

    @Override
    public List<int[]> getValidMoves() {
        return null;
    }
}
