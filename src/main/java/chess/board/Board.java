package chess.board;

import chess.piece.*;

import java.util.ArrayList;

/**
 * A sakktábla reprezentálása és az állapotok, valamint a logika kezelése.
 */
public class Board {
  private ArrayList<ArrayList<Piece>> pieces;
  private boolean whiteKingMoved;
  private boolean blackKingMoved;
  private boolean whiteRookMoved[] = new boolean[2];
  private boolean blackRookMoved[] = new boolean[2];

  private Piece.Color turn;

  /**
   * Létrehozza az alapértelmezett sakktáblát.
   */
  public Board() {
    initializeBoard();
  }

  /**
   * Másolatot készít a megadott sakktábláról.
   * 
   * @param other a másolandó sakktábla
   */
  public Board(Board other) {
    this.turn = other.turn;

    this.pieces = new ArrayList<>(8);

    for (int row = 0; row < 8; row++) {
      ArrayList<Piece> rowPieces = new ArrayList<>(8);
      for (int col = 0; col < 8; col++) {
        Piece piece = other.getPiece(row, col);
        if (piece != null) {
          rowPieces.add(piece.copy());
        } else {
          rowPieces.add(null);
        }
      }
      this.pieces.add(rowPieces);
    }
  }

  /**
   * Inicializálja a sakktáblát alaphelyzetbe.
   */
  public void initializeBoard() {
    this.turn = Piece.Color.WHITE;

    whiteKingMoved = false;
    blackKingMoved = false;

    for (int i = 0; i < 2; i++) {
      whiteRookMoved[i] = false;
      blackRookMoved[i] = false;
    }

    pieces = new ArrayList<>(8);
    for (int row = 0; row < 8; row++) {
      ArrayList<Piece> rowPieces = new ArrayList<>(8);
      for (int col = 0; col < 8; col++) {
        if (row == 1) {
          rowPieces.add(new Pawn(Piece.Color.BLACK, row, col));
        } else if (row == 6) {
          rowPieces.add(new Pawn(Piece.Color.WHITE, row, col));
        } else {
          rowPieces.add(null);
        }
      }
      pieces.add(rowPieces);
    }

    setupInitialPieces();
  }

  /**
   * Beállítja a kezdeti figurákat a sakktáblán.
   */
  private void setupInitialPieces() {
    pieces.get(0).set(0, new Rook(Piece.Color.BLACK, 0, 0));
    pieces.get(0).set(1, new Knight(Piece.Color.BLACK, 0, 1));
    pieces.get(0).set(2, new Bishop(Piece.Color.BLACK, 0, 2));
    pieces.get(0).set(3, new Queen(Piece.Color.BLACK, 0, 3));
    pieces.get(0).set(4, new King(Piece.Color.BLACK, 0, 4));
    pieces.get(0).set(5, new Bishop(Piece.Color.BLACK, 0, 5));
    pieces.get(0).set(6, new Knight(Piece.Color.BLACK, 0, 6));
    pieces.get(0).set(7, new Rook(Piece.Color.BLACK, 0, 7));
    pieces.get(7).set(0, new Rook(Piece.Color.WHITE, 7, 0));
    pieces.get(7).set(1, new Knight(Piece.Color.WHITE, 7, 1));
    pieces.get(7).set(2, new Bishop(Piece.Color.WHITE, 7, 2));
    pieces.get(7).set(3, new Queen(Piece.Color.WHITE, 7, 3));
    pieces.get(7).set(4, new King(Piece.Color.WHITE, 7, 4));
    pieces.get(7).set(5, new Bishop(Piece.Color.WHITE, 7, 5));
    pieces.get(7).set(6, new Knight(Piece.Color.WHITE, 7, 6));
    pieces.get(7).set(7, new Rook(Piece.Color.WHITE, 7, 7));
  }

  /**
   * Visszaadja a megadott pozícióban lévő figurát.
   * 
   * @param row a sor indexe
   * @param col az oszlop indexe
   * @return a kívánt figura, vagy null, ha nincs figura
   */
  public Piece getPiece(int row, int col) {
    if (row < 0 || row >= 8 || col < 0 || col >= 8) {
      return null;
    }
    return pieces.get(row).get(col);
  }

