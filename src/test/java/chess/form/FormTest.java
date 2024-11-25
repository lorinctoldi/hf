package chess.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Form osztály tesztelésére szolgáló osztály.
 */
class FormTest {
    /**
   * Teszteli az alapértelmezett konstruktor működését.
   * Az alapértelmezett név üres string, az alapértelmezett Elo-érték pedig 0 kell legyen.
   */
  @Test
  void testDefaultConstructor() {
    Form form = new Form();
    assertEquals("", form.getName(), "Alapértelmezett név üres string kell legyen.");
    assertEquals(0, form.getElo(), "Alapértelmezett ELO 0 kell legyen");
  }

    /**
   * Teszteli a setName() metódust.
   * Ellenőrzi, hogy a név sikeresen beállítható-e.
   */
  @Test
  void testSetName() {
    Form form = new Form();
    form.setName("Lorenzo Toldio");
    assertEquals("Lorenzo Toldio", form.getName(), "A nevet belehet állítani set-ter metódussal.");
  }

    /**
   * Teszteli a setElo() metódust.
   * Ellenőrzi, hogy az Elo-érték sikeresen beállítható-e.
   */
  @Test
  void testSetElo() {
    Form form = new Form();
    form.setElo(1500);
    assertEquals(1500, form.getElo(), "Az ELO-t belehet állítani set-ter metódussal.");
  }

    /**
   * Teszteli a név és Elo-érték együttes beállítását.
   * Ellenőrzi, hogy mindkét érték sikeresen beállítható-e egyszerre.
   */
  @Test
  void testSetNameAndEloTogether() {
    Form form = new Form();
    form.setName("Lorenzo Toldio");
    form.setElo(2000);
    assertEquals("Lorenzo Toldio", form.getName(), "A nevet belehet állítani set-ter metódussal.");
    assertEquals(2000, form.getElo(), "Az ELO-t belehet állítani set-ter metódussal.");
  }
}
