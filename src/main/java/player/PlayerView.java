package player;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {
  public PlayerView(Player player) {
    // Set fixed height of 20px
    setPreferredSize(new Dimension(300, 20)); // Width can be adjusted dynamically
    setMinimumSize(new Dimension(300, 20));
    setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); // Allow flexible width
    setBackground(Color.DARK_GRAY); // Match dark background

    // Use BorderLayout for horizontal arrangement
    setLayout(new BorderLayout());

    // Left panel: Player name and status indicator
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Tight spacing
    leftPanel.setBackground(Color.DARK_GRAY);

    // Player name
    JLabel nameLabel = new JLabel(player.getName());
    nameLabel.setForeground(Color.WHITE); // White text
    nameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjust font size

    leftPanel.add(nameLabel);

    // Right: Elo rating
    JLabel eloLabel = new JLabel(player.getElo() + "");
    eloLabel.setForeground(Color.LIGHT_GRAY);
    eloLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjust font size

    // Add components to the main panel
    add(leftPanel, BorderLayout.WEST); // Player info on the left
    add(eloLabel, BorderLayout.EAST);  // Elo on the right
}
}
