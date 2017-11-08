package ohtu;

import ohtu.verkkokauppa.AbstractKirjanpito;
import ohtu.verkkokauppa.AbstractPankki;
import ohtu.verkkokauppa.AbstractVarasto;
import ohtu.verkkokauppa.AbstractViitegeneraattori;
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;

public class Main {

    public static void main(String[] args) {
        AbstractKirjanpito kirjanpito = new Kirjanpito();
        AbstractVarasto varasto = new Varasto(kirjanpito);
        AbstractPankki pankki = new Pankki(kirjanpito);
        AbstractViitegeneraattori viiteGen = new Viitegeneraattori();
        Kauppa kauppa = new Kauppa(varasto, pankki, viiteGen);

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma : kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
