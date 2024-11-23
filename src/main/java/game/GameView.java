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
        sidePanel.setMaximumSize(new Dimension(400, 300));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        JComponent topPlayerView = playerView; 
        sidePanel.add(topPlayerView);

        
        sidePanel.add(moveView);

        
        JComponent bottomPlayerView = playerView; 
        sidePanel.add(bottomPlayerView);
        setBackground(new Color(22, 21, 19));
        sidePanel.setBackground(new Color(22, 21, 19));
        add(Box.createRigidArea(new Dimension(20, 0)));

        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
        sidePanel.add(fileChooser);

        saveButton = new JButton("Játék mentése");
        sidePanel.add(saveButton);

        resignButton = new JButton("Feladás");
        sidePanel.add(resignButton);

        exitButton = new JButton("Kilépés");
        sidePanel.add(exitButton);

        
        add(sidePanel);
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
