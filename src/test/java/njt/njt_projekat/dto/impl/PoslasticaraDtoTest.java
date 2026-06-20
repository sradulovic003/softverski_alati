package njt.njt_projekat.dto.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jakarta.validation.*;

class PoslasticaraDtoTest {

	PoslasticaraDto p;
	static Validator validator;

	@BeforeAll
	static void initValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeEach
	void setUp() throws Exception {
		p = new PoslasticaraDto();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	void testPoslasticaraDto() {
		assertNotNull(p);
	}

	@Test
	void testPoslasticaraDtoSaParametrima() {
		List<ProizvodDto> proizvodi = new ArrayList<>();
		p = new PoslasticaraDto(1L, "Slatki Kut", "Ulica 123", "0601234567", proizvodi);
		assertNotNull(p);
		assertEquals(1L, p.getPoslasticaraId());
		assertEquals("Slatki Kut", p.getNaziv());
		assertEquals("Ulica 123", p.getAdresa());
		assertEquals("0601234567", p.getKontakt());
		assertEquals(proizvodi, p.getProizvodi());
	}

	@Test
	void testSetProizvodi() {
		List<ProizvodDto> proizvodi = new ArrayList<>();
		p.setProizvodi(proizvodi);
		assertEquals(proizvodi, p.getProizvodi());
	}

	@Test
	void testNazivValidan() {
		p.setNaziv("Slatki Kut");
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "naziv");
		assertTrue(prekrsaji.isEmpty());
	}

	@ParameterizedTest
	@CsvSource({ "'', Niste uneli naziv!", "'   ', Niste uneli naziv!",
			"a, Naziv mora imati izmedju 2 i 100 karaktera" })
	void testNazivNevalidan(String naziv, String ocekivanaPoruka) {
		p.setNaziv(naziv);
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "naziv");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals(ocekivanaPoruka)));
	}

	@Test
	void testNazivPredug() {
		p.setNaziv("a".repeat(101));
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "naziv");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(
				prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Naziv mora imati izmedju 2 i 100 karaktera")));
	}

	@Test
	void testNazivNull() {
		p.setNaziv(null);
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "naziv");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli naziv!")));
	}

	@ParameterizedTest
	@CsvSource({ "'', Niste uneli adresu!", "'   ', Niste uneli adresu!",
			"a, Adresa mora imati između 2 i 200 karaktera" })
	void testAdresaNevalidna(String adresa, String ocekivanaPoruka) {
		p.setAdresa(adresa);
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "adresa");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals(ocekivanaPoruka)));
	}

	@Test
	void testAdresaPreduga() {
		p.setAdresa("a".repeat(201));
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "adresa");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(
				prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Adresa mora imati između 2 i 200 karaktera")));
	}

	@Test
	void testAdresaNull() {
		p.setAdresa(null);
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "adresa");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli adresu!")));
	}

	@Test
	void testKontaktValidan() {
		p.setKontakt("0601234567");
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "kontakt");
		assertTrue(prekrsaji.isEmpty());
	}

	@ParameterizedTest
	@CsvSource({ "'', Niste uneli kontakt!", "'   ', Niste uneli kontakt!" })
	void testKontaktPraznoIliRazmaci(String kontakt, String ocekivanaPoruka) {
		p.setKontakt(kontakt);
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "kontakt");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals(ocekivanaPoruka)));
	}

	@Test
	void testKontaktNevalidanFormat() {
		p.setKontakt("abc");
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "kontakt");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().contains("Broj telefona mora")));
	}

	@Test
	void testKontaktNull() {
		p.setKontakt(null);
		Set<ConstraintViolation<PoslasticaraDto>> prekrsaji = validator.validateProperty(p, "kontakt");
		assertFalse(prekrsaji.isEmpty());
		assertTrue(prekrsaji.stream().anyMatch(v -> v.getMessage().equals("Niste uneli kontakt!")));
	}

}
