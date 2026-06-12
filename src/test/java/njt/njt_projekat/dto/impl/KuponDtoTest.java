package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void testKodPrazan() {
        k.setKod("");
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "kod");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli kod kupona!")));
    }

    @Test
    void testKodNull() {
        k.setKod(null);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "kod");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli kod kupona!")));
    }

    @Test
    void testKodPredug() {
        k.setKod("KODKOJIIMAVISEODTRIDESETKARAKTERA1234");
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "kod");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Kod ne sme da sadrzi vise od 30 karaktera")));
    }

    @Test
    void testPopustValidan() {
        k.setPopust(10.0);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testPopustNull() {
        k.setPopust(null);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli popust!")));
    }

    @Test
    void testPopustNegativan() {
        k.setPopust(-5.0);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Popust mora biti pozitivan broj")));
    }

    @Test
    void testPopustNula() {
        k.setPopust(0.0);
        Set<ConstraintViolation<KuponDto>> prekrsaji = validator.validateProperty(k, "popust");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Popust mora biti pozitivan broj")));
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
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Nedostaje korisnik")));
    }
}
