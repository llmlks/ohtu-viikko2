package commands;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

public class Nollaus implements Komento {

    private final Sovelluslogiikka sovellus;
    private final JTextField tuloskentta;
    private final JTextField syotekentta;
    private int arvoEnnen;

    public Nollaus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        arvoEnnen = sovellus.tulos();

        sovellus.nollaa();

        syotekentta.setText("");
        tuloskentta.setText("0");
    }

    @Override
    public void peru() {
        sovellus.plus(arvoEnnen);

        arvoEnnen = 0;

        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }

}
