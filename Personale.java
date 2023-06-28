import java.io.Serializable;

public class Personale extends Persona implements Serializable {
    private String NumBadge;
    private String Mansione;
    private String OrarioIngresso;
    private String OrarioUscita;
    private boolean Status;
    private long TotMinuti;

    public Personale(String nome, String cognome, int eta, String sesso, String numBadge, String mansione, String orarioIngresso, String orarioUscita, boolean status, long totMinuti) {
        super(nome, cognome, eta, sesso);
        this.NumBadge = numBadge;
        this.Mansione = mansione;
        this.OrarioIngresso = orarioIngresso;
        this.OrarioUscita = orarioUscita;
        this.Status = status;
        this.TotMinuti = totMinuti;
    }

    public String getNumBadge() {
        return NumBadge;
    }

    public void setNumBadge(String numBadge) {
        NumBadge = numBadge;
    }

    public String getMansione() {
        return Mansione;
    }

    public void setMansione(String mansione) {
        Mansione = mansione;
    }

    public String getOrarioIngresso() {
        return OrarioIngresso;
    }

    public void setOrarioIngresso(String orarioIngresso) {
        OrarioIngresso = orarioIngresso;
    }

    public String getOrarioUscita() {
        return OrarioUscita;
    }

    public void setOrarioUscita(String orarioUscita) {
        OrarioUscita = orarioUscita;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public long getTotMinuti() {
        return TotMinuti;
    }

    public void setTotMinuti(long totMinuti) {
        TotMinuti = totMinuti;
    }

    @Override
    public String toString() {
        return super.toString() + ", Numero badge= " + NumBadge + ", Mansione= " + Mansione + " , Ingresso= " + OrarioIngresso + " , Uscita= " + OrarioUscita+ " , Minuti totali di lavoro= " + TotMinuti + '\'' ;
    }
}
