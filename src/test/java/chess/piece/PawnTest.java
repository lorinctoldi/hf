package chess.piece;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.board.Board;

/**
 * A Pawn (gyalog) osztály tesztelésére szolgáló osztály.
 * A tesztek célja a gyalog különböző mozgásainak és szabályainak ellenőrzése.
 */
class PawnTest {
    /**
     * Teszteli a gyalog alap mozgásait.
     * Ellenőrzi, hogy a gyalog helyesen lép előre egy mezőt és hogy nem léphet a
     * táblán kívülre.
     */
    @Test
    void testPawnBasicMoves() {
        Board board = new Board();
        Piece pawn = board.getPiece(6, 0);
        assertTrue(pawn.isValidMove(0, 6, 0, 5, board));

        pawn = board.getPiece(1, 0);
        assertTrue(pawn.isValidMove(0, 1, 0, 2, board));

        assertFalse(pawn.isValidMove(0, 6, 0, 7, board));
    }

    /**
     * Teszteli a gyalog kettős lépését.
     * Ellenőrzi, hogy a gyalog képes két mezőt lépni a kezdő pozícióból.
     */
    @Test
    void testPawnDoubleMove() {
        Board board = new Board();
        Piece pawn = board.getPiece(6, 0);
        assertTrue(pawn.isValidMove(0, 6, 0, 4, board));

        pawn = board.getPiece(1, 0);
        assertTrue(pawn.isValidMove(0, 1, 0, 3, board));

        board.setPiece(5, 0, new Pawn(Piece.Color.WHITE, 5, 0));
        assertFalse(pawn.isValidMove(0, 6, 0, 4, board));
    }

    /**
     * Teszteli, hogy a gyalog képes levenni egy ellenfél bábút.
     * Ellenőrzi, hogy a gyalog helyesen végrehajtja az elfogást átlós mozgás során.
     */
    @Test
    void testPawnCapture() {
        Board board = new Board();
        board.setPiece(5, 1, new Pawn(Piece.Color.BLACK, 5, 1));

        Piece whitePawn = board.getPiece(6, 0);
        assertTrue(whitePawn.isValidMove(0, 6, 1, 5, board));

        board.setPiece(5, 1, new Pawn(Piece.Color.WHITE, 5, 1));
        assertFalse(whitePawn.isValidMove(0, 6, 1, 5, board));
    }

    /**
     * Teszteli, hogy a gyalog nem tud előre lépni, ha akadályozva van.
     * Ellenőrzi, hogy a gyalog nem mozoghat, ha az útja blokkolva van.
     */
    @Test
    void testPawnBlockedMove() {
        Board board = new Board();
        board.setPiece(5, 0, new Pawn(Piece.Color.WHITE, 5, 0));

        Piece whitePawn = board.getPiece(6, 0);
        assertFalse(whitePawn.isValidMove(0, 6, 0, 5, board));
    }

    /**
     * Teszteli a gyalog érvénytelen mozgásait.
     * Ellenőrzi, hogy a gyalog nem hajthat végre érvénytelen lépéseket.
     */
    @Test
    void testPawnInvalidMoves() {
        Board board = new Board();
        Piece whitePawn = board.getPiece(6, 0);

        assertFalse(whitePawn.isValidMove(0, 6, 1, 6, board));

        assertFalse(whitePawn.isValidMove(0, 6, 0, 7, board));

        board.setPiece(5, 0, new Pawn(Piece.Color.WHITE, 5, 0));
        assertFalse(whitePawn.isValidMove(0, 6, 0, 4, board));
    }

    /**
     * Teszteli a gyalog előléptetését (promócióját).
     * Ellenőrzi, hogy a gyalog átlépésekor megfelelő bábuvá lép elő.
     */
    @Test
    void testPawnPromotion() {
        Board board = new Board();
        Piece whitePawn = new Pawn(Piece.Color.WHITE, 1, 0);
        board.setPiece(1, 0, whitePawn);

        board.performMove(0, 1, 0, 0);

        Piece promotedPiece = board.getPiece(0, 0);
        assertNotNull(promotedPiece);
        assertEquals(Piece.PieceType.QUEEN, promotedPiece.getType());
        assertEquals(Piece.Color.WHITE, promotedPiece.getColor());
    }
}
