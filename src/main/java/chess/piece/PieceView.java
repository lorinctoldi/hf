package chess.piece;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PieceView extends JPanel {
  private ImageIcon pieceIcon;
  private Piece piece;

  /**
   * A PieceView osztály konstruktora.
   * Inicializálja a nézetet egy adott sakkfigurával, és betölti annak képi
   * megjelenítését.
   * Beállítja az ajánlott méretet és átlátszóvá teszi a panelt.
   *
   * @param piece a sakkfigura, amelyet vizuálisan meg kell jeleníteni
   */
  public PieceView(Piece piece) {
    this.piece = piece;
    loadPieceImage(piece);
    this.setPreferredSize(new Dimension(getWidth(), getHeight()));
    this.setOpaque(false);
  }

  /**
   * Betölti a megadott sakkfigura képi megjelenítését.
   * Ha a figura null, akkor nem tölt be képet.
   *
   * @param piece a sakkfigura, amelyhez a képet be kell tölteni
   */
  private void loadPieceImage(Piece piece) {
    if (piece == null) {
      pieceIcon = null;
      return;
    }
    Piece.Color color = piece.getColor();
    String fileName = color.toString().toLowerCase() + "-" + piece.getType().toString().toLowerCase() + ".png";
    URL imgUrl = getClass().getResource("/images/cburnett/" + fileName);
    if (imgUrl != null) {
      pieceIcon = new ImageIcon(imgUrl);
    } else {
      System.err.println("Image not found: " + fileName);
      pieceIcon = null;
    }
  }

  /**
   * A panel grafikai komponenseinek megrajzolása.
   * Megjeleníti a sakkfigura ikonját, ha az elérhető.
   *
   * @param g a rajzoláshoz használt Graphics objektum
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (pieceIcon != null) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      Image img = pieceIcon.getImage();
      int imgWidth = getWidth();
      int imgHeight = getHeight();
      g2d.drawImage(img, 0, 0, imgWidth, imgHeight, this);
    }
  }

  /**
   * Visszaadja a nézethez tartozó sakkfigurát.
   *
   * @return a sakkfigura, amelyet ez a nézet képvisel
   */
  public Piece getPiece() {
    return piece;
  }
}
