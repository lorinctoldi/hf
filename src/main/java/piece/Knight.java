package piece;

import board.Board;

public class Knight implements Piece {
  private Color color;
  private int row;
  private int col;

  public Knight(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if(originRow == targetRow && originCol == targetCol) {
      return false;
    }
      int rowDiff = Math.abs(targetRow - originRow);
      int colDiff = Math.abs(targetCol - originCol);
      boolean isLShapeMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

      Piece targetPiece = board.getPiece(targetRow, targetCol);

      return isLShapeMove && (targetPiece == null || targetPiece.getColor() != this.color);
  }

    @Override
  public Piece copy() {
    return new Knight(this.color, this.row, this.col);
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
    return PieceType.KNIGHT;
  }

  @Override
  public String toString() {
    return color + " Knight";
  }
}
