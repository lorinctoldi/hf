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
  public Color getColor() {
    return color;
  }

  @Override
  public PieceType getType() {
    return PieceType.PAWN;
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
  public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Board board) {
    if (fromRow == toRow && fromCol == toCol) {
      return false;
    }
    int direction = (this.color == Piece.Color.WHITE) ? -1 : 1;
    if (toRow == fromRow + direction && toCol == fromCol) {
      if (board.getPiece(toRow, toCol) == null) {
        return true;
      }
    }
    if (((this.color == Piece.Color.WHITE) && fromRow == 6) || ((this.color == Piece.Color.BLACK) && fromRow == 1)) {
      if (toRow == fromRow + 2 * direction && toCol == fromCol) {
        if (board.getPiece(toRow, toCol) == null && board.getPiece(fromRow + direction, fromCol) == null) {
          return true;
        }
      }
    }
    if (toRow == fromRow + direction && Math.abs(toCol - fromCol) == 1) {
      Piece targetPiece = board.getPiece(toRow, toCol);
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
  public String toString() {
    return color + " Pawn";
  }
}
