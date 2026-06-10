/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.mapper.impl;


import java.util.List;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.PoslasticaraDto;
import njt.njt_projekat.dto.impl.ProizvodDto;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.mapper.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sara
 */
@Component
public class PoslasticaraMapper implements DtoEntityMapper<PoslasticaraDto, Poslasticara> {
    @Autowired
    private ProizvodMapper proizvodMapper;

    @Override
    public PoslasticaraDto toDto(Poslasticara e) {
        List<ProizvodDto> proizvodiDto = null;
        if (e.getProizvodi()!= null) {
            proizvodiDto = e.getProizvodi().stream().map(proizvodMapper::toDto).collect(Collectors.toList());
        }
        return new PoslasticaraDto(e.getPoslasticaraId(), e.getNaziv(), e.getAdresa(), e.getKontakt(), proizvodiDto);
    }

    @Override
    public Poslasticara toEntity(PoslasticaraDto t) {
        List<Proizvod> proizvodi = null;
        if (t.getProizvodi()!= null) {
            proizvodi = t.getProizvodi().stream().map(proizvodMapper::toEntity).collect(Collectors.toList());
        }
        return new Poslasticara(t.getPoslasticaraId(), t.getNaziv(), t.getAdresa(), t.getKontakt(), proizvodi);
    }

}
