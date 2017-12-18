package ohtu.kivipaperisakset;

public class Tekoaly implements InterfaceAI {

    int siirto;
    String[] mahdollisetSiirrot;

    public Tekoaly() {
        siirto = 0;
        mahdollisetSiirrot
                = new String[]{KPSSiirto.KIVI, KPSSiirto.PAPERI, KPSSiirto.SAKSET};
    }

    @Override
    public String annaSiirto() {
        siirto = ++siirto % 3;

        return mahdollisetSiirrot[siirto];
    }

    @Override
    public void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }
}
