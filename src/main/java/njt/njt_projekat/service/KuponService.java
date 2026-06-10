package njt.njt_projekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njt.njt_projekat.dto.impl.KuponDto;
import njt.njt_projekat.entity.impl.Kupon;
import njt.njt_projekat.mapper.impl.KuponMapper;
import njt.njt_projekat.repository.impl.KuponRepository;

@Service
public class KuponService {
	
	private final KuponRepository kuponRepository;
    private final KuponMapper kuponMapper;
    
    @Autowired
    public KuponService(KuponRepository kuponRepository, KuponMapper kuponMapper) {
        this.kuponRepository = kuponRepository;
        this.kuponMapper = kuponMapper;
    }

    public List<KuponDto> findAll() {
        return kuponRepository.findAll().stream().map(kuponMapper::toDto).collect(Collectors.toList());
    }

    public KuponDto findById(Long id) throws Exception {
        return kuponMapper.toDto(kuponRepository.findById(id));
    }

    public KuponDto create(KuponDto kuponDto) {
        Kupon kupon = kuponMapper.toEntity(kuponDto);
        kuponRepository.save(kupon);
        return kuponMapper.toDto(kupon);
    }

    public KuponDto update(KuponDto kuponDto) {
        Kupon kupon = kuponMapper.toEntity(kuponDto);
        kuponRepository.save(kupon);
        return kuponMapper.toDto(kupon);
    }

    public void deleteById(Long id) {
        kuponRepository.deleteById(id);
    }

    public List<KuponDto> findByKorisnikId(Long id) {
        return kuponRepository.findByKorisnikId(id).stream().map(kuponMapper::toDto).collect(Collectors.toList());
    }
}
