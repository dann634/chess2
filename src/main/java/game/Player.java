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

        this.pawns = 8;
        this.knights = 2;
        this.rooks = 2;
        this.bishops = 2;
        this.queen = 1;
    }

    public void move(int row, int column, Piece selectedPiece) {
        //this.selectedPiece moves to row,column and takes if needed
        //row and col of piece are updated

        selectedPiece.setRow(row); //new row
        selectedPiece.setColumn(column); //new column

        this.hasMoved = true;
        this.isTurn = false;

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
            this.pieces.add(new Pawn(false, 5, 4));
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
}
