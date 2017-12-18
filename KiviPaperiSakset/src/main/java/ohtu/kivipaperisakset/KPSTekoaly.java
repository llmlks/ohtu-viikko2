package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPSPeli {

    private final InterfaceAI tekoaly;

    public KPSTekoaly(Scanner scanner, InterfaceAI tekoaly) {
        super(scanner);
        this.tekoaly = tekoaly;
    }

    @Override
    protected String ekanSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        return ekanSiirto;
    }

    @Override
    protected String tokanSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(getEkanSiirto());
        return tokanSiirto;
    }
}
