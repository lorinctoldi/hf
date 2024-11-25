package chess.game;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;

import chess.appcontroller.AppController;
import chess.move.Move;

public class GameController {
  private AppController appController;
  private GameView gameView;
  private Game game;

  /**
   * Konstruktor, amely inicializálja a játék vezérlőt, a játéknézetet,
   * és beállítja az eseménykezelőket.
   * 
   * @param appController az alkalmazás vezérlője
   * @param name          a játékos neve
   * @param elo           a játékos Elo értéke
   */
  public GameController(AppController appController, String name, int elo) {
    this.appController = appController;
    game = new Game(name, elo);
    gameView = new GameView(game);

    addEventListeners();
  }

  /**
   * Visszaadja a játéknézetet.
   * 
   * @return a játéknézet
   */
  public JPanel getView() {
    return gameView;
  }

  /**
   * Beállítja a gombokhoz tartozó eseménykezelőket:
   * - Kilépés gomb: visszatér a főmenübe
   * - Feladás gomb: megerősíti a feladás szándékát, majd visszatér a főmenübe
   * - Mentés gomb: lehetővé teszi a játék mentését fájlba
   */
  private void addEventListeners() {
    gameView.getExitButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        appController.showMenu();
      }
    });

    gameView.getResignButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(gameView, "Biztosan feladja a játékot?", "Feladás",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(gameView, "Feladta a játékot.");
          appController.showMenu();
        }
      }
    });

    gameView.getSaveButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        gameView.getFileChooser().setVisible(true);
        int returnValue = gameView.getFileChooser().showSaveDialog(gameView);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = gameView.getFileChooser().getSelectedFile();
          saveGameToFile(selectedFile);
        }
      }
    });
  }

  /**
   * Elmenti a játék állapotát egy fájlba, beleértve a játékos nevét,
   * ELO értékét és az összes végrehajtott lépést.
   * 
   * @param file a fájl, ahová mentjük a játékot
   */
  private void saveGameToFile(File file) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      if (!file.getName().toLowerCase().endsWith(".txt")) {
        file = new File(file.getAbsolutePath() + ".txt");
      }

      writer.write("Player: " + game.getPlayerController().getName() + "\n");
      writer.write("Elo: " + game.getPlayerController().getElo() + "\n");

      writer.write("Moves:\n");
      for (Move move : game.getMoveController().getMoves()) {
        writer.write(move.getOriginCol() + "," + move.getOriginRow() + "->" +
            move.getTargetCol() + "," + move.getTargetRow() + "\n");
      }

      JOptionPane.showMessageDialog(gameView, "A játék sikeresen elmentve: " + file.getAbsolutePath());
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(gameView, "Hiba történt a mentés során: " + ex.getMessage(), "Hiba",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Beállítja a játék lépéseit a megadott listával.
   * 
   * @param moves a lépések listája
   */
  public void setMoves(ArrayList<Move> moves) {
    game.setMoves(moves);
  }
}
