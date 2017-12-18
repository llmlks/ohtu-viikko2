package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final String PELIN_LOPETUS
            = "peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun"
            + " muun kuin k, p tai s";
    private static final String VALIKKO
            = "\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean stopPlaying = false;

        while (!stopPlaying) {
            System.out.println(VALIKKO);

            String vastaus = scanner.nextLine();

            KPSPeli peli = PeliTehdas.uusiPeli(vastaus, scanner);

            if (peli != null) {
                System.out.println(PELIN_LOPETUS);
                peli.pelaa();
            }

            stopPlaying = (peli == null);
        }

    }
}
