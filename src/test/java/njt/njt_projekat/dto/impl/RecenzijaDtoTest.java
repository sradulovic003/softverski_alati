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

class RecenzijaDtoTest {

	RecenzijaDto r;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        r = new RecenzijaDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        r = null;
    }

    @Test
    void testRecenzijaDto() {
        assertNotNull(r);
    }

    @Test
    void testRecenzijaDtoSaParametrima() {
        r = new RecenzijaDto(1L, "Odlicna poslasticara", 5, 1L, 1L);
        assertNotNull(r);
        assertEquals(1L, r.getRecenzijaId());
        assertEquals("Odlicna poslasticara", r.getKomentar());
        assertEquals(5, r.getOcena());
        assertEquals(1L, r.getKorisnikId());
        assertEquals(1L, r.getPoslasticaraId());
    }

    @Test
    void testSetKomentar() {
        r.setKomentar("Odlicna poslasticara");
        assertEquals("Odlicna poslasticara", r.getKomentar());
    }

    @Test
    void testSetOcena() {
        r.setOcena(5);
        assertEquals(5, r.getOcena());
    }

    @Test
    void testSetKorisnikId() {
        r.setKorisnikId(1L);
        assertEquals(1L, r.getKorisnikId());
    }

    @Test
    void testSetPoslasticaraId() {
        r.setPoslasticaraId(1L);
        assertEquals(1L, r.getPoslasticaraId());
    }

    @Test
    void testKomentarValidan() {
        r.setKomentar("Odlicna poslasticara");
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "komentar");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKomentarNull() {
        r.setKomentar(null);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "komentar");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKomentarPredug() {
    	r.setKomentar("a".repeat(501));
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "komentar");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Komentar ne sme da sadrzi vise od 500 karaktera", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testOcenaValidna() {
        r.setOcena(5);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "ocena");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testOcenaNull() {
        r.setOcena(null);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "ocena");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Niste uneli ocenu!", prekrsaji.iterator().next().getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "0, Ocena mora biti najmanje 1",
        "6, Ocena moze biti najvise 5"
    })
    void testOcenaNevalidna(int ocena, String ocekivanaPoruka) {
        r.setOcena(ocena);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "ocena");
        assertFalse(prekrsaji.isEmpty());
        assertEquals(ocekivanaPoruka, prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testKorisnikIdValidan() {
        r.setKorisnikId(1L);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "korisnikId");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKorisnikIdNull() {
        r.setKorisnikId(null);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "korisnikId");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Nedostaje korisnik", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testPoslasticaraIdValidan() {
        r.setPoslasticaraId(1L);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "poslasticaraId");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testPoslasticaraIdNull() {
        r.setPoslasticaraId(null);
        Set<ConstraintViolation<RecenzijaDto>> prekrsaji = validator.validateProperty(r, "poslasticaraId");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Nedostaje poslasticara", prekrsaji.iterator().next().getMessage());
    }

}
