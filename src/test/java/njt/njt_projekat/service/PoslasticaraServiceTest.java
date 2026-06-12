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

import njt.njt_projekat.dto.impl.PoslasticaraDto;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.mapper.impl.PoslasticaraMapper;
import njt.njt_projekat.repository.impl.PoslasticaraRepository;

@ExtendWith(MockitoExtension.class)
class PoslasticaraServiceTest {

	@Mock
    PoslasticaraRepository poslasticaraRepository;

    @Mock
    PoslasticaraMapper poslasticaraMapper;

    @InjectMocks
    PoslasticaraService poslasticaraService;

    Poslasticara poslasticara;
    PoslasticaraDto poslasticaraDto;

    @BeforeEach
    void setUp() throws Exception {
        poslasticara = new Poslasticara(1L, "Slatki Kut", "Ulica 123", "0601234567", null);
        poslasticaraDto = new PoslasticaraDto(1L, "Slatki Kut", "Ulica 123", "0601234567", null);
    }

    @AfterEach
    void tearDown() throws Exception {
        poslasticara = null;
        poslasticaraDto = null;
    }

    @Test
    void testFindAll() {
        when(poslasticaraRepository.findAll()).thenReturn(Arrays.asList(poslasticara));
        when(poslasticaraMapper.toDto(poslasticara)).thenReturn(poslasticaraDto);

        List<PoslasticaraDto> rezultat = poslasticaraService.findAll();

        assertEquals(1, rezultat.size());
        assertEquals(poslasticaraDto, rezultat.get(0));
        verify(poslasticaraRepository, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(poslasticaraRepository.findById(1L)).thenReturn(poslasticara);
        when(poslasticaraMapper.toDto(poslasticara)).thenReturn(poslasticaraDto);

        PoslasticaraDto rezultat = poslasticaraService.findById(1L);

        assertEquals(poslasticaraDto, rezultat);
        verify(poslasticaraRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNePostoji() throws Exception {
        when(poslasticaraRepository.findById(99L)).thenThrow(new Exception("Poslasticara nije pronadjena"));

        Exception exception = assertThrows(Exception.class, () -> {
            poslasticaraService.findById(99L);
        });
        assertEquals("Poslasticara nije pronadjena", exception.getMessage());
    }

    @Test
    void testCreate() {
        when(poslasticaraMapper.toEntity(poslasticaraDto)).thenReturn(poslasticara);
        when(poslasticaraMapper.toDto(poslasticara)).thenReturn(poslasticaraDto);

        PoslasticaraDto rezultat = poslasticaraService.create(poslasticaraDto);

        assertEquals(poslasticaraDto, rezultat);
        verify(poslasticaraRepository, times(1)).save(poslasticara);
    }

    @Test
    void testUpdate() {
        when(poslasticaraMapper.toEntity(poslasticaraDto)).thenReturn(poslasticara);
        when(poslasticaraMapper.toDto(poslasticara)).thenReturn(poslasticaraDto);

        PoslasticaraDto rezultat = poslasticaraService.update(poslasticaraDto);

        assertEquals(poslasticaraDto, rezultat);
        verify(poslasticaraRepository, times(1)).save(poslasticara);
    }

    @Test
    void testDeleteById() {
        poslasticaraService.deleteById(1L);

        verify(poslasticaraRepository, times(1)).deleteById(1L);
    }

}
