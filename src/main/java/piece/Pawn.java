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
  public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow, Board board) {
    System.out.println("1 jo");
    if (fromRow == toRow && fromCol == toCol) {
      return false;
    }
    System.out.println("2 jo");
    int direction = (this.color == Piece.Color.WHITE) ? -1 : 1;
    if (toRow == fromRow + direction && toCol == fromCol) {
      if (board.getPiece(toRow, toCol) == null) {
        return true;
      }
    }
    System.out.println("3 jo");
    if (((this.color == Piece.Color.WHITE) && fromRow == 6) || ((this.color == Piece.Color.BLACK) && fromRow == 1)) {
      if (toRow == fromRow + 2 * direction && toCol == fromCol) {
        if (board.getPiece(toRow, toCol) == null && board.getPiece(fromRow + direction, fromCol) == null) {
          return true;
        }
      }
    }
    System.out.println("4 jo");
    if (toRow == fromRow + direction && Math.abs(toCol - fromCol) == 1) {
      System.out.println("5 jo");
      Piece targetPiece = board.getPiece(toRow, toCol);
      if (targetPiece != null) {
        System.out.println("6 jo");
        if (targetPiece.getType() == Piece.PieceType.KING) {
          System.out.println("7 jo");
          return false;
        }
        return targetPiece.getColor() != this.color;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return color + " Pawn";
  }
}
