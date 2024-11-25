package chess.piece;

import chess.board.Board;

/**
 * Az összes sakkfigura közös felületét meghatározó interfész.
 * Minden figura implementálja ezt az interfészt, biztosítva a közös funkciók és
 * jellemzők egységességét.
 */
public interface Piece {
  /**
   * Ellenőrzi, hogy egy adott lépés érvényes-e a figura számára.
   * 
   * @param originCol Az eredeti oszlop.
   * @param originRow Az eredeti sor.
   * @param targetCol A cél oszlop.
   * @param targetRow A cél sor.
   * @param board     A játék táblája.
   * @return true, ha a lépés érvényes, különben false.
   */
  boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board);

  /**
   * Létrehoz egy másolatot a figuráról.
   * 
   * @return A figura új példánya, amely azonos attribútumokkal rendelkezik.
   */
  public Piece copy();

  /**
   * Visszaadja a figura aktuális sorát.
   * 
   * @return A figura sora.
   */
  int getRow();

  /**
   * Visszaadja a figura aktuális oszlopát.
   * 
   * @return A figura oszlopa.
   */
  int getCol();

  /**
   * Beállítja a figura sorát.
   * 
   * @param row Az új sor értéke.
   */
  void setRow(int row);

  /**
   * Beállítja a figura oszlopát.
   * 
   * @param col Az új oszlop értéke.
   */
  void setCol(int col);

  /**
   * Visszaadja a figura színét.
   * 
   * @return A figura színe (WHITE vagy BLACK).
   */
  Color getColor();

  /**
   * Visszaadja a figura típusát.
   * 
   * @return A figura típusa (PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING).
   */
  PieceType getType();

  /**
   * Visszaadja a figura értékét a sakkban elfogadott pontértékek alapján.
   * 
   * @return A figura értéke: gyalog (1), huszár (3), futó (3), bástya (5), vezér
   *         (9), király (0).
   */
  default int getValue() {
    switch (this.getType()) {
      case PAWN:
        return 1;
      case KNIGHT:
        return 3;
      case BISHOP:
        return 3;
      case ROOK:
        return 5;
      case QUEEN:
        return 9;
      case KING:
        return 0;
      default:
        return 0;
    }
  }

  /**
   * Ellenőrzi, hogy az aktuális figura egyenlő-e egy másik objektummal.
   * 
   * @param obj Az összehasonlítandó objektum.
   * @return true, ha a két figura egyenlő, különben false.
   */
  public boolean equals(Object obj);

  /**
   * A figura színének felsorolása (WHITE vagy BLACK).
   */
  enum Color {
    WHITE, BLACK
  }

  /**
   * A figura típusának felsorolása.
   * Lehetséges értékek: PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING.
   */
  enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
  }
}
