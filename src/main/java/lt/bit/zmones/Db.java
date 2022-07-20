package lt.bit.zmones;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Db {
    
    private static final List<Zmogus> list;

    static {
        /////////////////////////////  Kontaktai

        Kontaktas kontaktas1 = new Kontaktas("telefonas", "00000000001");
        Kontaktas kontaktas2 = new Kontaktas("telefonas", "00000000002");
        Kontaktas kontaktas3 = new Kontaktas("telefonas", "00000000003");
        Kontaktas kontaktas4 = new Kontaktas("adresas", "Adresas 1");
        Kontaktas kontaktas5 = new Kontaktas("adresas", "Adresas 2");
        Kontaktas kontaktas6 = new Kontaktas("adresas", "Adresas 3");
        Kontaktas kontaktas7 = new Kontaktas("emailas", "emailas@emailas1");
        Kontaktas kontaktas8 = new Kontaktas("emailas", "emaials@emailas2");
        Kontaktas kontaktas9 = new Kontaktas("emailas", "emailas@emaials3");

        /////////////////////////////

        list = new ArrayList();
        list.add(new Zmogus("Jonas", "Jonaitis"));
        list.add(new Zmogus("Petras", "Petraitis"));
        list.add(new Zmogus("Antanas", "Antanaitis", null, null,
                new ArrayList<Kontaktas>(Arrays.asList(kontaktas1, kontaktas4, kontaktas7))));
        try {
            list.add(new Zmogus("Tomas", "Aloyzaitis",  new SimpleDateFormat("yyyy-MM-dd").parse("1985-11-21"), null));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            list.add(new Zmogus("Aloyzas", "Tomaitis", new SimpleDateFormat("yyyy-MM-dd").parse("1975-11-21"), new BigDecimal(1000)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        list.add(new Zmogus("Romas", "Romaitis", null, new BigDecimal(1000),
                new ArrayList<Kontaktas>(Arrays.asList(kontaktas2, kontaktas5, kontaktas8))));

    }
    
    public static List<Zmogus> getList() {
        return list;
    }
    
    public static Zmogus getById(int id) {
        for (Zmogus zmogus : list) {
            if (zmogus != null && zmogus.getId() == id) {
                return zmogus;
            }
        }
        return null;
    }

    public static void edit(int id,Zmogus z){
        Zmogus zmogus = Db.getById(id);
        zmogus.setVardas(z.getVardas());
        zmogus.setPavarde(z.getPavarde());
        zmogus.setGimimoData(z.getGimimoData());
        zmogus.setAlga(z.getAlga());
    }

    public static void add(Zmogus z) {
        list.add(z);
    }
    
    public static void delete(Zmogus z) {
        list.remove(z);
    }

    ////////////////////////////////////////  Kontaktai

    public static List<Kontaktas> getListKontaktaibyZmogusId(int id) {
        Zmogus zmogus = getById(id);
        return zmogus.getKontaktai();
    }

    public static void addKontaktas(int zmogausId, Kontaktas k) {
     //    listKontaktai.add(k);
    }

    public static void deleteKontaktas(int zmogausId, Kontaktas k) {

     //   listKontaktai.remove(k);
    }
}
