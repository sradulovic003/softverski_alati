package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PorudzbinaTest {

	Porudzbina p;

    @BeforeEach
    void setUp() throws Exception {
        p = new Porudzbina();
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
    }

    @Test
    void testPorudzbina() {
        assertNotNull(p);
    }

    @Test
    void testPorudzbinaSaId() {
        p = new Porudzbina(1L);
        assertNotNull(p);
        assertEquals(1L, p.getPorudzbinaId());
    }

    @Test
    void testPorudzbinaSaParametrima() {
        Korisnik korisnik = new Korisnik(1L);
        Date datum = new Date();
        List<StavkaPorudzbine> stavke = new ArrayList<>();
        p = new Porudzbina(1L, 100.0, korisnik, datum, StatusPorudzbine.KREIRANA, stavke);
        assertNotNull(p);
        assertEquals(1L, p.getPorudzbinaId());
        assertEquals(100.0, p.getUkupanIznos());
        assertEquals(korisnik, p.getKorisnik());
        assertEquals(datum, p.getDatum());
        assertEquals(StatusPorudzbine.KREIRANA, p.getStatus());
    }

    @Test
    void testStatusDefaultKreirana() {
        assertEquals(StatusPorudzbine.KREIRANA, p.getStatus());
    }

    @Test
    void testDatumDefaultNotNull() {
        assertNotNull(p.getDatum());
    }

    @Test
    void testSetPorudzbinaId() {
        p.setPorudzbinaId(1L);
        assertEquals(1L, p.getPorudzbinaId());
    }

    @Test
    void testSetUkupanIznos() {
        p.setUkupanIznos(100.0);
        assertEquals(100.0, p.getUkupanIznos());
    }

    @Test
    void testSetDatum() {
        Date datum = new Date();
        p.setDatum(datum);
        assertEquals(datum, p.getDatum());
    }

    @Test
    void testSetStatus() {
        p.setStatus(StatusPorudzbine.POTVRDJENA);
        assertEquals(StatusPorudzbine.POTVRDJENA, p.getStatus());
    }

    @Test
    void testSetKorisnik() {
        Korisnik korisnik = new Korisnik(1L);
        p.setKorisnik(korisnik);
        assertEquals(korisnik, p.getKorisnik());
    }

    @Test
    void testSetDostavljac() {
        Dostavljac dostavljac = new Dostavljac(1L);
        p.setDostavljac(dostavljac);
        assertEquals(dostavljac, p.getDostavljac());
    }

    @Test
    void testSetDostavljacNull() {
        p.setDostavljac(null);
        assertNull(p.getDostavljac());
    }

    @Test
    void testAddItem() {
        StavkaPorudzbine stavka = new StavkaPorudzbine();
        p.addItem(stavka);
        assertEquals(1, p.getStavkePorudzbine().size());
        assertEquals(p, stavka.getPorudzbina());
    }

    @Test
    void testRemoveItem() {
        StavkaPorudzbine stavka = new StavkaPorudzbine();
        p.addItem(stavka);
        p.removeItem(stavka);
        assertEquals(0, p.getStavkePorudzbine().size());
        assertNull(stavka.getPorudzbina());
    }

    @Test
    void testSetStavkePorudzbine() {
        List<StavkaPorudzbine> stavke = new ArrayList<>();
        stavke.add(new StavkaPorudzbine());
        p.setStavkePorudzbine(stavke);
        assertEquals(1, p.getStavkePorudzbine().size());
    }

}
