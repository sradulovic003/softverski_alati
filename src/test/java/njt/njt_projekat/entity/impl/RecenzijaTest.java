package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecenzijaTest {

	Recenzija r;

    @BeforeEach
    void setUp() throws Exception {
        r = new Recenzija();
    }

    @AfterEach
    void tearDown() throws Exception {
        r = null;
    }

    @Test
    void testRecenzija() {
        assertNotNull(r);
    }

    @Test
    void testRecenzijaSaId() {
        r = new Recenzija(1L);
        assertNotNull(r);
        assertEquals(1L, r.getRecenzijaId());
    }

    @Test
    void testRecenzijaSaParametrima() {
        Korisnik korisnik = new Korisnik(1L);
        Poslasticara poslasticara = new Poslasticara(1L);
        r = new Recenzija(1L, "Odlicna poslasticara", 5, korisnik, poslasticara);
        assertNotNull(r);
        assertEquals(1L, r.getRecenzijaId());
        assertEquals("Odlicna poslasticara", r.getKomentar());
        assertEquals(5, r.getOcena());
        assertEquals(korisnik, r.getKorisnik());
        assertEquals(poslasticara, r.getPoslasticara());
    }

    @Test
    void testSetRecenzijaId() {
        r.setRecenzijaId(1L);
        assertEquals(1L, r.getRecenzijaId());
    }

    @Test
    void testSetKomentar() {
        r.setKomentar("Odlicna poslasticara");
        assertEquals("Odlicna poslasticara", r.getKomentar());
    }

    @Test
    void testSetKomentarNull() {
        r.setKomentar(null);
        assertNull(r.getKomentar());
    }

    @Test
    void testSetOcena() {
        r.setOcena(5);
        assertEquals(5, r.getOcena());
    }

    @Test
    void testSetKorisnik() {
        Korisnik korisnik = new Korisnik(1L);
        r.setKorisnik(korisnik);
        assertEquals(korisnik, r.getKorisnik());
    }

    @Test
    void testSetPoslasticara() {
        Poslasticara poslasticara = new Poslasticara(1L);
        r.setPoslasticara(poslasticara);
        assertEquals(poslasticara, r.getPoslasticara());
    }

}
