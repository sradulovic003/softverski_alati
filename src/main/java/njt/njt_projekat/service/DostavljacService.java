package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njt.njt_projekat.dto.impl.DostavljacDto;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.mapper.impl.DostavljacMapper;
import njt.njt_projekat.repository.impl.DostavljacRepository;

/**
 * Servis koji implementira poslovnu logiku za upravljanje dostavljacima.
 *
 * Omogucava kreiranje, pregled, azuriranje i brisanje dostavljaca.
 * Koristi DostavljacRepository za pristup bazi podataka i
 * DostavljacMapper za konverziju izmedju entiteta i DTO objekata.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class DostavljacService {
	
	/**
	 * Repozitorijum za pristup podacima o dostavljacima u bazi podataka.
	 */
	private final DostavljacRepository dostavljacRepository;
	
	/**
	 * Maper za konverziju izmedju entiteta Dostavljac i DTO objekta DostavljacDto.
	 */
	private final DostavljacMapper dostavljacMapper;
	
	
	/**
     * Kreira objekat klase DostavljacService sa unetim zavisnostima.
     *
     * @param dostavljacRepository Repozitorijum za pristup podacima o dostavljacima.
     * @param dostavljacMapper Maper za konverziju izmedju entiteta i DTO objekata.
     */
	@Autowired
    public DostavljacService(DostavljacRepository dostavljacRepository, DostavljacMapper dostavljacMapper) {
        this.dostavljacRepository = dostavljacRepository;
        this.dostavljacMapper = dostavljacMapper;
    }

	/**
     * Vraca listu svih dostavljaca iz baze podataka.
     *
     * @return lista svih dostavljaca kao lista objekata klase DostavljacDto.
     */
    public List<DostavljacDto> findAll() {
        return dostavljacRepository.findAll().stream().map(dostavljacMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Vraca dostavljaca sa unetim identifikatorom.
     *
     * @param id Identifikator dostavljaca.
     * @return dostavljac sa unetim identifikatorom kao objekat klase DostavljacDto.
     * @throws java.lang.Exception Ako dostavljac sa unetim identifikatorom ne postoji.
     */
    public DostavljacDto findById(Long id) throws Exception {
        return dostavljacMapper.toDto(dostavljacRepository.findById(id));
    }

    /**
     * Kreira novog dostavljaca u bazi podataka.
     *
     * @param dostavljacDto Podaci o novom dostavljacu.
     * @return kreirani dostavljac kao objekat klase DostavljacDto.
     */
    public DostavljacDto create(DostavljacDto dostavljacDto) {
        Dostavljac dostavljac = dostavljacMapper.toEntity(dostavljacDto);
        dostavljacRepository.save(dostavljac);
        return dostavljacMapper.toDto(dostavljac);
    }

    /**
     * Azurira postojeceg dostavljaca u bazi podataka.
     *
     * @param dostavljacDto Novi podaci o dostavljacu.
     * @return azurirani dostavljac kao objekat klase DostavljacDto.
     */
    public DostavljacDto update(DostavljacDto dostavljacDto) {
        Dostavljac dostavljac = dostavljacMapper.toEntity(dostavljacDto);
        dostavljacRepository.save(dostavljac);
        return dostavljacMapper.toDto(dostavljac);
    }

    /**
     * Brise dostavljaca sa unetim identifikatorom iz baze podataka.
     *
     * @param id Identifikator dostavljaca koji se brise.
     */
    public void deleteById(Long id) {
        dostavljacRepository.deleteById(id);
    }
}
