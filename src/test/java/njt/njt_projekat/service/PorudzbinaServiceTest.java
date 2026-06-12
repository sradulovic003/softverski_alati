package njt.njt_projekat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import jakarta.persistence.EntityManager;
import njt.njt_projekat.dto.impl.PorudzbinaDto;
import njt.njt_projekat.dto.impl.StavkaPorudzbineDto;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.entity.impl.StatusPorudzbine;
import njt.njt_projekat.mapper.impl.PorudzbinaMapper;
import njt.njt_projekat.repository.impl.PorudzbinaRepository;

@ExtendWith(MockitoExtension.class)
class PorudzbinaServiceTest {

	@Mock
    PorudzbinaRepository porudzbine;

    @Mock
    PorudzbinaMapper mapper;

    @Mock
    EntityManager em;

    @InjectMocks
    PorudzbinaService porudzbinaService;

    Porudzbina porudzbina;
    PorudzbinaDto porudzbinaDto;

    @BeforeEach
    void setUp() throws Exception {
        porudzbina = new Porudzbina(1L);
        porudzbinaDto = new PorudzbinaDto(1L, 100.0, null, StatusPorudzbine.KREIRANA, 1L, new ArrayList<>());
        ReflectionTestUtils.setField(porudzbinaService, "em", em);
    }

    @AfterEach
    void tearDown() throws Exception {
        porudzbina = null;
        porudzbinaDto = null;
    }

    @Test
    void testFindAll() {
        when(porudzbine.findAll()).thenReturn(Arrays.asList(porudzbina));
        when(mapper.toDto(porudzbina)).thenReturn(porudzbinaDto);

        List<PorudzbinaDto> rezultat = porudzbinaService.findAll();

        assertEquals(1, rezultat.size());
        assertEquals(porudzbinaDto, rezultat.get(0));
        verify(porudzbine, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(porudzbine.findById(1L)).thenReturn(porudzbina);
        when(mapper.toDto(porudzbina)).thenReturn(porudzbinaDto);

        PorudzbinaDto rezultat = porudzbinaService.findById(1L);

        assertEquals(porudzbinaDto, rezultat);
        verify(porudzbine, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNePostoji() throws Exception {
        when(porudzbine.findById(99L)).thenThrow(new Exception("Porudzbina nije pronadjena: 99"));

        Exception exception = assertThrows(Exception.class, () -> {
            porudzbinaService.findById(99L);
        });
        assertEquals("Porudzbina nije pronadjena: 99", exception.getMessage());
    }

    @Test
    void testCreate() throws Exception {
        StavkaPorudzbineDto stavkaDto = new StavkaPorudzbineDto(null, null, 2.0, null, null, 1L);
        PorudzbinaDto dto = new PorudzbinaDto(null, null, null, null, 1L, Arrays.asList(stavkaDto));

        Korisnik korisnik = new Korisnik(1L);
        Proizvod proizvod = new Proizvod(1L);
        proizvod.setCena(500.0);

        when(em.getReference(Korisnik.class, 1L)).thenReturn(korisnik);
        when(em.getReference(Proizvod.class, 1L)).thenReturn(proizvod);
        when(mapper.toDto(org.mockito.ArgumentMatchers.any(Porudzbina.class))).thenReturn(porudzbinaDto);

        PorudzbinaDto rezultat = porudzbinaService.create(dto);

        assertEquals(porudzbinaDto, rezultat);
        verify(porudzbine, times(1)).save(org.mockito.ArgumentMatchers.any(Porudzbina.class));
    }

    @Test
    void testCreateBezKorisnika() {
        PorudzbinaDto dto = new PorudzbinaDto(null, null, null, null, null, Arrays.asList(new StavkaPorudzbineDto()));

        Exception exception = assertThrows(Exception.class, () -> {
            porudzbinaService.create(dto);
        });
        assertEquals("Nedostaje id korisnika", exception.getMessage());
    }

    @Test
    void testCreateBezStavki() {
        PorudzbinaDto dto = new PorudzbinaDto(null, null, null, null, 1L, new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> {
            porudzbinaService.create(dto);
        });
        assertEquals("Porudzbina mora da sadrzi bar jednu stavku", exception.getMessage());
    }

    @Test
    void testUpdateStatus() throws Exception {
        when(porudzbine.findById(1L)).thenReturn(porudzbina);
        when(mapper.toDto(porudzbina)).thenReturn(porudzbinaDto);

        PorudzbinaDto rezultat = porudzbinaService.updateStatus(1L, StatusPorudzbine.POTVRDJENA);

        assertEquals(porudzbinaDto, rezultat);
        assertEquals(StatusPorudzbine.POTVRDJENA, porudzbina.getStatus());
        verify(porudzbine, times(1)).save(porudzbina);
    }

    @Test
    void testDeleteById() {
        porudzbinaService.deleteById(1L);

        verify(porudzbine, times(1)).deleteById(1L);
    }

    @Test
    void testDodeliDostavljaca() throws Exception {
        Dostavljac dostavljac = new Dostavljac(1L);
        when(porudzbine.findById(1L)).thenReturn(porudzbina);
        when(em.getReference(Dostavljac.class, 1L)).thenReturn(dostavljac);
        when(mapper.toDto(porudzbina)).thenReturn(porudzbinaDto);

        PorudzbinaDto rezultat = porudzbinaService.dodeliDostavljaca(1L, 1L);

        assertEquals(porudzbinaDto, rezultat);
        assertEquals(dostavljac, porudzbina.getDostavljac());
        verify(porudzbine, times(1)).save(porudzbina);
    }

}
