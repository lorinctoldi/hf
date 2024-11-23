package move;

import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MoveController {
  private ArrayList<Move> moves;
  private JPanel movePanel;

  public MoveController() {
    moves = new ArrayList<Move>();
    movePanel = new JPanel();
    movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));
  }

  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    Move move = new Move(originCol, originRow, targetCol, targetRow);
    moves.add(move);

    movePanel.add(new MoveView(move.toString()));
    
    movePanel.revalidate();
    movePanel.repaint();

    JScrollPane parentScrollPane = (JScrollPane) movePanel.getParent().getParent();
    parentScrollPane.getVerticalScrollBar().setValue(parentScrollPane.getVerticalScrollBar().getMaximum());
  }

  public JScrollPane getView() {
    System.out.println("move rendered");
    JScrollPane scrollPane = new JScrollPane(movePanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    scrollPane.setMinimumSize(new Dimension(300, 300));
    scrollPane.setMaximumSize(new Dimension(300, 300));

    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    scrollPane.setViewportView(movePanel);

    movePanel.setBackground(Color.GREEN);

    return scrollPane;
  }

  public void print() {
    for (Move move : moves) {
      System.out.println(move);
    }
  }
}
