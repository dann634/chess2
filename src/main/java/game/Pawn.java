package game;

import ui.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static game.Game.board;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Pawn");
    }

    @Override
    public void move(Piece piece, int targetRow, int targetColumn) {
        //Add movement indicators
        board.addMovementIndicators(getValidMoves());
        System.out.println("HELLO");
    }

    public List<int[]> getValidMoves() {
        List<int[]> allMoves = getAllMoves();
        List<int[]> invalidMoves = new ArrayList<>();
        boolean isBlocked = false;

        for (int i = 0; i < allMoves.size(); i++) {
            int[] move = allMoves.get(i);
            boolean isCellOccupied = isCellOccupied(move[1], move[0]);
            if(i < 2 && !isBlocked) { //Checks first 2 squares forward
                if(isCellOccupied) {
                    invalidMoves.add(move);
                    isBlocked = true; // FIXME: 16/02/2023 Adding blocked condition makes more indciators>?>!>!>>?!?>!>?!?
                }
            } else {
                if(!isCellOccupied) { //If diagonals are empty they are invalid
                    invalidMoves.add(move);
                } else { //Check if enemy in diagonals
                    if((this.isWhite() == isTargetWhite(move[1], move[0])) || (!this.isWhite() == !isTargetWhite(move[1], move[0]))) {
                        invalidMoves.add(move);
                    }
                }
            }
        }

        allMoves.removeAll(invalidMoves);

        return allMoves;
    }

    private List<int[]> getAllMoves() { //Get all potential moves (for pawn)
        List<int[]> allMoves = new ArrayList<>();
        if(isWhite()) { //All white moves
            allMoves.add(new int[]{this.getColumn(), this.getRow()-1}); //Move forward one square
            allMoves.add(new int[]{this.getColumn(), this.getRow()-2}); //Move forward two squares
            allMoves.add(new int[]{this.getColumn()-1, this.getRow()-1}); //Take on left diagonal
            allMoves.add(new int[]{this.getColumn()+1, this.getRow()-1}); //Take on right diagonal
        } else { //All Black Moves
            allMoves.add(new int[]{this.getColumn(), this.getRow()+1}); //Move forward one square
            allMoves.add(new int[]{this.getColumn(), this.getRow()+2}); //Move forward two squares
            allMoves.add(new int[]{this.getColumn()+1, this.getRow()+1}); //Take on left diagonal
            allMoves.add(new int[]{this.getColumn()-1, this.getRow()+1}); //Take on right diagonal
        }
        return allMoves;
    }
}
