import java.io.Serializable;

public class Personale extends Persona implements Serializable {
    private String NumBadge;
    private String Mansione;

    public Personale(String nome, String cognome, int eta, String sesso, String numBadge, String mansione) {
        super(nome, cognome, eta, sesso);
        this.NumBadge = numBadge;
        this.Mansione = mansione;
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



    @Override
    public String toString() {
        return super.toString() + ", Numero badge= " + NumBadge + ", Mansione= " + Mansione + '\'' ;
    }
}
