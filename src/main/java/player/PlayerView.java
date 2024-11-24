package player;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {
  public PlayerView(Player player) {
    
    setPreferredSize(new Dimension(300, 20)); 
    setMinimumSize(new Dimension(300, 20));
    setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
    setBackground(Color.DARK_GRAY); 
    
    setLayout(new BorderLayout());
    
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0)); 
    leftPanel.setBackground(Color.DARK_GRAY);

    
    JLabel nameLabel = new JLabel(player.getName());
    nameLabel.setForeground(Color.WHITE); 
    nameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 

    leftPanel.add(nameLabel);

    
    JLabel eloLabel = new JLabel(player.getElo() + "");
    eloLabel.setForeground(Color.LIGHT_GRAY);
    eloLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 

    add(leftPanel, BorderLayout.WEST);
    add(eloLabel, BorderLayout.EAST);
}
}
