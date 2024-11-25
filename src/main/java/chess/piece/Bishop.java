package chess.piece;

import chess.board.Board;

public class Bishop implements Piece {
  private Color color;
  private int row;
  private int col;

  /**
   * Konstruktor, amely inicializálja a futó színét, sorát és oszlopát.
   * 
   * @param color A bábu színe.
   * @param row   A bábu kezdő sora.
   * @param col   A bábu kezdő oszlopa.
   */
  public Bishop(Color color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Ellenőrzi, hogy a megadott lépés érvényes-e a futó számára.
   * A futó csak átlósan mozoghat, és az útjában nem lehet másik bábú.
   * 
   * @param originCol Az eredeti oszlop.
   * @param originRow Az eredeti sor.
   * @param targetCol A cél oszlop.
   * @param targetRow A cél sor.
   * @param board     A játék táblája, hogy ellenőrizze az akadályokat.
   * @return true, ha a lépés érvényes, különben false.
   */
  @Override
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Board board) {
    if (Math.abs(targetRow - originRow) != Math.abs(targetCol - originCol)) {
      return false;
    }

    int rowStep = Integer.compare(targetRow, originRow);
    int colStep = Integer.compare(targetCol, originCol);

    return isPathClear(originCol, originRow, targetCol, targetRow, rowStep, colStep, board);
  }

  /**
   * Ellenőrzi, hogy a futó útja tiszta-e a kezdő és a cél pozíció között.
   * A futó úton lévő mezőknek üresnek kell lenniük.
   * 
   * @param originCol Az eredeti oszlop.
   * @param originRow Az eredeti sor.
   * @param targetCol A cél oszlop.
   * @param targetRow A cél sor.
   * @param rowStep   A sorban történő lépés iránya.
   * @param colStep   Az oszlopban történő lépés iránya.
   * @param board     A játék táblája.
   * @return true, ha az út tiszta, különben false.
   */
  private boolean isPathClear(int originCol, int originRow, int targetCol, int targetRow, int rowStep, int colStep,
      Board board) {
    int currentRow = originRow + rowStep;
    int currentCol = originCol + colStep;

    while (currentRow != targetRow && currentCol != targetCol) {
      if (board.getPiece(currentRow, currentCol) != null) {
        return false;
      }
      currentRow += rowStep;
      currentCol += colStep;
    }

    Piece targetPiece = board.getPiece(targetRow, targetCol);
    if (targetPiece == null || targetPiece.getColor() != this.color) {
      return true;
    }

    return false;
  }

  /**
   * Létrehoz egy új példányt a futótól a jelenlegi pozícióval és színnel.
   * 
   * @return A futó másolatát.
   */
  @Override
  public Piece copy() {
    return new Bishop(this.color, this.row, this.col);
  }

  /**
   * Visszaadja a bábu aktuális sorát.
   * 
   * @return A bábu sora.
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Visszaadja a bábu aktuális oszlopát.
   * 
   * @return A bábu oszlopa.
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Beállítja a bábu sorát.
   * 
   * @param row Az új sor.
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Beállítja a bábu oszlopát.
   * 
   * @param col Az új oszlop.
   */
  @Override
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Visszaadja a bábu színét.
   * 
   * @return A bábu színe.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Visszaadja a bábu típusát.
   * 
   * @return A bábu típusa, jelen esetben a futó.
   */
  @Override
  public PieceType getType() {
    return PieceType.BISHOP;
  }

  /**
   * Összehasonlítja a futót egy másik objektummal, hogy meghatározza,
   * egyenlők-e.
   * Két futó akkor egyenlő, ha ugyanazon színűek és típusúak.
   * 
   * @param obj A másik objektum.
   * @return true, ha az objektum ugyanazt a futót jelenti, különben false.
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
   * Visszaadja a futó színét és típusát szöveges formában.
   * 
   * @return A futó színe és típusa.
   */
  @Override
  public String toString() {
    return color + " Bishop";
  }
}
