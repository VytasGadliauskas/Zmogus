package lt.bit.zmones;

public class Kontaktas {
    private static int nextId = 1;
    private int id;
    private String tipas;
    private String reiksme;

    public Kontaktas(String tipas, String reiksme) {
        this.id = nextId++;
        this.tipas = tipas;
        this.reiksme = reiksme;
    }

    public int getId() {
        return id;
    }

    public String getTipas() {
        return tipas;
    }

    public void setTipas(String tipas) {
        this.tipas = tipas;
    }

    public String getReiksme() {
        return reiksme;
    }

    public void setReiksme(String reiksme) {
        this.reiksme = reiksme;
    }
}
