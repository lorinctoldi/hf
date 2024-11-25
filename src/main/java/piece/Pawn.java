package piece;

import board.Board;

public class Pawn implements Piece {
  private Color color;
  private int row;
  private int col;

  public Pawn(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if (originRow == targetRow && originCol == targetCol) {
      return false;
    }
    int direction = (this.color == Piece.Color.WHITE) ? -1 : 1;
    if (targetRow == originRow + direction && targetCol == originCol) {
      if (board.getPiece(targetRow, targetCol) == null) {
        return true;
      }
    }
    if (((this.color == Piece.Color.WHITE) && originRow == 6) || ((this.color == Piece.Color.BLACK) && originRow == 1)) {
      if (targetRow == originRow + 2 * direction && targetCol == originCol) {
        if (board.getPiece(targetRow, targetCol) == null && board.getPiece(originRow + direction, originCol) == null) {
          return true;
        }
      }
    }
    if (targetRow == originRow + direction && Math.abs(targetCol - originCol) == 1) {
      Piece targetPiece = board.getPiece(targetRow, targetCol);
      if (targetPiece != null) {
        return targetPiece.getColor() != this.color;
      }
    }
    return false;
  }

  @Override
  public Piece copy() {
    return new Pawn(this.color, this.row, this.col);
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  @Override
  public void setRow(int row) {
    this.row = row;
  }
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public PieceType getType() {
    return PieceType.PAWN;
  }

  @Override
  public String toString() {
    return color + " Pawn";
  }
}
