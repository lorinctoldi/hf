package chess.board;

import chess.piece.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Board osztály tesztelésére szolgáló osztály, amely biztosítja, hogy az
 * összes funkció megfelelően működik.
 */
public class BoardTest {
    private Board board;

    /**
     * Előkészíti a teszteket, minden teszt előtt egy új tábla példányt hoz létre.
     */
    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    /**
     * Teszteli a tábla inicializálását, ellenőrzi, hogy a kezdő bábuk megfelelően
     * kerülnek elhelyezésre.
     */
    @Test
    public void testInitializeBoard() {
        board.initializeBoard();
        assertNotNull(board.getPiece(0, 0));
        assertNotNull(board.getPiece(7, 7));
        assertNull(board.getPiece(4, 4));
    }

    /**
     * Teszteli a másoló konstruktort, hogy az új tábla másolatot készít az
     * eredetiről.
     */
    @Test
    public void testCopyConstructor() {
        Board copiedBoard = new Board(board);
        assertEquals(board.getPiece(0, 0), copiedBoard.getPiece(0, 0));
    }

    /**
     * Teszteli a bábuk elhelyezését a táblán.
     */
    @Test
    public void testSetPiece() {
        Piece piece = new Rook(Piece.Color.WHITE, 0, 0);
        board.setPiece(0, 0, piece);
        assertEquals(piece, board.getPiece(0, 0));
    }

    /**
     * Teszteli a bábú lekérdezését adott koordinátákból.
     */
    @Test
    public void testGetPiece() {
        Piece piece = new Rook(Piece.Color.WHITE, 0, 0);
        board.setPiece(0, 0, piece);
        assertEquals(piece, board.getPiece(0, 0));
        assertNull(board.getPiece(4, 4));
    }

    /**
     * Teszteli a soron következő játékos lekérdezését.
     */
    @Test
    public void testGetTurn() {
        assertEquals(Piece.Color.WHITE, board.getTurn());
    }

    /**
     * Teszteli, hogy egy adott mező támadás alatt áll-e.
     */
    @Test
    public void testIsSquareUnderAttack() {
        assertTrue(board.isSquareUnderAttack(5, 5, Piece.Color.BLACK));
        assertTrue(board.isSquareUnderAttack(2, 2, Piece.Color.WHITE));

        assertFalse(board.isSquareUnderAttack(5, 5, Piece.Color.WHITE));
        assertFalse(board.isSquareUnderAttack(2, 2, Piece.Color.BLACK));
    }

    /**
     * Teszteli, hogy a király nem kerülne-e sakkba egy adott lépés után.
     */
    @Test
    public void testWillKingBeInCheck() {
        board.initializeBoard();
        assertFalse(board.willKingBeInCheck(4, 4, 5, 5, Piece.Color.WHITE));
        assertTrue(board.willKingBeInCheck(4, 7, 3, 2, Piece.Color.WHITE));
    }

    /**
     * Teszteli, hogy a játékos mate helyzetben van-e.
     */
    @Test
    public void testIsMate() {
        assertFalse(board.isMate(Piece.Color.BLACK));

        board.setPiece(5, 0, board.getPiece(0, 4));
        board.setPiece(0, 4, null);
        board.setPiece(5, 1, new Queen(Piece.Color.WHITE, 5, 1));
        board.setPiece(6, 1, new Queen(Piece.Color.WHITE, 6, 1));
        assertTrue(board.isMate(Piece.Color.BLACK));
    }

    /**
     * Teszteli, hogy a játék döntetlen helyzetben van-e.
     */
    @Test
    public void testIsDraw() {
        assertFalse(board.isDraw(Piece.Color.WHITE));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.setPiece(row, col, null);
            }
        }
        board.setPiece(5, 1, new King(Piece.Color.WHITE, 0, 0));
        board.setPiece(6, 1, new King(Piece.Color.WHITE, 7, 7));
        assertTrue(board.isDraw(Piece.Color.WHITE));
    }

    /**
     * Teszteli, hogy a játék döntetlen helyzetben van-e.
     */
    @Test
    public void testIsStalemate() {
        assertFalse(board.isStalemate(Piece.Color.WHITE));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.setPiece(row, col, null);
            }
        }
        board.setPiece(0, 0, new King(Piece.Color.WHITE, 0, 0));
        board.setPiece(2, 1, new Queen(Piece.Color.BLACK, 2, 1));
        board.setPiece(1, 2, new Queen(Piece.Color.BLACK, 1, 2));
        board.setPiece(7, 7, new King(Piece.Color.BLACK, 7, 7));
        assertTrue(board.isStalemate(Piece.Color.WHITE));
    }

    /**
     * Teszteli, hogy a játékban van-e elég bábu.
     */
    @Test
    public void testIsInsufficientMaterial() {
        assertFalse(board.isInsufficientMaterial());

        assertFalse(board.isStalemate(Piece.Color.WHITE));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.setPiece(row, col, null);
            }
        }
        board.setPiece(0, 0, new King(Piece.Color.WHITE, 0, 0));
        board.setPiece(7, 7, new King(Piece.Color.BLACK, 7, 7));
        assertTrue(board.isInsufficientMaterial());
    }

    /**
     * Teszteli a kör váltását.
     */
    @Test
    public void testChangeTurn() {
        assertEquals(Piece.Color.WHITE, board.getTurn());
        board.changeTurn();
        assertEquals(Piece.Color.BLACK, board.getTurn());
    }

    /**
     * Teszteli, hogy a király már mozgott-e.
     */
    @Test
    public void testHasKingMoved() {
        assertFalse(board.hasKingMoved(Piece.Color.WHITE));
        board.setPiece(7, 4, new King(Piece.Color.WHITE, 7, 4));
        assertTrue(board.hasKingMoved(Piece.Color.WHITE));
    }

    /**
     * Teszteli, hogy a bástya már mozgott-e.
     */
    @Test
    public void testHasRookMoved() {
        assertFalse(board.hasRookMoved(Piece.Color.WHITE, false));
        board.setPiece(7, 0, new Rook(Piece.Color.WHITE, 7, 0));
        assertTrue(board.hasRookMoved(Piece.Color.WHITE, false));
    }

    /**
     * Teszteli, hogy a játékos sáncolt-e.
     */
    @Test
    public void testCanCastle() {
        assertTrue(board.canCastle(Piece.Color.WHITE, 0));
        assertTrue(board.canCastle(Piece.Color.WHITE, 7));
    }

}
