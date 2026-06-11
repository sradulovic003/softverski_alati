package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PoslasticaraTest {

	Poslasticara p;

    @BeforeEach
    void setUp() throws Exception {
        p = new Poslasticara();
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
    }

    @Test
    void testPoslasticara() {
        assertNotNull(p);
    }

    @Test
    void testPoslasticaraSaId() {
        p = new Poslasticara(1L);
        assertNotNull(p);
        assertEquals(1L, p.getPoslasticaraId());
    }

    @Test
    void testPoslasticaraSaParametrima() {
        List<Proizvod> proizvodi = new ArrayList<>();
        p = new Poslasticara(1L, "Slatki Kut", "Ulica 123", "0601234567", proizvodi);
        assertNotNull(p);
        assertEquals(1L, p.getPoslasticaraId());
        assertEquals("Slatki Kut", p.getNaziv());
        assertEquals("Ulica 123", p.getAdresa());
        assertEquals("0601234567", p.getKontakt());
        assertEquals(proizvodi, p.getProizvodi());
    }

    @Test
    void testSetPoslasticaraId() {
        p.setPoslasticaraId(1L);
        assertEquals(1L, p.getPoslasticaraId());
    }

    @Test
    void testSetNaziv() {
        p.setNaziv("Slatki Kut");
        assertEquals("Slatki Kut", p.getNaziv());
    }

    @Test
    void testSetAdresa() {
        p.setAdresa("Ulica 123");
        assertEquals("Ulica 123", p.getAdresa());
    }

    @Test
    void testSetKontakt() {
        p.setKontakt("0601234567");
        assertEquals("0601234567", p.getKontakt());
    }

    @Test
    void testSetProizvodi() {
        List<Proizvod> proizvodi = new ArrayList<>();
        proizvodi.add(new Proizvod(1L));
        p.setProizvodi(proizvodi);
        assertEquals(1, p.getProizvodi().size());
    }

}
