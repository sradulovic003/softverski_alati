package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StavkaPorudzbineTest {

	StavkaPorudzbine s;

    @BeforeEach
    void setUp() throws Exception {
        s = new StavkaPorudzbine();
    }

    @AfterEach
    void tearDown() throws Exception {
        s = null;
    }

    @Test
    void testStavkaPorudzbine() {
        assertNotNull(s);
    }

    @Test
    void testStavkaPorudzbineSaParametrima() {
        Porudzbina porudzbina = new Porudzbina(1L);
        Proizvod proizvod = new Proizvod(1L);
        s = new StavkaPorudzbine(1L, 500.0, 2.0, 1000.0, porudzbina, proizvod);
        assertNotNull(s);
        assertEquals(1L, s.getRb());
        assertEquals(500.0, s.getCena());
        assertEquals(2.0, s.getKolicina());
        assertEquals(1000.0, s.getIznos());
        assertEquals(porudzbina, s.getPorudzbina());
        assertEquals(proizvod, s.getProizvod());
    }

    @Test
    void testSetRb() {
        s.setRb(1L);
        assertEquals(1L, s.getRb());
    }

    @Test
    void testSetCena() {
        s.setCena(500.0);
        assertEquals(500.0, s.getCena());
    }

    @Test
    void testSetKolicina() {
        s.setKolicina(2.0);
        assertEquals(2.0, s.getKolicina());
    }

    @Test
    void testSetIznos() {
        s.setIznos(1000.0);
        assertEquals(1000.0, s.getIznos());
    }

    @Test
    void testSetPorudzbina() {
        Porudzbina porudzbina = new Porudzbina(1L);
        s.setPorudzbina(porudzbina);
        assertEquals(porudzbina, s.getPorudzbina());
    }

    @Test
    void testSetPorudzbinaNull() {
        s.setPorudzbina(null);
        assertNull(s.getPorudzbina());
    }

    @Test
    void testSetProizvod() {
        Proizvod proizvod = new Proizvod(1L);
        s.setProizvod(proizvod);
        assertEquals(proizvod, s.getProizvod());
    }

}
