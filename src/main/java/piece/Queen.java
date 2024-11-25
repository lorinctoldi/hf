package piece;

import board.Board;

public class Queen implements Piece {
  private Color color;
  private int row;
  private int col;

  public Queen(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if (originRow == targetRow && originCol == targetCol) {
      return false;
    }

    int rowDiff = Math.abs(targetRow - originRow);
    int colDiff = Math.abs(targetCol - originCol);

    Piece targetPiece = board.getPiece(targetRow, targetCol);
    if (targetPiece != null && (targetPiece.getColor() == this.color)) {
      return false;
    }

    if (rowDiff == 0 || colDiff == 0 || rowDiff == colDiff) {
      return isPathClear(originCol, originRow, targetCol, targetRow, board);
    }

    return false;
  }

  private boolean isPathClear(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    int rowStep = Integer.compare(targetRow, originRow);
    int colStep = Integer.compare(targetCol, originCol);
    int currentRow = originRow + rowStep;
    int currentCol = originCol + colStep;
    while (currentRow != targetRow || currentCol != targetCol) {
      if (board.getPiece(currentRow, currentCol) != null) {
        return false;
      }
      currentRow += rowStep;
      currentCol += colStep;
    }
    return true;
  }

  @Override
  public Piece copy() {
    return new Queen(this.color, this.row, this.col);
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
    return PieceType.QUEEN;
  }

  @Override
  public String toString() {
    return color + " Queen";
  }
}
