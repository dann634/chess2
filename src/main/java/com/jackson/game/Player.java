package com.jackson.game;

import java.util.*;

public class Player {
    private boolean isWhite;
    private boolean isTurn;
    private boolean inCheck;

    private List<Piece> pieces;

    private boolean hasMoved;

    private boolean piecesEnabled;

    public Player(boolean isWhite) {
        this.isWhite = isWhite;
        if(isWhite) { //white moves first
            this.isTurn = true;
            this.piecesEnabled = true;
        } else {
            this.isTurn = false;
            this.piecesEnabled = false;
        }

        this.hasMoved = false;
        this.inCheck = false;

    }

    public void move(int row, int column, Piece selectedPiece) {
        //this.selectedPiece moves to row,column and takes if needed
        //row and col of piece are updated

        //DESTROY ENEMY PIECE
        boolean destroyPiece = false;
        if(selectedPiece.isCellOccupied(row, column)) {
            if(this.isWhite && !selectedPiece.isTargetWhite(row, column)) {
                destroyPiece = true;
            } else if(!this.isWhite && selectedPiece.isTargetWhite(row, column)) {
                destroyPiece = true;
            }
        }

        if(destroyPiece) { //Eating pieces
            Piece enemy = Game.getPiece(row, column);
            if(enemy != null) {
                Game.destroyPiece(enemy);
            }
        }

        selectedPiece.setRow(row); //new row
        selectedPiece.setColumn(column); //new column

        this.hasMoved = true;
        this.isTurn = false;

    }

    public void isChecked() {
        King myKing = King.getKing(this.isWhite);
        if(myKing.isInCheck()) {
            this.inCheck = true;
            if(this.isWhite) {
                System.out.println("White King in Check");
            } else {
                System.out.println("Black King in Check");
            }
        } else {
            this.inCheck = false;
        }
    }




    public boolean getTurn() {
        return this.isTurn;
    }

    public void enablePieces() {
        this.piecesEnabled = true;
        for(Piece piece : this.pieces) {
            piece.getImageView().setDisable(false);
        }
    }

    public void disablePieces() {
        this.piecesEnabled = false;
        for(Piece piece : pieces) {
            piece.getImageView().setDisable(true);
        }
    }

    public boolean hasWon() {
        return false;
    }

    public Set<int[]> getAllMoves() {
        Set<int[]> allMoves = new HashSet<>();
        for(Piece piece : this.pieces) {
            allMoves.addAll(piece.getValidMoves());
        }
        return allMoves;
    }

    public Set<int[]> getAllMovesButKing() {
        Set<int[]> moves = new HashSet<>();
        for(Piece piece : this.pieces) {
            if(!piece.getClass().getSimpleName().equals("King")) {
                moves.addAll(piece.getValidMoves());
//                for(int[] move : piece.()) {
//                    System.out.println(piece.getClass().getSimpleName() + ": (" + move[1] + "," + move[0] +")");
//                }
            }
        }
        return moves;
    }

    public void initializePieces() {
        this.pieces = new ArrayList<>();
        if(this.isWhite) {
            for (int i = 0; i < 8; i++) {
                this.pieces.add(new Pawn(true, 6, i));
            }
            this.pieces.add(new Rook(true, 7, 0));
            this.pieces.add(new Rook(true, 7, 7));
            this.pieces.add(new Knight(true, 7, 1));
            this.pieces.add(new Knight(true, 7, 6));
            this.pieces.add(new Bishop(true, 7, 2));
            this.pieces.add(new Bishop(true, 7, 5));
            this.pieces.add(new Queen(true, 7, 3));
            this.pieces.add(new King(true, 7, 4));
        } else {
            for (int i = 0; i < 8; i++) {
                this.pieces.add(new Pawn(false, 1, i));
            }
            this.pieces.add(new Rook(false, 0, 0));
            this.pieces.add(new Rook(false, 0, 7));
            this.pieces.add(new Knight(false, 0, 1));
            this.pieces.add(new Knight(false, 0, 6));
            this.pieces.add(new Bishop(false, 0, 2));
            this.pieces.add(new Bishop(false, 0, 5));
            this.pieces.add(new Queen(false, 0, 3));
            this.pieces.add(new King(false, 0, 4));
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }


    public boolean arePiecesEnabled() {
        return this.piecesEnabled;
    }

    public void setPiecesEnabled(boolean piecesEnabled) {
        this.piecesEnabled = piecesEnabled;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public static boolean isColourOpposite(boolean isWhite, Piece piece) {
        if((isWhite && piece.isWhite()) || (!isWhite && !piece.isWhite())) {
            return false;
        }
        return true;
    }

}
