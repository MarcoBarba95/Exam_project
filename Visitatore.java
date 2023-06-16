public class Visitatore extends Persona{
    private String NumDoc;
    private String IDBiglietto;
    private String TipologiaVisita;

    public Visitatore(String nome, String cognome, int eta, String sesso, String numDoc, String IDBiglietto, String tipologiaVisita) {
        super(nome, cognome, eta, sesso);
        this.NumDoc = numDoc;
        this.IDBiglietto = IDBiglietto;
        this.TipologiaVisita = tipologiaVisita;

    }

    public String getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(String numDoc) {
        NumDoc = numDoc;
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
        return "Visitatore{" +
                "NumDoc='" + NumDoc + '\'' +
                ", IDBiglietto='" + IDBiglietto + '\'' +
                ", TipologiaVisita='" + TipologiaVisita + '\'' +
                '}';
    }
}