  /**
   * Beállítja a megadott pozícióba a megadott figurát.
   * 
   * @param row   a sor indexe
   * @param col   az oszlop indexe
   * @param piece a beállítandó figura
   */
  public void setPiece(int row, int col, Piece piece) {
    if (row >= 0 && row < 8 && col >= 0 && col < 8) {
      if (piece != null) {
        if (piece.getType() == Piece.PieceType.KING) {
          if (piece.getColor() == Piece.Color.WHITE) {
            whiteKingMoved = true;
          } else {
            blackKingMoved = true;
          }
        } else if (piece.getType() == Piece.PieceType.ROOK) {
          if (piece.getColor() == Piece.Color.WHITE) {
            if (row == 7 && col == 0)
              whiteRookMoved[0] = true;
            if (row == 7 && col == 7)
              whiteRookMoved[1] = true;
          } else {
            if (row == 0 && col == 0)
              blackRookMoved[0] = true;
            if (row == 0 && col == 7)
              blackRookMoved[1] = true;
          }
        }
      }

      pieces.get(row).set(col, piece);
    }
  }

  /**
   * Visszaadja a jelenlegi játékos színét.
   * 
   * @return a játékos színe
   */
  public Piece.Color getTurn() {
    return this.turn;
  }

  /**
   * Ellenőrzi, hogy a megadott mezőt támadja-e valamilyen ellenséges figura.
   * 
   * @param row   a sor indexe
   * @param col   az oszlop indexe
   * @param color a támadást vizsgáló játékos színe
   * @return true, ha a mezőt támadja valami, egyébként false
   */
  public boolean isSquareUnderAttack(int row, int col, Piece.Color color) {
    for (int originRow = 0; originRow < 8; originRow++) {
      for (int originCol = 0; originCol < 8; originCol++) {
        Piece piece = getPiece(originRow, originCol);
        if (piece == null)
          continue;
        if (piece != null && piece.getColor() != color) {
          if (piece.isValidMove(originCol, originRow, col, row, this)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * Ellenőrzi, hogy a megadott színű király sakkban lesz-e a lépés végrehajtása
   * után.
   * 
   * @param color a vizsgálandó király színe
   * @return true, ha sakkban van, egyébként false
   */
  public boolean willKingBeInCheck(int originCol, int originRow, int targetCol, int targetRow, Piece.Color color) {
    Board copy = new Board(this);
    copy.setPiece(targetRow, targetCol, copy.getPiece(originRow, originCol));
    copy.setPiece(originRow, originCol, null);

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Piece piece = copy.getPiece(row, col);
        if (piece == null)
          continue;
        if (piece.getType() == Piece.PieceType.KING && piece.getColor() == color) {
          return copy.isSquareUnderAttack(row, col, color);
        }
      }
    }

    return false;
  }

  /**
   * Ellenőrzi, hogy a játék mattal végződött-e (mate)
   * 
   * @param color a vizsgálandó szín
   * @return true, ha a vizsgált színű játékos nyert, egyébként false
   */
  public boolean isMate(Piece.Color color) {
    int kingRow = -1;
    int kingCol = -1;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Piece piece = getPiece(row, col);
        if (piece != null && piece.getColor() == color && piece.getType() == Piece.PieceType.KING) {
          kingRow = row;
          kingCol = col;
          break;
        }
      }
      if (kingRow != -1)
        break;
    }

    if (kingRow != -1 && isSquareUnderAttack(kingRow, kingCol, color)) {
      for (int originRow = 0; originRow < 8; originRow++) {
        for (int originCol = 0; originCol < 8; originCol++) {
          Piece piece = getPiece(originRow, originCol);
          if (piece == null || piece.getColor() != color)
            continue;

          for (int targetRow = 0; targetRow < 8; targetRow++) {
            for (int targetCol = 0; targetCol < 8; targetCol++) {
              if (originRow == targetRow && originCol == targetCol
                  || !piece.isValidMove(originCol, originRow, targetCol, targetRow, this))
                continue;
              if (!willKingBeInCheck(originCol, originRow, targetCol, targetRow, color)) {
                return false;
              }
            }
          }
        }
      }
      return true;
    }

    return false;
  }

  /**
   * Ellenőrzi, hogy a játék döntetlen (stalemate) vagy nincs elegendő bábu a táblán
   * egyik játékos számára sem.
   * 
   * @param color a vizsgálandó szín
   * @return true, ha döntetlen van, egyébként false
   */
  public boolean isDraw(Piece.Color color) {
    if (isStalemate(color) || isInsufficientMaterial()) {
      return true;
    }

    return false;
  }

  /**
   * Ellenőrzi, hogy a megadott színű játékos döntetlent (stalemate) helyzetbe került-e.
   * A döntetlen akkor következik be, ha a király nincs sakkban, de nincs elérhető
   * lépés.
   * 
   * @param color a vizsgálandó szín
   * @return true, ha döntetlen, egyébként false
   */
  public boolean isStalemate(Piece.Color color) {
    int kingRow = -1;
    int kingCol = -1;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Piece piece = getPiece(row, col);
        if (piece != null && piece.getColor() == color && piece.getType() == Piece.PieceType.KING) {
          kingRow = row;
          kingCol = col;
          break;
        }
      }
      if (kingRow != -1)
        break;
    }

