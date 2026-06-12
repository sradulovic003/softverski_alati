package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.*;
import njt.njt_projekat.entity.impl.Kategorija;

class ProizvodDtoTest {
	
	ProizvodDto p;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        p = new ProizvodDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
    }

    @Test
    void testProizvodDto() {
        assertNotNull(p);
    }

    @Test
    void testProizvodDtoSaParametrima() {
        p = new ProizvodDto(1L, "Torta", 1500.0, "http://slika.com/torta.jpg", Kategorija.TORTE, "Ukusna torta", 1L);
        assertNotNull(p);
        assertEquals(1L, p.getProizvodId());
        assertEquals("Torta", p.getNaziv());
        assertEquals(1500.0, p.getCena());
        assertEquals("http://slika.com/torta.jpg", p.getImageUrl());
        assertEquals(Kategorija.TORTE, p.getKategorija());
        assertEquals("Ukusna torta", p.getOpis());
        assertEquals(1L, p.getPoslasticaraId());
    }

    @Test
    void testSetPoslasticaraId() {
        p.setPoslasticaraId(1L);
        assertEquals(1L, p.getPoslasticaraId());
    }

    @Test
    void testNazivValidan() {
        p.setNaziv("Torta");
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "naziv");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testNazivPrazan() {
        p.setNaziv("");
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "naziv");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli naziv!")));
    }

    @Test
    void testNazivKratak() {
        p.setNaziv("T");
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "naziv");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Naziv mora imati između 2 i 100 karaktera")));
    }

    @Test
    void testNazivPredug() {
        p.setNaziv("a".repeat(101));
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "naziv");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Naziv mora imati između 2 i 100 karaktera")));
    }

    @Test
    void testCenaValidna() {
        p.setCena(1500.0);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "cena");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testCenaNull() {
        p.setCena(null);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "cena");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli cenu!")));
    }

    @Test
    void testCenaNegativna() {
        p.setCena(-100.0);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "cena");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Cena mora biti pozitivan broj")));
    }

    @Test
    void testImageUrlValidan() {
        p.setImageUrl("http://slika.com/torta.jpg");
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "imageUrl");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testImageUrlNull() {
        p.setImageUrl(null);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "imageUrl");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testImageUrlNevalidan() {
        p.setImageUrl("nije url");
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "imageUrl");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Slika mora biti link")));
    }

    @Test
    void testKategorijaValidna() {
        p.setKategorija(Kategorija.TORTE);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "kategorija");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKategorijaNull() {
        p.setKategorija(null);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "kategorija");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Kategorija mora biti izabrana")));
    }

    @Test
    void testOpisValidan() {
        p.setOpis("Ukusna torta");
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "opis");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testOpisNull() {
        p.setOpis(null);
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "opis");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testOpisPredug() {
        p.setOpis("a".repeat(501));
        Set<ConstraintViolation<ProizvodDto>> prekrsaji = validator.validateProperty(p, "opis");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Opis moze imati najvise 500 karaktera")));
    }
}
