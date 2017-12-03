package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public static final int ALKUKAPASITEETTI = 5;
    public static final int OLETUSKASVATUS = 5;

    private int kasvatuskoko;
    private int[] joukonLuvut;
    private int alkioidenLkm;

    public IntJoukko() {
        this(ALKUKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            String error = "Kapasiteetti: " + kapasiteetti
                    + ", Kasvatuskoko: " + kasvatuskoko;
            throw new IllegalArgumentException(error);
        }

        joukonLuvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {

            joukonLuvut[alkioidenLkm] = luku;
            alkioidenLkm++;

            if (alkioidenLkm % joukonLuvut.length == 0) {
                kasvataTaulukonKokoa();
            }
            return true;
        }
        return false;
    }

    private void lisaaMonta(int[] lisattavat) {
        Arrays.stream(lisattavat).forEach(i -> lisaa(i));
    }

    public boolean kuuluu(int luku) {
        return etsiEnsimmainen(luku) != -1;
    }

    private int etsiEnsimmainen(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int element) {
        int ekaIndeksi = etsiEnsimmainen(element);

        if (ekaIndeksi != -1) {
            alkioidenLkm--;
            System.arraycopy(joukonLuvut, ekaIndeksi + 1, joukonLuvut,
                    ekaIndeksi, alkioidenLkm - ekaIndeksi);
            return true;
        }

        return false;
    }

    private void kasvataTaulukonKokoa() {
        int[] newArray = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(joukonLuvut, newArray);
        joukonLuvut = newArray;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, alkioidenLkm);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String result = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            result += i > 0 ? ", " + joukonLuvut[i] : joukonLuvut[i];
        }
        return result + "}";
    }

    public int[] toIntArray() {
        int[] result = new int[alkioidenLkm];
        System.arraycopy(joukonLuvut, 0, result, 0, alkioidenLkm);
        return result;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();

        x.lisaaMonta(aTaulu);
        x.lisaaMonta(bTaulu);
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        int[] aTaulu = a.toIntArray();

        Arrays.stream(aTaulu).filter(i -> b.kuuluu(i)).forEach(i -> y.lisaa(i));

        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        int[] aTaulu = a.toIntArray();

        Arrays.stream(aTaulu).filter(i -> !b.kuuluu(i)).forEach(i -> z.lisaa(i));

        return z;
    }

}
