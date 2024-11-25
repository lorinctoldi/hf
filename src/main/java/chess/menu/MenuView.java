package chess.menu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.ArrayList;

public class MenuView extends JPanel {
    private ArrayList<JMenuItem> menuItems;

    /**
     * Konstruktor, amely létrehozza a menü elemeket és beállítja azok
     * megjelenítését.
     * Az elemeket a modell adja át, és a nézet a BoxLayout segítségével
     * függőlegesen rendezi el őket.
     * 
     * @param model A menü modell, amely tartalmazza a menü opcióit.
     */
    public MenuView(Menu model) {
        this.menuItems = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBackground(new Color(0, 0, 0, 0));

        ArrayList<String> options = model.getOptions();
        for (String option : options) {
            JMenuItem menuItem = createStyledMenuItem(option);
            menuItems.add(menuItem);
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(menuItem);
        }

        add(Box.createRigidArea(new Dimension(0, 0)));
    }

    /**
     * Létrehoz egy stílusozott JMenuItem objektumot a megadott szöveggel.
     * Az elem egyedi stílust kap, amely a menü elemeinek megjelenését formázza.
     * 
     * @param text A menüelem szövege.
     * @return A stílusozott JMenuItem objektum.
     */
    private JMenuItem createStyledMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);

        menuItem.setForeground(new Color(148, 148, 148));
        menuItem.setFont(new Font("Arial", Font.PLAIN, 18));
        menuItem.setBackground(new Color(57, 55, 51));
        menuItem.setOpaque(true);
        menuItem.setBorderPainted(false);
        menuItem.setUI(new BasicButtonUI());

        Dimension itemSize = new Dimension(260, 40);
        menuItem.setPreferredSize(itemSize);
        menuItem.setMinimumSize(itemSize);
        menuItem.setMaximumSize(itemSize);

        return menuItem;
    }

    /**
     * Visszaadja a menüelemek listáját.
     * 
     * @return A menüelemek listája, amely tartalmazza az összes létrehozott
     *         JMenuItem-et.
     */
    public ArrayList<JMenuItem> getMenuItems() {
        return menuItems;
    }
}
