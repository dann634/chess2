package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private boolean isWhite;
    private boolean isTurn;

    private byte pawns;
    private byte knights;
    private byte rooks;
    private byte bishops;
    private byte queen;

    private boolean hasPlayerMoved;

    private List<Piece> pieces;

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

        this.pawns = 8;
        this.knights = 2;
        this.rooks = 2;
        this.bishops = 2;
        this.queen = 1;
    }

    public void makeMove(Piece piece, int targetRow, int targetColumn, Piece[] board) {
        //Move piece into square
        //Update piece row and col
        piece.setRow(targetRow);
        piece.setColumn(targetColumn);
        this.hasPlayerMoved = true;
        //Call takePiece() if necessary
        // TODO: 13/02/2023 finish this so it checks if target is occupied with friendly or enemy
    }

    public static void move(int row, int column, boolean isWhite) {

    }
    private void takePiece() {

    }

    public boolean getTurn() {
        return this.isTurn;
    }

    public void enablePieces() {
        this.piecesEnabled = true;
        for(Piece piece : this.pieces) {
            piece.getImageView().setDisable(false);
        }
        if(this.isWhite) {
            System.out.println("White's Pieces enabled");
        } else {
            System.out.println("Black's Pieces enabled");
        }
    }

    public void disablePieces() {
        this.piecesEnabled = false;
        for(Piece piece : pieces) {
            piece.getImageView().setDisable(true);
        }
        if(this.isWhite) {
            System.out.println("White's Pieces disabled");
        } else {
            System.out.println("Black's Pieces disabled");
        }
    }

    public boolean hasWon() {
        return false;
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

    public boolean hasPlayerMoved() {
        return hasPlayerMoved;
    }

    public void setHasPlayerMoved(boolean hasPlayerMoved) {
        this.hasPlayerMoved = hasPlayerMoved;
    }

    public boolean arePiecesEnabled() {
        return this.piecesEnabled;
    }

    public void setPiecesEnabled(boolean piecesEnabled) {
        this.piecesEnabled = piecesEnabled;
    }

}
