import java.io.Serializable;

public class Personale extends Persona implements Serializable {
    private String NumBadge;
    private String Mansione;
    private String OrarioIngresso;
    private String OrarioUscita;

    public Personale(String nome, String cognome, int eta, String sesso, String numBadge, String mansione, String orarioIngresso, String orarioUscita) {
        super(nome, cognome, eta, sesso);
        this.NumBadge = numBadge;
        this.Mansione = mansione;
        this.OrarioIngresso = orarioIngresso;
        this.OrarioUscita = orarioUscita;
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

    @Override
    public String toString() {
        return super.toString() + ", Numero badge= " + NumBadge + ", Mansione= " + Mansione + '\'' ;
    }
}
