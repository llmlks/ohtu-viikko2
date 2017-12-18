package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.

import static ohtu.kivipaperisakset.KPSSiirto.KIVI;
import static ohtu.kivipaperisakset.KPSSiirto.PAPERI;
import static ohtu.kivipaperisakset.KPSSiirto.SAKSET;

public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    // sisäinen metodi, jolla tarkastetaan tuliko tasapeli
    private static boolean tasapeli(String eka, String toka) {
        return eka.equals(toka);
    }

    // sisäinen metodi joka tarkastaa voittaako eka pelaaja tokan
    private static boolean ekaVoittaa(String eka, String toka) {
        return (eka.equals(SAKSET) && toka.equals(PAPERI))
                || (eka.equals(PAPERI) && toka.equals(KIVI))
                || (eka.equals(KIVI) && toka.equals(SAKSET));
    }

    @Override
    public String toString() {
        String s = "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
        return s;
    }
}