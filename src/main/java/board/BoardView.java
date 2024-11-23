package board;

import javax.swing.*;
import java.awt.*;

import piece.*;

public class BoardView extends JPanel {
    private Board board;
    public static final int BOARD_SIZE = 800; // Fixed size of 800x800

    public BoardView(Board board) {
        this.board = board;
        this.setLayout(new GridLayout(8, 8)); // 8x8 grid layout for the chess board
        initializeBoard();
    }

    private void initializeBoard() {
        int tileSize = BOARD_SIZE / 8; // Calculate tile size based on fixed board size
        int pieceSize = tileSize * 80 / 100; // Piece size will be 80% of tile size
        int padding = tileSize * 10 / 100; // Padding will be 10% of tile size

        // Create the 8x8 board layout with pieces and labels
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel tile = new JPanel(null);
                setTileBackground(tile, row, col); // Set background color of tile
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    PieceView pieceView = new PieceView(piece);
                    pieceView.setBounds(padding, padding, pieceSize, pieceSize); // Position the piece
                    tile.add(pieceView);
                }

                // Add file (a-h) labels along the bottom row
                if (row == 7) {
                    JLabel fileLabel = new JLabel(Character.toString((char) ('a' + col)));
                    fileLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    fileLabel.setForeground(Color.BLACK);
                    fileLabel.setBounds(tileSize * col + padding, tileSize * 7 + padding, tileSize, tileSize);
                    tile.add(fileLabel);
                }

                // Add rank (1-8) labels along the right column
                if (col == 7) {
                    JLabel rankLabel = new JLabel(Integer.toString(8 - row));
                    rankLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    rankLabel.setForeground(Color.BLACK);
                    rankLabel.setBounds(tileSize * 7 + padding, tileSize * row + padding, tileSize, tileSize);
                    tile.add(rankLabel);
                }

                this.add(tile);
            }
        }

        // Set the preferred size of the board to a fixed size
        this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
    }

    public int getTileIndex(JPanel tile) {
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (this.getComponent(i) == tile) {
                return i;
            }
        }
        return -1;
    }

    private void setTileBackground(JPanel tile, int row, int col) {
        Color lightColor = new Color(222, 227, 230);
        Color darkColor = new Color(140, 162, 173);
        boolean isLightSquare = (row + col) % 2 == 0;
        tile.setBackground(isLightSquare ? lightColor : darkColor);
    }

    public void highlightTiles(Color color) {
        for (Component comp : this.getComponents()) {
            comp.setBackground(color);
        }
    }

    public void resetTileColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel tile = (JPanel) this.getComponent(row * 8 + col);
                setTileBackground(tile, row, col);
            }
        }
    }

    public void updateBoard() {
        this.removeAll();
        initializeBoard();
        this.revalidate();
        this.repaint();
    }
}
