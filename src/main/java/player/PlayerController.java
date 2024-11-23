package player;

import javax.swing.JPanel;

public class PlayerController {
  private Player player;
  private PlayerView playerView;

  public PlayerController(String name, int elo) {
    player = new Player(name, elo);
    playerView = new PlayerView(player);
  }

  public JPanel getView() {
    return playerView;
  }

  public String getName() {
    return player.getName();
  }

  public int getElo() {
    return player.getElo();
  }
}
