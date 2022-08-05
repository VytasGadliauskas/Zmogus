package lt.bit.zmones;

import lt.bit.zmones.data.Adresas;
import lt.bit.zmones.data.Kontaktas;
import lt.bit.zmones.data.Zmogus;

import java.util.*;

public class DbOld {
    
    private static final List<Zmogus> list = new ArrayList<>();
    private static final List<Zmogus> listFiltered = new ArrayList<>();

    public static List<Zmogus> getList() {
        return list;
    }

    public static List<Zmogus> getListFiltered(){

        return listFiltered;
    }

    public static List<Zmogus> getListSorted(String sortBy, String order) {
        List<Zmogus> listSorted =  new ArrayList<>();
        for (Zmogus zmogus: getList()) {
            listSorted.add(zmogus);
        }

        if ("asc".equals(order)){
            if (getComparatorAsc(sortBy) != null) {
                Collections.sort(listSorted, getComparatorAsc(sortBy));
            }
            return listSorted;
        } else if ("desc".equals(order)){
            if (getComparatorDesc(sortBy) != null) {
                Collections.sort(listSorted, getComparatorDesc(sortBy));
            }
            return listSorted;
        }
        return listSorted;
    }

    private static Comparator<Zmogus> getComparatorAsc(String sortBy){
        Comparator<Zmogus> comparator = null;
        switch (sortBy){
            case "vardas":
                 comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getVardas().compareTo(o2.getVardas());
                    }
                };
                break;
            case "pavarde":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getPavarde().compareTo(o2.getPavarde());
                    }
                };
                break;
            case "gdata":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getGimimoData().compareTo(o2.getGimimoData());
                    }
                };
                break;
            case "alga":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getAlga().compareTo(o2.getAlga());
                    }
                };
                break;
        }
        return  comparator;
    }

    private static Comparator<Zmogus> getComparatorDesc(String sortBy){
        Comparator<Zmogus> comparator = null;
        switch (sortBy){
            case "vardas":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getVardas().compareTo(o1.getVardas());
                    }
                };
                break;
            case "pavarde":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getPavarde().compareTo(o1.getPavarde());
                    }
                };
                break;
            case "gdata":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getGimimoData().compareTo(o1.getGimimoData());
                    }
                };
                break;
            case "alga":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getAlga().compareTo(o1.getAlga());
                    }
                };
                break;
        }
        return  comparator;
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
        Zmogus zmogus = DbOld.getById(id);
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
        if (zmogus.getKontaktai() == null){
          zmogus.setKontaktai(new ArrayList<Kontaktas>());
        }
        return zmogus.getKontaktai();
    }

    public static void addKontaktas(int zmogausId, Kontaktas k) {
     //    listKontaktai.add(k);
    }

    public static void deleteKontaktas(int zmogausId, Kontaktas k) {
     //   listKontaktai.remove(k);
    }

    public static List<Adresas> getListAdresaibyZmogusId(int id) {
        Zmogus zmogus = getById(id);
        if (zmogus.getAdresai() == null){
            zmogus.setAdresai(new ArrayList<Adresas>());
        }
        return zmogus.getAdresai();
    }

}
