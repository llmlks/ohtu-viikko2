package ohtu.matkakortti;

import ohtu.matkakortti.Matkakortti;
import ohtu.matkakortti.Kassapaate;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KassapaateTest {

    Kassapaate kassa;
    Matkakortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Matkakortti.class);
    }

    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);

        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }

    @Test
    public void kortilleLadataanRahaaJosMaaraPositiivinen() {
        kassa.lataa(kortti, 5);

        verify(kortti).lataa(5);
    }

    @Test
    public void kortilleEiLadataNegatiivistaRahasummaa() {
        kassa.lataa(kortti, -2);

        verify(kortti, times(0)).lataa(anyInt());
    }
}
