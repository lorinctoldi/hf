package chess.game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;

public class GameView extends JPanel {
    private JButton exitButton;
    private JButton resignButton;
    private JButton saveButton;
    private JFileChooser fileChooser;

    /**
     * Konstruktor, amely inicializálja a játéknézetet, beleértve a tábla,
     * a játékos és robot nézeteket, a lépések nézetét, valamint az oldalsó panelen
     * található gombokat (mentés, feladás, kilépés).
     * 
     * @param game a játék, amelyhez a nézet tartozik
     */
    public GameView(Game game) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(game.getBoardView());
        add(Box.createRigidArea(new Dimension(20, 0)));

        JPanel sidePanel = new JPanel();
        sidePanel.setMaximumSize(new Dimension(300, 800));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(new Color(22, 21, 19));

        sidePanel.add(Box.createVerticalGlue());

        JPanel playersAndMoveContainer = new JPanel();
        playersAndMoveContainer.setLayout(new BoxLayout(playersAndMoveContainer, BoxLayout.Y_AXIS));
        playersAndMoveContainer.setBackground(new Color(22, 21, 19));
        playersAndMoveContainer.add(game.getRobotView());
        playersAndMoveContainer.add(game.getMoveView());
        playersAndMoveContainer.add(game.getPlayerView());

        sidePanel.add(playersAndMoveContainer);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
        fileChooser.setAcceptAllFileFilterUsed(false);
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

    /**
     * Létrehoz egy formázott gombot a megadott szöveggel.
     * 
     * @param text a gomb szövege
     * @return az elkészített gomb
     */
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

    /**
     * Visszaadja a kilépés gombot.
     * 
     * @return a kilépés gomb
     */
    public JButton getExitButton() {
        return exitButton;
    }

    /**
     * Visszaadja a feladás gombot.
     * 
     * @return a feladás gomb
     */
    public JButton getResignButton() {
        return resignButton;
    }

    /**
     * Visszaadja a mentés gombot.
     * 
     * @return a mentés gomb
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Visszaadja a fájl választó komponenst.
     * 
     * @return a fájl választó
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
