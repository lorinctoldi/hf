package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;

import appcontroller.AppController;
import board.BoardController;
import player.PlayerController;
import move.MoveController;
import move.Move;

public class GameController {
  private AppController appController;
  private BoardController boardController;
  private PlayerController playerController;
  private MoveController moveController;
  private GameView gameView;

  public GameController(AppController appController, String name, int elo, ArrayList<Move> moves) {
    this.appController = appController;
    boardController = new BoardController(this);
    playerController = new PlayerController(name, elo);
    moveController = new MoveController(moves);
    gameView = new GameView(boardController.getView(), playerController.getView(), moveController.getView());

    moveController.setMoveClickListener(moveIndex -> {
      replayMoves(moveIndex); // Replay up to the selected move
    });

    addEventListeners();
  }

  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    moveController.addMove(originCol, originRow, targetCol, targetRow);
  }

  public JPanel getView() {
    return gameView;
  }

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

  private void saveGameToFile(File file) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        // Write player name and Elo to the file
        writer.write("Player: " + playerController.getName() + "\n");
        writer.write("Elo: " + playerController.getElo() + "\n");

        // Write moves to the file
        writer.write("Moves:\n");
        for (Move move : moveController.getMoves()) {
            writer.write(move.getOriginCol() + "," + move.getOriginRow() + "->" +
                         move.getTargetCol() + "," + move.getTargetRow() + "\n");
        }

        JOptionPane.showMessageDialog(gameView, "A játék sikeresen elmentve: " + file.getAbsolutePath());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(gameView, "Hiba történt a mentés során: " + ex.getMessage(), "Hiba",
                JOptionPane.ERROR_MESSAGE);
    }
  }

  public void replayMoves(int upToIndex) {
    boardController.resetBoard();

    for (int i = 0; i <= upToIndex; i++) {
      Move move = moveController.getMoves().get(i);
      boardController.performMove(move.getOriginCol(), move.getOriginRow(), move.getTargetCol(), move.getTargetRow());
    }

    moveController.deleteMovesAfter(upToIndex);
  }
}
