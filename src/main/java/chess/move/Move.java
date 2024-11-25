package chess.move;

public class Move {
  private int originCol;
  private int originRow;
  private int targetCol;
  private int targetRow;

  /**
   * Konstruktor, amely inicializálja a lépés kezdő és cél oszlopát és sorát.
   * 
   * @param originCol A kezdő oszlop (0-alapú index).
   * @param originRow A kezdő sor (0-alapú index).
   * @param targetCol A cél oszlop (0-alapú index).
   * @param targetRow A cél sor (0-alapú index).
   */
  public Move(int originCol, int originRow, int targetCol, int targetRow) {
    this.originCol = originCol;
    this.originRow = originRow;
    this.targetCol = targetCol;
    this.targetRow = targetRow;
  }

  /**
   * Visszaadja a lépés kezdő és cél mezőit ábrázoló karakterláncot a sakkáblán.
   * Például: "e2->e4".
   * 
   * @return A lépés leírása sakkjelöléssel (pl. "e2->e4").
   */
  @Override
  public String toString() {
    String origin = convertColToChessNotation(originCol) + (originRow + 1);
    String target = convertColToChessNotation(targetCol) + (targetRow + 1);
    return origin + "->" + target;
  }

  /**
   * A sakkoszlopok (a-h) átváltása oszlopindexekre (0-7).
   * 
   * @param col Az oszlop indexe (0-7).
   * @return Az oszlop neve sakkjelöléssel (pl. "a", "b", stb.).
   */
  private String convertColToChessNotation(int col) {
    return String.valueOf((char) ('a' + col));
  }

  /**
   * Visszaadja a kezdő oszlop indexét.
   * 
   * @return A kezdő oszlop (0-alapú index).
   */
  public int getOriginCol() {
    return this.originCol;
  }

  /**
   * Visszaadja a kezdő sor indexét.
   * 
   * @return A kezdő sor (0-alapú index).
   */
  public int getOriginRow() {
    return this.originRow;
  }

  /**
   * Visszaadja a cél oszlop indexét.
   * 
   * @return A cél oszlop (0-alapú index).
   */
  public int getTargetCol() {
    return this.targetCol;
  }

  /**
   * Visszaadja a cél sor indexét.
   * 
   * @return A cél sor (0-alapú index).
   */
  public int getTargetRow() {
    return this.targetRow;
  }
}
