package chess.menu;

import java.util.ArrayList;

public class Menu {
  ArrayList<String> options;

  /**
   * Konstruktor, amely inicializálja a menüt a három alapértelmezett
   * lehetőséggel:
   * "Új játék", "Játék betöltése", és "Kilépés".
   */
  public Menu() {
    options = new ArrayList<>();
    options.add("Új játék");
    options.add("Játék betöltése");
    options.add("Kilépés");
  }

  /**
   * Visszaadja a menü lehetőségeit tartalmazó listát.
   * 
   * @return a menü lehetőségeit tartalmazó lista
   */
  public ArrayList<String> getOptions() {
    return options;
  }
}
