package njt.njt_projekat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import njt.njt_projekat.dto.impl.RecenzijaDto;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.entity.impl.Recenzija;
import njt.njt_projekat.mapper.impl.RecenzijaMapper;
import njt.njt_projekat.repository.impl.RecenzijaRepository;

@ExtendWith(MockitoExtension.class)
class RecenzijaServiceTest {

	@Mock
    RecenzijaRepository recenzijaRepository;

    @Mock
    RecenzijaMapper recenzijaMapper;

    @InjectMocks
    RecenzijaService recenzijaService;

    Recenzija recenzija;
    RecenzijaDto recenzijaDto;

    @BeforeEach
    void setUp() throws Exception {
        Korisnik korisnik = new Korisnik(1L);
        Poslasticara poslasticara = new Poslasticara(1L);
        recenzija = new Recenzija(1L, "Odlicna poslasticara", 5, korisnik, poslasticara);
        recenzijaDto = new RecenzijaDto(1L, "Odlicna poslasticara", 5, 1L, 1L);
    }

    @AfterEach
    void tearDown() throws Exception {
        recenzija = null;
        recenzijaDto = null;
    }

    @Test
    void testFindAll() {
        when(recenzijaRepository.findAll()).thenReturn(Arrays.asList(recenzija));
        when(recenzijaMapper.toDto(recenzija)).thenReturn(recenzijaDto);

        List<RecenzijaDto> rezultat = recenzijaService.findAll();

        assertEquals(1, rezultat.size());
        assertEquals(recenzijaDto, rezultat.get(0));
        verify(recenzijaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(recenzijaRepository.findById(1L)).thenReturn(recenzija);
        when(recenzijaMapper.toDto(recenzija)).thenReturn(recenzijaDto);

        RecenzijaDto rezultat = recenzijaService.findById(1L);

        assertEquals(recenzijaDto, rezultat);
        verify(recenzijaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNePostoji() throws Exception {
        when(recenzijaRepository.findById(99L)).thenThrow(new Exception("Recenzija nije pronadjena"));

        Exception exception = assertThrows(Exception.class, () -> {
            recenzijaService.findById(99L);
        });
        assertEquals("Recenzija nije pronadjena", exception.getMessage());
    }

    @Test
    void testCreate() {
        when(recenzijaMapper.toEntity(recenzijaDto)).thenReturn(recenzija);
        when(recenzijaMapper.toDto(recenzija)).thenReturn(recenzijaDto);

        RecenzijaDto rezultat = recenzijaService.create(recenzijaDto);

        assertEquals(recenzijaDto, rezultat);
        verify(recenzijaRepository, times(1)).save(recenzija);
    }

    @Test
    void testDeleteById() {
        recenzijaService.deleteById(1L);

        verify(recenzijaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByPoslasticaraId() {
        when(recenzijaRepository.findByPoslasticaraId(1L)).thenReturn(Arrays.asList(recenzija));
        when(recenzijaMapper.toDto(recenzija)).thenReturn(recenzijaDto);

        List<RecenzijaDto> rezultat = recenzijaService.findByPoslasticaraId(1L);

        assertEquals(1, rezultat.size());
        assertEquals(recenzijaDto, rezultat.get(0));
        verify(recenzijaRepository, times(1)).findByPoslasticaraId(1L);
    }

    @Test
    void testFindByPoslasticaraIdPrazno() {
        when(recenzijaRepository.findByPoslasticaraId(2L)).thenReturn(Arrays.asList());

        List<RecenzijaDto> rezultat = recenzijaService.findByPoslasticaraId(2L);

        assertTrue(rezultat.isEmpty());
    }

}
