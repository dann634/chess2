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
//        board.addMovementIndicators(getValidMoves());
    }

    public List<int[]> getValidMoves() {
        List<int[]> allMoves = getAllMoves();
        List<int[]> invalidMoves = new ArrayList<>();

        try {
            //move forward one
            int[] move1 = allMoves.get(0);
            if(isCellOccupied(move1[1], move1[0])) {
                invalidMoves.add(move1);
            }

            //move forward two
            int[] move2 = allMoves.get(1);
            if(this.isWhite()) {
                if(this.getRow() != 6) { //if not on starting square
                    invalidMoves.add(move2);
                } else if(isCellOccupied(move2[1], move2[0]) || isCellOccupied(move1[1], move1[0])) { //if on starting square but cells are blocked
                    invalidMoves.add(move2);
                }
            } else {
                if(this.getRow() != 1) { //if not on starting square
                    invalidMoves.add(move2);
                } else if(isCellOccupied(move2[1], move2[0]) || isCellOccupied(move1[1], move1[0])) { //if on starting square but cells are blocked
                    invalidMoves.add(move2);
                }
            }

            //take on left diagonal
            int[] move3 = allMoves.get(2);
            if(this.isWhite()) {
                if(isCellOccupied(move3[1], move3[0]) && isTargetWhite(move3[1], move3[0])) {
                    invalidMoves.add(move3);
                } else if(!isCellOccupied(move3[1], move3[0])){
                    invalidMoves.add(move3);
                }
            } else {
                if(isCellOccupied(move3[1], move3[0]) && !isTargetWhite(move3[1], move3[0])) {
                    invalidMoves.add(move3);
                } else if(!isCellOccupied(move3[1], move3[0])){
                    invalidMoves.add(move3);
                }
            }

            //take on right diagonal
            int[] move4 = allMoves.get(3);
            if(this.isWhite()) {
                if(isCellOccupied(move4[1], move4[0]) && isTargetWhite(move4[1], move4[0])) {
                    invalidMoves.add(move4);
                } else if(!isCellOccupied(move4[1], move4[0])){
                    invalidMoves.add(move4);
                }
            } else {
                if(isCellOccupied(move4[1], move4[0]) && !isTargetWhite(move4[1], move4[0])) {
                    invalidMoves.add(move4);
                } else if(!isCellOccupied(move4[1], move4[0])){
                    invalidMoves.add(move4);
                }
            }
        } catch (IndexOutOfBoundsException ignored) {}

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
