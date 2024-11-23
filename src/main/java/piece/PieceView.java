package piece;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PieceView extends JPanel {
  private ImageIcon pieceIcon;
  private Piece piece;

  public PieceView(Piece piece) {
    this.piece = piece;
    loadPieceImage(piece);
    this.setPreferredSize(new Dimension(getWidth(), getHeight()));
    this.setOpaque(false);
  }

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

  public Piece getPiece() {
    return piece;
  }
}
