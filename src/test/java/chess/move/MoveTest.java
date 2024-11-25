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

        assertEquals(0, move.getOriginCol());
        assertEquals(1, move.getOriginRow());
        assertEquals(2, move.getTargetCol());
        assertEquals(3, move.getTargetRow());
    }

    /**
     * Teszteli a toString metódus működését.
     * Ellenőrzi, hogy a megfelelő lépés formátumban kerül-e kiírásra.
     */
    @Test
    void testToString() {
        Move move = new Move(0, 1, 2, 3);

        assertEquals("a2->c4", move.toString());
    }

    /**
     * Teszteli a toString metódus működését más koordinátákkal.
     * Ellenőrzi, hogy a helyes sakkáblázati jelölést kapjuk-e.
     */
    @Test
    void testConvertColToChessNotation() {
        Move move = new Move(0, 0, 7, 7);

        assertEquals("a1->h8", move.toString());
    }
}
