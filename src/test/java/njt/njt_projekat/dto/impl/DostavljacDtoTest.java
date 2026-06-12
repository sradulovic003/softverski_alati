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

class DostavljacDtoTest {

	DostavljacDto d;
    static Validator validator;

    @BeforeAll
    static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() throws Exception {
        d = new DostavljacDto();
    }

    @AfterEach
    void tearDown() throws Exception {
        d = null;
    }

    @Test
    void testDostavljacDto() {
        assertNotNull(d);
    }

    @Test
    void testDostavljacDtoSaParametrima() {
        d = new DostavljacDto(1L, "Marko", "Markovic", "0601234567");
        assertNotNull(d);
        assertEquals(1L, d.getDostavljacId());
        assertEquals("Marko", d.getIme());
        assertEquals("Markovic", d.getPrezime());
        assertEquals("0601234567", d.getTelefon());
    }

    @Test
    void testSetIme() {
        d.setIme("Marko");
        assertEquals("Marko", d.getIme());
    }

    @Test
    void testSetPrezime() {
        d.setPrezime("Markovic");
        assertEquals("Markovic", d.getPrezime());
    }

    @Test
    void testSetTelefon() {
        d.setTelefon("0601234567");
        assertEquals("0601234567", d.getTelefon());
    }

    @Test
    void testImeValidno() {
        d.setIme("Marko");
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "ime");
        assertTrue(prekrsaji.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "'', Niste uneli ime!",
        "MaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaToliko, Ime ne sme da sadrzi vise od 50 karaktera"
    })
    void testImeNevalidno(String ime, String ocekivanaPoruka) {
        d.setIme(ime);
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertEquals(ocekivanaPoruka, prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testImeNull() {
        d.setIme(null);
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "ime");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Niste uneli ime!", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testPrezimeValidno() {
        d.setPrezime("Markovic");
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "prezime");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testPrezimePrazno() {
        d.setPrezime("");
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "prezime");
        assertFalse(prekrsaji.isEmpty());
        assertEquals("Niste uneli prezime!", prekrsaji.iterator().next().getMessage());
    }

    @Test
    void testTelefonValidno() {
        d.setTelefon("0601234567");
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "telefon");
        assertTrue(prekrsaji.isEmpty());
    }

    @Test
    void testTelefonPrazno() {
        d.setTelefon("");
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "telefon");
        assertFalse(prekrsaji.isEmpty());
        boolean sadrziPoruku = prekrsaji.stream()
                .anyMatch(v -> v.getMessage().equals("Niste uneli telefon!"));
        assertTrue(sadrziPoruku);
    }

    @Test
    void testTelefonNevalidanFormat() {
        d.setTelefon("abc");
        Set<ConstraintViolation<DostavljacDto>> prekrsaji = validator.validateProperty(d, "telefon");
        assertFalse(prekrsaji.isEmpty());
        boolean sadrziPoruku = prekrsaji.stream()
                .anyMatch(v -> v.getMessage().equals("Telefon mora imati 6 do 20 karaktera (cifre, +, -, /, razmak)"));
        assertTrue(sadrziPoruku);
    }

}
