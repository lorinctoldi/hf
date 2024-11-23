package board;

import javax.swing.*;

import game.GameController;

import java.awt.*;
import java.awt.event.*;
import piece.Piece;

public class BoardController {
    private GameController gameController;
    private Board board;
    private BoardView boardView;
    private Piece selectedPiece;
    int selectedRow;
    int selectedCol;
    int targetRow;
    int targetCol;

    public BoardController(GameController gameController) {
        this.gameController = gameController;
        board = new Board();
        boardView = new BoardView(board);
        setUpListeners();
    }

    public Board getBoard() {
        return board;
    }

    public JPanel getView() {
        return boardView;
    }

    private void setUpListeners() {
        boardView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });
    }

    private void handleMousePressed(MouseEvent e) {
        Point point = e.getPoint();

        selectedRow = point.y / 100;
        selectedCol = point.x / 100;

        selectedPiece = board.getPiece(selectedRow, selectedCol);
        if (selectedPiece != null) {
            highlightTiles(Color.RED);
        } else {
            System.out.println("No piece at selected position: " + selectedRow + "," + selectedCol);
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        Component releasedComponent = boardView.findComponentAt(e.getPoint());

        if (releasedComponent == null) {
            System.out.println("Released component is neither a tile nor a piece view.");
            resetTileColors();
            return;
        }
        Point point = e.getPoint();
        targetRow = point.y / 100;
        targetCol = point.x / 100;

        if (true) {
            System.out.println("Moved piece to: " + targetRow + "," + targetCol);
            board.setPiece(targetRow, targetCol, selectedPiece);
            board.setPiece(selectedRow, selectedCol, null);
            gameController.addMove(selectedCol, selectedRow, targetCol, targetRow);
            boardView.updateBoard();
        } else {
            System.out.println("Move failed.");
        }

        selectedPiece = null;

        resetTileColors(); // Reset tile colors after the move attempt
    }

    private void highlightTiles(Color color) {
        boardView.highlightTiles(color);
    }

    private void resetTileColors() {
        boardView.resetTileColors();
    }
}
