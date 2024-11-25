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

        // Set the background color of the panel (MenuView)
        setBackground(new Color(0, 0, 0, 0)); 

        ArrayList<String> options = model.getOptions();
        for (String option : options) {
            JButton button = new JButton(option);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setForeground(new Color(148, 148, 148));
            button.setFont(new Font("Arial", Font.PLAIN, 18));

            // Set the button background color and make it opaque
            button.setBackground(new Color(57, 55, 51)); // Button background color (adjust as needed)
            button.setOpaque(true);
            button.setBorderPainted(false); // Remove the border to avoid default white background around text
            button.setUI(new BasicButtonUI()); // Override default button UI to ensure consistent behavior

            Dimension buttonSize = new Dimension(260, 40); // Adjust width (200) and height (50) as needed
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
