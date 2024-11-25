package chess.board;

import javax.swing.*;
import java.awt.*;

import chess.piece.*;

public class BoardView extends JPanel {
    private Board board;
    public int BOARD_SIZE = 800;

    /**
     * Konstruktor, amely inicializálja a táblát, beállítja a grid-et és megjeleníti
     * a kezdeti állapotot.
     * 
     * @param board a Board objektum, amely tartalmazza a játék aktuális állapotát
     */
    public BoardView(Board board) {
        this.board = board;
        this.setLayout(new GridLayout(8, 8));
        initializeBoard();
    }

    /**
     * Inicializálja a táblát, beállítja a mezőket és azok hátterét, valamint
     * elhelyezi a bábukat a mezőkön.
     */
    private void initializeBoard() {
        int tileSize = BOARD_SIZE / 8;
        int pieceSize = tileSize * 80 / 100;
        int padding = tileSize * 10 / 100;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel tile = new JPanel(null);
                setTileBackground(tile, row, col);
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    PieceView pieceView = new PieceView(piece);
                    pieceView.setBounds(padding, padding, pieceSize, pieceSize);
                    tile.add(pieceView);
                }

                this.add(tile);
            }
        }

        this.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
    }

    /**
     * Visszaadja a mező indexét a JPanel objektum alapján.
     * 
     * @param tile a keresett mező JPanel-je
     * @return a mező indexe, vagy -1, ha nem található
     */
    public int getTileIndex(JPanel tile) {
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (this.getComponent(i) == tile) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Beállítja a mező háttérszínét a sor és oszlop alapján, világos vagy sötét
     * színt választva.
     * 
     * @param tile a módosítandó mező JPanel-je
     * @param row  a mező sorának indexe
     * @param col  a mező oszlopának indexe
     */
    private void setTileBackground(JPanel tile, int row, int col) {
        Color lightColor = new Color(222, 227, 230);
        Color darkColor = new Color(140, 162, 173);
        boolean isLightSquare = (row + col) % 2 == 0;
        tile.setBackground(isLightSquare ? lightColor : darkColor);
    }

    /**
     * Visszaállítja a tábla összes mezőjének háttérszínét az alapértelmezett
     * állapotra.
     */
    public void resetTileColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel tile = (JPanel) this.getComponent(row * 8 + col);
                setTileBackground(tile, row, col);
            }
        }
    }

    /**
     * Frissíti a tábla nézetét az új állapotnak megfelelően.
     * Eltávolítja az összes komponenset, újra inicializálja a táblát és
     * újrarendereli.
     */
    public void updateBoard() {
        this.removeAll();
        initializeBoard();
        this.revalidate();
        this.repaint();
    }
}
