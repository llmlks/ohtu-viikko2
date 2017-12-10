package commands;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

public class Nollaus implements Komento {

    private final Sovelluslogiikka sovellus;
    private final JTextField tuloskentta;
    private final JTextField syotekentta;

    public Nollaus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        sovellus.nollaa();

        int tulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
    }

    @Override
    public void peru() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
