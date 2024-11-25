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
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = createTextField(model.getName(), "Adja meg a nevét..");

        eloField = createTextField(model.getElo() == 0 ? "" : String.valueOf(model.getElo()), "Adja meg az ELO számát...");

        submitButton = createButton("Tovább");
        cancelButton = createButton("Vissza");

        nameField.setAlignmentX(CENTER_ALIGNMENT);
        eloField.setAlignmentX(CENTER_ALIGNMENT);
        submitButton.setAlignmentX(CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(CENTER_ALIGNMENT);

        add(nameField);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(eloField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(submitButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(cancelButton);
    }


    private JTextField createTextField(String text, String placeholder) {
        JTextField textField = new JTextField(text, 20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(57, 55, 51));
        textField.setCaretColor(Color.WHITE);
        textField.setPreferredSize(new Dimension(260, 40));
        textField.setMaximumSize(new Dimension(260, 40));
        textField.setMinimumSize(new Dimension(260, 40));

        setPlaceholder(textField, placeholder);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    setPlaceholder(textField, placeholder);
                }
            }
        });

        return textField;
    }

    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(new Color(148, 148, 148));
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