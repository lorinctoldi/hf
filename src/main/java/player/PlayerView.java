package player;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {
  public PlayerView(Player player) {
    setPreferredSize(new Dimension(300, 40)); // Increased height for better visuals
        setMinimumSize(new Dimension(300, 40));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setBackground(new Color(38,36,34)); // Slightly lighter than DARK_GRAY for better contrast

        setLayout(new BorderLayout(10, 0)); // Add horizontal spacing between components

        // Left panel for the name
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Ensure vertical centering with 10px padding
        leftPanel.setBackground(new Color(38,36,34)); // Match background color

        JLabel nameLabel = new JLabel(player.getName());
        nameLabel.setForeground(new Color(230, 230, 230)); // Soft white for text
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Bold font for emphasis

        leftPanel.add(nameLabel);

        // ELO label on the right
        JLabel eloLabel = new JLabel(player.getElo() + "");
        eloLabel.setForeground(new Color(200, 200, 200)); // Light gray for ELO
        eloLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Regular font for secondary text
        eloLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Add margin to the right for the ELO label
        JPanel eloContainer = new JPanel(new BorderLayout());
        eloContainer.setBackground(new Color(38,36,34)); // Match background color
        eloContainer.add(eloLabel, BorderLayout.CENTER);
        eloContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Add 10px right margin

        add(leftPanel, BorderLayout.WEST);
        add(eloContainer, BorderLayout.EAST);

        // Optional border for separation
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(60, 60, 60))); // Subtle separator line
  }
}
