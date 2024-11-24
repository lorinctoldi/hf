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

    public void resetBoard() {
        board.initializeBoard();
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
        if (selectedPiece != null && selectedPiece.getColor() == board.getTurn()) {
            highlightValidMoves(selectedRow, selectedCol);
        } else {
            System.out.println("No piece at selected position: " + selectedRow + "," + selectedCol);
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        Component releasedComponent = boardView.findComponentAt(e.getPoint());

        if (releasedComponent == null) {
            resetTileColors();
            return;
        }
        Point point = e.getPoint();
        targetRow = point.y / 100;
        targetCol = point.x / 100;

        if (!(selectedCol == targetCol && selectedRow == targetRow)) {
            if (isValidMove(selectedCol, selectedRow, targetCol, targetRow)) {
                gameController.addMove(selectedCol, selectedRow, targetCol, targetRow);
                performMove(selectedCol, selectedRow, targetCol, targetRow);
            }
        }

        selectedPiece = null;

        resetTileColors();
    }

    private boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow) {
        if (selectedPiece == null)
            return false;
        if (board.getTurn() != selectedPiece.getColor())
            return false;
        if (!selectedPiece.isValidMove(originCol, originRow, targetCol, targetRow, board))
            return false;

        if (board.willKingBeInCheck(originCol, originRow, targetCol, targetRow, board.getTurn()))
            return false;
        return true;
    }

    public void performMove(int originCol, int originRow, int targetCol, int targetRow) {
        selectedPiece = board.getPiece(originRow, originCol);

        board.setPiece(targetRow, targetCol, selectedPiece);
        board.setPiece(originRow, originCol, null);
        board.changeTurn();

        boardView.updateBoard();
        boardView.repaint();
        boardView.revalidate();

        if (board.isDraw(board.getTurn())) {
            String drawMessage = "The game is a draw!";
            JOptionPane.showMessageDialog(null, drawMessage);
            return;
        }

        if (board.isMate(board.getTurn())) {
            String winner = (board.getTurn() == Piece.Color.WHITE) ? "Black" : "White";
            JOptionPane.showMessageDialog(null, winner + " wins! Checkmate!");
            return;
        }
    }

    private void highlightValidMoves(int originRow, int originCol) {
        // Clear previous highlights
        resetTileColors();

        // Get the piece at the selected position
        Piece piece = board.getPiece(originRow, originCol);

        if (piece != null) {
            // Highlight the tile where the piece currently stands
            JPanel currentTile = (JPanel) boardView.getComponent(originRow * 8 + originCol);
            currentTile.setBackground(new Color(147, 177, 120)); // Highlight color for the current piece position

            // Loop through all possible board positions to highlight valid moves
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    // If this is a valid move for the selected piece, highlight the tile
                    if (piece.isValidMove(originCol, originRow, col, row, board)) {
                        JPanel tile = (JPanel) boardView.getComponent(row * 8 + col);
                        tile.setBackground(new Color(147, 177, 120));
                    }
                }
            }
        }
    }

    private void resetTileColors() {
        boardView.resetTileColors();
    }
}
