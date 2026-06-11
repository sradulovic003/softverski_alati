package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KorisnikTest {

	Korisnik k;

    @BeforeEach
    void setUp() throws Exception {
        k = new Korisnik();
    }

    @AfterEach
    void tearDown() throws Exception {
        k = null;
    }

    @Test
    void testKorisnik() {
        assertNotNull(k);
    }

    @Test
    void testKorisnikSaId() {
        k = new Korisnik(1L);
        assertNotNull(k);
        assertEquals(1L, k.getKorisnikId());
    }

    @Test
    void testSetKorisnikId() {
        k.setKorisnikId(1L);
        assertEquals(1L, k.getKorisnikId());
    }

    @Test
    void testSetIme() {
        k.setIme("Marko");
        assertEquals("Marko", k.getIme());
    }

    @Test
    void testSetPrezime() {
        k.setPrezime("Markovic");
        assertEquals("Markovic", k.getPrezime());
    }

    @Test
    void testSetKorisnickoIme() {
        k.setKorisnickoIme("marko123");
        assertEquals("marko123", k.getKorisnickoIme());
    }

    @Test
    void testSetEmail() {
        k.setEmail("marko@gmail.com");
        assertEquals("marko@gmail.com", k.getEmail());
    }

    @Test
    void testSetLozinka() {
        k.setLozinka("hesovalozinka123");
        assertEquals("hesovalozinka123", k.getLozinka());
    }

    @Test
    void testSetUlogaUser() {
        k.setUloga(Uloga.USER);
        assertEquals(Uloga.USER, k.getUloga());
    }

    @Test
    void testSetUlogaAdmin() {
        k.setUloga(Uloga.ADMIN);
        assertEquals(Uloga.ADMIN, k.getUloga());
    }

    @Test
    void testSetAdresa() {
        k.setAdresa("Ulica 123");
        assertEquals("Ulica 123", k.getAdresa());
    }

    @Test
    void testSetEnabledTrue() {
        k.setEnabled(true);
        assertTrue(k.isEnabled());
    }

    @Test
    void testSetEnabledFalse() {
        k.setEnabled(false);
        assertFalse(k.isEnabled());
    }

    @Test
    void testUlogaDefaultUser() {
        assertEquals(Uloga.USER, k.getUloga());
    }

    @Test
    void testEnabledDefaultFalse() {
        assertFalse(k.isEnabled());
    }

    @Test
    void testSetPorudzbine() {
        List<Porudzbina> porudzbine = new ArrayList<>();
        porudzbine.add(new Porudzbina(1L));
        k.setPorudzbine(porudzbine);
        assertEquals(1, k.getPorudzbine().size());
    }

}
