package game;

import javax.swing.*;

import appcontroller.AppController;
import board.BoardController;
import player.PlayerController;
import move.MoveController;

public class GameController {
  private BoardController boardController;
  private PlayerController playerController;
  private MoveController moveController;

  public GameController(AppController appController, String name, int elo) {
    boardController = new BoardController(this);
    playerController = new PlayerController(name, elo);
    moveController = new MoveController();
  }

  public void addMove(int originCol, int originRow, int targetCol, int targetRow) {
    moveController.addMove(originCol, originRow, targetCol, targetRow);
    moveController.print();
  }

  public JPanel getView() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    panel.add(boardController.getView());
    // panel.add(playerController.getView());
    panel.add(moveController.getView());

    return panel;
  }
} 
