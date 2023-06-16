public class Personale extends Persona{
    private int NumBadge;
    private String Mansione;

    public Personale(String nome, String cognome, int eta, String sesso, int numBadge, String mansione) {
        super(nome, cognome, eta, sesso);
        this.NumBadge = numBadge;
        this.Mansione = mansione;
    }

    public int getNumBadge() {
        return NumBadge;
    }

    public void setNumBadge(int numBadge) {
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
        return "Personale{" +
                "NumBadge=" + NumBadge +
                ", Mansione='" + Mansione + '\'' +
                '}';
    }
}