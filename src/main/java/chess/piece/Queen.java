package chess.piece;

import chess.board.Board;

public class Queen implements Piece {
  private Color color;
  private int row;
  private int col;

  /**
   * A Queen osztály konstruktora.
   * Inicializálja a királynőt a színével és pozíciójával.
   *
   * @param color a királynő színe
   * @param row   a királynő sorának kezdőpozíciója
   * @param col   a királynő oszlopának kezdőpozíciója
   */
  public Queen(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Ellenőrzi, hogy egy adott lépés érvényes-e a királynő számára.
   * A királynő vízszintesen, függőlegesen vagy átlósan mozoghat, ha az útja
   * szabad.
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

    int rowDiff = Math.abs(targetRow - originRow);
    int colDiff = Math.abs(targetCol - originCol);

    Piece targetPiece = board.getPiece(targetRow, targetCol);
    if (targetPiece != null && (targetPiece.getColor() == this.color)) {
      return false;
    }

    if (rowDiff == 0 || colDiff == 0 || rowDiff == colDiff) {
      return isPathClear(originCol, originRow, targetCol, targetRow, board);
    }

    return false;
  }

  /**
   * Ellenőrzi, hogy a királynő útja a célmezőig akadálymentes-e.
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
   * Létrehoz egy másolatot a királynő objektumról.
   *
   * @return egy új Queen objektum, amely az eredetivel azonos tulajdonságokkal
   *         rendelkezik
   */
  @Override
  public Piece copy() {
    return new Queen(this.color, this.row, this.col);
  }

  /**
   * Visszaadja a királynő aktuális sorát.
   *
   * @return a királynő sora
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Visszaadja a királynő aktuális oszlopát.
   *
   * @return a királynő oszlopa
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Beállítja a királynő aktuális sorát.
   *
   * @param row az új sor értéke
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Beállítja a királynő aktuális oszlopát.
   *
   * @param col az új oszlop értéke
   */
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Visszaadja a királynő színét.
   *
   * @return a királynő színe
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Visszaadja a királynő típusát.
   *
   * @return a királynő típusa (QUEEN)
   */
  @Override
  public PieceType getType() {
    return PieceType.QUEEN;
  }

  /**
   * Ellenőrzi, hogy a királynő megegyezik-e egy másik sakkfigurával.
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
   * Visszaadja a királynő reprezentációját szöveges formában.
   *
   * @return a királynő szöveges reprezentációja
   */
  @Override
  public String toString() {
    return color + " Queen";
  }
}
