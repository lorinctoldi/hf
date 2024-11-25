package game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private JButton exitButton;
    private JButton resignButton;
    private JButton saveButton;
    private JFileChooser fileChooser;

    public GameView(JPanel boardView, JPanel playerView, JScrollPane moveView) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        add(boardView);
        
        JPanel sidePanel = new JPanel();
        sidePanel.setMaximumSize(new Dimension(300, 600));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        sidePanel.add(moveView);

        JComponent bottomPlayerView = playerView; 
        sidePanel.add(bottomPlayerView);
        setBackground(new Color(22, 21, 19));
        sidePanel.setBackground(new Color(22, 21, 19));

        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
        sidePanel.add(fileChooser);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between elements

        saveButton = createButton("Játék mentése");
        sidePanel.add(saveButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between elements

        resignButton = createButton("Feladás");
        sidePanel.add(resignButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between elements

        exitButton = createButton("Kilépés");
        sidePanel.add(exitButton);

        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        resignButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        
        add(sidePanel);
    }


    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font for buttons
        button.setForeground(new Color(148, 148, 148)); // Button text color
        button.setBackground(new Color(57, 55, 51)); // Button background color
        button.setOpaque(true);
        button.setBorderPainted(false); // Remove border
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI()); // Remove default button look
        button.setPreferredSize(new Dimension(260, 40)); // Set button width to 260px
        button.setMinimumSize(new Dimension(260, 40));
        button.setMaximumSize(new Dimension(260, 40));
        button.setFocusable(false); // Prevent focus outline
        return button;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getResignButton() {
        return resignButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
