import java.io.Serializable;

public class Visitatore extends Persona implements Serializable {
    private String IDBiglietto;
    private String TipologiaVisita;
    private boolean Validita;

    public Visitatore(String nome, String cognome, int eta, String sesso, String IDBiglietto, String tipologiaVisita, boolean Validita) {
        super(nome, cognome, eta, sesso);
        this.IDBiglietto = IDBiglietto;
        this.TipologiaVisita = tipologiaVisita;
        this.Validita = Validita;

    }
    public String getIDBiglietto() {
        return IDBiglietto;
    }

    public void setIDBiglietto(String IDBiglietto) {
        this.IDBiglietto = IDBiglietto;
    }

    public String getTipologiaVisita() {
        return TipologiaVisita;
    }

    public void setTipologiaVisita(String tipologiaVisita) {
        TipologiaVisita = tipologiaVisita;
    }

    public boolean isValidita() {
        return Validita;
    }

    public void setValidita(boolean validita) {
        Validita = validita;
    }

    @Override
    public String toString() {
        return super.toString() + ", IDBiglietto= " + IDBiglietto  + ", Tipologia di visita= " + TipologiaVisita + '\'';
    }
}
