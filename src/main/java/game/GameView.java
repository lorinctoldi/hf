package game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private JButton backButton;


    public GameView() {
        backButton = new JButton("back");
        this.add(backButton);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
