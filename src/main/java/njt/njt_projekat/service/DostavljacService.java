package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njt.njt_projekat.dto.impl.DostavljacDto;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.mapper.impl.DostavljacMapper;
import njt.njt_projekat.repository.impl.DostavljacRepository;

@Service
public class DostavljacService {
	
	private final DostavljacRepository dostavljacRepository;
	private final DostavljacMapper dostavljacMapper;
	
	@Autowired
    public DostavljacService(DostavljacRepository dostavljacRepository, DostavljacMapper dostavljacMapper) {
        this.dostavljacRepository = dostavljacRepository;
        this.dostavljacMapper = dostavljacMapper;
    }

    public List<DostavljacDto> findAll() {
        return dostavljacRepository.findAll().stream().map(dostavljacMapper::toDto).collect(Collectors.toList());
    }

    public DostavljacDto findById(Long id) throws Exception {
        return dostavljacMapper.toDto(dostavljacRepository.findById(id));
    }

    public DostavljacDto create(DostavljacDto dostavljacDto) {
        Dostavljac dostavljac = dostavljacMapper.toEntity(dostavljacDto);
        dostavljacRepository.save(dostavljac);
        return dostavljacMapper.toDto(dostavljac);
    }

    public DostavljacDto update(DostavljacDto dostavljacDto) {
        Dostavljac dostavljac = dostavljacMapper.toEntity(dostavljacDto);
        dostavljacRepository.save(dostavljac);
        return dostavljacMapper.toDto(dostavljac);
    }

    public void deleteById(Long id) {
        dostavljacRepository.deleteById(id);
    }
}
