package chess.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FormView extends JPanel {
    private JTextField nameField;
    private JTextField eloField;
    private JButton submitButton;
    private JButton cancelButton;

    /**
     * Konstruktor, amely beállítja a nézetet, beleértve a háttérszínt, elrendezést,
     * és a felhasználói űrlap mezőit (név, Elo szám) és gombokat (Tovább, Vissza).
     * 
     * @param model a Form modell, amely a nézetet táplálja
     */
    public FormView(Form model) {
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = createTextField(model.getName(), "Adja meg a nevét..");

        eloField = createTextField(model.getElo() == 0 ? "" : String.valueOf(model.getElo()),
                "Adja meg az ELO számát...");

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

    /**
     * Létrehoz egy JTextField objektumot, amelyhez beállítja az alapértelmezett
     * szöveget,
     * a helykitöltőt és a megfelelő fókusz eseményeket.
     * 
     * @param text        a mező kezdő szövege
     * @param placeholder a mező helykitöltő szövege
     * @return az új szövegdoboz
     */
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

    /**
     * Beállítja a helykitöltőt a JTextField számára, ha a mező üres.
     * 
     * @param textField   a módosítandó szövegdoboz
     * @param placeholder a helykitöltő szöveg
     */
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(new Color(148, 148, 148));
    }

    /**
     * Létrehoz egy JButton objektumot a megadott szöveggel, beállítva annak
     * stílusát.
     * 
     * @param text a gomb szövege
     * @return az új gomb
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
     * Visszaadja a felhasználó által beírt nevet.
     * 
     * @return a név, amit a felhasználó megadott
     */
    public String getNameInput() {
        return nameField.getText().trim();
    }

    /**
     * Visszaadja a felhasználó által beírt Elo számot.
     * 
     * @return az Elo szám, amit a felhasználó megadott
     */
    public String getEloInput() {
        return eloField.getText().trim();
    }

    /**
     * Visszaadja a Tovább gombot.
     * 
     * @return a Tovább gomb
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Visszaadja a Vissza gombot.
     * 
     * @return a Vissza gomb
     */
    public JButton getCancelButton() {
        return cancelButton;
    }
}
