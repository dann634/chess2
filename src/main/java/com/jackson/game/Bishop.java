package com.jackson.game;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private List<int[]> allMoves;

    public Bishop(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Bishop");
    }

    @Override
    public List<int[]> getValidMoves() {
        return getAllMoves();
    }

    @Override
    protected List<int[]> getAllMoves() {

        allMoves = new ArrayList<>();

        //Top Left Diagonal

        int row = this.getRow();

        int column = this.getColumn();

        boolean onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);

        while (onBoard) {
            row--;
            column--;
            onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
            if (!addNewMove(row, column)) {
                break;
            }
        }

        //Top Right Diagonal

        row = this.getRow();
        column = this.getColumn();
        onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
        while (onBoard) {
            column++;
            row--;
            onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
            if (!addNewMove(row, column)) {
                break;
            }
        }

        //Bottom Left Diagonal

        row = this.getRow();
        column = this.getColumn();
        onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
        while (onBoard) {
            row++;
            column--;
            onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
            if (!addNewMove(row, column)) {
                break;
            }
        }

        //Bottom Right Diagonal
        row = this.getRow();
        column = this.getColumn();
        onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
        while (onBoard) {
            row++;
            column++;
            onBoard = (row >= 0 && row <= 7 && column >= 0 && column <= 7);
            if (!addNewMove(row, column)) {
                break;
            }
        }

        return allMoves;
    }

    private boolean addNewMove(int row, int column) {
        if (row >= 0 && row <= 7 && column >= 0 && column <= 7) {
            if (!isCellOccupied(row, column)) {
                allMoves.add(new int[]{column, row});
                return true;
            } else if (isCellOccupied(row, column) && Player.isColourOpposite(isWhite(), Game.getPiece(row, column))) {
                allMoves.add(new int[]{column, row});
                return false;
            }
        }
        return false;
    }
}
