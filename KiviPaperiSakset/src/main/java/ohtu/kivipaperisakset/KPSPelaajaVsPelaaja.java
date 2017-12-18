package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSPeli {

    public KPSPelaajaVsPelaaja(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected String ekanSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        return ekanSiirto;
    }

    @Override
    protected String tokanSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }
}
