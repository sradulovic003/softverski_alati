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

import njt.njt_projekat.dto.impl.KuponDto;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Kupon;
import njt.njt_projekat.mapper.impl.KuponMapper;
import njt.njt_projekat.repository.impl.KuponRepository;

@ExtendWith(MockitoExtension.class)
class KuponServiceTest {

	@Mock
    KuponRepository kuponRepository;

    @Mock
    KuponMapper kuponMapper;

    @InjectMocks
    KuponService kuponService;

    Kupon kupon;
    KuponDto kuponDto;

    @BeforeEach
    void setUp() throws Exception {
        Korisnik korisnik = new Korisnik(1L);
        kupon = new Kupon(1L, "KOD10", 10.0, false, korisnik, null);
        kuponDto = new KuponDto(1L, "KOD10", 10.0, false, 1L, null);
    }

    @AfterEach
    void tearDown() throws Exception {
        kupon = null;
        kuponDto = null;
    }

    @Test
    void testFindAll() {
        when(kuponRepository.findAll()).thenReturn(Arrays.asList(kupon));
        when(kuponMapper.toDto(kupon)).thenReturn(kuponDto);

        List<KuponDto> rezultat = kuponService.findAll();

        assertEquals(1, rezultat.size());
        assertEquals(kuponDto, rezultat.get(0));
        verify(kuponRepository, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(kuponRepository.findById(1L)).thenReturn(kupon);
        when(kuponMapper.toDto(kupon)).thenReturn(kuponDto);

        KuponDto rezultat = kuponService.findById(1L);

        assertEquals(kuponDto, rezultat);
        verify(kuponRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNePostoji() throws Exception {
        when(kuponRepository.findById(99L)).thenThrow(new Exception("Kupon nije pronadjen"));

        Exception exception = assertThrows(Exception.class, () -> {
            kuponService.findById(99L);
        });
        assertEquals("Kupon nije pronadjen", exception.getMessage());
    }

    @Test
    void testCreate() {
        when(kuponMapper.toEntity(kuponDto)).thenReturn(kupon);
        when(kuponMapper.toDto(kupon)).thenReturn(kuponDto);

        KuponDto rezultat = kuponService.create(kuponDto);

        assertEquals(kuponDto, rezultat);
        verify(kuponRepository, times(1)).save(kupon);
    }

    @Test
    void testUpdate() {
        when(kuponMapper.toEntity(kuponDto)).thenReturn(kupon);
        when(kuponMapper.toDto(kupon)).thenReturn(kuponDto);

        KuponDto rezultat = kuponService.update(kuponDto);

        assertEquals(kuponDto, rezultat);
        verify(kuponRepository, times(1)).save(kupon);
    }

    @Test
    void testDeleteById() {
        kuponService.deleteById(1L);

        verify(kuponRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByKorisnikId() {
        when(kuponRepository.findByKorisnikId(1L)).thenReturn(Arrays.asList(kupon));
        when(kuponMapper.toDto(kupon)).thenReturn(kuponDto);

        List<KuponDto> rezultat = kuponService.findByKorisnikId(1L);

        assertEquals(1, rezultat.size());
        assertEquals(kuponDto, rezultat.get(0));
        verify(kuponRepository, times(1)).findByKorisnikId(1L);
    }

    @Test
    void testFindByKorisnikIdPrazno() {
        when(kuponRepository.findByKorisnikId(2L)).thenReturn(Arrays.asList());

        List<KuponDto> rezultat = kuponService.findByKorisnikId(2L);

        assertTrue(rezultat.isEmpty());
    }

}
