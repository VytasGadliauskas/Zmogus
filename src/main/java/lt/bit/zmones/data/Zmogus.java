package lt.bit.zmones.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zmogus {

    private int id;
    private String vardas;
    private String pavarde;
    private Date gimimoData;
    private BigDecimal alga;

    private List<Kontaktas> kontaktai = new ArrayList<>();
    private List<Adresas> adresai = new ArrayList<>();

    public Zmogus(int id ,String vardas, String pavarde) {
        this.id = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
    }

    public Zmogus(int id ,String vardas, String pavarde, Date gimimoData, BigDecimal alga) {
        this.id = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.gimimoData = gimimoData;
        this.alga = alga;
    }

    public Zmogus(String vardas, String pavarde) {
        this.vardas = vardas;
        this.pavarde = pavarde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public Date getGimimoData() {
        return gimimoData;
    }

    public void setGimimoData(Date gimimoData) {
        this.gimimoData = gimimoData;
    }

    public BigDecimal getAlga() {
        return alga;
    }

    public void setAlga(BigDecimal alga) {
        this.alga = alga;
    }

    public List<Kontaktas> getKontaktai() {
        return kontaktai;
    }


    public void setKontaktai(List<Kontaktas> kontaktai) {
        this.kontaktai = kontaktai;
    }

    public List<Adresas> getAdresai() {
        return adresai;
    }

    public void setAdresai(List<Adresas> adresai) {
        this.adresai = adresai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zmogus other = (Zmogus) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Zmogus{" +
                "id=" + id +
                ", vardas='" + vardas + '\'' +
                ", pavarde='" + pavarde + '\'' +
                ", gimimoData=" + gimimoData +
                ", alga=" + alga +
                ", kontaktai=" + kontaktai +
                ", adresai=" + adresai +
                '}';
    }
}
