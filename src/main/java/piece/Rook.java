package piece;

import board.Board;

public class Rook implements Piece {
  private Color color;
  private int row;
  private int col;

  public Rook(Color color, int row, int col) {
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
    return PieceType.ROOK;
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
    Piece targetPiece = board.getPiece(toRow, toCol);
    if (targetPiece != null && targetPiece.getColor() == this.color)
      return false;
    if (fromRow == toRow || fromCol == toCol) {
      return isPathClear(fromCol, fromRow, toCol, toRow, board);
    }
    return false;
  }

  private boolean isPathClear(int fromCol, int fromRow, int toCol, int toRow, Board board) {
    int rowStep = Integer.compare(toRow, fromRow);
    int colStep = Integer.compare(toCol, fromCol);
    int currentRow = fromRow + rowStep;
    int currentCol = fromCol + colStep;
    while (currentRow != toRow || currentCol != toCol) {
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
    return new Rook(this.color, this.row, this.col);
  }

  @Override
  public String toString() {
    return color + " Rook";
  }
}
