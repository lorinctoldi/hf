package chess.piece;

import chess.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Knight osztály tesztelésére szolgáló osztály.
 * A tesztek a ló mozgásának érvényességét és a bábuk elfogásának szabályait
 * ellenőrzik.
 */
class KnightTest {
    private Board board;
    private Knight whiteKnight;
    private Knight blackKnight;

    /**
     * A tesztelés előtti inicializálás.
     * Létrehozza a tábla objektumot és két lovat, fehér és fekete színben.
     */
    @BeforeEach
    void setUp() {
        board = new Board();
        whiteKnight = new Knight(Piece.Color.WHITE, 4, 4);
        blackKnight = new Knight(Piece.Color.BLACK, 3, 3);
        board.setPiece(4, 4, whiteKnight);
        board.setPiece(3, 3, blackKnight);
    }

    /**
     * Teszteli, hogy a ló képes-e végrehajtani az érvényes mozgásokat.
     * Ellenőrzi a ló összes lehetséges helyes lépését a táblán.
     */
    @Test
    void testValidMoves() {
        assertTrue(whiteKnight.isValidMove(4, 4, 6, 5, board));
        assertTrue(whiteKnight.isValidMove(4, 4, 6, 3, board));
        assertFalse(whiteKnight.isValidMove(4, 4, 5, 6, board));
        assertTrue(whiteKnight.isValidMove(4, 4, 5, 2, board));
        assertFalse(whiteKnight.isValidMove(4, 4, 3, 6, board));
        assertTrue(whiteKnight.isValidMove(4, 4, 3, 2, board));
        assertTrue(whiteKnight.isValidMove(4, 4, 2, 5, board));
        assertTrue(whiteKnight.isValidMove(4, 4, 2, 3, board));
    }

    /**
     * Teszteli, hogy a ló nem hajthat végre érvénytelen mozgásokat.
     * Ellenőrzi, hogy a ló helytelen lépéseket próbál meg végrehajtani.
     */
    @Test
    void testInvalidMoves() {
        assertFalse(whiteKnight.isValidMove(4, 4, 4, 5, board));
        assertFalse(whiteKnight.isValidMove(4, 4, 5, 5, board));
        assertFalse(whiteKnight.isValidMove(4, 4, 6, 4, board));
        assertFalse(whiteKnight.isValidMove(4, 4, 4, 4, board));
    }

    /**
     * Teszteli, hogy a ló képes elkapni egy ellenfél bábút.
     * Ellenőrzi, hogy a ló képes-e levenni egy ellenséges gyalogot a helyes
     * lépéssel.
     */
    @Test
    void testCaptureEnemyPiece() {
        Pawn blackPawn = new Pawn(Piece.Color.BLACK, 6, 5);
        board.setPiece(6, 5, blackPawn);

        assertTrue(whiteKnight.isValidMove(4, 4, 6, 5, board));
    }

    /**
     * Teszteli, hogy a ló nem ütheti le a saját bábuját.
     * Ellenőrzi, hogy a ló megakadályozza a saját bábu leütését.
     */
    @Test
    void testCannotCaptureOwnPiece() {
        Pawn whitePawn = new Pawn(Piece.Color.WHITE, 6, 5);
        board.setPiece(6, 5, whitePawn);

        assertFalse(whiteKnight.isValidMove(4, 4, 5, 6, board));
    }

    /**
     * Teszteli a ló mozgását szélső helyzetekben.
     * Ellenőrzi, hogy a ló érvényes lépéseket hajt végre a tábla szélein, és nem
     * próbál lépni a táblán kívülre.
     */
    @Test
    void testBoundaryConditions() {
        Knight edgeKnight = new Knight(Piece.Color.WHITE, 0, 0);
        board.setPiece(0, 0, edgeKnight);

        assertTrue(edgeKnight.isValidMove(0, 0, 2, 1, board));
        assertTrue(edgeKnight.isValidMove(0, 0, 1, 2, board));
        assertFalse(edgeKnight.isValidMove(0, 0, -1, -1, board));
    }
}
