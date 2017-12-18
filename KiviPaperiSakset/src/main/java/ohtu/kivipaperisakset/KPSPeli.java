package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPeli {

    protected Scanner scanner;
    private Tuomari tuomari;
    private String ekanSiirto;
    private String tokanSiirto;

    public KPSPeli(Scanner scanner) {
        this.scanner = scanner;
    }

    public void pelaa() {
        tuomari = new Tuomari();
        boolean peliJatkuu = true;

        while (peliJatkuu) {
            if (kierros()) {
                tulokset();
            } else {
                peliJatkuu = false;
            }
        }

        pelinLopetus();
    }

    private void pelinLopetus() {
        System.out.println("\nKiitos!");
        System.out.println(tuomari);
    }

    private void tulokset() {
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tuomari + "\n");
    }

    private boolean kierros() {
        ekanSiirto = ekanSiirto();
        tokanSiirto = tokanSiirto();
        return KPSSiirto.isValid(ekanSiirto) && KPSSiirto.isValid(tokanSiirto);
    }

    protected String getEkanSiirto() {
        return ekanSiirto;
    }

    protected abstract String ekanSiirto();

    protected abstract String tokanSiirto();
}
