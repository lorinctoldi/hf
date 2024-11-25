package chess.move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MoveView extends JPanel {
  private JButton button;
  private JLabel indexLabel;

  /**
   * Konstruktor, amely inicializálja a MoveView-t, létrehoz egy gombot a lépés
   * megjelenítéséhez
   * és egy címkét, amely az adott lépés indexét tartalmazza.
   * 
   * @param move  A lépés, amelyet megjelenítünk.
   * @param index A lépés indexe.
   */

  public MoveView(String move, int index) {
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    setOpaque(true);
    setBackground(new Color(57, 55, 51));

    indexLabel = new JLabel(String.valueOf(index));
    indexLabel.setPreferredSize(new Dimension(30, 30));
    indexLabel.setForeground(new Color(148, 148, 148));
    indexLabel.setBackground(new Color(51, 49, 46));
    indexLabel.setOpaque(true);
    indexLabel.setHorizontalAlignment(SwingConstants.CENTER);

    button = new JButton(move);
    button.setFont(new Font("Monospaced", Font.PLAIN, 14));
    button.setMinimumSize(new Dimension(270, 30));
    button.setPreferredSize(new Dimension(270, 30));
    button.setMaximumSize(new Dimension(270, 30));
    button.setFont(new Font("Arial", Font.PLAIN, 14));
    button.setForeground(new Color(148, 148, 148));
    button.setBackground(new Color(57, 55, 51));
    button.setOpaque(true);
    button.setBorderPainted(false);
    button.setUI(new javax.swing.plaf.basic.BasicButtonUI());

    button.setMargin(new Insets(0, 0, 0, 0));

    this.add(indexLabel);
    this.add(button);
  }

  /**
   * Hozzáad egy eseménykezelőt a gombhoz, amely reagál a lépésre kattintásra.
   * 
   * @param listener Az ActionListener, amely kezeli a kattintást.
   */
  public void addMoveClickListener(ActionListener listener) {
    button.addActionListener(listener);
  }
}
