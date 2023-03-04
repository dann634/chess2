package com.jackson.game;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    public Rook(boolean isWhite, int row, int column) {
        super(isWhite, row, column, "Rook");
    }

    @Override
    public List<int[]> getValidMoves() {
        return getAllMoves();
    }

    @Override
    protected List<int[]> getAllMoves() {
        List<int[]> allMoves = new ArrayList<>();


        //Up
        int row = this.getRow();
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
        int column = this.getColumn();
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
}
