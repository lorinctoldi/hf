package chess.board;

import javax.swing.*;

import chess.game.Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import chess.piece.Piece;

public class BoardController {
    private Game game;
    private Board board;
    private BoardView boardView;

    int selectedRow;
    int selectedCol;
    int targetRow;
    int targetCol;

    /**
     * Konstruktor, amely inicializálja a játékot, a táblát és a nézetet,
     * valamint beállítja az eseménykezelőket.
     * 
     * @param game a Game objektum, amely tartalmazza a játék állapotát
     */
    public BoardController(Game game) {
        this.game = game;
        board = new Board();
        boardView = new BoardView(board);
        setUpListeners();
    }

    public void resetBoard() {
        board.initializeBoard();
    }

    /**
     * Újraindítja a táblát, visszaállítva annak kezdeti állapotát.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Visszaadja a tábla aktuális állapotát.
     * 
     * @return a tábla
     */
    public JPanel getView() {
        return boardView;
    }

    /**
     * Visszaadja a táblát megjelenítő JPanel nézetet.
     * 
     * @return a BoardView JPanel nézet
     */
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

    /**
     * Eseménykezelő beállítása a táblán, hogy reagáljon az egérnyomásra és felengedésre.
     */
    private void handleMousePressed(MouseEvent e) {
        Point point = e.getPoint();

        selectedRow = point.y / 100;
        selectedCol = point.x / 100;

        Piece piece = board.getPiece(selectedRow, selectedCol);
        if (piece != null && piece.getColor() == board.getTurn()) {
            highlightValidMoves(selectedRow, selectedCol);
        }
    }

    /**
     * Kezeli az egér lenyomásának eseményét, kiválasztja az adott mezőt és kiemeli
     * a lehetséges lépéseket.
     * 
     * @param e az egérnyomás eseménye
     */
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
                game.addMove(selectedCol, selectedRow, targetCol, targetRow);
                performMove(selectedCol, selectedRow, targetCol, targetRow);

                robotMove();
            }
        }

        resetTileColors();
    }

    /**
     * Kezeli az egér felengedésének eseményét, végrehajtja a lépést, ha érvényes,
     * és a robotot is lépteti, ha szükséges.
     * 
     * @param e az egérfelengedés eseménye
     */
    private boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow) {
        return board.isValidMove(originCol, originRow, targetCol, targetRow);
    }

    /**
     * Ellenőrzi, hogy a megadott lépés érvényes-e a táblán.
     * 
     * @param originCol a kiinduló oszlop indexe
     * @param originRow a kiinduló sor indexe
     * @param targetCol a cél oszlop indexe
     * @param targetRow a cél sor indexe
     * @return true, ha a lépés érvényes, egyébként false
     */
    public void performMove(int originCol, int originRow, int targetCol, int targetRow) {
        board.performMove(originCol, originRow, targetCol, targetRow);

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

    /**
     * Végrehajtja a megadott lépést a táblán, és frissíti a nézetet.
     * Ellenőrzi, hogy döntetlen vagy matt történt-e a lépés után.
     * 
     * @param originCol a kiinduló oszlop indexe
     * @param originRow a kiinduló sor indexe
     * @param targetCol a cél oszlop indexe
     * @param targetRow a cél sor indexe
     */
    private void highlightValidMoves(int originRow, int originCol) {
        resetTileColors();

        Piece piece = board.getPiece(originRow, originCol);

        if (piece != null) {
            JPanel currentTile = (JPanel) boardView.getComponent(originRow * 8 + originCol);
            currentTile.setBackground(new Color(147, 177, 120));

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (piece.isValidMove(originCol, originRow, col, row, board)) {
                        JPanel tile = (JPanel) boardView.getComponent(row * 8 + col);
                        tile.setBackground(new Color(147, 177, 120));
                    }
                }
            }
        }
    }

    /**
     * A robot lépéseit hajtja végre, véletlenszerűen választva egy érvényes lépést
     * a lehetségesek közül.
     * Ha nincs érvényes lépés, ellenőrzi a döntetlent vagy mattot.
     */
    public void robotMove() {
        Piece.Color robotColor = board.getTurn();
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getColor() == robotColor) {
                    for (int targetRow = 0; targetRow < 8; targetRow++) {
                        for (int targetCol = 0; targetCol < 8; targetCol++) {
                            if (isValidMove(col, row, targetCol, targetRow)) {
                                possibleMoves.add(new int[] { col, row, targetCol, targetRow });
                            }
                        }
                    }
                }
            }
        }

        if (possibleMoves.size() == 0) {
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

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int originCol = chosenMove[0];
                int originRow = chosenMove[1];
                int targetCol = chosenMove[2];
                int targetRow = chosenMove[3];

                game.addMove(originCol, originRow, targetCol, targetRow);
                performMove(originCol, originRow, targetCol, targetRow);

                ((Timer) e.getSource()).stop();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Ellenőrzi, hogy történt-e matt a táblán.
     * 
     * @return true, ha matt történt, egyébként false
     */
    public boolean isMate() {
        return this.board.isMate(this.board.getTurn());
    }

    /**
     * Ellenőrzi, hogy döntetlen van-e a táblán.
     * 
     * @return true, ha döntetlen, egyébként false
     */
    public boolean isDraw() {
        return this.board.isDraw(this.board.getTurn());
    }

    /**
     * Visszaállítja a táblán lévő mezők színeit az alapértelmezett állapotra.
     */
    private void resetTileColors() {
        boardView.resetTileColors();
    }
}
