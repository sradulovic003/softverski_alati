package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njt.njt_projekat.dto.impl.KuponDto;
import njt.njt_projekat.entity.impl.Kupon;
import njt.njt_projekat.mapper.impl.KuponMapper;
import njt.njt_projekat.repository.impl.KuponRepository;

/**
 * Servis koji implementira poslovnu logiku za upravljanje kuponima.
 *
 * Omogucava kreiranje, pregled, azuriranje i brisanje kupona,
 * kao i pregled kupona po korisniku.
 * Koristi KuponRepository za pristup bazi podataka i
 * KuponMapper za konverziju izmedju entiteta i DTO objekata.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class KuponService {
	
	/**
     * Repozitorijum za pristup podacima o kuponima u bazi podataka.
     */
	private final KuponRepository kuponRepository;
	
	/**
     * Maper za konverziju izmedju entiteta Kupon i DTO objekta KuponDto.
     */
    private final KuponMapper kuponMapper;
    
    
    /**
     * Kreira objekat klase KuponService sa unetim zavisnostima.
     *
     * @param kuponRepository Repozitorijum za pristup podacima o kuponima.
     * @param kuponMapper Maper za konverziju izmedju entiteta i DTO objekata.
     */
    @Autowired
    public KuponService(KuponRepository kuponRepository, KuponMapper kuponMapper) {
        this.kuponRepository = kuponRepository;
        this.kuponMapper = kuponMapper;
    }

    /**
     * Vraca listu svih kupona iz baze podataka.
     *
     * @return lista svih kupona kao lista objekata klase KuponDto.
     */
    public List<KuponDto> findAll() {
        return kuponRepository.findAll().stream().map(kuponMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Vraca kupon sa unetim identifikatorom.
     *
     * @param id Identifikator kupona.
     * @return kupon sa unetim identifikatorom kao objekat klase KuponDto.
     * @throws java.lang.Exception Ako kupon sa unetim identifikatorom ne postoji.
     */
    public KuponDto findById(Long id) throws Exception {
        return kuponMapper.toDto(kuponRepository.findById(id));
    }

    /**
     * Kreira novi kupon u bazi podataka.
     *
     * @param kuponDto Podaci o novom kuponu.
     * @return kreirani kupon kao objekat klase KuponDto.
     */
    public KuponDto create(KuponDto kuponDto) {
        Kupon kupon = kuponMapper.toEntity(kuponDto);
        kuponRepository.save(kupon);
        return kuponMapper.toDto(kupon);
    }

    /**
     * Azurira postojeci kupon u bazi podataka.
     *
     * @param kuponDto Novi podaci o kuponu.
     * @return azurirani kupon kao objekat klase KuponDto.
     */
    public KuponDto update(KuponDto kuponDto) {
        Kupon kupon = kuponMapper.toEntity(kuponDto);
        kuponRepository.save(kupon);
        return kuponMapper.toDto(kupon);
    }

    /**
     * Brise kupon sa unetim identifikatorom iz baze podataka.
     *
     * @param id Identifikator kupona koji se brise.
     */
    public void deleteById(Long id) {
        kuponRepository.deleteById(id);
    }

    /**
     * Vraca listu svih kupona koji pripadaju korisniku sa unetim identifikatorom.
     *
     * @param id Identifikator korisnika.
     * @return lista kupona korisnika kao lista objekata klase KuponDto.
     */
    public List<KuponDto> findByKorisnikId(Long id) {
        return kuponRepository.findByKorisnikId(id).stream().map(kuponMapper::toDto).collect(Collectors.toList());
    }
}
