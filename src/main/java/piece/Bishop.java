package piece;

import board.Board;

public class Bishop implements Piece {
  private Color color;
  private int row;
  private int col;

  public Bishop(Color color, int row, int col) {
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
    return PieceType.BISHOP;
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
    if (Math.abs(toRow - fromRow) == Math.abs(toCol - fromCol)) {
      int rowDirection = (toRow - fromRow) > 0 ? 1 : -1;
      int colDirection = (toCol - fromCol) > 0 ? 1 : -1;
      int currentRow = fromRow + rowDirection;
      int currentCol = fromCol + colDirection;

      while (currentRow != toRow && currentCol != toCol) {
        if (board.getPiece(currentRow, currentCol) != null) {
          return false;
        }
        currentRow += rowDirection;
        currentCol += colDirection;
      }

      Piece targetPiece = board.getPiece(toRow, toCol);
      if (targetPiece == null) {
        return true;
      }
      if (targetPiece.getColor() != this.color && targetPiece.getType() != Piece.PieceType.KING) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return color + " Pawn";
  }
}
