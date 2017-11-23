package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author llmlks
 */
public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viitteet;
    private Varasto varasto;
    private Kauppa kauppa;
    private Tuote maito;
    private Tuote leipa;

    private static final int MAITO_ID = 1;
    private static final int LEIPA_ID = 2;
    private static final int MAITO_HINTA = 5;
    private static final int LEIPA_HINTA = 3;
    private static final int MAITO_SALDO = 10;
    private static final int LEIPA_SALDO = 2;

    private static final int VIITE1 = 123;
    private static final int VIITE2 = 234;
    private static final int VIITE3 = 345;
    private static final int VIITE4 = 456;
    private static final String KAUPAN_TILI = "33333-44455";
    private static final String NIMI = "pekka";
    private static final String TILI = "123456";

    public KauppaTest() {
    }

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viitteet = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);

        maito = new Tuote(MAITO_ID, "maito", MAITO_HINTA);
        leipa = new Tuote(LEIPA_ID, "leipa", LEIPA_HINTA);

        when(varasto.haeTuote(MAITO_ID)).thenReturn(maito);
        when(varasto.haeTuote(LEIPA_ID)).thenReturn(leipa);
        when(varasto.saldo(MAITO_ID)).thenReturn(MAITO_SALDO);

        kauppa = new Kauppa(varasto, pankki, viitteet);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viitteet.uusi()).thenReturn(VIITE1);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));
    }

    @Test
    public void kahdenEriTuotteenOstonJalkeenHintaOnOikeinTilisiirrossa() {
        when(viitteet.uusi()).thenReturn(VIITE1);
        when(varasto.saldo(LEIPA_ID)).thenReturn(LEIPA_SALDO);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.lisaaKoriin(LEIPA_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA + LEIPA_HINTA));
    }

    @Test
    public void kahdenSamanTuotteenOstonJalkeenHintaOnOikeinTilisiirrossa() {
        when(viitteet.uusi()).thenReturn(VIITE1);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(2 * MAITO_HINTA));
    }

    @Test
    public void loppunutTuoteEiMuutaHintaaTilisiirrossa() {
        when(varasto.saldo(LEIPA_ID)).thenReturn(0);
        when(viitteet.uusi()).thenReturn(VIITE1);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.lisaaKoriin(LEIPA_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));
    }

    @Test
    public void aloitaAsiointiNollaaOstokset() {
        when(viitteet.uusi()).thenReturn(VIITE1);
        when(varasto.saldo(LEIPA_ID)).thenReturn(LEIPA_SALDO);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.lisaaKoriin(MAITO_ID);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(LEIPA_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(LEIPA_HINTA));
    }

    @Test
    public void jokaiselleMaksutapahtumalleUusiViite() {
        when(viitteet.uusi())
                .thenReturn(VIITE1)
                .thenReturn(VIITE2)
                .thenReturn(VIITE3)
                .thenReturn(VIITE4);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE2), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE3), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE4), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));
    }

    @Test
    public void tuotteenPoistoVahentaaTilisiirronSummaa() {
        when(viitteet.uusi()).thenReturn(VIITE1);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.lisaaKoriin(MAITO_ID);
        kauppa.poistaKorista(MAITO_ID);
        kauppa.tilimaksu(NIMI, TILI);

        verify(pankki).tilisiirto(eq(NIMI), eq(VIITE1), eq(TILI),
                eq(KAUPAN_TILI), eq(MAITO_HINTA));
    }
}
