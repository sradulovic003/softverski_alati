/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.mapper.impl;


import njt.njt_projekat.dto.impl.ProizvodDto;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sara
 */
@Component
public class ProizvodMapper implements DtoEntityMapper<ProizvodDto, Proizvod> {


    @Override
    public ProizvodDto toDto(Proizvod e) {
        Long poslasticaraId = e.getPoslasticara() != null ? e.getPoslasticara().getPoslasticaraId() : null;
        return new ProizvodDto(e.getProizvodId(), e.getNaziv(), e.getCena(), e.getImageUrl(), e.getKategorija(), e.getOpis(), poslasticaraId);
    }

    @Override
    public Proizvod toEntity(ProizvodDto t) {
        Poslasticara poslasticara = t.getPoslasticaraId() != null ? new Poslasticara(t.getPoslasticaraId()) : null;
        return new Proizvod(t.getProizvodId(), t.getNaziv(), t.getCena(), t.getImageUrl(), t.getKategorija(), t.getOpis(), poslasticara);
    }

}
