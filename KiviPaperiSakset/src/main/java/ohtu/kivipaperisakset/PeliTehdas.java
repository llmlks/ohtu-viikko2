package ohtu.kivipaperisakset;

import java.util.Scanner;

public class PeliTehdas {

    public static KPSPeli uusiPeli(String s, Scanner scanner) {
        String lastLetter = s.substring(s.length() - 1);
        KPSPeli peli = null;

        switch (lastLetter) {
            case "a":
                peli = kaksinpeli(scanner);
                break;
            case "b":
                peli = yksinpeli(scanner);
                break;
            case "c":
                peli = pahaYksinpeli(scanner);
                break;
        }

        return peli;
    }

    private static KPSPeli kaksinpeli(Scanner scanner) {
        return new KPSPelaajaVsPelaaja(scanner);
    }

    private static KPSPeli yksinpeli(Scanner scanner) {
        return new KPSTekoaly(scanner, new Tekoaly());
    }

    private static KPSPeli pahaYksinpeli(Scanner scanner) {
        return new KPSTekoaly(scanner, new TekoalyParannettu());
    }

}
