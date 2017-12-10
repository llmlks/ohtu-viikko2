package commands;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

public class Erotus implements Komento {

    private final Sovelluslogiikka sovellus;
    private final JTextField tuloskentta;
    private final JTextField syotekentta;
    private int arvo;

    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        arvo = Integer.parseInt(syotekentta.getText());
        sovellus.miinus(arvo);

        int tulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);

        arvo = 0;

        int tulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
    }

}
