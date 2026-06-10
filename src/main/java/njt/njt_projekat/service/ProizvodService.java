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
 *
 * @author Sara
 */
@Service
public class ProizvodService {
    private final ProizvodRepository proizvodRepository;
    private final ProizvodMapper proizvodMapper;

    @Autowired
    public ProizvodService(ProizvodRepository proizvodRepository, ProizvodMapper proizvodMapper) {
        this.proizvodRepository = proizvodRepository;
        this.proizvodMapper = proizvodMapper;
    }
    
    public List<ProizvodDto> findAll(){
        return proizvodRepository.findAll().stream().map(proizvodMapper::toDto).collect(Collectors.toList());
    }

    public ProizvodDto create(ProizvodDto proizvodDto) {
        Proizvod proizvod = proizvodMapper.toEntity(proizvodDto);
        proizvodRepository.save(proizvod);
        return proizvodMapper.toDto(proizvod);
    }

    public void deleteById(Long id) {
        proizvodRepository.deleteById(id);
    }

    public ProizvodDto update(ProizvodDto proizvodDto) {
        Proizvod proizvod = proizvodMapper.toEntity(proizvodDto);
        proizvodRepository.save(proizvod);
        return proizvodMapper.toDto(proizvod);
    }

    public List<ProizvodDto> findByPoslasticaraId(Long id) {
        return proizvodRepository.findByPoslasticaraId(id).stream().map(proizvodMapper::toDto).collect(Collectors.toList());
    }
    
}
