package chess.appcontroller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import chess.menu.MenuController;
import chess.move.Move;
import chess.form.FormController;
import chess.game.GameController;

/**
 * Az alkalmazás vezérlője, amely kezeli az egyes nézetek közötti váltást
 * és az alkalmazás főablakának beállításait.
 */
public class AppController {
    private JFrame frame;
    private MenuController menuController;
    private FormController formController;
    private GameController gameController;

    /**
     * Létrehozza az AppController osztály egy példányát, inicializálja az
     * alkalmazás főablakát és annak alapvető tulajdonságait.
     */
    public AppController() {
        frame = new JFrame("Game Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sakk");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height - 25;

        frame.setSize(screenWidth, screenHeight);
        frame.getContentPane().setBackground(new Color(22, 21, 19));
        frame.setLayout(new GridBagLayout());

        frame.setLocationRelativeTo(null);
    }

    /**
     * Elindítja az alkalmazást, megjeleníti a főmenüt és az ablakot láthatóvá
     * teszi.
     */
    public void start() {
        showMenu();
        frame.setVisible(true);
    }

    /**
     * Nézet váltása az alkalmazáson belül egy adott JPanel-re.
     * 
     * @param view a JPanel, amelyre váltani szeretnénk
     */
    private void switchToView(JPanel view) {
        frame.getContentPane().removeAll();
        frame.add(view);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Megjeleníti a főmenü nézetét.
     */
    public void showMenu() {
        if (menuController == null) {
            menuController = new MenuController(this);
        }
        switchToView(menuController.getView());
    }

    /**
     * Megjeleníti a játékhoz kapcsolódó form nézetét.
     */
    public void showForm() {
        if (formController == null) {
            formController = new FormController(this);
        }
        switchToView(formController.getView());
    }

    /**
     * Új játékot indít a megadott névvel és értékszinttel (ELO).
     * 
     * @param name a játékos neve
     * @param elo  a játékos értékszintje
     */
    public void newGame(String name, int elo) {
        gameController = new GameController(this, name, elo);
        switchToView(gameController.getView());
    }

    /**
     * Betölt egy korábban mentett játékot fájlból.
     * Megnyit egy fájlválasztó ablakot, beolvassa a fájl tartalmát, majd
     * inicializálja a játékot a mentett állapottal.
     */
    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String name = reader.readLine().split(":")[1].trim();
                int elo = Integer.parseInt(reader.readLine().split(":")[1].trim());

                reader.readLine();

                ArrayList<Move> moves = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] moveParts = line.split("->");
                    String[] origin = moveParts[0].split(",");
                    String[] target = moveParts[1].split(",");

                    int originCol = Integer.parseInt(origin[0]);
                    int originRow = Integer.parseInt(origin[1]);
                    int targetCol = Integer.parseInt(target[0]);
                    int targetRow = Integer.parseInt(target[1]);

                    moves.add(new Move(originCol, originRow, targetCol, targetRow));
                }

                gameController = new GameController(this, name, elo);
                gameController.setMoves(moves);

                switchToView(gameController.getView());
            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Hiba történt a játék betöltésekor: " + ex.getMessage(), "Hiba",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Visszaadja az alkalmazás főablakát.
     * 
     * @return a főablak JFrame példánya
     */
    public JFrame getFrame() {
        return frame;
    }
}
