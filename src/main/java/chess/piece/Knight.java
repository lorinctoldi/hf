package chess.piece;

import chess.board.Board;

public class Knight implements Piece {
  private Color color;
  private int row;
  private int col;

  /**
   * Konstruktor, amely inicializálja a ló színét, sorát és oszlopát.
   * 
   * @param color A ló színe.
   * @param row   A ló kezdő sora.
   * @param col   A ló kezdő oszlopa.
   */
  public Knight(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Ellenőrzi, hogy a ló lépése érvényes-e.
   * A ló lépése mindig egy "L" alakú, tehát két mezőt léphet egy irányba
   * (függőlegesen vagy vízszintesen)
   * és egy mezőt a másik irányba. A ló nem léphet a saját színű bábuval elfoglalt
   * mezőre.
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
    int rowDiff = Math.abs(targetRow - originRow);
    int colDiff = Math.abs(targetCol - originCol);
    boolean isLShapeMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

    Piece targetPiece = board.getPiece(targetRow, targetCol);

    return isLShapeMove && (targetPiece == null || targetPiece.getColor() != this.color);
  }

  /**
   * Létrehoz egy új példányt a lóval, amely a jelenlegi pozícióval és színnel
   * rendelkezik.
   * 
   * @return A ló másolatát.
   */
  @Override
  public Piece copy() {
    return new Knight(this.color, this.row, this.col);
  }

  /**
   * Visszaadja a ló aktuális sorát.
   * 
   * @return A ló sora.
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Visszaadja a ló aktuális oszlopát.
   * 
   * @return A ló oszlopa.
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Beállítja a ló sorát.
   * 
   * @param row Az új sor.
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Beállítja a ló oszlopát.
   * 
   * @param col Az új oszlop.
   */
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Visszaadja a ló színét.
   * 
   * @return A ló színe.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Visszaadja a ló típusát.
   * 
   * @return A ló típusa.
   */
  @Override
  public PieceType getType() {
    return PieceType.KNIGHT;
  }

  /**
   * Összehasonlítja a lovat egy másik objektummal, hogy meghatározza, egyenlők-e.
   * Két ló akkor egyenlő, ha ugyanazon színűek és típusúak.
   * 
   * @param obj A másik objektum.
   * @return true, ha az objektum ugyanazt a lovat jelenti, különben false.
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
   * Visszaadja a ló színét és típusát szöveges formában.
   * 
   * @return A ló színe és típusa.
   */
  @Override
  public String toString() {
    return color + " Knight";
  }
}