    if (kingRow != -1 && isSquareUnderAttack(kingRow, kingCol, color)) {
      return false;
    }

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Piece piece = getPiece(row, col);
        if (piece == null || piece.getColor() != color)
          continue;

        for (int r2 = 0; r2 < 8; r2++) {
          for (int c2 = 0; c2 < 8; c2++) {
            if (row == r2 && col == c2 || !piece.isValidMove(col, row, c2, r2, this))
              continue;
            if (!willKingBeInCheck(col, row, c2, r2, color)) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  /**
   * Ellenőrzi, hogy van-e elegendő bábu a játékban, hogy folytatódjon.
   * Ha egyik félnek nincs elég bábuja (például csak királya van), akkor
   * döntetlen.
   * 
   * @return true, ha nincs elegendő bábu, egyébként false
   */
  public boolean isInsufficientMaterial() {
    int whiteMaterial = 0;
    int blackMaterial = 0;

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Piece piece = getPiece(row, col);
        if (piece != null) {
          if (piece.getColor() == Piece.Color.WHITE) {
            whiteMaterial += piece.getValue();
          } else {
            blackMaterial += piece.getValue();
          }
        }
      }
    }

    if ((whiteMaterial <= 1 && blackMaterial <= 1) ||
        (whiteMaterial == 3 && blackMaterial == 1) ||
        (whiteMaterial == 1 && blackMaterial == 3)) {
      return true;
    }

    return false;
  }

  /**
   * Megváltoztatja a következő játékos színét.
   * Ha a fehér játékos következik, akkor a következő lépés a feketéé lesz, és
   * fordítva.
   */
  public void changeTurn() {
    this.turn = (this.turn == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE);
  }

  /**
   * Ellenőrzi, hogy a megadott színű király lépett-e már a játék során.
   * 
   * @param color a vizsgálandó szín
   * @return true, ha a király már lépett, egyébként false
   */
  public boolean hasKingMoved(Piece.Color color) {
    return color == Piece.Color.WHITE ? whiteKingMoved : blackKingMoved;
  }

  /**
   * Ellenőrzi, hogy a megadott színű játékos egyik bástyája lépett e már a játék sorn.
   * 
   * @param color     a vizsgálandó szín
   * @param rightRook a jobb oldalra eső bástya (true - jobb, false - bal)
   * @return true, ha a bástya már lépett, egyébként false
   */
  public boolean hasRookMoved(Piece.Color color, boolean rightRook) {
    if (color == Piece.Color.WHITE) {
      return whiteRookMoved[rightRook ? 1 : 0];
    } else {
      return blackRookMoved[rightRook ? 1 : 0];
    }
  }

