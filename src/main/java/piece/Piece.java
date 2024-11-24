package piece;

import board.Board;

public interface Piece {
  boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Board board);

  int getRow();

  int getCol();

  void setRow(int row);

  void setCol(int col);

  Color getColor();

  PieceType getType();

  public Piece copy();

  enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
  }
  
  enum Color {
    WHITE, BLACK
  }  
}
