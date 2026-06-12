package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.*;

class StavkaPorudzbineDtoTest {

	StavkaPorudzbineDto s;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        s = new StavkaPorudzbineDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        s = null;
    }

    @Test
    void testStavkaPorudzbineDto() {
        assertNotNull(s);
    }

    @Test
    void testStavkaPorudzbineDtoSaParametrima() {
        s = new StavkaPorudzbineDto(1L, 500.0, 2.0, 1000.0, 1L, 1L);
        assertNotNull(s);
        assertEquals(1L, s.getRb());
        assertEquals(500.0, s.getCena());
        assertEquals(2.0, s.getKolicina());
        assertEquals(1000.0, s.getIznos());
        assertEquals(1L, s.getPorudzbinaId());
        assertEquals(1L, s.getProizvodId());
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
    void testSetIznos() {
        s.setIznos(1000.0);
        assertEquals(1000.0, s.getIznos());
    }

    @Test
    void testSetPorudzbinaId() {
        s.setPorudzbinaId(1L);
        assertEquals(1L, s.getPorudzbinaId());
    }

    @Test
    void testKolicinaValidna() {
        s.setKolicina(2.0);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "kolicina");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKolicinaNull() {
        s.setKolicina(null);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "kolicina");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli kolicinu!")));
    }

    @Test
    void testKolicinaNegativna() {
        s.setKolicina(-1.0);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "kolicina");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Kolicina mora biti pozitivan broj")));
    }

    @Test
    void testKolicinaNula() {
        s.setKolicina(0.0);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "kolicina");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Kolicina mora biti pozitivan broj")));
    }

    @Test
    void testProizvodIdValidan() {
        s.setProizvodId(1L);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "proizvodId");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testProizvodIdNull() {
        s.setProizvodId(null);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "proizvodId");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Nedostaje proizvod")));
    }

}
