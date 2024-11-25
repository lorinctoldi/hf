package board;

import piece.*;

import java.util.ArrayList;

public class Board {
  private ArrayList<ArrayList<Piece>> pieces;
  private boolean whiteKingMoved;
  private boolean blackKingMoved;
  private boolean whiteRookMoved[] = new boolean[2];
  private boolean blackRookMoved[] = new boolean[2];

  private Piece.Color turn;

  public Board() {
    initializeBoard();
  }

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

  public void initializeBoard() {
    this.turn = Piece.Color.WHITE;

    whiteKingMoved = false;
    blackKingMoved = false;

    for(int i = 0; i < 2; i++) {
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

  public Piece getPiece(int row, int col) {
    if (row < 0 || row >= 8 || col < 0 || col >= 8) {
      return null;
    }
    return pieces.get(row).get(col);
  }

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

  public Piece.Color getTurn() {
    return this.turn;
  }

  public boolean isSquareUnderAttack(int row, int col, Piece.Color color) {
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = getPiece(r, c);
        if (piece == null)
          continue;
        if (piece != null && piece.getColor() != color) {
          if (piece.isValidMove(c, r, col, row, this)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public boolean willKingBeInCheck(int originCol, int originRow, int targetCol, int targetRow, Piece.Color color) {
    Board copy = new Board(this);
    copy.setPiece(targetRow, targetCol, copy.getPiece(originRow, originCol));
    copy.setPiece(originRow, originCol, null);

    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = copy.getPiece(r, c);
        if (piece == null)
          continue;
        if (piece.getType() == Piece.PieceType.KING && piece.getColor() == color) {
          return copy.isSquareUnderAttack(r, c, color);
        }
      }
    }

    return false;
  }

  public boolean isMate(Piece.Color color) {
    int kingRow = -1;
    int kingCol = -1;
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = getPiece(r, c);
        if (piece != null && piece.getColor() == color && piece.getType() == Piece.PieceType.KING) {
          kingRow = r;
          kingCol = c;
          break;
        }
      }
      if (kingRow != -1)
        break;
    }

    if (kingRow != -1 && isSquareUnderAttack(kingRow, kingCol, color)) {
      for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
          Piece piece = getPiece(r, c);
          if (piece == null || piece.getColor() != color)
            continue;

          for (int r2 = 0; r2 < 8; r2++) {
            for (int c2 = 0; c2 < 8; c2++) {
              if (r == r2 && c == c2 || !piece.isValidMove(c, r, c2, r2, this))
                continue;
              if (!willKingBeInCheck(c, r, c2, r2, color)) {
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

  public boolean isDraw(Piece.Color color) {
    if (isStalemate(color) || isInsufficientMaterial(color)) {
      return true;
    }

    return false;
  }

  public boolean isStalemate(Piece.Color color) {
    int kingRow = -1;
    int kingCol = -1;
    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = getPiece(r, c);
        if (piece != null && piece.getColor() == color && piece.getType() == Piece.PieceType.KING) {
          kingRow = r;
          kingCol = c;
          break;
        }
      }
      if (kingRow != -1)
        break;
    }

    if (kingRow != -1 && isSquareUnderAttack(kingRow, kingCol, color)) {
      return false;
    }

    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = getPiece(r, c);
        if (piece == null || piece.getColor() != color)
          continue;

        for (int r2 = 0; r2 < 8; r2++) {
          for (int c2 = 0; c2 < 8; c2++) {
            if (r == r2 && c == c2 || !piece.isValidMove(c, r, c2, r2, this))
              continue;
            if (!willKingBeInCheck(c, r, c2, r2, color)) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  public boolean isInsufficientMaterial(Piece.Color color) {
    int whiteMaterial = 0;
    int blackMaterial = 0;

    for (int r = 0; r < 8; r++) {
      for (int c = 0; c < 8; c++) {
        Piece piece = getPiece(r, c);
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

  public void changeTurn() {
    this.turn = (this.turn == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE);
  }

  public boolean hasKingMoved(Piece.Color color) {
    return color == Piece.Color.WHITE ? whiteKingMoved : blackKingMoved;
  }

  public boolean hasRookMoved(Piece.Color color, boolean rightRook) {
    if (color == Piece.Color.WHITE) {
      return whiteRookMoved[rightRook ? 1 : 0];
    } else {
      return blackRookMoved[rightRook ? 1 : 0];
    }
  }

  public boolean canCastle(Piece.Color color, int rookCol) {
    if (color == Piece.Color.WHITE) {
      return !whiteKingMoved && !whiteRookMoved[rookCol == 7 ? 1 : 0];
    } else {
      return !blackKingMoved && !blackRookMoved[rookCol == 7 ? 1 : 0];
    }
  }
}
