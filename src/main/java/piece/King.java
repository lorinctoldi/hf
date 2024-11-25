package piece;

import board.Board;

public class King implements Piece {
  private Color color;
  private int row;
  private int col;

  public King(Color color, int row, int col) {
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
    return PieceType.KING;
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
    if (Math.abs(toRow - fromRow) <= 1 && Math.abs(toCol - fromCol) <= 1) {
      Piece targetPiece = board.getPiece(toRow, toCol);
      if (targetPiece != null && targetPiece.getColor() == this.color) {
        return false;
      }

      for (int r = toRow - 1; r <= toRow + 1; r++) {
        for (int c = toCol - 1; c <= toCol + 1; c++) {
          if (r >= 0 && r < 8 && c >= 0 && c < 8) {
            Piece piece = board.getPiece(r, c);

            if (piece != null && piece.getType() == PieceType.KING && piece.getColor() != this.color) {
              return false;
            }
          }
        }
      }

      if(board.willKingBeInCheck(fromCol, fromRow, toCol, toRow, this.color)) return false;
      return true;
    }

    if (fromRow == toRow && Math.abs(toCol - fromCol) == 2) {
      if (board.hasKingMoved(this.color) || board.hasRookMoved(this.color, toCol > fromCol)) {
        return false;
      }
  
      int direction = (toCol > fromCol) ? 1 : -1;
      int rookCol = (toCol > fromCol) ? 7 : 0;
  
      for (int col = fromCol + direction; col != rookCol; col += direction) {
        if (board.getPiece(fromRow, col) != null) {
          return false;
        }
      }
  
      for (int col = fromCol + direction; col <= rookCol; col += direction) {
        if (board.isSquareUnderAttack(fromRow, col, this.color)) {
          return false;
        }
      }
  
  
      return true;
    }
    return false;
  }

  @Override
  public Piece copy() {
    return new King(this.color, this.row, this.col);
  }

  @Override
  public String toString() {
    return color + " King";
  }
}
