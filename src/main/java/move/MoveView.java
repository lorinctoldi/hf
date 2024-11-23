package move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MoveView extends JPanel {
  private JButton button;

  public MoveView(String move) {
    button = new JButton(move);
    setLayout(new FlowLayout(FlowLayout.LEFT));
    button.setFont(new Font("Monospaced", Font.PLAIN, 14));
    setPreferredSize(new Dimension(300, 30));
    setMaximumSize(new Dimension(300, 30));
    this.add(button);
  }

  public void addMoveClickListener(ActionListener listener) {
    button.addActionListener(listener);
  }
}
