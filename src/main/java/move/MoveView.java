package move;

import javax.swing.*;
import java.awt.*;

public class MoveView extends JPanel {
  JButton button;
  public MoveView(String move) {
    button = new JButton(move);
    setLayout(new FlowLayout(FlowLayout.LEFT)); // Align moves to the left
    button.setFont(new Font("Monospaced", Font.PLAIN, 14));
    setPreferredSize(new Dimension(300, 30));
    setMaximumSize(new Dimension(300, 30)); // Prevent stretching of MoveView
    this.add(button);
  }
}