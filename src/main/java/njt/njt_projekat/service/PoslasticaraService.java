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
 *
 * @author Sara
 */
@Service
public class PoslasticaraService {
    private final PoslasticaraRepository poslasticaraRepository;
    private final PoslasticaraMapper poslasticaraMapper;

    @Autowired  //Spring će automatski pronaći bean-ove, i proslediti ih ovom konstruktoru kada pravi objekat klase PoslasticaraService.
    public PoslasticaraService(PoslasticaraRepository poslasticaraRepository, PoslasticaraMapper poslasticaraMapper) {
        this.poslasticaraRepository = poslasticaraRepository;
        this.poslasticaraMapper = poslasticaraMapper;
    }
    
    public List<PoslasticaraDto> findAll(){
        return poslasticaraRepository.findAll().stream().map(poslasticaraMapper::toDto).collect(Collectors.toList());
    }
    
    public PoslasticaraDto findById(Long id) throws Exception{
        return poslasticaraMapper.toDto(poslasticaraRepository.findById(id));
    }

    public PoslasticaraDto create(PoslasticaraDto poslasticaraDto) {
        Poslasticara poslasticara = poslasticaraMapper.toEntity(poslasticaraDto);
        poslasticaraRepository.save(poslasticara);
        return poslasticaraMapper.toDto(poslasticara);
    }

    public void deleteById(Long id) {
        poslasticaraRepository.deleteById(id);
    }

    public PoslasticaraDto update(PoslasticaraDto poslasticaraDto) {
        Poslasticara poslasticara = poslasticaraMapper.toEntity(poslasticaraDto);
        poslasticaraRepository.save(poslasticara);
        return poslasticaraMapper.toDto(poslasticara);
    }
    
}
