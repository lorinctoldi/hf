package chess.player;

import javax.swing.JPanel;

/**
 * A játékos vezérlésére szolgáló osztály, amely összeköti a Player modellt és a
 * PlayerView nézetet.
 */
public class PlayerController {
  private Player player;
  private PlayerView playerView;

  /**
   * Létrehoz egy PlayerController példányt a megadott név és Elo-érték alapján.
   * 
   * @param name a játékos neve
   * @param elo  a játékos Elo-értéke
   */
  public PlayerController(String name, int elo) {
    player = new Player(name, elo);
    playerView = new PlayerView(player);
  }

  /**
   * Visszaadja a játékoshoz tartozó nézetet.
   * 
   * @return a PlayerView komponens
   */
  public JPanel getView() {
    return playerView;
  }

  /**
   * Visszaadja a játékos nevét.
   * 
   * @return a játékos neve
   */
  public String getName() {
    return player.getName();
  }

  /**
   * Visszaadja a játékos Elo-értékét.
   * 
   * @return a játékos Elo-értéke
   */
  public int getElo() {
    return player.getElo();
  }
}
