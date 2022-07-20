package lt.bit.zmones;

import java.util.List;

public class Testamas {
    public static void main(String[] args) {
       List<Zmogus> zmones =  Db.getList();
        for (int i = 0; i < zmones.size(); i++) {
            System.out.println(" "+zmones.get(i).getId()+" ");
            if(zmones.get(i).getKontaktai() != null){
                for (Kontaktas in:  zmones.get(i).getKontaktai()) {
                    System.out.print(" "+in+" ");
                }
            }
        }
    }
}
