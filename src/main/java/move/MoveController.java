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

  public MoveController() {
    this.moves = new ArrayList<>();
    movePanel = new JPanel();
    movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));
  }

  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    Move move = new Move(originCol, originRow, targetCol, targetRow);
    moves.add(move);

    System.out.println("Current moves");
    for(Move m : moves) {
      System.out.println(m);
    }
    repaint();
  }

  private void repaint() {
    movePanel.removeAll();
    for (Move move : moves) {
      MoveView moveView = new MoveView(move.toString());
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
      }
    }

    repaint();
  }

  public void saveMovesToFile(File file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      for (Move move : moves) {
        writer.write(move.toString());
        writer.newLine();
      }
    }
  }

  public void setMoves(ArrayList<Move> moves) {
    this.moves = moves;
  }
}
