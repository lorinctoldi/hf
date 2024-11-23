package board;

import piece.*;

import java.util.ArrayList;

public class Board {
  private ArrayList<ArrayList<Piece>> pieces;
  
  private Piece.Color turn;

  public Board() {
    initializeBoard();
  }

  public void initializeBoard() {
    this.turn = Piece.Color.WHITE;

    pieces = new ArrayList<>(8);
    for (int row = 0; row < 8; row++) {
      ArrayList<Piece> rowPieces = new ArrayList<>(8);
      for (int col = 0; col < 8; col++) {
        if (row == 1) {
          rowPieces.add(new Pawn(Piece.Color.BLACK, row, col));
        } else if (row == 6) {
          rowPieces.add(new Pawn(Piece.Color.WHITE, row, col));
        } else {
          rowPieces.add(null);
        }
      }
      pieces.add(rowPieces);
    }
    
    setupInitialPieces();
    System.out.println("Board is setup");
  }

  private void setupInitialPieces() {
    pieces.get(0).set(0, new Rook(Piece.Color.BLACK, 0, 0));
    pieces.get(0).set(1, new Knight(Piece.Color.BLACK, 0, 1));
    pieces.get(0).set(2, new Bishop(Piece.Color.BLACK, 0, 2));
    pieces.get(0).set(3, new Queen(Piece.Color.BLACK, 0, 3));
    pieces.get(0).set(4, new King(Piece.Color.BLACK, 0, 4));
    pieces.get(0).set(5, new Bishop(Piece.Color.BLACK, 0, 5));
    pieces.get(0).set(6, new Knight(Piece.Color.BLACK, 0, 6));
    pieces.get(0).set(7, new Rook(Piece.Color.BLACK, 0, 7));
    pieces.get(7).set(0, new Rook(Piece.Color.WHITE, 7, 0));
    pieces.get(7).set(1, new Knight(Piece.Color.WHITE, 7, 1));
    pieces.get(7).set(2, new Bishop(Piece.Color.WHITE, 7, 2));
    pieces.get(7).set(3, new Queen(Piece.Color.WHITE, 7, 3));
    pieces.get(7).set(4, new King(Piece.Color.WHITE, 7, 4));
    pieces.get(7).set(5, new Bishop(Piece.Color.WHITE, 7, 5));
    pieces.get(7).set(6, new Knight(Piece.Color.WHITE, 7, 6));
    pieces.get(7).set(7, new Rook(Piece.Color.WHITE, 7, 7));
  }

  public Piece getPiece(int row, int col) {
    if (row < 0 || row >= 8 || col < 0 || col >= 8) {
      return null;
    }
    return pieces.get(row).get(col);
  }

  public void setPiece(int row, int col, Piece piece) {
    if (row >= 0 && row < 8 && col >= 0 && col < 8) {
      pieces.get(row).set(col, piece);
    }
  }

  public Piece.Color getTurn() {
    return this.turn;
  }

  public void changeTurn() {
    this.turn = (this.turn == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE);
  }
}
