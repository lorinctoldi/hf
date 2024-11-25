package form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FormView extends JPanel {
    private JTextField nameField;
    private JTextField eloField;
    private JButton submitButton;
    private JButton cancelButton;

    public FormView(Form model) {
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the form

        nameField = createTextField(model.getName(), "Adja meg a nevét..");

        eloField = createTextField(model.getElo() == 0 ? "" : String.valueOf(model.getElo()), "Adja meg az ELO számát...");

        // Buttons
        submitButton = createButton("Tovább");
        cancelButton = createButton("Vissza");

        nameField.setAlignmentX(CENTER_ALIGNMENT);
        eloField.setAlignmentX(CENTER_ALIGNMENT);
        submitButton.setAlignmentX(CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(CENTER_ALIGNMENT);

        // Add components to the panel with 20px space between each element
        add(nameField);
        add(Box.createRigidArea(new Dimension(0, 10))); // Space between elements
        add(eloField);
        add(Box.createRigidArea(new Dimension(0, 20))); // Space between elements
        add(submitButton);
        add(Box.createRigidArea(new Dimension(0, 20))); // Space between elements
        add(cancelButton);
    }


    private JTextField createTextField(String text, String placeholder) {
        JTextField textField = new JTextField(text, 20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font for text fields
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(57, 55, 51)); // Dark background for text fields
        textField.setCaretColor(Color.WHITE); // Set the caret (cursor) color to white
        textField.setPreferredSize(new Dimension(260, 40)); // Set width to 260px
        textField.setMaximumSize(new Dimension(260, 40)); // Set width to 260px
        textField.setMinimumSize(new Dimension(260, 40)); // Set width to 260px

        // Set placeholder text when field is empty
        setPlaceholder(textField, placeholder);

        // Add focus listener to handle placeholder text behavior
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Remove placeholder text when the field is focused
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Reapply placeholder if the field is empty
                if (textField.getText().isEmpty()) {
                    setPlaceholder(textField, placeholder);
                }
            }
        });

        return textField;
    }

    // Helper method to set placeholder text
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(new Color(148, 148, 148)); // Set placeholder text color (light gray)
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


    public String getNameInput() {
        return nameField.getText().trim();
    }

    public String getEloInput() {
        return eloField.getText().trim();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}