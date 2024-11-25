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
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if (Math.abs(targetRow - originRow) <= 1 && Math.abs(targetCol - originCol) <= 1) {
      Piece targetPiece = board.getPiece(targetRow, targetCol);
      if (targetPiece != null && targetPiece.getColor() == this.color) {
        return false;
      }

      for (int r = targetRow - 1; r <= targetRow + 1; r++) {
        for (int c = targetCol - 1; c <= targetCol + 1; c++) {
          if (r >= 0 && r < 8 && c >= 0 && c < 8) {
            Piece piece = board.getPiece(r, c);

            if (piece != null && piece.getType() == PieceType.KING && piece.getColor() != this.color) {
              return false;
            }
          }
        }
      }

      if(board.willKingBeInCheck(originCol, originRow, targetCol, targetRow, this.color)) return false;
      return true;
    }

    if (originRow == targetRow && Math.abs(targetCol - originCol) == 2) {
      if (board.hasKingMoved(this.color) || board.hasRookMoved(this.color, targetCol > originCol)) {
        return false;
      }
  
      int direction = (targetCol > originCol) ? 1 : -1;
      int rookCol = (targetCol > originCol) ? 7 : 0;
  
      for (int col = originCol + direction; col != rookCol; col += direction) {
        if (board.getPiece(originRow, col) != null) {
          return false;
        }
      }
  
      for (int col = originCol + direction; col <= rookCol; col += direction) {
        if (board.isSquareUnderAttack(originRow, col, this.color)) {
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
    return PieceType.KING;
  }

  @Override
  public String toString() {
    return color + " King";
  }
}
