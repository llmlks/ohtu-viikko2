package ohtu.kivipaperisakset;

public class KPSSiirto {

    public final static String KIVI = "k";
    public final static String PAPERI = "p";
    public final static String SAKSET = "s";

    private final String siirto;
    private int maara;

    public KPSSiirto(String s) {
        this.siirto = s;
        this.maara = 0;
    }

    public void lisaa() {
        maara++;
    }

    public int getMaara() {
        return maara;
    }

    public String getMerkki() {
        return siirto;
    }
    
    public boolean onEnemmanKuin(KPSSiirto toinen) {
        return this.maara > toinen.maara;
    }

    public static boolean isValid(String s) {
        return s.equals(SAKSET) || s.equals(PAPERI) || s.equals(KIVI);
    }
}
