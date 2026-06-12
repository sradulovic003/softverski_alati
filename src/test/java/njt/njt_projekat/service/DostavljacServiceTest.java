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

import njt.njt_projekat.dto.impl.DostavljacDto;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.mapper.impl.DostavljacMapper;
import njt.njt_projekat.repository.impl.DostavljacRepository;

@ExtendWith(MockitoExtension.class)
class DostavljacServiceTest {

	@Mock
    DostavljacRepository dostavljacRepository;

    @Mock
    DostavljacMapper dostavljacMapper;

    @InjectMocks
    DostavljacService dostavljacService;

    Dostavljac dostavljac;
    DostavljacDto dostavljacDto;

    @BeforeEach
    void setUp() throws Exception {
        dostavljac = new Dostavljac(1L, "Marko", "Markovic", "0601234567");
        dostavljacDto = new DostavljacDto(1L, "Marko", "Markovic", "0601234567");
    }

    @AfterEach
    void tearDown() throws Exception {
        dostavljac = null;
        dostavljacDto = null;
    }

    @Test
    void testFindAll() {
        when(dostavljacRepository.findAll()).thenReturn(Arrays.asList(dostavljac));
        when(dostavljacMapper.toDto(dostavljac)).thenReturn(dostavljacDto);

        List<DostavljacDto> rezultat = dostavljacService.findAll();

        assertEquals(1, rezultat.size());
        assertEquals(dostavljacDto, rezultat.get(0));
        verify(dostavljacRepository, times(1)).findAll();
    }

    @Test
    void testFindAllPrazno() {
        when(dostavljacRepository.findAll()).thenReturn(Arrays.asList());

        List<DostavljacDto> rezultat = dostavljacService.findAll();

        assertTrue(rezultat.isEmpty());
    }

    @Test
    void testFindById() throws Exception {
        when(dostavljacRepository.findById(1L)).thenReturn(dostavljac);
        when(dostavljacMapper.toDto(dostavljac)).thenReturn(dostavljacDto);

        DostavljacDto rezultat = dostavljacService.findById(1L);

        assertEquals(dostavljacDto, rezultat);
        verify(dostavljacRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNePostoji() throws Exception {
        when(dostavljacRepository.findById(99L)).thenThrow(new Exception("Dostavljac nije pronadjen"));

        Exception exception = assertThrows(Exception.class, () -> {
            dostavljacService.findById(99L);
        });
        assertEquals("Dostavljac nije pronadjen", exception.getMessage());
    }

    @Test
    void testCreate() {
        when(dostavljacMapper.toEntity(dostavljacDto)).thenReturn(dostavljac);
        when(dostavljacMapper.toDto(dostavljac)).thenReturn(dostavljacDto);

        DostavljacDto rezultat = dostavljacService.create(dostavljacDto);

        assertEquals(dostavljacDto, rezultat);
        verify(dostavljacRepository, times(1)).save(dostavljac);
    }

    @Test
    void testUpdate() {
        when(dostavljacMapper.toEntity(dostavljacDto)).thenReturn(dostavljac);
        when(dostavljacMapper.toDto(dostavljac)).thenReturn(dostavljacDto);

        DostavljacDto rezultat = dostavljacService.update(dostavljacDto);

        assertEquals(dostavljacDto, rezultat);
        verify(dostavljacRepository, times(1)).save(dostavljac);
    }

    @Test
    void testDeleteById() {
        dostavljacService.deleteById(1L);

        verify(dostavljacRepository, times(1)).deleteById(1L);
    }

}
