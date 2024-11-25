package chess.move;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A Move osztály tesztelésére szolgáló osztály.
 */
class MoveTest {
    /**
     * Teszteli a konstruktor és a getter metódusok működését.
     * Ellenőrzi, hogy a konstruktor által beállított értékek helyesek-e.
     */
    @Test
    void testConstructorAndGetters() {
        Move move = new Move(0, 1, 2, 3);

        assertEquals(0, move.getOriginCol(), "Az originCol értékének 0-nak kell lennie.");
        assertEquals(1, move.getOriginRow(), "Az originRow értékének 1-nek kell lennie.");
        assertEquals(2, move.getTargetCol(), "A targetCol értékének 2-nek kell lennie.");
        assertEquals(3, move.getTargetRow(), "A targetRow értékének 3-nak kell lennie.");
    }

    /**
     * Teszteli a toString metódus működését.
     * Ellenőrzi, hogy a megfelelő lépés formátumban kerül-e kiírásra.
     */
    @Test
    void testToString() {
        Move move = new Move(0, 1, 2, 3);

        assertEquals("a2->c4", move.toString(), "A toString eredményének 'a2->c4'-nek kell lennie.");
    }

    /**
     * Teszteli a toString metódus működését más koordinátákkal.
     * Ellenőrzi, hogy a helyes sakkáblázati jelölést kapjuk-e.
     */
    @Test
    void testConvertColToChessNotation() {
        Move move = new Move(0, 0, 7, 7);

        assertEquals("a1->h8", move.toString(), "A toString eredményének 'a1->h8'-nek kell lennie.");
    }
}
