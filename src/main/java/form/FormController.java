package form;

import javax.swing.*;
import appcontroller.AppController;

public class FormController {
    private final Form model;
    private final FormView view;
    private final AppController appController;

    public FormController(AppController appController) {
        this.model = new Form();
        this.view = new FormView(model);
        this.appController = appController;

        initController();
    }

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
                    JOptionPane.showMessageDialog(view, "Az ELO szám formátumú kell legyen!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getCancelButton().addActionListener(e -> appController.showMenu());
    }

    public JPanel getView() {
        return view;
    }
}

