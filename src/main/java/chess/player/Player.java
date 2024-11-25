package chess.player;

/**
 * A sakkozót reprezentáló osztály.
 * Tárolja a játékos nevét és Elo-értékét.
 */
public class Player {
  private String name;
  private int elo;

  /**
   * A Player osztály konstruktora.
   * Inicializálja a játékos nevét és Elo-értékét.
   *
   * @param name a játékos neve
   * @param elo  a játékos Elo-értéke
   */
  public Player(String name, int elo) {
    this.name = name;
    this.elo = elo;
  }

  /**
   * Visszaadja a játékos nevét.
   *
   * @return a játékos neve
   */
  public String getName() {
    return this.name;
  }

  /**
   * Visszaadja a játékos Elo-értékét.
   *
   * @return a játékos Elo-értéke
   */
  public int getElo() {
    return this.elo;
  }
}
