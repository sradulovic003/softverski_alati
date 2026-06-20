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

class KuponDtoTest {

	KuponDto k;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        k = new KuponDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        k = null;
    }

    @Test
    void testKuponDto() {
        assertNotNull(k);
    }

    @Test
    void testKuponDtoSaParametrima() {
        k = new KuponDto(1L, "KOD10", 10.0, false, 1L, 1L);
        assertNotNull(k);
        assertEquals(1L, k.getKuponId());
        assertEquals("KOD10", k.getKod());
        assertEquals(10.0, k.getPopust());
        assertFalse(k.isIskoriscen());
        assertEquals(1L, k.getKorisnikId());
        assertEquals(1L, k.getPorudzbinaId());
    }

    @Test
    void testSetKod() {
        k.setKod("KOD10");
        assertEquals("KOD10", k.getKod());
    }

    @Test
    void testSetPopust() {
        k.setPopust(10.0);
        assertEquals(10.0, k.getPopust());
    }

    @Test
    void testSetIskoriscenTrue() {
        k.setIskoriscen(true);
        assertTrue(k.isIskoriscen());
    }

    @Test
    void testSetIskoriscenFalse() {
        k.setIskoriscen(false);
        assertFalse(k.isIskoriscen());
    }

    @Test
    void testSetKorisnikId() {
        k.setKorisnikId(1L);
        assertEquals(1L, k.getKorisnikId());
    }

    @Test
    void testSetPorudzbinaId() {
        k.setPorudzbinaId(1L);
        assertEquals(1L, k.getPorudzbinaId());
    }

    @Test
    void testKodValidan() {
        k.setKod("KOD10");
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "kod");
        assertTrue(prekrsaji.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "'', Niste uneli kod kupona!",
        "'   ', Niste uneli kod kupona!",
        "KODKOJIIMAVISEODTRIDESETKARAKTERA1234, Kod ne sme da sadrzi vise od 30 karaktera"
    })
    void testKodNevalidan(String kod, String ocekivanaPoruka) {
    	k.setKod(kod);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "kod");
        assertFalse(prekrsaji.isEmpty());
        assertEquals(ocekivanaPoruka, prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testKodNull() {
    	k.setKod(null);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "kod");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Niste uneli kod kupona!", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testPopustValidan() {
        k.setPopust(10.0);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertTrue(prekrsaji.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "-5.0, Popust mora biti pozitivan broj",
        "0.0, Popust mora biti pozitivan broj"
    })
    void testPopustNevalidan(Double popust, String ocekivanaPoruka) {
    	k.setPopust(popust);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertFalse(prekrsaji.isEmpty());
        assertEquals(ocekivanaPoruka, prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testPopustNull() {
    	k.setPopust(null);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Niste uneli popust!", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testKorisnikIdValidan() {
        k.setKorisnikId(1L);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "korisnikId");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKorisnikIdNull() {
    	k.setKorisnikId(null);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "korisnikId");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Nedostaje korisnik", prekrsaji.iterator().next().getMessage());
    }
}