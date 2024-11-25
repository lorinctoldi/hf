package menu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.util.ArrayList;

public class MenuView extends JPanel {
    private final ArrayList<JButton> buttons;

public MenuView(Menu model) {
        this.buttons = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        setBackground(new Color(0, 0, 0, 0)); 

        ArrayList<String> options = model.getOptions();
        for (String option : options) {
            JButton button = new JButton(option);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setForeground(new Color(148, 148, 148));
            button.setFont(new Font("Arial", Font.PLAIN, 18));

            button.setBackground(new Color(57, 55, 51));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setUI(new BasicButtonUI());

            Dimension buttonSize = new Dimension(260, 40);
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);


            buttons.add(button);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(button);
        }

        add(Box.createRigidArea(new Dimension(0, 0)));
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }
}
