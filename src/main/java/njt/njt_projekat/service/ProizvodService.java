/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.ProizvodDto;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.mapper.impl.ProizvodMapper;
import njt.njt_projekat.repository.impl.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servis koji implementira poslovnu logiku za upravljanje proizvodima.
 *
 * Omogucava kreiranje, pregled, azuriranje i brisanje proizvoda,
 * kao i pregled proizvoda po poslasticari.
 * Koristi ProizvodRepository za pristup bazi podataka i
 * ProizvodMapper za konverziju izmedju entiteta i DTO objekata.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class ProizvodService {
	
	/**
     * Repozitorijum za pristup podacima o proizvodima u bazi podataka.
     */
    private final ProizvodRepository proizvodRepository;
    
    /**
     * Maper za konverziju izmedju entiteta Proizvod i DTO objekta ProizvodDto.
     */
    private final ProizvodMapper proizvodMapper;

    /**
     * Kreira objekat klase ProizvodService sa unetim zavisnostima.
     *
     * @param proizvodRepository Repozitorijum za pristup podacima o proizvodima.
     * @param proizvodMapper Maper za konverziju izmedju entiteta i DTO objekata.
     */
    @Autowired
    public ProizvodService(ProizvodRepository proizvodRepository, ProizvodMapper proizvodMapper) {
        this.proizvodRepository = proizvodRepository;
        this.proizvodMapper = proizvodMapper;
    }
    
    /**
     * Vraca listu svih proizvoda iz baze podataka.
     *
     * @return lista svih proizvoda kao lista objekata klase ProizvodDto.
     */
    public List<ProizvodDto> findAll(){
        return proizvodRepository.findAll().stream().map(proizvodMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Kreira novi proizvod u bazi podataka.
     *
     * @param proizvodDto Podaci o novom proizvodu.
     * @return kreirani proizvod kao objekat klase ProizvodDto.
     */
    public ProizvodDto create(ProizvodDto proizvodDto) {
        Proizvod proizvod = proizvodMapper.toEntity(proizvodDto);
        proizvodRepository.save(proizvod);
        return proizvodMapper.toDto(proizvod);
    }

    /**
     * Brise proizvod sa unetim identifikatorom iz baze podataka.
     *
     * @param id Identifikator proizvoda koji se brise.
     */
    public void deleteById(Long id) {
        proizvodRepository.deleteById(id);
    }

    /**
     * Azurira postojeci proizvod u bazi podataka.
     *
     * @param proizvodDto Novi podaci o proizvodu.
     * @return azurirani proizvod kao objekat klase ProizvodDto.
     */
    public ProizvodDto update(ProizvodDto proizvodDto) {
        Proizvod proizvod = proizvodMapper.toEntity(proizvodDto);
        proizvodRepository.save(proizvod);
        return proizvodMapper.toDto(proizvod);
    }

    /**
     * Vraca listu svih proizvoda koji pripadaju poslasticari sa unetim identifikatorom.
     *
     * @param id Identifikator poslasticare.
     * @return lista proizvoda poslasticare kao lista objekata klase ProizvodDto.
     */
    public List<ProizvodDto> findByPoslasticaraId(Long id) {
        return proizvodRepository.findByPoslasticaraId(id).stream().map(proizvodMapper::toDto).collect(Collectors.toList());
    }
    
}
