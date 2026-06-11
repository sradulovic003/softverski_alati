package njt.njt_projekat.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProizvodTest {

	Proizvod p;

    @BeforeEach
    void setUp() throws Exception {
        p = new Proizvod();
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
    }

    @Test
    void testProizvod() {
        assertNotNull(p);
    }

    @Test
    void testProizvodSaId() {
        p = new Proizvod(1L);
        assertNotNull(p);
        assertEquals(1L, p.getProizvodId());
    }

    @Test
    void testProizvodSaParametrima() {
        Poslasticara poslasticara = new Poslasticara(1L);
        p = new Proizvod(1L, "Torta", 1500.0, "http://slika.com", Kategorija.TORTE, "Ukusna torta", poslasticara);
        assertNotNull(p);
        assertEquals(1L, p.getProizvodId());
        assertEquals("Torta", p.getNaziv());
        assertEquals(1500.0, p.getCena());
        assertEquals("http://slika.com", p.getImageUrl());
        assertEquals(Kategorija.TORTE, p.getKategorija());
        assertEquals("Ukusna torta", p.getOpis());
        assertEquals(poslasticara, p.getPoslasticara());
    }

    @Test
    void testSetProizvodId() {
        p.setProizvodId(1L);
        assertEquals(1L, p.getProizvodId());
    }

    @Test
    void testSetNaziv() {
        p.setNaziv("Torta");
        assertEquals("Torta", p.getNaziv());
    }

    @Test
    void testSetCena() {
        p.setCena(1500.0);
        assertEquals(1500.0, p.getCena());
    }

    @Test
    void testSetImageUrl() {
        p.setImageUrl("http://slika.com");
        assertEquals("http://slika.com", p.getImageUrl());
    }

    @Test
    void testSetOpis() {
        p.setOpis("Ukusna torta");
        assertEquals("Ukusna torta", p.getOpis());
    }

    @Test
    void testSetPoslasticara() {
        Poslasticara poslasticara = new Poslasticara(1L);
        p.setPoslasticara(poslasticara);
        assertEquals(poslasticara, p.getPoslasticara());
    }

    @ParameterizedTest
    @CsvSource({
        "TORTE",
        "KOLACI",
        "PITE",
        "TARTOVI",
        "KROFNE"
    })
    void testSetKategorija(Kategorija kategorija) {
        p.setKategorija(kategorija);
        assertEquals(kategorija, p.getKategorija());
    }

}
