package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.*;
import njt.njt_projekat.entity.impl.Uloga;

class KorisnikDtoTest {

	KorisnikDto k;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        k = new KorisnikDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        k = null;
    }

    @Test
    void testKorisnikDto() {
        assertNotNull(k);
    }

    @Test
    void testKorisnikDtoSaParametrima() {
        k = new KorisnikDto(1L, "Marko", "Markovic", "marko123", "marko@gmail.com", "Ulica 123", Uloga.USER, null);
        assertNotNull(k);
        assertEquals(1L, k.getKorisnikId());
        assertEquals("Marko", k.getIme());
        assertEquals("Markovic", k.getPrezime());
        assertEquals("marko123", k.getKorisnickoIme());
        assertEquals("marko@gmail.com", k.getEmail());
        assertEquals("Ulica 123", k.getAdresa());
        assertEquals(Uloga.USER, k.getUloga());
    }

    @Test
    void testUlogaDefaultUser() {
        assertEquals(Uloga.USER, k.getUloga());
    }

    @Test
    void testSetUlogaAdmin() {
        k.setUloga(Uloga.ADMIN);
        assertEquals(Uloga.ADMIN, k.getUloga());
    }

    @Test
    void testSetPorudzbine() {
        k.setPorudzbine(null);
        assertNull(k.getPorudzbine());
    }

    @Test
    void testImeValidno() {
        k.setIme("Marko");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testImePrazno() {
        k.setIme("");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli ime!")));
    }

    @Test
    void testImeKratko() {
        k.setIme("Ma");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Ime mora imati izmedju 3 i 50 karaktera")));
    }

    @Test
    void testImePredugo() {
        k.setIme("a".repeat(51));
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Ime mora imati izmedju 3 i 50 karaktera")));
    }

    @Test
    void testPrezimeValidno() {
        k.setPrezime("Markovic");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "prezime");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testPrezimeKratko() {
        k.setPrezime("Ma");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "prezime");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Prezime mora imati izmedju 3 i 100 karaktera")));
    }

    @Test
    void testKorisnickoImeValidno() {
        k.setKorisnickoIme("marko123");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKorisnickoImePrazno() {
        k.setKorisnickoIme("");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli korisnicko ime!")));
    }

    @Test
    void testKorisnickoImeKratko() {
        k.setKorisnickoIme("ma");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Korisnicko ime mora imati izmedju 3 i 50 karaktera")));
    }

    @Test
    void testEmailValidan() {
        k.setEmail("marko@gmail.com");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "email");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testEmailPrazan() {
        k.setEmail("");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "email");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli email!")));
    }

    @Test
    void testEmailNevalidanFormat() {
        k.setEmail("markogmail.com");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "email");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Email nije u ispravnom formatu")));
    }

    @Test
    void testAdresaValidna() {
        k.setAdresa("Ulica 123");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "adresa");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testAdresaKratka() {
        k.setAdresa("ab");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "adresa");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Adresa mora imati izmedju 5 i 255 karaktera")));
    }

    @Test
    void testAdresaPreduga() {
        k.setAdresa("a".repeat(256));
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "adresa");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Adresa mora imati izmedju 5 i 255 karaktera")));
    }
}
