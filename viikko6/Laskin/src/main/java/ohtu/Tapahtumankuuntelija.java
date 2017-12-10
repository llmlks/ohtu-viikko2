package ohtu;

import commands.Erotus;
import commands.Komento;
import commands.Nollaus;
import commands.Summa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Tapahtumankuuntelija implements ActionListener {

    private final JButton nollaa;
    private final JButton undo;
    private final Sovelluslogiikka sovellus;
    private final Map<JButton, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa,
            JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();

        komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        komennot.put(nollaa, new Nollaus(sovellus, tuloskentta, syotekentta));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Komento komento = komennot.get((JButton) ae.getSource());

        if (komento != null) {
            komento.suorita();
            edellinen = komento;
        } else {
            edellinen.peru();
            edellinen = null;
        }

        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(edellinen != null);
    }

}
