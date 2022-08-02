package lt.bit.zmones;

public class Adresas {

    private int id;
    private int zmogaus_id;
    private String adresas;
    private String miestas;
    private String pastokodas;
    private String valstybe;

    public Adresas(int id, int zmogaus_id, String adresas, String miestas, String pastoKodas, String valstybe) {
        this.id = id;
        this.zmogaus_id = zmogaus_id;
        this.adresas = adresas;
        this.miestas = miestas;
        this.pastokodas = pastoKodas;
        this.valstybe = valstybe;
    }

    public Adresas(int zmogaus_id, String adresas, String miestas, String pastoKodas, String valstybe) {
        this.zmogaus_id = zmogaus_id;
        this.adresas = adresas;
        this.miestas = miestas;
        this.pastokodas = pastoKodas;
        this.valstybe = valstybe;
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

    public String getAdresas() {
        return adresas;
    }

    public void setAdresas(String adresas) {
        this.adresas = adresas;
    }

    public String getMiestas() {
        return miestas;
    }

    public void setMiestas(String miestas) {
        this.miestas = miestas;
    }

    public String getPastoKodas() {
        return pastokodas;
    }

    public void setPastoKodas(String pastoKodas) {
        this.pastokodas = pastoKodas;
    }

    public String getValstybe() {
        return valstybe;
    }

    public void setValstybe(String valstybe) {
        this.valstybe = valstybe;
    }
}
