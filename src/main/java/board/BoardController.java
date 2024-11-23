package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import piece.Piece;
import piece.PieceView;

public class BoardController {
    private Board board;
    private BoardView boardView;
    private Piece selectedPiece;
    int selectedRow;
    int selectedCol;
    int targetRow;
    int targetCol;

    public BoardController() {
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
            highlightTiles(Color.RED); // Highlight all tiles in red
            System.out.println("Piece selected: " + selectedPiece + " at: " + selectedRow + "," + selectedCol);
        } else {
            System.out.println("No piece at selected position: " + selectedRow + "," + selectedCol);
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        Component releasedComponent = boardView.findComponentAt(e.getPoint());
        System.out.println(releasedComponent);
        JPanel releasedTile;

        if (releasedComponent == null) {
            System.out.println("Released component is neither a tile nor a piece view.");
            resetTileColors(); // Reset tile colors before exiting
            return;
        }
        Point point = e.getPoint();
        targetRow = point.y / 100;
        targetCol = point.x / 100;

        if (releasedComponent instanceof PieceView) {
            releasedTile = (JPanel) releasedComponent.getParent();
        } else if (releasedComponent instanceof JPanel) {
            releasedTile = (JPanel) releasedComponent;
        }

        if (true) {
            System.out.println("Moved piece to: " + targetRow + "," + targetCol);
            board.setPiece(targetRow, targetCol, selectedPiece);
            board.setPiece(selectedRow, selectedCol, null);
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
