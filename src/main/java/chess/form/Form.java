package chess.form;

public class Form {
    private String name;
    private int elo;

    /**
     * Konstruktor, amely alapértelmezetten üres nevet és 0 Elo pontot állít be.
     */
    public Form() {
        this.name = "";
        this.elo = 0;
    }

    /**
     * Visszaadja a form neve értékét.
     * 
     * @return a form neve
     */
    public String getName() {
        return name;
    }

    /**
     * Beállítja a form nevét.
     * 
     * @param name a beállítandó név
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a form Elo pontját.
     * 
     * @return az Elo pontszám
     */
    public int getElo() {
        return elo;
    }

    /**
     * Beállítja a form Elo pontját.
     * 
     * @param elo a beállítandó Elo pontszám
     */
    public void setElo(int elo) {
        this.elo = elo;
    }
}
