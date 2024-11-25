package chess.menu;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A Menu osztály tesztelésére szolgáló osztály.
 */
class MenuTest {
    /**
     * Teszteli az alapértelmezett konstruktor működését.
     * Az alapértelmezett opcióknak tartalmazniuk kell a "Új játék", "Játék betöltése" és "Kilépés" értékeket.
     */
    @Test
    void testDefaultConstructor() {
        Menu menu = new Menu();
        
        ArrayList<String> expectedOptions = new ArrayList<>();
        expectedOptions.add("Új játék");
        expectedOptions.add("Játék betöltése");
        expectedOptions.add("Kilépés");
        
        assertEquals(expectedOptions, menu.getOptions(), "Az alapértelmezett opcióknak meg kell egyezniük.");
    }

        /**
     * Teszteli az opciók módosításának működését.
     * Ellenőrzi, hogy az opciók listája módosítható-e, és csak az új opció marad-e benne.
     */
    @Test
    void testModifyOptions() {
        Menu menu = new Menu();
        
        menu.getOptions().clear();
        menu.getOptions().add("Beállítások");
        
        assertEquals(1, menu.getOptions().size(), "Az opciók számának 1-nek kell lennie.");
        assertEquals("Beállítások", menu.getOptions().get(0), "Az egyetlen opciónak a 'Beállítások'-nak kell lennie.");
    }
}
