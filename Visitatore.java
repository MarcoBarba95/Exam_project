import java.io.Serializable;

public class Visitatore extends Persona implements Serializable {
    private String IDBiglietto;
    private String TipologiaVisita;

    public Visitatore(String nome, String cognome, int eta, String sesso, String IDBiglietto, String tipologiaVisita) {
        super(nome, cognome, eta, sesso);
        this.IDBiglietto = IDBiglietto;
        this.TipologiaVisita = tipologiaVisita;

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

    @Override
    public String toString() {
        return super.toString() + ", IDBiglietto= " + IDBiglietto  + ", Tipologia di visita= " + TipologiaVisita + '\'';
    }
}
