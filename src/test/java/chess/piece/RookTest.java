package chess.piece;

import chess.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Rook (bástya) osztály tesztelésére szolgáló osztály.
 * A tesztek célja a bástya bábú mozgásainak és szabályainak ellenőrzése.
 */
class RookTest {
    private Board board;
    private Rook whiteRook;
    private Rook blackRook;

    /**
     * Minden teszt előtt beállítja a tábla és a bábuk állapotát.
     */
    @BeforeEach
    void setUp() {
        board = new Board();
        whiteRook = new Rook(Piece.Color.WHITE, 4, 4);
        blackRook = new Rook(Piece.Color.BLACK, 7, 7);

        board.setPiece(4, 4, whiteRook);
        board.setPiece(7, 7, blackRook);
    }

    /**
     * Teszteli a bástya érvényes vízszintes mozgását.
     */
    @Test
    void testValidHorizontalMove() {
        assertFalse(whiteRook.isValidMove(4, 4, 4, 7, board));
        assertFalse(whiteRook.isValidMove(4, 4, 4, 0, board));
    }

    /**
     * Teszteli a bástya érvényes függőleges mozgását.
     */
    @Test
    void testValidVerticalMove() {
        assertTrue(whiteRook.isValidMove(4, 4, 7, 4, board));
        assertTrue(whiteRook.isValidMove(4, 4, 0, 4, board));
    }

    /**
     * Teszteli, hogy a bástya érvénytelen mozgásokat hajt-e végre.
     */
    @Test
    void testInvalidMove() {
        assertFalse(whiteRook.isValidMove(4, 4, 5, 5, board));
        assertFalse(whiteRook.isValidMove(4, 4, 4, 4, board));
    }

    /**
     * Teszteli, hogy a bástya útja blokkolva van-e vízszintes mozgás közben.
     */
    @Test
    void testBlockedPathHorizontal() {
        board.setPiece(4, 6, new Rook(Piece.Color.WHITE, 4, 6));
        assertFalse(whiteRook.isValidMove(4, 4, 4, 7, board));
    }

    /**
     * Teszteli, hogy a bástya útja blokkolva van-e függőleges mozgás közben.
     */
    @Test
    void testBlockedPathVertical() {
        board.setPiece(6, 4, new Rook(Piece.Color.WHITE, 6, 4));
        assertTrue(whiteRook.isValidMove(4, 4, 7, 4, board));
    }

    /**
     * Teszteli, hogy a bástya képes-e levenni egy ellenfelet.
     */
    @Test
    void testCaptureOpponent() {
        assertFalse(whiteRook.isValidMove(4, 4, 7, 7, board));
    }

    /**
     * Teszteli, hogy a bástya nem tudja-e levenni a saját bábuját.
     */
    @Test
    void testCaptureFriendly() {
        board.setPiece(7, 7, new Rook(Piece.Color.WHITE, 7, 7));
        assertFalse(whiteRook.isValidMove(4, 4, 7, 7, board));
    }

    /**
     * Teszteli a bástya másolatának készítését.
     */
    @Test
    void testCopy() {
        Piece copy = whiteRook.copy();
        assertNotSame(copy, whiteRook);
        assertEquals(copy.getColor(), whiteRook.getColor());
        assertEquals(copy.getType(), whiteRook.getType());
    }

    /**
     * Teszteli a bástya `toString` metódusát.
     */
    @Test
    void testToString() {
        assertEquals("WHITE Rook", whiteRook.toString());
        assertEquals("BLACK Rook", blackRook.toString());
    }
}
