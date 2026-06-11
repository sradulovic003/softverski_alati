package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KuponTest {

	Kupon k;

    @BeforeEach
    void setUp() throws Exception {
        k = new Kupon();
    }

    @AfterEach
    void tearDown() throws Exception {
        k = null;
    }

    @Test
    void testKupon() {
        assertNotNull(k);
    }

    @Test
    void testKuponSaId() {
        k = new Kupon(1L);
        assertNotNull(k);
        assertEquals(1L, k.getKuponId());
    }

    @Test
    void testKuponSaParametrima() {
        Korisnik korisnik = new Korisnik(1L);
        Porudzbina porudzbina = new Porudzbina(1L);
        k = new Kupon(1L, "KOD10", 10.0, false, korisnik, porudzbina);
        assertNotNull(k);
        assertEquals(1L, k.getKuponId());
        assertEquals("KOD10", k.getKod());
        assertEquals(10.0, k.getPopust());
        assertFalse(k.isIskoriscen());
        assertEquals(korisnik, k.getKorisnik());
        assertEquals(porudzbina, k.getPorudzbina());
    }

    @Test
    void testSetKuponId() {
        k.setKuponId(1L);
        assertEquals(1L, k.getKuponId());
    }

    @Test
    void testSetKod() {
        k.setKod("KOD10");
        assertEquals("KOD10", k.getKod());
    }

    @Test
    void testSetPopust() {
        k.setPopust(10.0);
        assertEquals(10.0, k.getPopust());
    }

    @Test
    void testSetIskoriscenTrue() {
        k.setIskoriscen(true);
        assertTrue(k.isIskoriscen());
    }

    @Test
    void testSetIskoriscenFalse() {
        k.setIskoriscen(false);
        assertFalse(k.isIskoriscen());
    }

    @Test
    void testSetKorisnik() {
        Korisnik korisnik = new Korisnik(1L);
        k.setKorisnik(korisnik);
        assertEquals(korisnik, k.getKorisnik());
    }

    @Test
    void testSetPorudzbinaNull() {
        k.setPorudzbina(null);
        assertNull(k.getPorudzbina());
    }

    @Test
    void testSetPorudzbina() {
        Porudzbina porudzbina = new Porudzbina(1L);
        k.setPorudzbina(porudzbina);
        assertEquals(porudzbina, k.getPorudzbina());
    }

}
