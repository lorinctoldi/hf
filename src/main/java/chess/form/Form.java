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
     * Visszaadja a formban tárolt név értékét.
     * 
     * @return a formban tárolt név értéke
     */
    public String getName() {
        return name;
    }

    /**
     * Beállítja a formban tárolt nevet.
     * 
     * @param name a beállítandó név
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a formban tárolt Elo pontot.
     * 
     * @return az Elo pontszám
     */
    public int getElo() {
        return elo;
    }

    /**
     * Beállítja a formban tárolt ELO pontot.
     * 
     * @param elo a beállítandó Elo pontszám
     */
    public void setElo(int elo) {
        this.elo = elo;
    }
}
