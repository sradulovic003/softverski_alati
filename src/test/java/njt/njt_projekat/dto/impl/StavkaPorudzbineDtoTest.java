package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        assertEquals("Niste uneli kolicinu!", prekrsaji.iterator().next().getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "-1.0, Kolicina mora biti pozitivan broj",
        "0.0, Kolicina mora biti pozitivan broj"
    })
    void testKolicinaNevalidna(double kolicina, String ocekivanaPoruka) {
        s.setKolicina(kolicina);
        Set<ConstraintViolation<StavkaPorudzbineDto>> prekrsaji = validator.validateProperty(s, "kolicina");
        assertFalse(prekrsaji.isEmpty());
        assertEquals(ocekivanaPoruka, prekrsaji.iterator().next().getMessage());
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
        assertEquals("Nedostaje proizvod", prekrsaji.iterator().next().getMessage());
    }

}
