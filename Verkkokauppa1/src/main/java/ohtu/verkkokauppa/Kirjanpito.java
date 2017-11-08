
package ohtu.verkkokauppa;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Kirjanpito implements AbstractKirjanpito {    
    private final ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<>();
    }
    
    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }       
}
