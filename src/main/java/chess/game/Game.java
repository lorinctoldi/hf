package chess.game;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chess.board.BoardController;
import chess.move.Move;
import chess.move.MoveController;
import chess.player.PlayerController;

public class Game {
  private PlayerController playerController;
  private PlayerController robotController;
  private BoardController boardController;
  private MoveController moveController;

  /**
   * Konstruktor, amely inicializálja a játék vezérlőit: játékosok, tábla, lépések
   * vezérlője.
   * A konstruktornak átadott név és Elo érték alapján hozza létre az emberi
   * játékost.
   * A "Robot" nevű gépi játékos is létrejön.
   * 
   * @param name a játékos neve
   * @param elo  a játékos Elo értéke
   */
  public Game(String name, int elo) {
    playerController = new PlayerController(name, elo);
    robotController = new PlayerController("Robot", 200);
    boardController = new BoardController(this);
    moveController = new MoveController();

    moveController.setMoveClickListener(moveIndex -> {
      replayMoves(moveIndex);
    });
  }

  /**
   * Visszajátsza az összes lépést a megadott indexig, újraindítja a táblát,
   * majd lépésről lépésre végrehajtja az összes lépést.
   * Az utolsó lépés után, ha a lépések száma páros, és nincs döntetlen vagy matt,
   * a robot következő lépése is végrehajtásra kerül.
   * 
   * @param upToIndex az index, amíg visszajátsszuk a lépéseket
   */
  public void replayMoves(int upToIndex) {
    boardController.resetBoard();

    for (int i = 0; i <= upToIndex; i++) {
      Move move = moveController.getMoves().get(i);
      boardController.performMove(move.getOriginCol(), move.getOriginRow(), move.getTargetCol(), move.getTargetRow());
    }

    boardController.getView().repaint();
    boardController.getView().revalidate();
    moveController.deleteMovesAfter(upToIndex);

    if (upToIndex % 2 == 0 && !boardController.isDraw() && !boardController.isMate())
      boardController.robotMove();
  }

  /**
   * Hozzáad egy új lépést a lépések listájához.
   * 
   * @param originCol az eredeti oszlop
   * @param originRow az eredeti sor
   * @param targetCol a cél oszlop
   * @param targetRow a cél sor
   */
  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    moveController.addMove(originCol, originRow, targetCol, targetRow);
  }

  /**
   * Beállítja a játék lépéseit és visszajátssza az összes lépést.
   * 
   * @param moves a játék lépéseinek listája
   */

  public void setMoves(ArrayList<Move> moves) {
    moveController.setMoves(moves);
    replayMoves(moves.size() - 1);
  }

  /**
   * Visszaadja a robot vezérlőjének nézetét.
   * 
   * @return a robot vezérlő nézete
   */
  public JPanel getRobotView() {
    return robotController.getView();
  }

  /**
   * Visszaadja a tábla vezérlőjének nézetét.
   * 
   * @return a tábla vezérlő nézete
   */
  public JPanel getBoardView() {
    return boardController.getView();
  }

  /**
   * Visszaadja a lépéseket megjelenítő görgethető nézetet.
   * 
   * @return a lépések nézete
   */
  public JScrollPane getMoveView() {
    return moveController.getView();
  }

  /**
   * Visszaadja a játékos vezérlőjének nézetét.
   * 
   * @return a játékos vezérlő nézete
   */
  public JPanel getPlayerView() {
    return playerController.getView();
  }

  /**
   * Visszaadja a lépések vezérlőjét.
   * 
   * @return a lépések vezérlője
   */
  public MoveController getMoveController() {
    return this.moveController;
  }

  /**
   * Visszaadja a játékos vezérlőjét.
   * 
   * @return a játékos vezérlője
   */
  public PlayerController getPlayerController() {
    return this.playerController;
  }
}
