package chess.move;

import javax.swing.*;
import java.awt.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MoveController {
  private ArrayList<Move> moves;
  private JPanel movePanel;
  private MoveClickListener listener;

  /**
   * Hozzáad egy új lépést a listához, majd frissíti a nézetet.
   * 
   * @param originCol A kezdő oszlop indexe.
   * @param originRow A kezdő sor indexe.
   * @param targetCol A cél oszlop indexe.
   * @param targetRow A cél sor indexe.
   */
  public MoveController() {
    this.moves = new ArrayList<>();
    movePanel = new JPanel();
    movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));
    movePanel.setOpaque(true);
    movePanel.setBackground(new Color(57, 55, 51));
  }

  /**
   * Frissíti a nézetet az összes lépés megjelenítésével.
   */
  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    Move move = new Move(originCol, originRow, targetCol, targetRow);
    moves.add(move);

    repaint();
  }

  /**
   * Visszaadja a lépések megjelenítéséhez szükséges JScrollPane-t.
   * 
   * @return A JScrollPane, amely tartalmazza a lépések paneljét.
   */
  private void repaint() {
    movePanel.removeAll();
    for (Move move : moves) {
      MoveView moveView = new MoveView(move.toString(), moves.indexOf(move));
      moveView.addMoveClickListener(e -> {
        if (listener != null) {
          listener.onMoveClicked(moves.indexOf(move));
        }
      });

      movePanel.add(moveView);
    }
    movePanel.revalidate();
    movePanel.repaint();

    JScrollPane parentScrollPane = (JScrollPane) movePanel.getParent().getParent();
    SwingUtilities.invokeLater(() -> {
      JScrollBar verticalScrollBar = parentScrollPane.getVerticalScrollBar();
      verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    });
  }

  /**
   * Visszaadja az összes lépést tartalmazó listát.
   * 
   * @return A lépések listája.
   */
  public JScrollPane getView() {
    JScrollPane scrollPane = new JScrollPane(movePanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setPreferredSize(new Dimension(300, 200));
    scrollPane.setMaximumSize(new Dimension(300, 200));
    scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    scrollPane.setBackground(new Color(57, 55, 51));
    scrollPane.setOpaque(true);
    return scrollPane;
  }

  /**
   * Beállítja a MoveClickListener eseménykezelőt, amely reagál a lépésekre való
   * kattintásra.
   * 
   * @param listener A MoveClickListener, amely kezeli a kattintásokat.
   */
  public ArrayList<Move> getMoves() {
    return moves;
  }

  /**
   * Az interfész a lépésre kattintás eseményének kezelésére.
   */
  public void setMoveClickListener(MoveClickListener listener) {
    this.listener = listener;
  }

  public interface MoveClickListener {
    void onMoveClicked(int moveIndex);
  }

  /**
   * Törli az összes lépést az adott index után, majd frissíti a nézetet.
   * 
   * @param index A lépés indexe, amely után törlés történik.
   */
  public void deleteMovesAfter(int index) {
    if (index < moves.size() - 1) {
      for (int i = moves.size() - 1; i > index; i--) {
        moves.remove(i);
      }
    }

    repaint();
  }

  /**
   * Elmenti az összes lépést egy fájlba.
   * 
   * @param file A fájl, ahova a lépéseket menteni kell.
   * @throws IOException Ha hiba történik az írás során.
   */
  public void saveMovesToFile(File file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      for (Move move : moves) {
        writer.write(move.toString());
        writer.newLine();
      }
    }
  }

  /**
   * Beállítja a lépések listáját.
   * 
   * @param moves Az új lépések listája.
   */
  public void setMoves(ArrayList<Move> moves) {
    this.moves = moves;
  }
}
