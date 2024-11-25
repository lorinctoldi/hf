package chess.piece;

import chess.board.Board;

public class Rook implements Piece {
  private Color color;
  private int row;
  private int col;

  /**
   * A Rook osztály konstruktora.
   * Inicializálja a bástyát a színével és pozíciójával.
   *
   * @param color a bástya színe
   * @param row   a bástya sorának kezdőpozíciója
   * @param col   a bástya oszlopának kezdőpozíciója
   */
  public Rook(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Ellenőrzi, hogy egy adott lépés érvényes-e a bástya számára.
   * A bástya vízszintesen vagy függőlegesen mozoghat, ha az útja szabad.
   *
   * @param originCol az eredeti oszlop
   * @param originRow az eredeti sor
   * @param targetCol a cél oszlop
   * @param targetRow a cél sor
   * @param board     a sakktábla objektuma
   * @return true, ha a lépés érvényes, különben false
   */
  @Override
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if (originRow == targetRow && originCol == targetCol) {
      return false;
    }
    Piece targetPiece = board.getPiece(targetRow, targetCol);
    if (targetPiece != null && targetPiece.getColor() == this.color)
      return false;
    if (originRow == targetRow || originCol == targetCol) {
      return isPathClear(originCol, originRow, targetCol, targetRow, board);
    }
    return false;
  }

  /**
   * Ellenőrzi, hogy a bástya útja a célmezőig akadálymentes-e.
   *
   * @param originCol az eredeti oszlop
   * @param originRow az eredeti sor
   * @param targetCol a cél oszlop
   * @param targetRow a cél sor
   * @param board     a sakktábla objektuma
   * @return true, ha az út szabad, különben false
   */
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

  /**
   * Létrehoz egy másolatot a bástya objektumról.
   *
   * @return egy új Rook objektum, amely az eredetivel azonos tulajdonságokkal
   *         rendelkezik
   */
  @Override
  public Piece copy() {
    return new Rook(this.color, this.row, this.col);
  }

  /**
   * Visszaadja a bástya aktuális sorát.
   *
   * @return a bástya sora
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Visszaadja a bástya aktuális oszlopát.
   *
   * @return a bástya oszlopa
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Beállítja a bástya aktuális sorát.
   *
   * @param row az új sor értéke
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Beállítja a bástya aktuális oszlopát.
   *
   * @param col az új oszlop értéke
   */
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Visszaadja a bástya színét.
   *
   * @return a bástya színe
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Visszaadja a bástya típusát.
   *
   * @return a bástya típusa (ROOK)
   */
  @Override
  public PieceType getType() {
    return PieceType.ROOK;
  }

  /**
   * Ellenőrzi, hogy a bástya megegyezik-e egy másik sakkfigurával.
   *
   * @param obj az összehasonlítandó objektum
   * @return true, ha a két figura megegyezik, különben false
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
   * Visszaadja a bástya reprezentációját szöveges formában.
   *
   * @return a bástya szöveges reprezentációja
   */
  @Override
  public String toString() {
    return color + " Rook";
  }
}
