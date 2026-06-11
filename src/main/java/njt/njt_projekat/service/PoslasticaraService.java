 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.PoslasticaraDto;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.mapper.impl.PoslasticaraMapper;
import njt.njt_projekat.repository.impl.PoslasticaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servis koji implementira poslovnu logiku za upravljanje poslasticarama.
 *
 * Omogucava kreiranje, pregled, azuriranje i brisanje poslasticara.
 * Koristi PoslasticaraRepository za pristup bazi podataka i
 * PoslasticaraMapper za konverziju izmedju entiteta i DTO objekata.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class PoslasticaraService {
	
	/**
     * Repozitorijum za pristup podacima o poslasticarama u bazi podataka.
     */
    private final PoslasticaraRepository poslasticaraRepository;
    
    /**
     * Maper za konverziju izmedju entiteta Poslasticara i DTO objekta PoslasticaraDto.
     */
    private final PoslasticaraMapper poslasticaraMapper;

    
    /**
     * Kreira objekat klase PoslasticaraService sa unetim zavisnostima.
     *
     * @param poslasticaraRepository Repozitorijum za pristup podacima o poslasticarama.
     * @param poslasticaraMapper Maper za konverziju izmedju entiteta i DTO objekata.
     */
    @Autowired
    public PoslasticaraService(PoslasticaraRepository poslasticaraRepository, PoslasticaraMapper poslasticaraMapper) {
        this.poslasticaraRepository = poslasticaraRepository;
        this.poslasticaraMapper = poslasticaraMapper;
    }
    
    /**
     * Vraca listu svih poslasticara iz baze podataka.
     *
     * @return lista svih poslasticara kao lista objekata klase PoslasticaraDto.
     */
    public List<PoslasticaraDto> findAll(){
        return poslasticaraRepository.findAll().stream().map(poslasticaraMapper::toDto).collect(Collectors.toList());
    }
    
    /**
     * Vraca poslasticaru sa unetim identifikatorom.
     *
     * @param id Identifikator poslasticare.
     * @return poslasticara sa unetim identifikatorom kao objekat klase PoslasticaraDto.
     * @throws java.lang.Exception Ako poslasticara sa unetim identifikatorom ne postoji.
     */
    public PoslasticaraDto findById(Long id) throws Exception{
        return poslasticaraMapper.toDto(poslasticaraRepository.findById(id));
    }

    /**
     * Kreira novu poslasticaru u bazi podataka.
     *
     * @param poslasticaraDto Podaci o novoj poslasticari.
     * @return kreirana poslasticara kao objekat klase PoslasticaraDto.
     */
    public PoslasticaraDto create(PoslasticaraDto poslasticaraDto) {
        Poslasticara poslasticara = poslasticaraMapper.toEntity(poslasticaraDto);
        poslasticaraRepository.save(poslasticara);
        return poslasticaraMapper.toDto(poslasticara);
    }

    /**
     * Brise poslasticaru sa unetim identifikatorom iz baze podataka.
     *
     * @param id Identifikator poslasticare koja se brise.
     */
    public void deleteById(Long id) {
        poslasticaraRepository.deleteById(id);
    }

    /**
     * Azurira postojecu poslasticaru u bazi podataka.
     *
     * @param poslasticaraDto Novi podaci o poslasticari.
     * @return azurirana poslasticara kao objekat klase PoslasticaraDto.
     */
    public PoslasticaraDto update(PoslasticaraDto poslasticaraDto) {
        Poslasticara poslasticara = poslasticaraMapper.toEntity(poslasticaraDto);
        poslasticaraRepository.save(poslasticara);
        return poslasticaraMapper.toDto(poslasticara);
    }
    
}
