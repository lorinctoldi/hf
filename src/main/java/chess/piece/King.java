package chess.piece;

import chess.board.Board;

public class King implements Piece {
  private Color color;
  private int row;
  private int col;

  /**
   * Konstruktor, amely inicializálja a király színét, sorát és oszlopát.
   * 
   * @param color A király színe.
   * @param row   A király kezdő sora.
   * @param col   A király kezdő oszlopa.
   */
  public King(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Ellenőrzi, hogy a király lépése érvényes-e.
   * A király egy mezőt léphet bármely irányba, de nem léphet olyan mezőre,
   * amelyen saját színű bábu van, és nem állhat olyan mezőre, amely támadott.
   * 
   * @param originCol Az eredeti oszlop.
   * @param originRow Az eredeti sor.
   * @param targetCol A cél oszlop.
   * @param targetRow A cél sor.
   * @param board     A játék táblája.
   * @return true, ha a lépés érvényes, különben false.
   */
  @Override
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if (Math.abs(targetRow - originRow) <= 1 && Math.abs(targetCol - originCol) <= 1) {
      Piece targetPiece = board.getPiece(targetRow, targetCol);
      if (targetPiece != null && targetPiece.getColor() == this.color) {
        return false;
      }

      for (int row = targetRow - 1; row <= targetRow + 1; row++) {
        for (int col = targetCol - 1; col <= targetCol + 1; col++) {
          if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            Piece piece = board.getPiece(row, col);

            if (piece != null && piece.getType() == PieceType.KING && piece.getColor() != this.color) {
              return false;
            }
          }
        }
      }

      if (board.willKingBeInCheck(originCol, originRow, targetCol, targetRow, this.color))
        return false;
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

  /**
   * Létrehoz egy új példányt a királyról a jelenlegi pozícióval és színnel.
   * 
   * @return A király másolatát.
   */
  @Override
  public Piece copy() {
    return new King(this.color, this.row, this.col);
  }

  /**
   * Visszaadja a király aktuális sorát.
   * 
   * @return A király sora.
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Visszaadja a király aktuális oszlopát.
   * 
   * @return A király oszlopa.
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Beállítja a király sorát.
   * 
   * @param row Az új sor.
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Beállítja a király oszlopát.
   * 
   * @param col Az új oszlop.
   */
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Visszaadja a király színét.
   * 
   * @return A király színe.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Visszaadja a király típusát.
   * 
   * @return A király típusa.
   */
  @Override
  public PieceType getType() {
    return PieceType.KING;
  }

  /**
   * Összehasonlítja a királyt egy másik objektummal, hogy meghatározza,
   * egyenlők-e.
   * Két király akkor egyenlő, ha ugyanazon színűek és típusúak.
   * 
   * @param obj A másik objektum.
   * @return true, ha az objektum ugyanazt a királyt jelenti, különben false.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Piece piece = (Piece) obj;
    return this.color == piece.getColor() && this.getType().equals(piece.getType());
  }

  /**
   * Visszaadja a király színét és típusát szöveges formában.
   * 
   * @return A király színe és típusa.
   */
  @Override
  public String toString() {
    return color + " King";
  }
}
