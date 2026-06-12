package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.*;
import njt.njt_projekat.entity.impl.StatusPorudzbine;

class PorudzbinaDtoTest {

	PorudzbinaDto p;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        p = new PorudzbinaDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
    }

    @Test
    void testPorudzbinaDto() {
        assertNotNull(p);
    }

    @Test
    void testPorudzbinaDtoSaParametrima() {
        Date datum = new Date();
        List<StavkaPorudzbineDto> stavke = new ArrayList<>();
        p = new PorudzbinaDto(1L, 100.0, datum, StatusPorudzbine.KREIRANA, 1L, stavke);
        assertNotNull(p);
        assertEquals(1L, p.getPorudzbinaId());
        assertEquals(100.0, p.getUkupanIznos());
        assertEquals(datum, p.getDatum());
        assertEquals(StatusPorudzbine.KREIRANA, p.getStatus());
        assertEquals(1L, p.getKorisnikId());
        assertEquals(stavke, p.getStavkePorudzbine());
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
    void testKorisnikIdValidan() {
        p.setKorisnikId(1L);
        Set<ConstraintViolation<PorudzbinaDto>> prekrsaji = validator.validateProperty(p, "korisnikId");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKorisnikIdNull() {
        p.setKorisnikId(null);
        Set<ConstraintViolation<PorudzbinaDto>> prekrsaji = validator.validateProperty(p, "korisnikId");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Nedostaje korisnik")));
    }

    @Test
    void testStavkePorudzbineValidne() {
        List<StavkaPorudzbineDto> stavke = new ArrayList<>();
        stavke.add(new StavkaPorudzbineDto(1L, 100.0, 1.0, 100.0, 1L, 1L));
        p.setStavkePorudzbine(stavke);
        Set<ConstraintViolation<PorudzbinaDto>> prekrsaji = validator.validateProperty(p, "stavkePorudzbine");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testStavkePorudzbineNull() {
        p.setStavkePorudzbine(null);
        Set<ConstraintViolation<PorudzbinaDto>> prekrsaji = validator.validateProperty(p, "stavkePorudzbine");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Porudzbina mora da sadrzi bar jednu stavku")));
    }

    @Test
    void testStavkePorudzbinePrazna() {
        p.setStavkePorudzbine(new ArrayList<>());
        Set<ConstraintViolation<PorudzbinaDto>> prekrsaji = validator.validateProperty(p, "stavkePorudzbine");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Porudzbina mora da sadrzi bar jednu stavku")));
    }

}
