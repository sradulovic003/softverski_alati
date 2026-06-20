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

    @ParameterizedTest
    @CsvSource({
        "'', Niste uneli ime!",
        "'   ', Niste uneli ime!",
        "Ma, Ime mora imati izmedju 3 i 50 karaktera"
    })
    void testImeNevalidno(String ime, String ocekivanaPoruka) {
        k.setIme(ime);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals(ocekivanaPoruka)));
    }

    @Test
    void testImePredugo() {
        k.setIme("a".repeat(51));
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Ime mora imati izmedju 3 i 50 karaktera", prekrsaji.iterator().next().getMessage());
    }
    
    @Test
    void testImeNull() {
        k.setIme(null);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli ime!")));
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
    void testPrezimePredugo() {
        k.setPrezime("a".repeat(101));
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "prezime");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Prezime mora imati izmedju 3 i 100 karaktera", prekrsaji.iterator().next().getMessage());
    }
    
    @Test
    void testPrezimeNull() {
        k.setPrezime(null);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "prezime");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testKorisnickoImeValidno() {
        k.setKorisnickoIme("marko123");
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertTrue(prekrsaji.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "'', Niste uneli korisnicko ime!",
        "'   ', Niste uneli korisnicko ime!",
        "ma, Korisnicko ime mora imati izmedju 3 i 50 karaktera"
    })
    void testKorisnickoImeNevalidno(String korisnickoIme, String ocekivanaPoruka) {
        k.setKorisnickoIme(korisnickoIme);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals(ocekivanaPoruka)));
    }

    @Test
    void testKorisnickoImePredugo() {
        k.setKorisnickoIme("a".repeat(51));
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Korisnicko ime mora imati izmedju 3 i 50 karaktera", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testKorisnickoImeNull() {
        k.setKorisnickoIme(null);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "korisnickoIme");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli korisnicko ime!")));
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
        assertEquals("Email nije u ispravnom formatu", prekrsaji.iterator().next().getMessage());
    }
    
    @Test
    void testEmailNull() {
        k.setEmail(null);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "email");
        assertFalse(prekrsaji.isEmpty());
        assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli email!")));
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
        assertEquals("Adresa mora imati izmedju 5 i 255 karaktera", prekrsaji.iterator().next().getMessage());    }

    @Test
    void testAdresaPreduga() {
        k.setAdresa("a".repeat(256));
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "adresa");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Adresa mora imati izmedju 5 i 255 karaktera", prekrsaji.iterator().next().getMessage());
    }
    
    @Test
    void testAdresaNull() {
        k.setAdresa(null);
        Set<ConstraintViolation<KorisnikDto>> prekrsaji = validator.validateProperty(k, "adresa");
        assertTrue(prekrsaji.isEmpty());
    }
}
