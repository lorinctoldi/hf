package chess.piece;

import chess.board.Board;

public class Pawn implements Piece {
  private Color color;
  private int row;
  private int col;

  /**
   * Konstruktor, amely inicializálja a gyalog színét, sorát és oszlopát.
   * 
   * @param color A gyalog színe.
   * @param row   A gyalog kezdő sora.
   * @param col   A gyalog kezdő oszlopa.
   */
  public Pawn(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Ellenőrzi, hogy a gyalog lépése érvényes-e.
   * A gyalog egy lépésben előre haladhat, ha az mező üres, vagy diagonálisan
   * léphet, ha az ellentétes színű bábu van ott.
   * Emellett, ha a gyalog a kezdő sorban van, két lépést is léphet előre.
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
    if (originRow == targetRow && originCol == targetCol) {
      return false;
    }
    int direction = (this.color == Piece.Color.WHITE) ? -1 : 1;
    if (targetRow == originRow + direction && targetCol == originCol) {
      if (board.getPiece(targetRow, targetCol) == null) {
        return true;
      }
    }
    if (((this.color == Piece.Color.WHITE) && originRow == 6)
        || ((this.color == Piece.Color.BLACK) && originRow == 1)) {
      if (targetRow == originRow + 2 * direction && targetCol == originCol) {
        if (board.getPiece(targetRow, targetCol) == null && board.getPiece(originRow + direction, originCol) == null) {
          return true;
        }
      }
    }
    if (targetRow == originRow + direction && Math.abs(targetCol - originCol) == 1) {
      Piece targetPiece = board.getPiece(targetRow, targetCol);
      if (targetPiece != null) {
        return targetPiece.getColor() != this.color;
      }
    }
    return false;
  }

  /**
   * Létrehoz egy új példányt a gyaloggal, amely a jelenlegi pozícióval és színnel
   * rendelkezik.
   * 
   * @return A gyalog másolatát.
   */
  @Override
  public Piece copy() {
    return new Pawn(this.color, this.row, this.col);
  }

  /**
   * Visszaadja a gyalog aktuális sorát.
   * 
   * @return A gyalog sora.
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Visszaadja a gyalog aktuális oszlopát.
   * 
   * @return A gyalog oszlopa.
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Beállítja a gyalog sorát.
   * 
   * @param row Az új sor.
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Beállítja a gyalog oszlopát.
   * 
   * @param col Az új oszlop.
   */
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Visszaadja a gyalog színét.
   * 
   * @return A gyalog színe.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Visszaadja a gyalog típusát.
   * 
   * @return A gyalog típusa.
   */
  @Override
  public PieceType getType() {
    return PieceType.PAWN;
  }

  /**
   * Összehasonlítja a gyalogot egy másik objektummal, hogy meghatározza,
   * egyenlők-e.
   * Két gyalog akkor egyenlő, ha ugyanazon színűek és típusúak.
   * 
   * @param obj A másik objektum.
   * @return true, ha az objektum ugyanazt a gyalogot jelenti, különben false.
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
   * Visszaadja a gyalog színét és típusát szöveges formában.
   * 
   * @return A gyalog színe és típusa.
   */
  @Override
  public String toString() {
    return color + " Pawn";
  }
}
