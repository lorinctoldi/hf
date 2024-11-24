package move;

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

  public MoveController(ArrayList<Move> moves) {
    if(moves != null && moves.size() != 0) {
      this.moves = moves;
    } else {
      this.moves = new ArrayList<>();
    }
    movePanel = new JPanel();
    movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));
  }

  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    Move move = new Move(originCol, originRow, targetCol, targetRow);
    moves.add(move);

    MoveView moveView = new MoveView(move.toString());
    moveView.addMoveClickListener(e -> {
      if (listener != null) {
        listener.onMoveClicked(moves.indexOf(move));
      }
    });

    movePanel.add(moveView);
    movePanel.revalidate();
    movePanel.repaint();

    JScrollPane parentScrollPane = (JScrollPane) movePanel.getParent().getParent();
    parentScrollPane.getVerticalScrollBar().setValue(parentScrollPane.getVerticalScrollBar().getMaximum());
  }

  public JScrollPane getView() {
    JScrollPane scrollPane = new JScrollPane(movePanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    movePanel.setBackground(Color.GREEN);
    return scrollPane;
  }

  public ArrayList<Move> getMoves() {
    return moves;
  }

  public void setMoveClickListener(MoveClickListener listener) {
    this.listener = listener;
  }

  public interface MoveClickListener {
    void onMoveClicked(int moveIndex);
  }

  public void deleteMovesAfter(int index) {
    if (index < moves.size() - 1) {
      for (int i = moves.size() - 1; i > index; i--) {
        moves.remove(i);
        movePanel.remove(i);
      }
  
      movePanel.revalidate();
      movePanel.repaint();
    }
  }

    /**
   * Saves the moves to a file in a simple text format.
   * @param file The file to save the moves to.
   * @throws IOException If an error occurs while writing to the file.
   */
  public void saveMovesToFile(File file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      for (Move move : moves) {
        writer.write(move.toString());
        writer.newLine();
      }
    }
  }
}
