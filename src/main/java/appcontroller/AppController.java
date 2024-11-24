package appcontroller;

import javax.swing.*;

import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import menu.MenuController;
import move.Move;
import form.FormController;
import game.GameController;

public class AppController {
    private JFrame frame;
    private MenuController menuController;
    private FormController formController;
    private GameController gameController;

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

    public void start() {
        showMenu();
        frame.setVisible(true);
    }

    private void switchToView(JPanel view) {
        frame.getContentPane().removeAll();
        frame.add(view);
        frame.revalidate();
        frame.repaint();
    }

    public void showMenu() {
        if (menuController == null) {
            menuController = new MenuController(this);
        }
        switchToView(menuController.getView());
    }

    public void showForm() {
        if (formController == null) {
            formController = new FormController(this);
        }
        switchToView(formController.getView());
    }

    public void newGame(String name, int elo) {
        gameController = new GameController(this, name, elo, null);
        switchToView(gameController.getView());
    }

    public void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
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

                gameController = new GameController(this, name, elo, moves);
                switchToView(gameController.getView());
                gameController.replayMoves(moves.size()); 

                switchToView(gameController.getView());

            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Hiba történt a játék betöltésekor: " + ex.getMessage(), "Hiba",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}
