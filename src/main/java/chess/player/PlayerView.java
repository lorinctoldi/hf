package chess.player;

import javax.swing.*;
import java.awt.*;

/**
 * A játékos megjelenítésére szolgáló grafikus panel.
 * Tartalmazza a játékos nevét és Elo-értékét, rendezett elrendezésben.
 */
public class PlayerView extends JPanel {
  /**
   * Létrehoz egy PlayerView panelt egy adott játékos adataival.
   * 
   * @param player a játékos, akinek az adatait meg kell jeleníteni
   */
  public PlayerView(Player player) {
    setPreferredSize(new Dimension(300, 40));
    setMinimumSize(new Dimension(300, 40));
    setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    setBackground(new Color(38, 36, 34));

    setLayout(new BorderLayout(10, 0));

    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    leftPanel.setBackground(new Color(38, 36, 34));

    JLabel nameLabel = new JLabel(player.getName());
    nameLabel.setForeground(new Color(230, 230, 230));
    nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

    leftPanel.add(nameLabel);

    JLabel eloLabel = new JLabel(player.getElo() + "");
    eloLabel.setForeground(new Color(200, 200, 200));
    eloLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    eloLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    JPanel eloContainer = new JPanel(new BorderLayout());
    eloContainer.setBackground(new Color(38, 36, 34));
    eloContainer.add(eloLabel, BorderLayout.CENTER);
    eloContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

    add(leftPanel, BorderLayout.WEST);
    add(eloContainer, BorderLayout.EAST);

    setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(60, 60, 60)));
  }
}
