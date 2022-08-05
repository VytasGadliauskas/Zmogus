package lt.bit.zmones;

import lt.bit.zmones.data.Adresas;
import lt.bit.zmones.data.AdresasRepo;

public class Testamas {
    public static void main(String[] args) {
      Adresas adresas = AdresasRepo.getById(3);
      System.out.println(adresas);

    }

}
