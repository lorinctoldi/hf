package move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MoveView extends JPanel {
  private JButton button;
  private JLabel indexLabel;

  public MoveView(String move, int index) {
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));  // No gap between components
    
    // Remove any margin/padding for this panel
    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    setOpaque(true);  // Ensure the panel is opaque so that the background color is visible
    setBackground(new Color(57, 55, 51));

    indexLabel = new JLabel(String.valueOf(index));
    indexLabel.setPreferredSize(new Dimension(30, 30)); // Fixed width of 30px for the index label
    indexLabel.setForeground(new Color(148, 148, 148));  // Text color for the index
    indexLabel.setBackground(new Color(51, 49, 46));     // Background color for the index
    indexLabel.setOpaque(true);                           // Make the label opaque to show background
    indexLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text within the label

    // Create the move button
    button = new JButton(move);
    button.setFont(new Font("Monospaced", Font.PLAIN, 14));
    button.setMinimumSize(new Dimension(270, 30));   // Button size
    button.setPreferredSize(new Dimension(270, 30));  // Button size
    button.setMaximumSize(new Dimension(270, 30));    // Button size
    button.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for buttons
    button.setForeground(new Color(148, 148, 148));  // Button text color
    button.setBackground(new Color(57, 55, 51));     // Button background color
    button.setOpaque(true);
    button.setBorderPainted(false);                  // Remove button border
    button.setUI(new javax.swing.plaf.basic.BasicButtonUI()); // Basic UI

    // Remove any internal padding or margin inside the button
    button.setMargin(new Insets(0, 0, 0, 0));        // No margin

    // Add the index label and button to the panel
    this.add(indexLabel);
    this.add(button);
  }

  public void addMoveClickListener(ActionListener listener) {
    button.addActionListener(listener);
  }
}
