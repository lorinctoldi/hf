package chess.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    /**
     * Teszteli a Player konstruktorának működését és a getter metódusokat.
     * Ellenőrzi, hogy a név és az ELO értékek helyesen kerülnek beállításra.
     */
    @Test
    void testConstructorAndGetters() {
        Player player = new Player("Magnus Carlsen", 2847);

        assertEquals("Magnus Carlsen", player.getName());
        assertEquals(2847, player.getElo());
    }

    /**
     * Teszteli, hogy negatív ELO érték esetén a konstruktor megfelelően működik-e.
     * Ellenőrzi, hogy a konstruktor elfogadja a negatív ELO értéket.
     */
    @Test
    void testNegativeElo() {
        Player player = new Player("Fischer", -100);

        assertEquals(-100, player.getElo());
    }

    /**
     * Teszteli, hogy üres név esetén a Player konstruktor megfelelően működik-e.
     * Ellenőrzi, hogy az üres név nem okoz hibát a konstruktorban.
     */
    @Test
    void testEmptyName() {
        Player player = new Player("", 1500);

        assertEquals("", player.getName());
    }
}
