package chess.menu;

import javax.swing.*;
import chess.appcontroller.AppController;

public class MenuController {
    private Menu model;
    private MenuView view;
    private AppController appController;

    /**
     * Konstruktor, amely inicializálja a modell, nézet és vezérlő objektumokat,
     * majd beállítja az eseménykezelőket a menüelemekhez.
     * 
     * @param appController Az alkalmazás vezérlője
     */
    public MenuController(AppController appController) {
        this.model = new Menu();
        this.view = new MenuView(model);
        this.appController = appController;

        initController();
    }

    /**
     * Inicializálja a menüpontok eseménykezelőit. Minden egyes menüponthoz
     * hozzáad egy ActionListener-t, amely a megfelelő funkciókat hajtja végre.
     */
    private void initController() {
        for (int i = 0; i < view.getMenuItems().size(); i++) {
            JMenuItem menuItem = view.getMenuItems().get(i);

            switch (i) {
                case 0:
                    menuItem.addActionListener(e -> appController.showForm());
                    break;
                case 1:
                    menuItem.addActionListener(e -> appController.loadGame());
                    break;
                case 2:
                    menuItem.addActionListener(e -> System.exit(0));
                    break;
                default:
                    break;
            }
        }
        ;
    }

    /**
     * Visszaadja a menü nézetét.
     * 
     * @return A menü nézet, amely a felhasználónak megjeleníti a menüt
     */
    public JPanel getView() {
        return view;
    }
}
