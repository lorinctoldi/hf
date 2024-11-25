package piece;

import board.Board;

public interface Piece {
  boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board);

  public Piece copy();

  int getRow();

  int getCol();

  void setRow(int row);

  void setCol(int col);

  Color getColor();

  PieceType getType();

  default int getValue() {
    switch (this.getType()) {
      case PAWN:
        return 1;
      case KNIGHT:
        return 3;
      case BISHOP:
        return 3;
      case ROOK:
        return 5;
      case QUEEN:
        return 9;
      case KING:
        return 0;
      default:
        return 0;
    }
  }

  enum Color {
    WHITE, BLACK
  }

  enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
  }
}
