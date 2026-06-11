package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njt.njt_projekat.dto.impl.RecenzijaDto;
import njt.njt_projekat.entity.impl.Recenzija;
import njt.njt_projekat.mapper.impl.RecenzijaMapper;
import njt.njt_projekat.repository.impl.RecenzijaRepository;

/**
 * Servis koji implementira poslovnu logiku za upravljanje recenzijama.
 *
 * Omogucava kreiranje, pregled i brisanje recenzija,
 * kao i pregled recenzija po poslasticari.
 * Koristi RecenzijaRepository za pristup bazi podataka i
 * RecenzijaMapper za konverziju izmedju entiteta i DTO objekata.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class RecenzijaService {
	
	/**
     * Repozitorijum za pristup podacima o recenzijama u bazi podataka.
     */
	private final RecenzijaRepository recenzijaRepository;
	
	/**
     * Maper za konverziju izmedju entiteta Recenzija i DTO objekta RecenzijaDto.
     */
	private final RecenzijaMapper recenzijaMapper;

	/**
     * Kreira objekat klase RecenzijaService sa unetim zavisnostima.
     *
     * @param recenzijaRepository Repozitorijum za pristup podacima o recenzijama.
     * @param recenzijaMapper Maper za konverziju izmedju entiteta i DTO objekata.
     */
	@Autowired
	public RecenzijaService(RecenzijaRepository recenzijaRepository, RecenzijaMapper recenzijaMapper) {
		this.recenzijaRepository = recenzijaRepository;
		this.recenzijaMapper = recenzijaMapper;
	}

	/**
     * Vraca listu svih recenzija iz baze podataka.
     *
     * @return lista svih recenzija kao lista objekata klase RecenzijaDto.
     */
	public List<RecenzijaDto> findAll() {
		return recenzijaRepository.findAll().stream().map(recenzijaMapper::toDto).collect(Collectors.toList());
	}

	/**
     * Vraca recenziju sa unetim identifikatorom.
     *
     * @param id Identifikator recenzije.
     * @return recenzija sa unetim identifikatorom kao objekat klase RecenzijaDto.
     * @throws java.lang.Exception Ako recenzija sa unetim identifikatorom ne postoji.
     */
	public RecenzijaDto findById(Long id) throws Exception {
		return recenzijaMapper.toDto(recenzijaRepository.findById(id));
	}

	/**
     * Kreira novu recenziju u bazi podataka.
     *
     * @param recenzijaDto Podaci o novoj recenziji.
     * @return kreirana recenzija kao objekat klase RecenzijaDto.
     */
	public RecenzijaDto create(RecenzijaDto recenzijaDto) {
		Recenzija recenzija = recenzijaMapper.toEntity(recenzijaDto);
		recenzijaRepository.save(recenzija);
		return recenzijaMapper.toDto(recenzija);
	}

	/**
     * Brise recenziju sa unetim identifikatorom iz baze podataka.
     *
     * @param id Identifikator recenzije koja se brise.
     */
	public void deleteById(Long id) {
		recenzijaRepository.deleteById(id);
	}

	/**
     * Vraca listu svih recenzija za poslasticaru sa unetim identifikatorom.
     *
     * @param id Identifikator poslasticare.
     * @return lista recenzija poslasticare kao lista objekata klase RecenzijaDto.
     */
	public List<RecenzijaDto> findByPoslasticaraId(Long id) {
		return recenzijaRepository.findByPoslasticaraId(id).stream().map(recenzijaMapper::toDto)
				.collect(Collectors.toList());
	}
}
