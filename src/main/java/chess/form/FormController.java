package chess.form;

import javax.swing.*;
import chess.appcontroller.AppController;

public class FormController {
    private Form model;
    private FormView view;
    private AppController appController;

    /**
     * Konstruktor, amely inicializálja a modellt, a nézetet és az
     * alkalmazásvezérlőt.
     * Beállítja az eseménykezelőket is.
     * 
     * @param appController az alkalmazásvezérlő, amelyet a form vezérlőhöz rendel
     */
    public FormController(AppController appController) {
        this.model = new Form();
        this.view = new FormView(model);
        this.appController = appController;

        initController();
    }

    /**
     * Inicializálja az eseménykezelőket:
     * - A "submit" gomb eseményét, amely ellenőrzi a név és az Elo formátumát,
     * majd elmenti az adatokat, és elindítja az új játékot.
     * - A "cancel" gomb eseményét, amely visszatér a menübe.
     */
    private void initController() {
        view.getSubmitButton().addActionListener(e -> {
            String name = view.getNameInput();
            String eloText = view.getEloInput();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(view, "A név nem lehet üres!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int elo = Integer.parseInt(eloText);
                    model.setName(name);
                    model.setElo(elo);
                    appController.newGame(name, elo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Az ELO szám formátumú kell legyen!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getCancelButton().addActionListener(e -> appController.showMenu());
    }

    /**
     * Visszaadja a nézetet, amelyet a FormController kezel.
     * 
     * @return a nézet JPanel objektumként
     */
    public JPanel getView() {
        return view;
    }
}
