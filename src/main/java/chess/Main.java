package chess;
import chess.appcontroller.AppController;

/**
 * A program belépési pontja, amely elindítja az alkalmazást.
 */
public class Main {
    /**
   * A program fő metódusa, amely létrehozza az AppController példányt és elindítja az alkalmazást.
   * 
   * @param args a parancssori argumentumok
   */
  public static void main(String[] args) {
    AppController appController = new AppController();
    appController.start();
  }
}
