package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njt.njt_projekat.dto.impl.RecenzijaDto;
import njt.njt_projekat.entity.impl.Recenzija;
import njt.njt_projekat.mapper.impl.RecenzijaMapper;
import njt.njt_projekat.repository.impl.RecenzijaRepository;

@Service
public class RecenzijaService {
	
	private final RecenzijaRepository recenzijaRepository;
	private final RecenzijaMapper recenzijaMapper;

	@Autowired
	public RecenzijaService(RecenzijaRepository recenzijaRepository, RecenzijaMapper recenzijaMapper) {
		this.recenzijaRepository = recenzijaRepository;
		this.recenzijaMapper = recenzijaMapper;
	}

	public List<RecenzijaDto> findAll() {
		return recenzijaRepository.findAll().stream().map(recenzijaMapper::toDto).collect(Collectors.toList());
	}

	public RecenzijaDto findById(Long id) throws Exception {
		return recenzijaMapper.toDto(recenzijaRepository.findById(id));
	}

	public RecenzijaDto create(RecenzijaDto recenzijaDto) {
		Recenzija recenzija = recenzijaMapper.toEntity(recenzijaDto);
		recenzijaRepository.save(recenzija);
		return recenzijaMapper.toDto(recenzija);
	}

	public void deleteById(Long id) {
		recenzijaRepository.deleteById(id);
	}

	public List<RecenzijaDto> findByPoslasticaraId(Long id) {
		return recenzijaRepository.findByPoslasticaraId(id).stream().map(recenzijaMapper::toDto)
				.collect(Collectors.toList());
	}
}
