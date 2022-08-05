package lt.bit.zmones.data;

public class Kontaktas {
    private int id;
    private int zmogaus_id;
    private String tipas;
    private String reiksme;

    public Kontaktas(int zmogaus_id, String tipas, String reiksme) {
        this.zmogaus_id = zmogaus_id;
        this.tipas = tipas;
        this.reiksme = reiksme;
    }

    public Kontaktas(int id, int zmogaus_id, String tipas, String reiksme) {
        this.id = id;
        this.zmogaus_id = zmogaus_id;
        this.tipas = tipas;
        this.reiksme = reiksme;
    }

    public int getId() {
        return id;
    }

    public int getZmogaus_id() {
        return zmogaus_id;
    }

    public void setZmogaus_id(int zmogaus_id) {
        this.zmogaus_id = zmogaus_id;
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
