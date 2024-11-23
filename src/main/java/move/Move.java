package move;

public class Move {
  private int originCol; 
  private int originRow;
  private int targetCol;
  private int targetRow;

  public Move(int originCol, int originRow, int targetCol, int targetRow) {
    this.originCol = originCol;
    this.originRow = originRow;
    this.targetCol = targetCol;
    this.targetRow = targetRow;
  }

  @Override
  public String toString() {
    String origin = convertColToChessNotation(originCol) + (originRow + 1);
    String target = convertColToChessNotation(targetCol) + (targetRow + 1);
    return origin + "->" + target;
  }

  private String convertColToChessNotation(int col) {
    return String.valueOf((char) ('a' + col));
  }

  public int getOriginCol() {
    return this.originCol;
  }

  public int getOriginRow() {
    return this.originRow;
  }

  public int getTargetCol() {
    return this.targetCol;
  }

  public int getTargetRow() {
    return this.targetRow;
  }
}
