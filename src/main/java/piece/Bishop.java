package piece;

import java.util.HashMap;
import java.util.Map;

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
      if (Math.abs(toRow - fromRow) != Math.abs(toCol - fromCol)) {
          return false;
      }
  
      int rowStep = Integer.compare(toRow, fromRow);
      int colStep = Integer.compare(toCol, fromCol);
  
      return isPathClear(fromCol, fromRow, toCol, toRow, rowStep, colStep, board);
  }
  
  private boolean isPathClear(int fromCol, int fromRow, int toCol, int toRow, int rowStep, int colStep, Board board) {
      int currentRow = fromRow + rowStep;
      int currentCol = fromCol + colStep;
  
      while (currentRow != toRow && currentCol != toCol) {
          if (board.getPiece(currentRow, currentCol) != null) {
              return false;
          }
          currentRow += rowStep;
          currentCol += colStep;
      }
  
      Piece targetPiece = board.getPiece(toRow, toCol);
      if (targetPiece == null || targetPiece.getColor() != this.color) {
          return true;
      }
  
      return false;
  }
  @Override
  public Piece copy() {
    return new Bishop(this.color, this.row, this.col);
  }

  @Override
  public String toString() {
    return color + " Bishop";
  }
}
