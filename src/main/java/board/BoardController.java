package board;

import javax.swing.*;

import game.GameController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import piece.Pawn;
import piece.Piece;
import piece.Queen;

public class BoardController {
    private GameController gameController;
    private Board board;
    private BoardView boardView;

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

        Piece piece = board.getPiece(selectedRow, selectedCol);
        if (piece != null && piece.getColor() == board.getTurn()) {
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
            Piece piece = board.getPiece(selectedRow, selectedCol);
            if (isValidMove(selectedCol, selectedRow, targetCol, targetRow, piece)) {
                gameController.addMove(selectedCol, selectedRow, targetCol, targetRow);
                performMove(selectedCol, selectedRow, targetCol, targetRow);

                robotMove();
            }
        }

        resetTileColors();
    }

    private boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow, Piece piece) {
        if (piece == null)
            return false;
        if (board.getTurn() != piece.getColor())
            return false;

        if (!piece.isValidMove(originCol, originRow, targetCol, targetRow, board))
            return false;

        if (board.willKingBeInCheck(originCol, originRow, targetCol, targetRow, board.getTurn()))
            return false;

        if (piece.getType() == Piece.PieceType.KING && Math.abs(targetCol - originCol) == 2) {
            int rookCol = (targetCol > originCol) ? 7 : 0;
            if (!board.canCastle(piece.getColor(), rookCol)) {
                return false;
            }

            int step = (targetCol > originCol) ? 1 : -1;
            for (int col = originCol; col != targetCol + step; col += step) {
                if (board.isSquareUnderAttack(originRow, col, piece.getColor())) {
                    return false;
                }
            }
            return true;
        }

        return true;
    }

    public void performMove(int originCol, int originRow, int targetCol, int targetRow) {
        Piece piece = board.getPiece(originRow, originCol);

        if (piece.getType() == Piece.PieceType.KING && Math.abs(targetCol - originCol) == 2) {
            int rookCol = (targetCol > originCol) ? 7 : 0; // Determine which rook is involved
            int rookTargetCol = (targetCol > originCol) ? targetCol - 1 : targetCol + 1;

            // Move the rook
            Piece rook = board.getPiece(originRow, rookCol);
            board.setPiece(originRow, rookTargetCol, rook);
            board.setPiece(originRow, rookCol, null);
        }

        board.setPiece(targetRow, targetCol, piece);
        board.setPiece(originRow, originCol, null);

        if (piece.getType() == Piece.PieceType.PAWN) {
            if ((piece.getColor() == Piece.Color.WHITE && targetRow == 0) ||
                    (piece.getColor() == Piece.Color.BLACK && targetRow == 7)) {
                promotePawn(targetRow, targetCol, piece.getColor());
            }
        }

        board.changeTurn();

        boardView.updateBoard();
        boardView.repaint();
        boardView.revalidate();

        if (isDraw()) {
            JOptionPane.showMessageDialog(null, "The game is a draw!");
        }
        if (isMate()) {
            String winner = (board.getTurn() == Piece.Color.WHITE) ? "Black" : "White";
            JOptionPane.showMessageDialog(null, winner + " wins! Checkmate!");
        }
    }

    private void promotePawn(int row, int col, Piece.Color color) {
        Piece promotedPiece = new Queen(color, row, col);
        board.setPiece(row, col, promotedPiece);
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

    public void robotMove() {
        Piece.Color robotColor = board.getTurn();
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        // Find all possible moves
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getColor() == robotColor) {
                    for (int targetRow = 0; targetRow < 8; targetRow++) {
                        for (int targetCol = 0; targetCol < 8; targetCol++) {
                            if (isValidMove(col, row, targetCol, targetRow, board.getPiece(row, col))) {
                                possibleMoves.add(new int[] { col, row, targetCol, targetRow });
                            }
                        }
                    }
                }
            }
        }

        // Select a random move
        if(possibleMoves.size() == 0) {
            if (isDraw()) {
                JOptionPane.showMessageDialog(null, "The game is a draw!");
            }
            if (isMate()) {
                String winner = (board.getTurn() == Piece.Color.WHITE) ? "Black" : "White";
                JOptionPane.showMessageDialog(null, winner + " wins! Checkmate!");
            }
            return;
        }
        Random random = new Random(possibleMoves.size());
        int[] chosenMove = possibleMoves.get(random.nextInt(possibleMoves.size()));

        // Perform the move
        int originCol = chosenMove[0];
        int originRow = chosenMove[1];
        int targetCol = chosenMove[2];
        int targetRow = chosenMove[3];

        gameController.addMove(originCol, originRow, targetCol, targetRow);
        performMove(originCol, originRow, targetCol, targetRow);
    }

    public boolean isMate() {
        return this.board.isMate(this.board.getTurn());
    }

    public boolean isDraw() {
        return this.board.isDraw(this.board.getTurn());
    }

    private void resetTileColors() {
        boardView.resetTileColors();
    }
}
