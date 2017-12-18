package ohtu.kivipaperisakset;

// "Muistava tekoäly"
import java.util.HashMap;
import java.util.Map;

public class TekoalyParannettu implements InterfaceAI {

    private final KPSSiirto kivi;
    private final KPSSiirto sakset;
    private final KPSSiirto paperi;
    private Map<String, KPSSiirto> siirrot;

    public TekoalyParannettu() {
        kivi = new KPSSiirto(KPSSiirto.KIVI);
        sakset = new KPSSiirto(KPSSiirto.SAKSET);
        paperi = new KPSSiirto(KPSSiirto.PAPERI);
        initialiseMap();
    }

    private void initialiseMap() {
        siirrot = new HashMap<>();
        siirrot.put(kivi.getMerkki(), kivi);
        siirrot.put(sakset.getMerkki(), sakset);
        siirrot.put(paperi.getMerkki(), paperi);

    }

    @Override
    public void asetaSiirto(String siirto) {
        KPSSiirto which = siirrot.get(siirto);
        if (siirto != null) {
            which.lisaa();
        }
    }

    @Override
    public String annaSiirto() {
        if (kivi.onEnemmanKuin(paperi) && kivi.onEnemmanKuin(sakset)) {
            return paperi.getMerkki();
        } else if (paperi.onEnemmanKuin(sakset)) {
            return sakset.getMerkki();
        }

        return kivi.getMerkki();
    }
}
