package com.jackson.game;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private List<int[]> allMoves;
    public Queen(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Queen");
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

        //Up
        row = this.getRow();
        while(row > 0) {
            row--;
            if(!isCellOccupied(row, this.getColumn())) {
                allMoves.add(new int[]{this.getColumn(), row});
            } else if(isCellOccupied(row, this.getColumn()) && Player.isColourOpposite(this.isWhite(), Game.getPiece(row, this.getColumn()))) {
                allMoves.add(new int[]{this.getColumn(), row});
                break;
            }
            else {
                break;
            }
        }

        //Right
        column = this.getColumn();
        while(column < 8) {
            column++;
            if(!isCellOccupied(this.getRow(), column)) {
                allMoves.add(new int[]{column, this.getRow()});
            } else if (isCellOccupied(this.getRow(), column) && Player.isColourOpposite(this.isWhite(), Game.getPiece(this.getRow(), column))) {
                allMoves.add(new int[]{column, this.getRow()});
                break;
            } else {
                break;
            }
        }

        //Down
        row = this.getRow();
        while(row < 8) {
            row++;
            if(!isCellOccupied(row, this.getColumn())) {
                allMoves.add(new int[]{this.getColumn(), row});
            } else if (isCellOccupied(row, this.getColumn()) && Player.isColourOpposite(this.isWhite(), Game.getPiece(row, this.getColumn()))) {
                allMoves.add(new int[]{this.getColumn(), row});
                break;
            } else {
                break;
            }
        }

        //Left
        column = this.getColumn();
        while(column > 0) {
            column--;
            if(!isCellOccupied(this.getRow(), column)) {
                allMoves.add(new int[]{column, this.getRow()});
            } else if (isCellOccupied(this.getRow(), column) && Player.isColourOpposite(this.isWhite(), Game.getPiece(this.getRow(), column))) {
                allMoves.add(new int[]{column, this.getRow()});
                break;
            } else {
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
