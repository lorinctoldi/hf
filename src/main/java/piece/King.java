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
          // Ensure the cell is within bounds
          if (r >= 0 && r < 8 && c >= 0 && c < 8) {
            Piece piece = board.getPiece(r, c);

            // Check if there's an opposing king in any adjacent cell
            if (piece != null && piece.getType() == PieceType.KING && piece.getColor() != this.color) {
              return false; // Target square is under attack by the opposing king
            }
          }
        }
      }

      if(board.willKingBeInCheck(fromCol, fromRow, toCol, toRow, this.color)) return false;
      return true;
    }

    if (fromRow == toRow && Math.abs(toCol - fromCol) == 2) {
      // Ensure the king and rook have not moved
      if (board.hasKingMoved(this.color) || board.hasRookMoved(this.color, toCol > fromCol)) {
        return false;
      }

      // Ensure no pieces are between the king and the rook
      System.out.println("Checking this");
      int direction = (toCol > fromCol) ? 1 : -1;
      for (int col = fromCol + direction; col != toCol + direction; col += direction) {
        System.out.println(board.getPiece(fromRow, col));
        if (board.getPiece(fromRow, col) != null) {
          System.out.println("Returning false");
          return false;
        }
      }

      // Ensure the king does not pass through or land on a square under attack
      for (int col = fromCol; col != toCol + direction; col += direction) {
        if (board.isSquareUnderAttack(fromRow, col, this.color)) {
          return false;
        }
      }

      return true; // Castling is valid
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
