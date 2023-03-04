package com.jackson.game;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    public Knight(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Knight");
    }

    @Override
    public List<int[]> getValidMoves() {
        List<int[]> allMoves = getAllMoves();
        List<int[]> invalidMoves = new ArrayList<>();
        for(int[] move : allMoves) {
            if(isCellOccupied(move[1], move[0])) {
               if(this.isWhite() && isTargetWhite(move[1], move[0])) {
                   invalidMoves.add(move);
               } else if(!this.isWhite() && !isTargetWhite(move[1], move[0])) {
                   invalidMoves.add(move);
               }
            }

            //is out of bounds
            if(move[0] < 0 || move[0] > 7) {
                invalidMoves.add(move);
            }

            if(move[1] < 0 || move[1] > 7) {
                invalidMoves.add(move);
            }

        }

        allMoves.removeAll(invalidMoves);

        return allMoves;
    }

    @Override
    protected List<int[]> getAllMoves() {
        List<int[]> allMoves = new ArrayList<>();
        try {
            //Forward
            allMoves.add(new int[]{this.getColumn()-1, this.getRow()-2});
            allMoves.add(new int[]{this.getColumn()+1, this.getRow()-2});

            //Right
            allMoves.add(new int[]{this.getColumn()+2, this.getRow()+1});
            allMoves.add(new int[]{this.getColumn()+2, this.getRow()-1});

            //Down
            allMoves.add(new int[]{this.getColumn()+1, this.getRow()+2});
            allMoves.add(new int[]{this.getColumn()-1, this.getRow()+2});

            //Left
            allMoves.add(new int[]{this.getColumn()-2, this.getRow()+1});
            allMoves.add(new int[]{this.getColumn()-2, this.getRow()-1});
        } catch (IndexOutOfBoundsException ignored) {}
        return allMoves;
    }

}