  /**
   * Ellenőrzi, hogy a megadott színű játékos tud-e sáncolni a megadott
   * bástyával.
   * 
   * @param color   a vizsgálandó szín
   * @param rookCol a bástya oszlopának indexe
   * @return true, ha a sáncolás lehetséges, egyébként false
   */
  public boolean canCastle(Piece.Color color, int rookCol) {
    if (color == Piece.Color.WHITE) {
      return !whiteKingMoved && !whiteRookMoved[rookCol == 7 ? 1 : 0];
    } else {
      return !blackKingMoved && !blackRookMoved[rookCol == 7 ? 1 : 0];
    }
  }

  /**
   * Ellenőrzi, hogy a megadott lépés érvényes-e a táblán.
   * A lépés csak akkor érvényes, ha az adott figura típusának megfelelő, és nem
   * vezeti a királyt sakkba.
   * 
   * @param originCol a kiinduló oszlop indexe
   * @param originRow a kiinduló sor indexe
   * @param targetCol a cél oszlop indexe
   * @param targetRow a cél sor indexe
   * @return true, ha a lépés érvényes, egyébként false
   */
  public boolean isValidMove(int originCol, int originRow, int targetCol, int targetRow) {
    Piece piece = this.getPiece(originRow, originCol);
    if (piece == null)
      return false;
    if (this.getTurn() != piece.getColor())
      return false;

    if (!piece.isValidMove(originCol, originRow, targetCol, targetRow, this))
      return false;

    if (this.willKingBeInCheck(originCol, originRow, targetCol, targetRow, this.getTurn()))
      return false;

    if (piece.getType() == Piece.PieceType.KING && Math.abs(targetCol - originCol) == 2) {
      int rookCol = (targetCol > originCol) ? 7 : 0;
      if (!this.canCastle(piece.getColor(), rookCol)) {
        return false;
      }

      int step = (targetCol > originCol) ? 1 : -1;
      for (int col = originCol; col != targetCol + step; col += step) {
        if (this.isSquareUnderAttack(originRow, col, piece.getColor())) {
          return false;
        }
      }
      return true;
    }

    return true;
  }

  /**
   * Végrehajtja a megadott lépést a táblán, beleértve a speciális lépéseket, mint
   * a sáncolás.
   * Ha szükséges, előlépteti a gyalogot vezérré.
   * 
   * @param originCol a kiinduló oszlop indexe
   * @param originRow a kiinduló sor indexe
   * @param targetCol a cél oszlop indexe
   * @param targetRow a cél sor indexe
   */
  public void performMove(int originCol, int originRow, int targetCol, int targetRow) {
    Piece piece = this.getPiece(originRow, originCol);
    if(piece == null) return;
    if (piece.getType() == Piece.PieceType.KING && Math.abs(targetCol - originCol) == 2) {
      int rookCol = (targetCol > originCol) ? 7 : 0;
      int rookTargetCol = (targetCol > originCol) ? targetCol - 1 : targetCol + 1;

      Piece rook = this.getPiece(originRow, rookCol);
      this.setPiece(originRow, rookTargetCol, rook);
      this.setPiece(originRow, rookCol, null);
    }

    this.setPiece(targetRow, targetCol, piece);
    this.setPiece(originRow, originCol, null);

    if (piece.getType() == Piece.PieceType.PAWN) {
      if ((piece.getColor() == Piece.Color.WHITE && targetRow == 0) ||
          (piece.getColor() == Piece.Color.BLACK && targetRow == 7)) {
        promotePawn(targetRow, targetCol, piece.getColor());
      }
    }

    this.changeTurn();
  }

  /**
   * Előlépteti a gyalogot vezérré, ha az elérte a tábla végét.
   * 
   * @param row   a gyalog célhelye
   * @param col   a gyalog célhelye
   * @param color a gyalog színe
   */
  private void promotePawn(int row, int col, Piece.Color color) {
    Piece promotedPiece = new Queen(color, row, col);
    this.setPiece(row, col, promotedPiece);
  }
}
