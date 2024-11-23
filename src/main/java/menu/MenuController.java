package menu;

import javax.swing.*;
import appcontroller.AppController;

public class MenuController {
    private final Menu model;
    private final MenuView view;
    private final AppController appController;

    public MenuController(AppController appController) {
        this.model = new Menu();
        this.view = new MenuView(model);
        this.appController = appController;

        initController();
    }

    private void initController() {
        for (int i = 0; i < view.getButtons().size(); i++) {
            JButton button = view.getButtons().get(i);
            switch (i) {
                case 0:
                    button.addActionListener(e -> appController.showForm());
                    break;
                case 1:
                    button.addActionListener(e -> appController.loadGame());
                    break;
                case 2:
                    button.addActionListener(e -> System.exit(0));
                    break;
                default:
                    break;
            }
        }
    }

    public JPanel getView() {
        return view;
    }
}
