package chess.piece;

import chess.board.Board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Bishop osztály tesztelésére szolgáló osztály.
 * A tesztek ellenőrzik a futó mozgásának helyességét.
 */
class BishopTest {
    /**
     * Teszteli, hogy a futó érvényes lépést hajt végre, ha az útvonal szabad.
     * Ellenőrzi, hogy a futó helyesen mozog átlósan, ha nincs akadály az úton.
     */
    @Test
    void testValidMoveDiagonalPathClear() {
        Board board = new Board();
        Bishop bishop = new Bishop(Piece.Color.WHITE, 4, 4);

        boolean isValid = bishop.isValidMove(4, 4, 5, 5, board);

        assertTrue(isValid);
    }

    /**
     * Teszteli, hogy a futó nem mozoghat nem átlósan.
     * Ellenőrzi, hogy a futó helytelen lépést hajt végre, ha nem átlósan mozog.
     */
    @Test
    void testInvalidMoveNonDiagonal() {
        Board board = new Board();
        Bishop bishop = new Bishop(Piece.Color.WHITE, 4, 4);

        boolean isValid = bishop.isValidMove(4, 4, 5, 6, board);

        assertFalse(isValid);
    }

    /**
     * Teszteli, hogy a futó nem hajthat végre érvényes lépést, ha az útvonal
     * blokkolva van.
     * Ellenőrzi, hogy ha akadály van az úton, a futó nem tud lépni.
     */
    @Test
    void testInvalidMovePathBlocked() {
        Board board = new Board();
        Bishop bishop = new Bishop(Piece.Color.WHITE, 4, 4);

        boolean isValid = bishop.isValidMove(0, 0, 7, 7, board);

        assertFalse(isValid);
    }

    /**
     * Teszteli, hogy a futó képes leütni egy ellenfél bábút.
     * Ellenőrzi, hogy a futó helyesen végrehajt egy érvényes lépést, ha egy
     * ellenfél bábut üt fel.
     */
    @Test
    void testCaptureOpponentPiece() {
        Board board = new Board();
        Bishop bishop = new Bishop(Piece.Color.WHITE, 4, 4);

        boolean isValid = bishop.isValidMove(4, 4, 1, 1, board);

        assertTrue(isValid);
    }

    /**
     * Teszteli, hogy a futó nem ütheti le a saját bábuját.
     * Ellenőrzi, hogy a futó helyesen megakadályozza a saját bábu leütését.
     */
    @Test
    void testCannotCaptureOwnPiece() {
        Board board = new Board();
        Bishop bishop = new Bishop(Piece.Color.WHITE, 4, 4);

        boolean isValid = bishop.isValidMove(7, 7, 6, 6, board);

        assertFalse(isValid);
    }
}
