package game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private JButton exitButton;
    private JButton resignButton;
    private JButton saveButton;
    private JFileChooser fileChooser;

    public GameView(JPanel boardView, JPanel playerView, JPanel robotView, JScrollPane moveView) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(boardView);
        add(Box.createRigidArea(new Dimension(20, 0)));

        JPanel sidePanel = new JPanel();
        sidePanel.setMaximumSize(new Dimension(300, 800));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(new Color(22, 21, 19));

        sidePanel.add(Box.createVerticalGlue());

        JPanel playersAndMoveContainer = new JPanel();
        playersAndMoveContainer.setLayout(new BoxLayout(playersAndMoveContainer, BoxLayout.Y_AXIS));
        playersAndMoveContainer.setBackground(new Color(22, 21, 19));
        playersAndMoveContainer.add(robotView);
        playersAndMoveContainer.add(moveView);
        playersAndMoveContainer.add(playerView);

        sidePanel.add(playersAndMoveContainer);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
        sidePanel.add(fileChooser);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        saveButton = createButton("Játék mentése");
        sidePanel.add(saveButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        resignButton = createButton("Feladás");
        sidePanel.add(resignButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        exitButton = createButton("Kilépés");
        sidePanel.add(exitButton);

        sidePanel.add(Box.createVerticalGlue());

        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        resignButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        add(sidePanel);
        setBackground(new Color(22, 21, 19));
    }


    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setForeground(new Color(148, 148, 148));
        button.setBackground(new Color(57, 55, 51));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setPreferredSize(new Dimension(260, 40));
        button.setMinimumSize(new Dimension(260, 40));
        button.setMaximumSize(new Dimension(260, 40));
        button.setFocusable(false);
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
