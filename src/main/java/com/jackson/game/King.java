package com.jackson.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends Piece{
    public King(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "King");
    }

    @Override
    public List<int[]> getValidMoves() {
        List<int[]> allMoves = getAllMoves();
        List<int[]> invalidMoves = new ArrayList<>();
        for(int[] move : allMoves) {
            if(move[0] < 0 || move[0] > 7) { //If in bounds
                invalidMoves.add(move);
            } else if(move[1] < 0 || move[1] > 7) { //If in bounds
                invalidMoves.add(move);
            } else if (isCellOccupied(move[1], move[0])){
                if((this.isWhite() && isTargetWhite(move[1], move[0])) || (!this.isWhite() && !isTargetWhite(move[1], move[0]))) { //Cant move on friendly piece
                    invalidMoves.add(move);
                }
            } else if(isMoveProtected(move[1], move[0])) { //Cant move into check
                invalidMoves.add(move);
                System.out.println("(" + move[1] + "," + move[0] + ") is protected");
            }
        }
        allMoves.removeAll(invalidMoves);
        return allMoves;
    }

    private boolean isMoveProtected(int row, int column) {
        Set<int[]> allEnemyMoves = this.isWhite() ? Game.player2.getAllMovesButKing() : Game.player1.getAllMovesButKing();
        allEnemyMoves.addAll(King.getKing(!this.isWhite()).getAllMoves());
        return Game.contains(allEnemyMoves, new int[]{column, row});

        // TODO: 04/03/2023 Special case the pawn as the move only becomes valid once the king has moved there

    }

    /*
    Get all pieces excluding king
    Remove all moves surrounding enemy king and add to invalid moves
     */

    public boolean isInCheck() {
        Set<int[]> allEnemyMoves = new HashSet<>();
        if(isWhite()) {
            //Get all black moves'
            allEnemyMoves.addAll(Game.player2.getAllMoves());
        } else {
            //Get all white moves
            allEnemyMoves.addAll(Game.player1.getAllMoves());
        }

        return Game.contains(allEnemyMoves, new int[]{getColumn(), getRow()});
    }

    public static King getKing(boolean isWhite) {
        if(isWhite) {
            for(Piece piece : Game.player1.getPieces()) {
                if(piece.getClass().equals(King.class)) {
                    return (King) piece;
                }
            }
        } else {
            for(Piece piece : Game.player2.getPieces()) {
                if(piece.getClass().equals(King.class)) {
                    return (King) piece;
                }
            }
        }
        return null;
    }

    @Override
    protected List<int[]> getAllMoves() {
        List<int[]> allMoves = new ArrayList<>();

        //Sides
        for (int i = -1; i < 2; i++) {
            allMoves.add(new int[]{this.getColumn()-1, this.getRow()+i});
            allMoves.add(new int[]{this.getColumn()+1, this.getRow()+i});
        }

        //Top
        allMoves.add(new int[]{this.getColumn(), this.getRow()-1});

        //Bottom
        allMoves.add(new int[]{this.getColumn(), this.getRow()+1});



        return allMoves;
    }
}
