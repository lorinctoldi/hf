package chess.piece;

import chess.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Queen (királynő) osztály tesztelésére szolgáló osztály.
 * A tesztek célja a királynő bábú mozgásainak és szabályainak ellenőrzése.
 */
class QueenTest {
    private Board board;
    private Queen whiteQueen;
    private Queen blackQueen;

    /**
     * Minden teszt előtt beállítja a tábla és a bábuk állapotát.
     */
    @BeforeEach
    void setUp() {
        board = new Board();
        whiteQueen = new Queen(Piece.Color.WHITE, 4, 4);
        blackQueen = new Queen(Piece.Color.BLACK, 7, 7);

        board.setPiece(4, 4, whiteQueen);
        board.setPiece(7, 7, blackQueen);
    }

    /**
     * Teszteli a királynő érvényes egyenes mozgását.
     */
    @Test
    void testValidStraightMove() {
        assertFalse(whiteQueen.isValidMove(4, 4, 4, 7, board));
        assertTrue(whiteQueen.isValidMove(4, 4, 7, 4, board));
    }

    /**
     * Teszteli a királynő érvényes átlós mozgását.
     */
    @Test
    void testValidDiagonalMove() {
        assertFalse(whiteQueen.isValidMove(4, 4, 7, 7, board));
        assertTrue(whiteQueen.isValidMove(4, 4, 1, 1, board));
    }

    /**
     * Teszteli, hogy a királynő érvénytelen mozgásokat hajt-e végre.
     */
    @Test
    void testInvalidMove() {
        assertFalse(whiteQueen.isValidMove(4, 4, 5, 6, board));
        assertFalse(whiteQueen.isValidMove(4, 4, 4, 4, board));
    }

    /**
     * Teszteli, hogy a királynő útja blokkolva van-e egyenes mozgás közben.
     */
    @Test
    void testBlockedPathStraight() {
        board.setPiece(4, 6, new Queen(Piece.Color.WHITE, 4, 6));
        assertFalse(whiteQueen.isValidMove(4, 4, 4, 7, board));
    }

    /**
     * Teszteli, hogy a királynő útja blokkolva van-e átlós mozgás közben.
     */
    @Test
    void testBlockedPathDiagonal() {
        board.setPiece(5, 5, new Queen(Piece.Color.WHITE, 5, 5));
        assertFalse(whiteQueen.isValidMove(4, 4, 7, 7, board));
    }

    /**
     * Teszteli, hogy a királynő képes-e levenni egy ellenfelet.
     */
    @Test
    void testCaptureOpponent() {
        assertFalse(whiteQueen.isValidMove(4, 4, 7, 7, board));
    }

    /**
     * Teszteli, hogy a királynő nem tudja-e levenni a saját bábuját.
     */
    @Test
    void testCaptureFriendly() {
        board.setPiece(7, 7, new Queen(Piece.Color.WHITE, 7, 7));
        assertFalse(whiteQueen.isValidMove(4, 4, 7, 7, board));
    }

    /**
     * Teszteli a királynő másolatának készítését.
     */
    @Test
    void testCopy() {
        Piece copy = whiteQueen.copy();
        assertNotSame(copy, whiteQueen);
        assertEquals(copy.getColor(), whiteQueen.getColor());
        assertEquals(copy.getType(), whiteQueen.getType());
    }

    /**
     * Teszteli a királynő `toString` metódusát.
     */
    @Test
    void testToString() {
        assertEquals("WHITE Queen", whiteQueen.toString());
        assertEquals("BLACK Queen", blackQueen.toString());
    }
}
