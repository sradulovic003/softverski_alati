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

import njt.njt_projekat.dto.impl.ProizvodDto;
import njt.njt_projekat.entity.impl.Kategorija;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.mapper.impl.ProizvodMapper;
import njt.njt_projekat.repository.impl.ProizvodRepository;

@ExtendWith(MockitoExtension.class)
class ProizvodServiceTest {

	@Mock
    ProizvodRepository proizvodRepository;

    @Mock
    ProizvodMapper proizvodMapper;

    @InjectMocks
    ProizvodService proizvodService;

    Proizvod proizvod;
    ProizvodDto proizvodDto;

    @BeforeEach
    void setUp() throws Exception {
        Poslasticara poslasticara = new Poslasticara(1L);
        proizvod = new Proizvod(1L, "Torta", 1500.0, "http://slika.com/torta.jpg", Kategorija.TORTE, "Ukusna torta", poslasticara);
        proizvodDto = new ProizvodDto(1L, "Torta", 1500.0, "http://slika.com/torta.jpg", Kategorija.TORTE, "Ukusna torta", 1L);
    }

    @AfterEach
    void tearDown() throws Exception {
        proizvod = null;
        proizvodDto = null;
    }

    @Test
    void testFindAll() {
        when(proizvodRepository.findAll()).thenReturn(Arrays.asList(proizvod));
        when(proizvodMapper.toDto(proizvod)).thenReturn(proizvodDto);

        List<ProizvodDto> rezultat = proizvodService.findAll();

        assertEquals(1, rezultat.size());
        assertEquals(proizvodDto, rezultat.get(0));
        verify(proizvodRepository, times(1)).findAll();
    }

    @Test
    void testFindAllPrazno() {
        when(proizvodRepository.findAll()).thenReturn(Arrays.asList());

        List<ProizvodDto> rezultat = proizvodService.findAll();

        assertTrue(rezultat.isEmpty());
    }

    @Test
    void testCreate() {
        when(proizvodMapper.toEntity(proizvodDto)).thenReturn(proizvod);
        when(proizvodMapper.toDto(proizvod)).thenReturn(proizvodDto);

        ProizvodDto rezultat = proizvodService.create(proizvodDto);

        assertEquals(proizvodDto, rezultat);
        verify(proizvodRepository, times(1)).save(proizvod);
    }

    @Test
    void testUpdate() {
        when(proizvodMapper.toEntity(proizvodDto)).thenReturn(proizvod);
        when(proizvodMapper.toDto(proizvod)).thenReturn(proizvodDto);

        ProizvodDto rezultat = proizvodService.update(proizvodDto);

        assertEquals(proizvodDto, rezultat);
        verify(proizvodRepository, times(1)).save(proizvod);
    }

    @Test
    void testDeleteById() {
        proizvodService.deleteById(1L);

        verify(proizvodRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByPoslasticaraId() {
        when(proizvodRepository.findByPoslasticaraId(1L)).thenReturn(Arrays.asList(proizvod));
        when(proizvodMapper.toDto(proizvod)).thenReturn(proizvodDto);

        List<ProizvodDto> rezultat = proizvodService.findByPoslasticaraId(1L);

        assertEquals(1, rezultat.size());
        assertEquals(proizvodDto, rezultat.get(0));
        verify(proizvodRepository, times(1)).findByPoslasticaraId(1L);
    }

    @Test
    void testFindByPoslasticaraIdPrazno() {
        when(proizvodRepository.findByPoslasticaraId(2L)).thenReturn(Arrays.asList());

        List<ProizvodDto> rezultat = proizvodService.findByPoslasticaraId(2L);

        assertTrue(rezultat.isEmpty());
    }

}
